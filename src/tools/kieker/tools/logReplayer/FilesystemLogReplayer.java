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

package kieker.tools.logReplayer;

import kieker.analysis.AnalysisController;
import kieker.analysis.exception.AnalysisConfigurationException;
import kieker.analysis.plugin.annotation.InputPort;
import kieker.analysis.plugin.filter.AbstractFilterPlugin;
import kieker.analysis.plugin.reader.AbstractReaderPlugin;
import kieker.analysis.plugin.reader.filesystem.FSReader;
import kieker.common.configuration.Configuration;
import kieker.common.logging.Log;
import kieker.common.logging.LogFactory;
import kieker.common.record.IMonitoringRecord;
import kieker.monitoring.core.controller.IMonitoringController;
import kieker.tools.util.LoggingTimestampConverter;

/**
 * Replays a filesystem monitoring log and simply passes each record to a
 * specified {@link kieker.common.record.IMonitoringRecordReceiver}. The {@link FilesystemLogReplayer} can replay monitoring logs in
 * real-time.
 * 
 * @author Andre van Hoorn
 */
public class FilesystemLogReplayer {

	public static final long MAX_TIMESTAMP = Long.MAX_VALUE;
	public static final long MIN_TIMESTAMP = 0;

	private static final Log LOG = LogFactory.getLog(FilesystemLogReplayer.class);

	private final long ignoreRecordsBeforeTimestamp;
	private final long ignoreRecordsAfterTimestamp;

	/** Each record is delegated to this receiver. */
	private final IMonitoringController recordReceiver;
	private final String[] inputDirs;
	private final boolean realtimeMode;
	private final int numRealtimeWorkerThreads;

	/** Normal replay mode (i.e., non-real-time). */
	public FilesystemLogReplayer(final IMonitoringController monitoringController, final String[] inputDirs) {
		this(monitoringController, inputDirs, false, -1);
	}

	/**
	 * 
	 * @param monitoringController
	 * @param inputDirs
	 * @param realtimeMode
	 * @param numRealtimeWorkerThreads
	 */
	public FilesystemLogReplayer(final IMonitoringController monitoringController, final String[] inputDirs, final boolean realtimeMode,
			final int numRealtimeWorkerThreads) {
		this(monitoringController, inputDirs, realtimeMode, numRealtimeWorkerThreads, MIN_TIMESTAMP, MAX_TIMESTAMP);
	}

	/**
	 * 
	 * 
	 * @param monitoringController
	 * @param inputDirs
	 * @param realtimeMode
	 * @param numRealtimeWorkerThreads
	 * @param ignoreRecordsBeforeTimestamp
	 * @param ignoreRecordsAfterTimestamp
	 */
	public FilesystemLogReplayer(final IMonitoringController monitoringController, final String[] inputDirs, final boolean realtimeMode,
			final int numRealtimeWorkerThreads, final long ignoreRecordsBeforeTimestamp, final long ignoreRecordsAfterTimestamp) {
		this.recordReceiver = monitoringController;
		// 1.5 compability
		this.inputDirs = new String[inputDirs.length];
		System.arraycopy(inputDirs, 0, this.inputDirs, 0, inputDirs.length);
		// for 1.6+:
		// this.inputDirs = Arrays.copyOf(inputDirs, inputDirs.length);
		this.realtimeMode = realtimeMode;
		this.numRealtimeWorkerThreads = numRealtimeWorkerThreads;
		this.ignoreRecordsBeforeTimestamp = ignoreRecordsBeforeTimestamp;
		this.ignoreRecordsAfterTimestamp = ignoreRecordsAfterTimestamp;
	}

	/**
	 * Replays the monitoring log terminates after the last record was passed to
	 * the configured {@link kieker.common.record.IMonitoringRecordReceiver}.
	 * 
	 * @return true on success; false otherwise
	 */
	public boolean replay() {
		boolean success = true;

		AbstractReaderPlugin fsReader;
		if (this.realtimeMode) {
			final Configuration configuration = new Configuration();
			configuration.setProperty(FSReaderRealtime.CONFIG_PROPERTY_NAME_INPUTDIRNAMES, Configuration.toProperty(this.inputDirs));
			configuration.setProperty(FSReaderRealtime.CONFIG_PROPERTY_NAME_NUM_WORKERS, Integer.toString(this.numRealtimeWorkerThreads));
			fsReader = new FSReaderRealtime(configuration);
		} else {
			final Configuration configuration = new Configuration(null);
			configuration.setProperty(FSReader.CONFIG_PROPERTY_NAME_INPUTDIRS, Configuration.toProperty(this.inputDirs));
			fsReader = new FSReader(configuration);
		}
		final AnalysisController tpanInstance = new AnalysisController();
		tpanInstance.registerReader(fsReader);

		final Configuration delegationConfiguration = new Configuration();
		delegationConfiguration.setProperty(RecordDelegationPlugin.CONFIG_PROPERTY_NAME_IGNORE_RECORDS_AFTER_TIMESTAMP,
				Long.toString(this.ignoreRecordsAfterTimestamp));
		delegationConfiguration.setProperty(RecordDelegationPlugin.CONFIG_PROPERTY_NAME_IGNORE_RECORDS_BEFORE_TIMESTAMP,
				Long.toString(this.ignoreRecordsBeforeTimestamp));
		final RecordDelegationPlugin delegationPlugin = new RecordDelegationPlugin(delegationConfiguration);
		delegationPlugin.setRec(this.recordReceiver);

		tpanInstance.registerFilter(delegationPlugin);
		try {
			tpanInstance.connect(fsReader, FSReader.OUTPUT_PORT_NAME_RECORDS, delegationPlugin, RecordDelegationPlugin.INPUT_PORT_NAME_MONITORING_RECORDS);
		} catch (final AnalysisConfigurationException ex) {
			LOG.error("Failed to connect reader to delegation.", ex);
		}
		try {
			tpanInstance.run();
			success = true;
		} catch (final Exception ex) { // NOPMD NOCS (IllegalCatchCheck)
			LOG.error("Exception running analysis instance", ex);
			success = false;
		}
		return success;
	}
}

/**
 * Kieker analysis plugin that delegates each record to the configured {@link kieker.common.record.IMonitoringRecordReceiver}.<br>
 * 
 * <b>Don't</b> change the visibility modificator to public. The class does not have the necessary <i>Configuration</i>-Constructor in order to be used by the
 * analysis meta model. <br>
 * 
 * TODO: We need to extract this class and merge it with that of {@link JMSLogReplayer} See ticket http://samoa.informatik.uni-kiel.de:8000/kieker/ticket/172
 * 
 * @author Andre van Hoorn
 * 
 */
class RecordDelegationPlugin extends AbstractFilterPlugin {

	public static final String INPUT_PORT_NAME_MONITORING_RECORDS = "monitoringRecords";

	public static final String CONFIG_PROPERTY_NAME_IGNORE_RECORDS_BEFORE_TIMESTAMP = "ignoreRecordsBeforeTimestamp";
	public static final String CONFIG_PROPERTY_NAME_IGNORE_RECORDS_AFTER_TIMESTAMP = "ignoreRecordsAfterTimestamp";

	private static final Log LOG = LogFactory.getLog(RecordDelegationPlugin.class);

	private IMonitoringController rec;

	private final long ignoreRecordsBeforeTimestamp;
	private final long ignoreRecordsAfterTimestamp;

	/**
	 * Creates a new instance of this class using the given configuration object.
	 * 
	 * @param configuration
	 *            The object to configure this plugin.
	 */
	public RecordDelegationPlugin(final Configuration configuration) {
		super(configuration);

		this.ignoreRecordsBeforeTimestamp = configuration.getLongProperty(RecordDelegationPlugin.CONFIG_PROPERTY_NAME_IGNORE_RECORDS_BEFORE_TIMESTAMP);
		this.ignoreRecordsAfterTimestamp = configuration.getLongProperty(RecordDelegationPlugin.CONFIG_PROPERTY_NAME_IGNORE_RECORDS_AFTER_TIMESTAMP);
	}

	/**
	 * Sets the controller for this instance to a new value. Data is only sent via the first input port of the given plugin.
	 * 
	 * @param rec
	 *            The controller.
	 */
	public void setRec(final IMonitoringController rec) {
		this.rec = rec;
	}

	@InputPort(
			name = RecordDelegationPlugin.INPUT_PORT_NAME_MONITORING_RECORDS,
			eventTypes = { IMonitoringRecord.class }, description = RecordDelegationPlugin.INPUT_PORT_NAME_MONITORING_RECORDS)
	public void inputMonitoringRecord(final IMonitoringRecord record) {
		if ((record.getLoggingTimestamp() >= this.ignoreRecordsBeforeTimestamp) && (record.getLoggingTimestamp() <= this.ignoreRecordsAfterTimestamp)) {
			/* Delegate the record to the monitoring controller. */
			this.rec.newMonitoringRecord(record);
		}
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public boolean init() {
		if (RecordDelegationPlugin.LOG.isDebugEnabled()) {
			RecordDelegationPlugin.LOG.debug(RecordDelegationPlugin.class.getName() + " starting ...");
		}
		RecordDelegationPlugin.LOG
				.info("Ignoring records before " + LoggingTimestampConverter.convertLoggingTimestampToUTCString(this.ignoreRecordsBeforeTimestamp));
		RecordDelegationPlugin.LOG.info("Ignoring records after " + LoggingTimestampConverter.convertLoggingTimestampToUTCString(this.ignoreRecordsAfterTimestamp));
		return true;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	protected Configuration getDefaultConfiguration() {
		return new Configuration();
	}

	/**
	 * {@inheritDoc}
	 */

	public Configuration getCurrentConfiguration() {
		/* No configuration possible. */
		return new Configuration(null);
	}
}
