package kieker.monitoring.writer.elasticapm.requestPojos;

import java.math.BigDecimal;

import com.google.gson.annotations.Expose;

/**
 * Span representation
 *
 * @author Valentin Seifermann
 *
 */
public class Span {

	@Expose
	private String type;
	@Expose
	private BigDecimal start;
	@Expose
	private BigDecimal duration;
	private String traceId;
	@Expose
	private Long parent;
	@Expose
	private long id;
	@Expose
	private String name;
	private long tin;
	private long tout;

	public Span() {

	}

	public Span(final Span span) {
		this.traceId = span.traceId;
		this.parent = span.parent;
		this.id = span.id;
		this.name = span.name;
		this.tin = span.tin;
		this.tout = span.tout;
	}

	public String getTraceId() {
		return this.traceId;
	}

	/**
	 * Sets traceId by converting the kieker traceID to an elasticAPM traceId
	 * elasticAPM traceId pattern: [a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}
	 *
	 * @param traceId
	 */
	public void setTraceId(String traceId) {
		final int dif = 32 - traceId.length();
		// append 0 to traceId to have a number amount of 32
		if (dif > 0) {
			for (int index = 0; index < dif; index++) {
				traceId = traceId + "0";
			}
		}
		final String elasticTraceId = traceId.substring(0, 8) + "-" + traceId.substring(8, 12) + "-" + traceId.substring(12, 16) + "-" + traceId.substring(16, 20)
				+ "-" + traceId.substring(20, 32);
		this.traceId = elasticTraceId;
	}

	public Long getParent() {
		return this.parent;
	}

	public void setParent(final Long parentId) {
		this.parent = parentId;
	}

	public long getId() {
		return this.id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public long getTin() {
		return this.tin;
	}

	public void setTin(final long tin) {
		this.tin = tin;
	}

	public long getTout() {
		return this.tout;
	}

	public void setTout(final long tout) {
		this.tout = tout;
	}

	public String getType() {
		return this.type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public BigDecimal getStart() {
		return this.start;
	}

	public void setStart(final BigDecimal start) {
		this.start = start;
	}

	public BigDecimal getDuration() {
		return this.duration;
	}

	public void setDuration(final BigDecimal duration) {
		this.duration = duration;
	}

	public static BigDecimal calculateDuration(final long tin, final long tout) {
		final long milisec = (tout - tin) / 1000000;
		return new BigDecimal(milisec);
	}

	public static BigDecimal calculateStart(final long spanTin, final long traceTin) {

		final long milisec = (spanTin - traceTin) / 1000000;
		return new BigDecimal(milisec);
	}
}
