/*-
 * ============LICENSE_START=======================================================
 * SDC
 * ================================================================================
 * Copyright (C) 2019 AT&T Intellectual Property. All rights reserved.
 * ================================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ============LICENSE_END=========================================================
 */

package org.openecomp.sdc.asdctool.servlets;

import org.janusgraph.core.JanusGraph;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.ws.rs.core.Response;
import java.io.File;

public class ExportImportJanusGraphServletTest {

	private ExportImportJanusGraphServlet createTestSubject() {
		return new ExportImportJanusGraphServlet();
	}

	@Test
	public void testExport() throws Exception {
		assertThrows(NullPointerException.class, () -> {
			ExportImportJanusGraphServlet testSubject;
			File janusGraphPropertiesFile = null;
			String exportGraphMetadata = "";
			Response result;

			// default test
			testSubject = createTestSubject();
			result = testSubject.export(janusGraphPropertiesFile, exportGraphMetadata);
		});
	}

	@Test
	public void testExportGraph() throws Exception {
		assertThrows(NullPointerException.class, () -> {
			ExportImportJanusGraphServlet testSubject;
			JanusGraph graph = null;
			String outputDirectory = "";
			String result;

			// default test
			testSubject = createTestSubject();
			result = testSubject.exportGraph(graph, outputDirectory);
		});
	}
}
