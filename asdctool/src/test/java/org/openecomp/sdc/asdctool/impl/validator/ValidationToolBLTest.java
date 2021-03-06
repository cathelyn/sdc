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

package org.openecomp.sdc.asdctool.impl.validator;

import org.junit.jupiter.api.Test;
import org.openecomp.sdc.asdctool.impl.validator.executor.TopologyTemplateValidatorExecutor;
import org.openecomp.sdc.asdctool.impl.validator.executor.ValidatorExecutor;
import org.openecomp.sdc.asdctool.impl.validator.report.Report;
import org.openecomp.sdc.be.dao.jsongraph.JanusGraphDao;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.openecomp.sdc.asdctool.impl.validator.report.ReportFile.makeTxtFile;
import static org.openecomp.sdc.asdctool.impl.validator.report.ReportFileWriterTestFactory.makeConsoleWriter;

public class ValidationToolBLTest {

    @Test
    public void testValidateAll() {
        JanusGraphDao janusGraphDaoMock = mock(JanusGraphDao.class);

        List<ValidatorExecutor> validators = new ArrayList<>();
        validators.add(TopologyTemplateValidatorExecutor.serviceValidatorExecutor(janusGraphDaoMock));
        ValidationToolBL testSubject = new ValidationToolBL(validators);

        Report report = Report.make();
        assertThrows(
                NullPointerException.class,
                () -> testSubject.validateAll(report, makeTxtFile(makeConsoleWriter()))
        );
    }
}
