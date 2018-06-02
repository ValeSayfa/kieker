package kieker.monitoring.writer.elasticapm.requestPojos;

/**
 * Span JSON object
 * 
 * @author Valentin Seifermann
 *
 */
public class Span {

	private String name;
	private String type;
	private String start;
	private String duration;

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

	public String getStart() {
		return this.start;
	}

	public void setStart(final String start) {
		this.start = start;
	}

	public String getDuration() {
		return this.duration;
	}

	public void setDuration(final String duration) {
		this.duration = duration;
	}
}
