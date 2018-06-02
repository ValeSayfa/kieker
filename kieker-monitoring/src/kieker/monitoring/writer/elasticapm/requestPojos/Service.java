package kieker.monitoring.writer.elasticapm.requestPojos;

class Service {

	String name;
	Agent agent;

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
