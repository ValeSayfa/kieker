package kieker.monitoring.writer.elasticapm.requestPojos;

import com.google.gson.annotations.Expose;

public class Agent {
	@Expose
	String name;
	@Expose
	String version;

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}
}
