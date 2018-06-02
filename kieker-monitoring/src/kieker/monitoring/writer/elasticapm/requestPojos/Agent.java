package kieker.monitoring.writer.elasticapm.requestPojos;

class Agent {
	String name;
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
