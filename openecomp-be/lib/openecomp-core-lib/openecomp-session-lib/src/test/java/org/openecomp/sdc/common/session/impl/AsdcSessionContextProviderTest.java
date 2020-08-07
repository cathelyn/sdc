/*
 *
 *  Copyright © 2017-2018 European Support Limited
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  * Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * /
 *
 */

package org.openecomp.sdc.common.session.impl;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.openecomp.sdc.common.errors.CoreException;
import org.openecomp.sdc.common.session.SessionContext;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AsdcSessionContextProviderTest {

    private static final String USER_ID = "cs0008";

    @InjectMocks
    private AsdcSessionContextProvider asdcSessionContextProvider;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserIdNull() throws Exception {
        assertThrows(CoreException.class, () -> {
            asdcSessionContextProvider.create(null, null);
            asdcSessionContextProvider.get();
        });
    }

    @Test
    public void testGetTenantNull() throws  Exception {
        assertThrows(CoreException.class, () -> {
            asdcSessionContextProvider.create(USER_ID, null);
            asdcSessionContextProvider.get();
                });
    }

    @Test
    public void testGet() {
        asdcSessionContextProvider.create(USER_ID, "tenant");
        SessionContext sessionContext = asdcSessionContextProvider.get();

        assertNotNull(sessionContext);
        assertSame(USER_ID, sessionContext.getUser().getUserId());
        assertSame("tenant", sessionContext.getTenant());
    }
}
