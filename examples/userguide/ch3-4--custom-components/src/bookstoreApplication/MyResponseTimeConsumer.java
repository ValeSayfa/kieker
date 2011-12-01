/***************************************************************************
 * Copyright 2011 by
 *  + Christian-Albrechts-University of Kiel
 *    + Department of Computer Science
 *      + Software Engineering Group 
 *  and others.
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

package bookstoreApplication;

import java.util.Collection;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

import kieker.analysis.configuration.Configuration;
import kieker.analysis.plugin.AbstractAnalysisPlugin;
import kieker.analysis.plugin.ISingleInputPort;
import kieker.analysis.plugin.port.AbstractInputPort;
import kieker.analysis.plugin.port.InputPort;

public class MyResponseTimeConsumer extends AbstractAnalysisPlugin implements ISingleInputPort {

	private static final Collection<Class<?>> IN_CLASSES = Collections.unmodifiableCollection(new CopyOnWriteArrayList<Class<?>>(
			new Class<?>[] { MyResponseTimeRecord.class }));
	private final AbstractInputPort input = new InputPort("in", MyResponseTimeConsumer.IN_CLASSES, this);

	public MyResponseTimeConsumer(final Configuration configuration) {
		super(configuration);
	}

	public MyResponseTimeConsumer() {
		super(new Configuration(null));
	}

	@Override
	public void newEvent(final Object event) {
		if (event instanceof MyResponseTimeRecord) {
			/* Write the content to the standard output stream. */
			final MyResponseTimeRecord myRecord = (MyResponseTimeRecord) event;
			System.out.println("[Consumer] " + myRecord.getLoggingTimestamp()
					+ ": " + myRecord.className + ", " + myRecord.methodName
					+ ", " + myRecord.responseTimeNanos);
		}
	}

	public AbstractInputPort getDefaultInputPort() {
		return this.input;
	}

	@Override
	public boolean execute() {
		return true;
	}

	@Override
	public void terminate(final boolean error) {}

	@Override
	protected Properties getDefaultProperties() {
		return new Properties();
	}

	@Override
	public Configuration getCurrentConfiguration() {
		return new Configuration(null);
	}
}
