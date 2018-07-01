package kieker.monitoring.writer.elasticapm.requestPojos;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

/**
 * JSON Schema for Transaction POST request to the elastic APM server
 *
 * @author Valentin Seifermann
 *
 */
public class TransactionRequest {
	@Expose
	Service service;
	@Expose
	List<Transaction> transactions;

	public TransactionRequest() {
		this.service = new Service();
		this.transactions = new ArrayList<>();
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

	public void addTransactions(final Transaction trans) {
		this.transactions.add(trans);
	}
}
