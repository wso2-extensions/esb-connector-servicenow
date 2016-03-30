package org.wso2.carbon.connector.integration.test.serviceNow;
/*
 *
 *  Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */

import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.connector.integration.test.base.ConnectorIntegrationTestBase;
import org.wso2.connector.integration.test.base.RestResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServiceNowConnectorIntegrationTest extends ConnectorIntegrationTestBase {
    private Map<String, String> esbRequestHeadersMap = new HashMap<String, String>();
    private Map<String, String> apiRequestHeadersMap = new HashMap<String, String>();

    /**
     * Set up the environment.
     */
    @BeforeClass(alwaysRun = true)
    public void setEnvironment() throws Exception {
        init("servicenow-connector-1.0.0");
        esbRequestHeadersMap.put("Accept-Charset", "UTF-8");
        esbRequestHeadersMap.put("Content-Type", "application/json");
        apiRequestHeadersMap.putAll(esbRequestHeadersMap);
        String authString = connectorProperties.getProperty("username") + ":" + connectorProperties.getProperty("password");
        byte[] encodedString = Base64.encodeBase64(authString.getBytes());
        apiRequestHeadersMap.put("Authorization", "Basic " + new String(encodedString));
        apiRequestHeadersMap.put("Content-Type", "application/json");
        apiRequestHeadersMap.put("Accept", "application/json");
    }

    /**
     * Test case for getRecords method with mandatory parameter.
     */
    @Test(priority = 1, groups = {"wso2.esb"}, description = "servicenow {getRecords} integration test with mandatory parameters.")
    public void testGetRecordsWithMandatoryParameters() throws IOException, JSONException {
        esbRequestHeadersMap.put("Action", "urn:getRecords");
        String apiEndPoint = connectorProperties.getProperty("serviceNowInstanceURL") + "/api/now/table/" + connectorProperties.getProperty("tableName");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "getRecords_mandatory.json");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("result").getJSONObject(1).get("number"), apiRestResponse.getBody().getJSONArray("result").getJSONObject(1).get("number"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("result").getJSONObject(1).get("category"), apiRestResponse.getBody().getJSONArray("result").getJSONObject(1).get("category"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("result").getJSONObject(1).get("short_description"), apiRestResponse.getBody().getJSONArray("result").getJSONObject(1).get("short_description"));
    }

    /**
     * Test case for getRecords method with optional parameter.
     */
    @Test(priority = 1, groups = {"wso2.esb"}, description = "servicenow {getRecords} integration test with optional parameters.")
    public void testGetRecordsWithOptionalParameters() throws IOException, JSONException {
        esbRequestHeadersMap.put("Action", "urn:getRecords");
        String apiEndPoint = connectorProperties.getProperty("serviceNowInstanceURL") + "/api/now/table/" + connectorProperties.getProperty("tableName") + "?" + "&sysparm_query=" + connectorProperties.getProperty("sysparmQuery") + "&sysparm_limit=" + connectorProperties.getProperty("sysparmLimit") + "&sysparm_display_value=" + connectorProperties.getProperty("sysparmDisplayValue") + "&sysparm_fields=" + connectorProperties.getProperty("sysparmFields") + "&sysparm_view=" + connectorProperties.getProperty("sysparmView") + "&sysparm_offset=" + connectorProperties.getProperty("sysparmOffset") + "&sysparm_exclude_reference_link=" + connectorProperties.getProperty("sysparmExcludeReferenceLink") + "&sysparm_read_replica_category=" + connectorProperties.getProperty("sysparmReadReplicaCategory") + "&" + connectorProperties.getProperty("KeyValuePairs");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "getRecords_optional.json");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("result").getJSONObject(1).get("number"), apiRestResponse.getBody().getJSONArray("result").getJSONObject(1).get("number"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("result").getJSONObject(1).get("short_description"), apiRestResponse.getBody().getJSONArray("result").getJSONObject(1).get("short_description"));
    }

    /**
     * Negative test case for getRecords method.
     */
    @Test(priority = 1, groups = {"wso2.esb"}, description = "servicenow {getRecords} integration test with negative case.")
    public void testGetRecordsWithNegativeCase() throws IOException, JSONException {
        esbRequestHeadersMap.put("Action", "urn:getRecords");
        String apiEndPoint = connectorProperties.getProperty("serviceNowInstanceURL") + "/api/now/table/" + connectorProperties.getProperty("tableNameNegative");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "getRecords_negative.json");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), apiRestResponse.getHttpStatusCode());
    }

    /**
     * Test case for getRecordById method with mandatory parameter.
     */
    @Test(enabled = true, dependsOnMethods = {"testPostRecordWithMandatoryParameters"}, groups = {"wso2.esb"}, description = "servicenow {getRecordById} integration test with mandatory parameters.")
    public void testGetRecordByIdWithMandatoryParameters() throws IOException, JSONException {
        esbRequestHeadersMap.put("Action", "urn:getRecordById");
        String apiEndPoint = connectorProperties.getProperty("serviceNowInstanceURL") + "/api/now/table/" + connectorProperties.getProperty("tableName") + "/" + connectorProperties.getProperty("sysId");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "getRecordById_mandatory.json");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getJSONObject("result").get("number"), apiRestResponse.getBody().getJSONObject("result").get("number"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONObject("result").get("category"), apiRestResponse.getBody().getJSONObject("result").get("category"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONObject("result").get("short_description"), apiRestResponse.getBody().getJSONObject("result").get("short_description"));
    }

    /**
     * Test case for getRecordById method with optional parameter.
     */
    @Test(enabled = true, dependsOnMethods = {"testGetRecordByIdWithMandatoryParameters"}, groups = {"wso2.esb"}, description = "servicenow {getRecordById} integration test with optional parameters.")
    public void testGetRecordByIdWithOptionalParameters() throws IOException, JSONException {
        esbRequestHeadersMap.put("Action", "urn:getRecordById");
        String apiEndPoint = connectorProperties.getProperty("serviceNowInstanceURL") + "/api/now/table/" + connectorProperties.getProperty("tableName") + "/" + connectorProperties.getProperty("sysId") + "?" + "&sysparm_display_value=" + connectorProperties.getProperty("sysparmDisplayValue") + "&sysparm_fields=" + connectorProperties.getProperty("sysparmFields") + "&sysparm_view=" + connectorProperties.getProperty("sysparmView") + "&sysparm_exclude_reference_link=" + connectorProperties.getProperty("sysparmExcludeReferenceLink");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "getRecordById_optional.json");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getJSONObject("result").get("number"), apiRestResponse.getBody().getJSONObject("result").get("number"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONObject("result").get("short_description"), apiRestResponse.getBody().getJSONObject("result").get("short_description"));
    }

    /**
     * Negative test case for getRecordById method.
     */
    @Test(priority = 1, groups = {"wso2.esb"}, description = "servicenow {patchRecordById} integration test with negative case.")
    public void testGetRecordByIDWithNegativeCase() throws IOException, JSONException {
        esbRequestHeadersMap.put("Action", "urn:getRecordById");
        String apiEndPoint = connectorProperties.getProperty("serviceNowInstanceURL") + "/api/now/table/" + connectorProperties.getProperty("tableName") + "/" + connectorProperties.getProperty("sysIdNegative");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "getRecordById_negative.json");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), apiRestResponse.getHttpStatusCode());
    }

    /**
     * Test case for postRecord method with mandatory parameter.
     */
    @Test(priority = 1, groups = {"wso2.esb"}, description = "servicenow {postRecord} integration test with mandatory parameters.")
    public void testPostRecordWithMandatoryParameters() throws IOException, JSONException {
        esbRequestHeadersMap.put("Action", "urn:postRecord");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "postRecord_mandatory.json");
        String sysIdPosted = esbRestResponse.getBody().getJSONObject("result").get("sys_id").toString();
        connectorProperties.put("sysId", sysIdPosted);
        String apiEndPoint = connectorProperties.getProperty("serviceNowInstanceURL") + "/api/now/table/" + connectorProperties.getProperty("tableName") + "/" + sysIdPosted;
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 201);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getJSONObject("result").get("number"), apiRestResponse.getBody().getJSONObject("result").get("number"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONObject("result").get("category"), apiRestResponse.getBody().getJSONObject("result").get("category"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONObject("result").get("short_description"), apiRestResponse.getBody().getJSONObject("result").get("short_description"));
    }

    /**
     * Test case for postRecord method with optional parameter.
     */
    @Test(priority = 1, groups = {"wso2.esb"}, description = "servicenow {postRecord} integration test with optional parameters.")
    public void testPostRecordWithOptionalParameters() throws IOException, JSONException {
        esbRequestHeadersMap.put("Action", "urn:postRecord");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "postRecord_optional.json");
        String sysIdPosted = esbRestResponse.getBody().getJSONObject("result").get("sys_id").toString();
        String apiEndPoint = connectorProperties.getProperty("serviceNowInstanceURL") + "/api/now/table/" + connectorProperties.getProperty("tableName") + "/" + sysIdPosted + "?" + "&sysparm_display_value=" + connectorProperties.getProperty("sysparmDisplayValue") + "&sysparm_fields=" + connectorProperties.getProperty("sysparmFields") + "&sysparm_view=" + connectorProperties.getProperty("sysparmView") + "&sysparm_exclude_reference_link=" + connectorProperties.getProperty("sysparmExcludeReferenceLink") + "&sysparm_input_display_value=" + connectorProperties.getProperty("sysparmInputDisplayValue");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 201);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getJSONObject("result").get("number"), apiRestResponse.getBody().getJSONObject("result").get("number"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONObject("result").get("short_description"), apiRestResponse.getBody().getJSONObject("result").get("short_description"));
    }

    /**
     * Negative test case for postRecord method.
     */
    @Test(priority = 1, groups = {"wso2.esb"}, description = "servicenow {postRecord} integration test with negative case.")
    public void testPostRecordWithNegativeCase() throws IOException, JSONException {
        esbRequestHeadersMap.put("Action", "urn:postRecord");
        String apiEndPoint = connectorProperties.getProperty("serviceNowInstanceURL") + "/api/now/table/" + connectorProperties.getProperty("tableNameNegative");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "postRecord_negative.json");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), apiRestResponse.getHttpStatusCode());
    }

    /**
     * Test case for patchRecordById method with mandatory parameter.
     */
    @Test(enabled = true, dependsOnMethods = {"testGetRecordByIdWithOptionalParameters"}, groups = {"wso2.esb"}, description = "servicenow {patchRecordById} integration test with mandatory parameters.")
    public void testPatchRecordByIdWithMandatoryParameters() throws IOException, JSONException {
        esbRequestHeadersMap.put("Action", "urn:patchRecordById");
        String apiEndPoint = connectorProperties.getProperty("serviceNowInstanceURL") + "/api/now/table/" + connectorProperties.getProperty("tableName") + "/" + connectorProperties.getProperty("sysId");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "patchRecordById_mandatory.json");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getJSONObject("result").get("number"), apiRestResponse.getBody().getJSONObject("result").get("number"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONObject("result").get("category"), apiRestResponse.getBody().getJSONObject("result").get("category"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONObject("result").get("short_description"), apiRestResponse.getBody().getJSONObject("result").get("short_description"));
    }

    /**
     * Test case for patchRecordById method with optional parameter.
     */
    @Test(enabled = true, dependsOnMethods = {"testPatchRecordByIdWithMandatoryParameters"}, groups = {"wso2.esb"}, description = "servicenow {patchRecordById} integration test with optional parameters.")
    public void testPatchRecordByIdWithOptionalParameters() throws IOException, JSONException {
        esbRequestHeadersMap.put("Action", "urn:patchRecordById");
        String apiEndPoint = connectorProperties.getProperty("serviceNowInstanceURL") + "/api/now/table/" + connectorProperties.getProperty("tableName") + "/" + connectorProperties.getProperty("sysId") + "?" + "&sysparm_display_value=" + connectorProperties.getProperty("sysparmDisplayValue") + "&sysparm_fields=" + connectorProperties.getProperty("sysparmFields") + "&sysparm_view=" + connectorProperties.getProperty("sysparmView") + "&sysparm_exclude_reference_link=" + connectorProperties.getProperty("sysparmExcludeReferenceLink") + "&sysparm_input_display_value=" + connectorProperties.getProperty("sysparmInputDisplayValue");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "patchRecordById_optional.json");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getJSONObject("result").get("number"), apiRestResponse.getBody().getJSONObject("result").get("number"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONObject("result").get("short_description"), apiRestResponse.getBody().getJSONObject("result").get("short_description"));
    }

    /**
     * Negative test case for patchRecordById method.
     */
    @Test(priority = 1, groups = {"wso2.esb"}, description = "servicenow {patchRecordById} integration test with negative case.")
    public void testPatchRecordByIDWithNegativeCase() throws IOException, JSONException {
        esbRequestHeadersMap.put("Action", "urn:patchRecordById");
        String apiEndPoint = connectorProperties.getProperty("serviceNowInstanceURL") + "/api/now/table/" + connectorProperties.getProperty("tableName") + "/" + connectorProperties.getProperty("sysIdNegative");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "patchRecordById_negative.json");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), apiRestResponse.getHttpStatusCode());
    }

    /**
     * Test case for putRecordById method with mandatory parameter.
     */
    @Test(enabled = true, dependsOnMethods = {"testPatchRecordByIdWithOptionalParameters"}, groups = {"wso2.esb"}, description = "servicenow {putRecordById} integration test with mandatory parameters.")
    public void testPutRecordByIdWithMandatoryParameters() throws IOException, JSONException {
        esbRequestHeadersMap.put("Action", "urn:putRecordById");
        String apiEndPoint = connectorProperties.getProperty("serviceNowInstanceURL") + "/api/now/table/" + connectorProperties.getProperty("tableName") + "/" + connectorProperties.getProperty("sysId");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "putRecordById_mandatory.json");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getJSONObject("result").get("number"), apiRestResponse.getBody().getJSONObject("result").get("number"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONObject("result").get("category"), apiRestResponse.getBody().getJSONObject("result").get("category"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONObject("result").get("short_description"), apiRestResponse.getBody().getJSONObject("result").get("short_description"));
    }

    /**
     * Test case for putRecordById method with optional parameter.
     */
    @Test(enabled = true, dependsOnMethods = {"testPutRecordByIdWithMandatoryParameters"}, groups = {"wso2.esb"}, description = "servicenow {putRecordById} integration test with optional parameters.")
    public void testPutRecordByIdWithOptionalParameters() throws IOException, JSONException {
        esbRequestHeadersMap.put("Action", "urn:putRecordById");
        String apiEndPoint = connectorProperties.getProperty("serviceNowInstanceURL") + "/api/now/table/" + connectorProperties.getProperty("tableName") + "/" + connectorProperties.getProperty("sysId") + "?" + "&sysparm_display_value=" + connectorProperties.getProperty("sysparmDisplayValue") + "&sysparm_fields=" + connectorProperties.getProperty("sysparmFields") + "&sysparm_view=" + connectorProperties.getProperty("sysparmView") + "&sysparm_exclude_reference_link=" + connectorProperties.getProperty("sysparmExcludeReferenceLink") + "&sysparm_input_display_value=" + connectorProperties.getProperty("sysparmInputDisplayValue");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "putRecordById_optional.json");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getJSONObject("result").get("number"), apiRestResponse.getBody().getJSONObject("result").get("number"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONObject("result").get("short_description"), apiRestResponse.getBody().getJSONObject("result").get("short_description"));
    }

    /**
     * Negative test case for putRecordById method.
     */
    @Test(priority = 1, groups = {"wso2.esb"}, description = "servicenow {putRecordById} integration test with negative case.")
    public void testPutRecordByIDWithNegativeCase() throws IOException, JSONException {
        esbRequestHeadersMap.put("Action", "urn:putRecordById");
        String apiEndPoint = connectorProperties.getProperty("serviceNowInstanceURL") + "/api/now/table/" + connectorProperties.getProperty("tableName") + "/" + connectorProperties.getProperty("sysIdNegative");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "putRecordById_negative.json");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), apiRestResponse.getHttpStatusCode());
    }

    /**
     * Test case for deleteRecordById method with mandatory parameter.
     */
    @Test(enabled = true, dependsOnMethods = {"testPutRecordByIdWithOptionalParameters"}, groups = {"wso2.esb"}, description = "servicenow {deleteRecordById} integration test with mandatory parameters.")
    public void testDeleteRecordByIdWithMandatoryParameters() throws IOException, JSONException {
        esbRequestHeadersMap.put("Action", "urn:deleteRecordById");
        String apiEndPoint = connectorProperties.getProperty("serviceNowInstanceURL") + "/api/now/table/" + connectorProperties.getProperty("tableName") + "/" + connectorProperties.getProperty("sysId");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "deleteRecordById_mandatory.json");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 204);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 404);
    }

    /**
     * Negative test case for deleteRecordById method.
     */
    @Test(priority = 1, groups = {"wso2.esb"}, description = "servicenow {deleteRecordById} integration test with negative case.")
    public void testDeleteRecordByIDWithNegativeCase() throws IOException, JSONException {
        esbRequestHeadersMap.put("Action", "urn:deleteRecordById");
        String apiEndPoint = connectorProperties.getProperty("serviceNowInstanceURL") + "/api/now/table/" + connectorProperties.getProperty("tableName") + "/" + connectorProperties.getProperty("sysIdNegative");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "deleteRecordById_negative.json");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "DELETE", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), apiRestResponse.getHttpStatusCode());
    }

    /**
     * Test case for getRecordStagingTable method with mandatory parameter.
     */
    @Test(priority = 1, groups = {"wso2.esb"}, description = "servicenow {getRecordStagingTable} integration test with mandatory parameters.")
    public void testgetRecordStagingTableWithMandatoryParameters() throws IOException, JSONException {
        esbRequestHeadersMap.put("Action", "urn:getRecordsStagingTable");
        String apiEndPoint = connectorProperties.getProperty("serviceNowInstanceURL") + "/api/now/import/" + connectorProperties.getProperty("tableNameStaging") + "/" + connectorProperties.getProperty("sysIdStaging");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "getRecordStagingTable_mandatory.json");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("result").getJSONObject(0).get("display_name"), apiRestResponse.getBody().getJSONArray("result").getJSONObject(0).get("display_name"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("result").getJSONObject(0).get("sys_id"), apiRestResponse.getBody().getJSONArray("result").getJSONObject(0).get("sys_id"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("result").getJSONObject(0).get("status"), apiRestResponse.getBody().getJSONArray("result").getJSONObject(0).get("status"));
    }

    /**
     * Negative test case for getRecordStagingTable method.
     */
    @Test(priority = 1, groups = {"wso2.esb"}, description = "servicenow {postRecordStagingTable} integration test with negative case.")
    public void testGetRecordStagingTableWithNegativeCase() throws IOException, JSONException {
        esbRequestHeadersMap.put("Action", "urn:getRecordsStagingTable");
        String apiEndPoint = connectorProperties.getProperty("serviceNowInstanceURL") + "/api/now/import/" + connectorProperties.getProperty("tableNameNegative");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "getRecordStagingTable_negative.json");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), apiRestResponse.getHttpStatusCode());
    }

    /**
     * Test case for postRecordStagingTable method with mandatory parameter.
     */
    @Test(priority = 1, groups = {"wso2.esb"}, description = "servicenow {postRecordStagingTable} integration test with mandatory parameters.")
    public void testPostRecordStagingTableWithMandatoryParameters() throws IOException, JSONException {
        esbRequestHeadersMap.put("Action", "urn:postRecordStagingTable");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "postRecordStagingTable_mandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 201);
    }

    /**
     * Negative test case for postRecordStagingTable method.
     */
    @Test(priority = 1, groups = {"wso2.esb"}, description = "servicenow {postRecordStagingTable} integration test with negative case.")
    public void testpostRecordStagingTableWithNegativeCase() throws IOException, JSONException {
        esbRequestHeadersMap.put("Action", "urn:postRecordStagingTable");
        String apiEndPoint = connectorProperties.getProperty("serviceNowInstanceURL") + "/api/now/import/" + connectorProperties.getProperty("tableNameNegative");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "postRecordStagingTable_negative.json");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), apiRestResponse.getHttpStatusCode());
    }

    /**
     * Test case for getAggregateRecord method with mandatory parameter.
     */
    @Test(priority = 1, groups = {"wso2.esb"}, description = "servicenow {getAggregateRecord} integration test with mandatory parameters.")
    public void testgetAggregateRecordWithMandatoryParameters() throws IOException, JSONException {
        esbRequestHeadersMap.put("Action", "urn:getAggregateRecord");
        String apiEndPoint = connectorProperties.getProperty("serviceNowInstanceURL") + "/api/now/stats/" + connectorProperties.getProperty("tableName") + "?" + "&sysparm_count=" + connectorProperties.getProperty("sysparmCount");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "getAggregateRecord_mandatory.json");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getJSONObject("result").getJSONObject("stats").get("count"), apiRestResponse.getBody().getJSONObject("result").getJSONObject("stats").get("count"));
    }

    /**
     * Test case for getAggregateRecord method with optional parameter.
     */
    @Test(priority = 1, groups = {"wso2.esb"}, description = "servicenow {getAggregateRecord} integration test with optional parameters.")
    public void testgetAggregateRecordWithOptionalParameters() throws IOException, JSONException {
        esbRequestHeadersMap.put("Action", "urn:getAggregateRecord");
        String apiEndPoint = connectorProperties.getProperty("serviceNowInstanceURL") + "/api/now/stats/" + connectorProperties.getProperty("tableName") + "?" + "&sysparm_count=" + connectorProperties.getProperty("sysparmCount") + "&sysparm_avg_fields=" + connectorProperties.getProperty("sysparmAvgFields") + "&sysparm_min_fields=" + connectorProperties.getProperty("sysparmMinFields") + "&sysparm_max_fields=" + connectorProperties.getProperty("sysparmMaxFields") + "&sysparm_sum_fields=" + connectorProperties.getProperty("sysparmSumFields");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "getAggregateRecord_optional.json");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getJSONObject("result").getJSONObject("stats").get("count"), apiRestResponse.getBody().getJSONObject("result").getJSONObject("stats").get("count"));
    }

    /**
     * Negative test case for getAggregateRecord method.
     */
    @Test(priority = 1, groups = {"wso2.esb"}, description = "servicenow {getAggregateRecord} integration test with negative case.")
    public void testgetAggregateRecordWithNegativeCase() throws IOException, JSONException {
        esbRequestHeadersMap.put("Action", "urn:getAggregateRecord");
        String apiEndPoint = connectorProperties.getProperty("serviceNowInstanceURL") + "/api/now/stats/" + connectorProperties.getProperty("tableNameNegative") + "&sysparm_count=" + connectorProperties.getProperty("sysparmCount");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "getAggregateRecord_negative.json");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), apiRestResponse.getHttpStatusCode());
    }
}