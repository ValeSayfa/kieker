package kieker.monitoring.writer.elasticapm;

import java.io.IOException;

import kieker.common.configuration.Configuration;
import kieker.common.record.IMonitoringRecord;
import kieker.monitoring.writer.AbstractMonitoringWriter;

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
			final HTTPClient httpClient = new HTTPClient();
			httpClient.doPostRequest(this.server_endpoint, ElasticAPMServerWriter.recordTransformation(record));

		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTerminating() {
		// TODO Auto-generated method stub
	}

	static String recordTransformation(final IMonitoringRecord record) {

		return "";
	}
}
