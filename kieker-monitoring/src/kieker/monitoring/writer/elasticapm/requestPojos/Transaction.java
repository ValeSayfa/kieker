package kieker.monitoring.writer.elasticapm.requestPojos;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import com.google.gson.annotations.Expose;

/**
 *
 * @author Valentin Seifermann
 *
 */
public class Transaction {
	@Expose
	String id;
	@Expose
	String name;
	@Expose
	String type;
	@Expose
	BigDecimal duration;
	@Expose
	String timestamp;
	@Expose
	List<Span> spans;

	public Transaction() {
		this.spans = new ArrayList<>();
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public BigDecimal getDuration() {
		return this.duration;
	}

	public void setDuration(final BigDecimal duration) {
		this.duration = duration;
	}

	public String getTimestamp() {
		return this.timestamp;
	}

	/**
	 * Converts timestamp in nanoseconds to UTC time with format YYYY-MM-DDTHH:mm:ss.sssZ
	 *
	 * @param timestamp
	 */
	public void setTimestamp(final long timestamp) {
		final Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(timestamp / 1000000);
		final DateFormat formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		formatter.setCalendar(calendar);
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

		String utcTimestamp = formatter.format(calendar.getTime());
		utcTimestamp = utcTimestamp.replaceAll(" ", "T") + "Z";
		this.timestamp = utcTimestamp;
	}

	public List<Span> getSpans() {
		return this.spans;
	}

	public void addSpans(final Span span) {
		this.spans.add(span);
	}

	public void setSpans(final List<Span> spans) {
		this.spans = spans;
	}

	public static BigDecimal calculateDuration(final long tin, final long tout) {

		final long duration = tout - tin;
		final BigDecimal durationAsDecimal = new BigDecimal(duration / 1000000);
		return durationAsDecimal;
	}
}
