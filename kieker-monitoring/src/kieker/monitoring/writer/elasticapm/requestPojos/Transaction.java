package kieker.monitoring.writer.elasticapm.requestPojos;

import java.util.List;

/**
 *
 * @author Valentin Seifermann
 *
 */
public class Transaction {
	String id;
	String name;
	String type;
	String duration;
	String timestamp;
	List<Span> spans;

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

	public String getDuration() {
		return this.duration;
	}

	public void setDuration(final String duration) {
		this.duration = duration;
	}

	public String getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(final String timestamp) {
		this.timestamp = timestamp;
	}
}
