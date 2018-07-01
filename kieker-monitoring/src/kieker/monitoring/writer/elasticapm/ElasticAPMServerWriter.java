package kieker.monitoring.writer.elasticapm;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import kieker.common.configuration.Configuration;
import kieker.common.record.IMonitoringRecord;
import kieker.common.record.controlflow.OperationExecutionRecord;
import kieker.monitoring.writer.AbstractMonitoringWriter;
import kieker.monitoring.writer.elasticapm.requestPojos.Span;
import kieker.monitoring.writer.elasticapm.requestPojos.Transaction;
import kieker.monitoring.writer.elasticapm.requestPojos.TransactionRequest;

/**
 * Writer which can be used to send record data to the Elastic APM Server.
 *
 * @author Valentin Seifermann
 *
 */
public class ElasticAPMServerWriter extends AbstractMonitoringWriter {

	public static final String PREFIX = ElasticAPMServerWriter.class.getName() + ".";
	// Server URL --> http(s)://{hostname}:{port}
	public static final String CONFIG_SERVERURL = PREFIX + "ServerUrl";
	// Server transactions endpoint
	private final String server_endpoint;
	// buffer to store the spans related to each trace
	private final Map<String, List<SpanNode>> spanBuffer = new HashMap<>();

	public ElasticAPMServerWriter(final Configuration configuration) {
		super(configuration);
		this.server_endpoint = configuration.getStringProperty(CONFIG_SERVERURL) + "/v1/transactions";
	}

	@Override
	public void onStarting() {
		// TODO Auto-generated method stub
	}

	@Override
	public void writeMonitoringRecord(final IMonitoringRecord record) {
		try {
			final OperationExecutionRecord exRecord = new OperationExecutionRecord(record.toArray());
			final Span span = new Span();
			this.setSpanValues(span, exRecord);
			final SpanNode spanNode = new SpanNode();
			spanNode.setSpan(span);
			spanNode.setLevel(exRecord.getEss());
			spanNode.setExecIndex(exRecord.getEoi());
			this.addSpanNodeToBuffer(spanNode);

		} catch (final Exception e) {

		}
	}

	@Override
	public void onTerminating() {
		// TODO Auto-generated method stub
	}

	/**
	 * Set span values by using the given kieker record
	 *
	 * @param span
	 * @param record
	 */
	private void setSpanValues(final Span span, final OperationExecutionRecord record) {
		span.setType("");
		span.setId(record.getEoi());
		span.setTraceId(String.valueOf(record.getTraceId()));
		span.setName(record.getOperationSignature());
		span.setTin(record.getTin());
		span.setTout(record.getTout());
		span.setDuration(Span.calculateDuration(record.getTin(), record.getTout()));
	}

	/**
	 * Add Span to buffer
	 *
	 * @param node
	 */
	private void addSpanNodeToBuffer(final SpanNode node) {
		if (this.spanBuffer.containsKey(node.getSpan().getTraceId())) {
			this.spanBuffer.get(node.getSpan().getTraceId()).add(node);
		} else {
			this.spanBuffer.put(node.getSpan().getTraceId(), new LinkedList<SpanNode>());
			this.spanBuffer.get(node.getSpan().getTraceId()).add(node);
		}
		if ((node.getExecIndex() == 0) && (node.getLevel() == 0)) {
			this.buildSpanTree(node);
		}
	}

	/**
	 * Builds the SpanTree after all spans related to the specific trace are collected and stored
	 *
	 * @param record
	 * @return
	 */
	private void buildSpanTree(final SpanNode node) {
		final SpanTree spanTree = new SpanTree(node);
		final List<SpanNode> nodes = this.spanBuffer.get(node.getSpan().getTraceId());
		Collections.sort(nodes, new Comparator<SpanNode>() {

			@Override
			public int compare(final SpanNode o1, final SpanNode o2) {
				// TODO Auto-generated method stub
				if (o1.getExecIndex() < o2.getExecIndex()) {
					return -1;
				} else if (o1.getExecIndex() == o2.getExecIndex()) {
					return 0;
				} else {
					return 1;
				}
			}
		});

		int i = 0;
		for (final SpanNode spanNode : nodes) {
			if (i != 0) {
				spanTree.insert(spanNode);
			}
			i = i + 1;
		}
		this.prepareAndSendData(nodes);
	}

	/**
	 * Convert data to JSON and send it to the APM server
	 *
	 * @param nodes
	 */
	private void prepareAndSendData(final List<SpanNode> nodes) {
		final TransactionRequest request = new TransactionRequest();
		request.getService().setName("custom service");
		request.getService().getAgent().setName("kieker framework");
		request.getService().getAgent().setVersion("1.13");
		final Transaction trace = new Transaction();
		trace.setId(nodes.get(0).getSpan().getTraceId());
		trace.setType("request");
		trace.setDuration(Transaction.calculateDuration(nodes.get(0).getSpan().getTin(), nodes.get(0).getSpan().getTout()));
		trace.setName(nodes.get(0).getSpan().getName());
		trace.setTimestamp(nodes.get(0).getSpan().getTin());

		for (final SpanNode node : nodes) {
			node.getSpan().setStart(Span.calculateStart(node.getSpan().getTin(), nodes.get(0).getSpan().getTin()));
			trace.addSpans(node.getSpan());
			System.out.println("spandId: " + node.getSpan().getId() + "; parentSpan: " + node.getSpan().getParent() + " ; name: " + node.getSpan().getName());
		}

		request.addTransactions(trace);

		final GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		final Gson gson = builder.serializeNulls().create();
		final HTTPClient httpClient = new HTTPClient();
		try {
			httpClient.doPostRequest(this.server_endpoint, gson.toJson(request));
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
