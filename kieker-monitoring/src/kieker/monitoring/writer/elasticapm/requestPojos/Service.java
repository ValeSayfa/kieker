package kieker.monitoring.writer.elasticapm.requestPojos;

import com.google.gson.annotations.Expose;

public class Service {

	@Expose
	String name;
	@Expose
	Agent agent;

	public Service() {
		this.agent = new Agent();
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Agent getAgent() {
		return this.agent;
	}

	public void setAgent(final Agent agent) {
		this.agent = agent;
	}
}
