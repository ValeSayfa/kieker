package kieker.monitoring.writer.elasticapm;

import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Handles the HTTP connection to the APM server
 * 
 * @author Valentin Seifermann
 *
 */
public class HTTPClient {
	public void doPostRequest(final String UrlAsString, final String data) throws IOException {
		final URL url = new URL(UrlAsString);
		final HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		con.setDoOutput(true);

		this.sendData(con, data);
	}

	protected void sendData(final HttpURLConnection con, final String data) throws IOException {
		DataOutputStream wr = null;
		try {
			wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(data);
			wr.flush();
			wr.close();
		} catch (final IOException exception) {
			throw exception;
		} finally {
			this.closeConnection(wr);
		}
	}

	protected void closeConnection(final Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (final IOException ex) {

		}
	}
}
