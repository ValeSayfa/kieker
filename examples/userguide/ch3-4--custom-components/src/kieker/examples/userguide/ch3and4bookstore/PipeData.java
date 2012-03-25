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

package kieker.examples.userguide.ch3and4bookstore;

public class PipeData {
	private final long loggingTimestamp;
	private final Object[] recordData;

	public PipeData(final long loggingTimestamp, final Object[] recordData) {
		this.loggingTimestamp = loggingTimestamp;
		this.recordData = recordData; // FIXME: clone()?
	}

	public final long getLoggingTimestamp() {
		return this.loggingTimestamp;
	}

	public final Object[] getRecordData() {
		return this.recordData; // FIXME: clone()?
	}
}
