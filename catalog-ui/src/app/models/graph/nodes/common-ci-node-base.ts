/*-
 * ============LICENSE_START=======================================================
 * SDC
 * ================================================================================
 * Copyright (C) 2017 AT&T Intellectual Property. All rights reserved.
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
import {CommonNodeBase} from "./base-common-node";
import {ComponentInstance} from "../../componentsInstances/componentInstance";
export abstract class CommonCINodeBase extends CommonNodeBase {

    public certified:boolean;
    public archived:boolean;
    public template:string;
    public componentInstance:ComponentInstance;
    public group:string;

    constructor(instance:ComponentInstance) {
        super();
        this.componentInstance = instance;
        this.id = this.componentInstance.uniqueId;
        this.name = this.componentInstance.name;
        this.img = '';
        this.certified = this.isCertified(this.componentInstance.componentVersion);
        this.displayName = instance.name;
        this.archived = instance.originArchived;
    }

    private isCertified(version:string):boolean {
        return 0 === (parseFloat(version)) % 1;
    }

}

