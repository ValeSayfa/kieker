/***************************************************************************
 * Copyright 2015 Kieker Project (http://kieker-monitoring.net)
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

package kieker.test.tools.junit.writeRead;

import java.util.List;

import kieker.common.record.IMonitoringRecord;
import kieker.monitoring.core.controller.IMonitoringController;

/**
 *
 * @author Christian Wulf
 *
 * @since 1.13
 */
public class TestProbe {

	private final IMonitoringController monitoringController;

	public TestProbe(final IMonitoringController monitoringController) {
		super();
		this.monitoringController = monitoringController;
	}

	public void triggerRecords(final List<IMonitoringRecord> records) {
		for (final IMonitoringRecord record : records) {
			this.monitoringController.newMonitoringRecord(record);
		}
	}
}