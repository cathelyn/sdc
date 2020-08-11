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

package org.openecomp.sdc.itempermissions.impl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.openecomp.sdc.common.errors.CoreException;
import org.openecomp.sdc.itempermissions.dao.impl.PermissionsServicesImpl;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;


/**
 * Created by ayalaben on 7/10/2017
 */
public class PermissionsRulesImplTest {

  private static final String ITEM1_ID = "1";
  private static final String USER1_ID = "testUser1";
  private static final String PERMISSION_OWNER = "Owner";
  private static final String PERMISSION_CONTRIBUTOR = "Contributor";
  private static final String INVALID_PERMISSION = "Invalid_Permission";
  private static final String SUBMIT_ACTION = "Submit_Item";
  private static final String EDIT_ACTION = "Edit_Item";
  private static final String CHANGE_PERMISSIONS_ACTION = "Change_Item_Permissions";
  private static final String INVALID_ACTION = "Invalid_Action";

  @Mock
  private PermissionsServicesImpl permissionsServices;

  @InjectMocks
  @Spy
  private PermissionsRulesImpl permissionsRules;


  @BeforeEach
  public void setUp() throws Exception {

    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testIsAllowedWhenInvalidPermission() throws Exception {
    CoreException thrown = assertThrows(CoreException.class, () -> {
      permissionsRules.isAllowed(INVALID_PERMISSION, EDIT_ACTION);
    });

    assertTrue(thrown.getMessage().contains("Invalid permission type"));
  }

  @Test(expectedExceptionsMessageRegExp =
          "Invalid action type")
  public void testIsAllowedWhenInvalidAction() throws Exception {
    assertThrows(CoreException.class, () -> {
      permissionsRules.isAllowed(PERMISSION_CONTRIBUTOR, INVALID_ACTION);
    });
  }

  @Test
  public void testIsAllowedCaseSubmitOwner(){
    assertTrue(permissionsRules.isAllowed(PERMISSION_OWNER,SUBMIT_ACTION));
  }

  @Test
  public void testIsAllowedCaseSubmitNotOwner(){
    assertTrue(permissionsRules.isAllowed(PERMISSION_CONTRIBUTOR,SUBMIT_ACTION));
  }

  @Test
  public void testIsAllowedCaseEditOwner(){
    assertTrue(permissionsRules.isAllowed(PERMISSION_OWNER,EDIT_ACTION));
  }

  @Test
  public void testIsAllowedCaseEditContributer(){
    assertTrue(permissionsRules.isAllowed(PERMISSION_CONTRIBUTOR,EDIT_ACTION));
  }

  @Test
  public void testIsAllowedCaseChangePermissionsContributer(){
    assertFalse(permissionsRules.isAllowed(PERMISSION_CONTRIBUTOR,CHANGE_PERMISSIONS_ACTION));
  }

  @Test
  public void testIsAllowedCaseChangePermissionsOwner(){
    assertTrue(permissionsRules.isAllowed(PERMISSION_OWNER,CHANGE_PERMISSIONS_ACTION));
  }

  @Test(expectedExceptionsMessageRegExp =
          "Invalid permission type")
  public void testUpdatePermissionWhenInvalidPermission() throws Exception {
    assertThrows(CoreException.class, () -> {
      permissionsRules.updatePermission(ITEM1_ID,USER1_ID,INVALID_PERMISSION,new HashSet<String>(),
              new HashSet<String>());
    });
  }

  @Test(expectedExceptions = CoreException.class,expectedExceptionsMessageRegExp =
          "Invalid action type")
  public void testExecuteActionInvalidAction(){
    permissionsRules.executeAction(ITEM1_ID,USER1_ID,INVALID_ACTION);
  }


}
