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

package kieker.monitoring.writer.filesystem;

import java.io.FilenameFilter;
import java.nio.file.Path;

/**
 * Only used within tests (hence, declared package-private).
 *
 * @author Christian Wulf
 *
 * @since 1.13
 */
interface IFileWriter {

	/**
	 * @since 1.13
	 */
	FilenameFilter getFileNameFilter();

	/**
	 * @since 1.13
	 */
	Path getLogFolder();
}
