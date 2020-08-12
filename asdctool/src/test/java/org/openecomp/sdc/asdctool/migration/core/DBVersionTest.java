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

package org.openecomp.sdc.asdctool.migration.core;

import org.testng.annotations.DataProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.params.ParameterizedTest;

public class DBVersionTest {


    @DataProvider(name = "invalidVersionStringsProvider")
    private Object[][] invalidVersionStringsProvider() {
        return new Object[][] {
                {"1.1.1"},
                {"1.a"},
                {"a.1"},
                {"1"}
        };
    }

    @ParametrizedTest
    @MethodSource(invalidVersionStringsProvider)
    public void testFromString_invalidVersionString(String invalidVersion) throws Exception {
        assertThrows(MigrationException.class, () -> {
            DBVersion.fromString(invalidVersion);
        });
    }

    @DataProvider(name = "validVersionStringsProvider")
    private Object[][] validVersionStringsProvider() {
        return new Object[][] {
                {"1.1", "1.1"},
                {"10100.0001", "10100.1"},
                {"000.1", "0.1"},
                {"01.00001000", "1.1000"},
        };
    }

    @Test(dataProvider = "validVersionStringsProvider")
    public void testFromString(String validString, String expectedVersionString) {
        assertEquals(expectedVersionString, DBVersion.fromString(validString).toString());
    }

    @DataProvider(name = "versionComparisionProvider")
    public static Object[][] versionComparisionProvider() {
        return new Object[][] {
                {"1.1", "001.00001", 0},
                {"10.1", "0010.00001", 0},
                {"1.1", "001.000010", -1},
                {"1.1", "0010.00001", -1},
                {"10.10", "0010.00001", 1},
                {"1.1", "001.00", 1},
        };
    }

    @Test(dataProvider = "versionComparisionProvider")
    public void testVersionCompareTo2(String firstVersion, String otherVersion, int expectedComparisionResult) {
        assertEquals(DBVersion.fromString(firstVersion).compareTo(DBVersion.fromString(otherVersion)), expectedComparisionResult);
    }
}
