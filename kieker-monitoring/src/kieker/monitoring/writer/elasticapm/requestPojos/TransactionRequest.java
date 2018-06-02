package kieker.monitoring.writer.elasticapm.requestPojos;

import java.util.List;

/**
 * JSON Schema for Transaction POST request to the elastic APM server
 *
 * @author Valentin Seifermann
 *
 */
public class TransactionRequest {
	Service service;
	List<Transaction> transactions;

	public TransactionRequest() {
		this.service = new Service();
	}

	public Service getService() {
		return this.service;
	}

	public void setService(final Service service) {
		this.service = service;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(final List<Transaction> transactions) {
		this.transactions = transactions;
	}
}
