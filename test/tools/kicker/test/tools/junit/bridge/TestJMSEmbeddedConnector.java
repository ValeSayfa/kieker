/***************************************************************************
 * Copyright 2013 Kicker Project (http://kicker-monitoring.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/
package kicker.test.tools.junit.bridge;

import org.junit.Test;

import kicker.common.configuration.Configuration;
import kicker.monitoring.core.configuration.ConfigurationFactory;
import kicker.tools.bridge.connector.ConnectorDataTransmissionException;
import kicker.tools.bridge.connector.jms.JMSClientConnector;
import kicker.tools.bridge.connector.jms.JMSEmbeddedConnector;

/**
 * 
 * @author Reiner Jung
 * 
 * @since 1.9
 */
public class TestJMSEmbeddedConnector extends AbstractConnectorTest {

	/**
	 * Empty constructor to satisfy coding style.
	 */
	public TestJMSEmbeddedConnector() {
		// Nothing to be done.
	}

	/**
	 * Test the JMS embedded connector.
	 * 
	 * @throws ConnectorDataTransmissionException
	 *             on lookup failure for the test record
	 */
	@Test
	public void testJMSEmbeddedConnector() throws ConnectorDataTransmissionException { // NOPMD

		final Thread messageGenerator = new Thread(new JMSMessageGenerator(), "Generator");
		messageGenerator.start();

		final Thread broker = new Thread(new JMSBroker(), "Broker");
		broker.start();

		final Configuration configuration = ConfigurationFactory.createSingletonConfiguration();
		configuration.setProperty(JMSEmbeddedConnector.PORT, String.valueOf(ConfigurationParameters.JMS_EMBEDDED_PORT));
		configuration.setProperty(JMSClientConnector.USERNAME, String.valueOf(ConfigurationParameters.JMS_USERNAME));
		configuration.setProperty(JMSClientConnector.PASSWORD, String.valueOf(ConfigurationParameters.JMS_PASSWORD));
		configuration.setProperty(JMSClientConnector.URI, String.valueOf(ConfigurationParameters.JMS_URI));
		// test the connector
		this.setConnector(new JMSEmbeddedConnector(configuration, this.createLookupEntityMap()));
		this.initialize();
		this.deserialize(ConfigurationParameters.SEND_NUMBER_OF_RECORDS);
		this.close(ConfigurationParameters.SEND_NUMBER_OF_RECORDS);
	}

}