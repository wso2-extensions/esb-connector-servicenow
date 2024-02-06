# Working with Table API in ServiceNow

[[Overview]](#overview)  [[Operation details]](#operation-details)  [[Sample configuration]](#sample-configuration)

### Overview 

The following operations allow you to work with Table API. Click an operation name to see details on how to use it.
For a sample REST API that illustrates how to work with tables, see [Sample configuration](#sample-configuration).

| Operation        | Description |
| ------------- |-------------|
| [getRecords](#Retrieving-a-set-of-records-from-a-table)    | Retrieves set of records from table. |
| [getRecordById](#Retrieves-record-using-sysId)      | Retrieves a record from the table using sysId.|
| [postRecord](#Insert-a-record-into-a-table)      |  Insert a record into a table.|
| [patchRecordById](#Patch-a-record-from-table-using-sysId-of-the-record)    | Patch a record from table by specifying sysId. |
| [putRecordById](#Update-a-record-in-a-table-using-sysId-of-the-record)      | Put a record to table by specifying sysId. |
| [deleteRecordById](#Delete-a-record-from-table-by-specifying-sysId)   | Delete a record from table by specifying sysId.|

### Operation details

This section provides more details on each of the operations related to  table API.

#### Retrieving a set of Records from a table
The getRecords operation retrieves set of records from a table.

**getRecords**
```xml
<servicenow.getRecords>
    <tableName>{$ctx:tableName}</tableName>
    <sysparmQuery>{$ctx:sysparmQuery}</sysparmQuery>
    <sysparmDisplayValue>{$ctx:sysparmDisplayValue}</sysparmDisplayValue>
    <sysparmFields>{$ctx:sysparmFields}</sysparmFields>
    <sysparmView>{$ctx:sysparmView}</sysparmView>
    <sysparmLimit>{$ctx:sysparmLimit}</sysparmLimit>
    <sysparmOffset>{$ctx:sysparmOffset}</sysparmOffset>
    <sysparmExcludeReferenceLink>{$ctx:sysparmExcludeReferenceLink}</sysparmExcludeReferenceLink>
    <sysparmReadReplicaCategory>{$ctx:sysparmReadReplicaCategory}</sysparmReadReplicaCategory>
    <keyValuePairs>{$ctx:keyValuePairs}</keyValuePairs>
    <sysparmSupPaginationHeader>{$ctx:sysparmPaginationHeader}</sysparmSupPaginationHeader>
    <sysparmQueryCategory>{$ctx:sysparmQueryCategory}</sysparmQueryCategory>
    <apiVersion>{$ctx:spiVersion}</apiVersion>
</servicenow.getRecords>
```
**Properties**
* tableName:  Name of the data retrieving table.
* sysparmQuery: Filter rerturning records using query parameters.
* sysparmDisplayValue: Determines the type of data returned ( actual values from the database or display values of the fields).
* sysparmFields: Comma-separated list of fields to return in the response.
* sysparmView: UI view for which to render the data.
* sysparmLimit: Maximum number of records to return.  
* sysparmOffset: Starting record index for which to begin retrieving records.
* sysparmExcludeReferenceLink: Exclude or include Table API links for reference fields.
* sysparmSupPaginationHeader: Remove or keep the Link header from the response.
* keyValuePairs: Name-value pairs to use to filter the result set.  
* sysparmQueryNoDomain: Restrict the record search to only the domains the logged in user is configured.
* sysparmQueryCategory: Name of the category to use for queries.
* apiVersion: Optional version to be embeded in the URL.

**Sample request**

Following is a sample request that can be handled by the getRecords operation.

```json
{
  "tableName":"problem",
  "sysparmDisplayValue":"true",
  "sysparmFields":"short_description,number,sys_id",
  "sysparmView":"short_description,number,sys_id",
  "sysparmLimit":"10",
  "sysparmOffset":"4",
  "sysparmExcludeReferenceLink":"true",
  "keyValuePairs":{"active":"true"}
}
```
**Sample response**

Given below is a sample response for the getRecords operation.

```json
{
    "result": [
        {
            "short_description": "Boot with shift key held down",
            "number": "PRB0000004",
            "sys_id": "471a5450a9fe198101f2054169e75bf4"
        },
        {
            "short_description": "Unable to send or receive emails.",
            "number": "PRB0007601",
            "sys_id": "62304320731823002728660c4cf6a7e8"
        },
        {
            "short_description": "Unable to connect to Wifi",
            "number": "PRB0001002",
            "sys_id": "6632130c730123002728660c4cf6a734"
        },
        {
            "short_description": "",
            "number": "PRB0040003",
            "sys_id": "81ad543b933b7110e1dd70718bba1067"
        },
        {
            "short_description": "Unable to connect to the VPN",
            "number": "PRB0001000",
            "sys_id": "9ae30b6d730123002728660c4cf6a736"
        },
        {
            "short_description": "Router Down",
            "number": "PRB0000007",
            "sys_id": "9d3a266ac6112287004e37fb2ceb0133"
        },
        {
            "short_description": "2nd Floor File Server Short on Space\n\t\t",
            "number": "PRB0000002",
            "sys_id": "9d3d3b85c61122870138f3d1472bba01"
        },
        {
            "short_description": "Oracle Down",
            "number": "PRB0000010",
            "sys_id": "9d4f87a3c6112287010ed0a5ccdcba04"
        },
        {
            "short_description": "Issue in connecting to internet using modem.",
            "number": "PRB0001001",
            "sys_id": "a1e0f770734123002728660c4cf6a745"
        },
        {
            "short_description": "Can't access SFA software",
            "number": "PRB0000006",
            "sys_id": "a9e4890bc6112276003d7a5a5c774a74"
        }
    ]
}
```

**Related ServiceNow documentation**
https://developer.servicenow.com/dev.do#!/reference/api/utah/rest/c_TableAPI#table-GET.

#### Retrieves record using sysId

The getRecordById operation retrieves a record according to the sysId from a table.

**getRecordById**
```xml
<servicenow.getRecordById>
     <tableName>{$ctx:tableName}</tableName>
     <sysId>{$ctx:sysId}</sysId>
     <sysparmDisplayValue>{$ctx:sysparmDisplayValue}</sysparmDisplayValue>
     <sysparmFields>{$ctx:sysparmFields}</sysparmFields>
     <sysparmView>{$ctx:sysparmView}</sysparmView>
     <sysparmExcludeReferenceLink>{$ctx:sysparmExcludeReferenceLink}</sysparmExcludeReferenceLink>
     <sysparmQueryNoDomain>{$ctx:sysparmQueryNoDomain}</sysparmQueryNoDomain>
     <keyValuePairs>{$ctx:keyValuePairs}</keyValuePairs>
     <sysparmQuery>{$ctx:sysparmQuery}</sysparmQuery>
     <apiVersion>{$ctx:apiVersion}</apiVersion>
</servicenow.getRecordById>
```
**Properties**
* tableName:  Name of the data retrieving table.
* sysId: The unique id which is automatically generated by ServiceNow for each record.
* sysparmDisplayValue: Determines the type of data returned ( actual values from the database or display values of the fields).
* sysparmFields: Comma-separated field names to return in the response.
* sysparmView: UI view for which to render the data.  
* sysparmExcludeReferenceLink: Exclude or include Table API links for reference fields.
* keyValuePairs: Name-value pairs to use to filter the result set.  
* sysparmQueryNoDomain: Restrict the record search to only the domains the logged in user is configured.
* apiVersion: Optional version to be embeded in the URL.

**Sample request**

Following is a sample request that can be handled by the getRecordById operation.

```json
{
  "tableName":"problem",
  "sysId":"16f8af3793840210e1dd70718bba10e1",
  "sysparmDisplayValue":"true",
  "sysparmFields":"short_description,number,sys_id",
  "sysparmView":"short_description,number,sys_id",
  "sysparmExcludeReferenceLink":"true"
}
```
**Sample response**

Given below is a sample response for the getRecordById operation.

```json
{
    "result": {
        "short_description": "Newly added problem record",
        "number": "PRB0040012",
        "sys_id": "16f8af3793840210e1dd70718bba10e1"
    }
}
```

**Related ServiceNow documentation**
https://developer.servicenow.com/dev.do#!/reference/api/utah/rest/c_TableAPI#table-GET-id

#### Insert a record into a table

The postRecord operation insert a record with given attributes into a table.

**postRecord**
```xml
<servicenow.postRecord>
  <tableName>problem</tableName>
  <sysparmDisplayValue>{json-eval($.sysparmDisplayValue)}</sysparmDisplayValue>
  <sysparmFields>{json-eval($.sysparmFields)}</sysparmFields>
  <sysparmView>{json-eval($.sysparmView)}</sysparmView>
  <sysparmExcludeReferenceLink>{json-eval($.sysparmExcludeReferenceLink)}<sysparmExcludeReferenceLink>
  <sysparmInputDisplayValue>{json-eval($.sysparmInputDisplayValue)}</sysparmInputDisplayValue>
  <apiVersion>{json-eval($.apiVersion)}</apiVersion>
</servicenow.postRecord>
```
**Properties**

* tableName: Name of the table you want to retrieve a record.
* sysparmDisplayValue: Determines the type of data returned ( actual values from the database or display values of the fields).
* sysparmFields: Comma-separated field names to return in the response.
* sysparmView: UI view for which to render the data.  
* sysparmExcludeReferenceLink: Exclude or include Table API links for reference fields.
* sysparmInputDisplayValue: Flag that indicates whether to set field values using the display value or the actual value.
* apiVersion: API version to be embedded in the URL.


**Sample request**

Following is a sample request that can be handled by the postRecord operation.

```json
{
    "short_description":"Newly added problem record",
    "urgency": "3",
    "approval": "not requested",
    "category": "hardware",
    "sysparmExcludeReferenceLink":"true",
    "sysparmFields":"number,sys_id,short_description,urgency,approval,category"

}

```
**Sample response**

Given below is a sample response for the postRecord operation.

```json
{
    "result": {
        "number": "PRB0040013",
        "sys_id": "a90aebb793840210e1dd70718bba1040",
        "short_description": "Newly added problem record",
        "urgency": "3",
        "approval": "not requested",
        "category": "hardware"
    }
}
```

**Related ServiceNow documentation**

https://developer.servicenow.com/dev.do#!/reference/api/utah/rest/c_TableAPI#table-POST

#### Patch a record from table using sysId of the record

The patchRecordById operation patches a record which is already in table using the sysId.

**patchRecordById**
```xml
<servicenow.patchRecordById>
    <sysId>{$ctx:sysId}</sysId>
    <tableName>{$ctx:tableName}</tableName>
    <apiVersion>{$ctx:apiVersion}</apiVersion>
    <sysparmDisplayValue>{$ctx:sysparmDisplayValue}</sysparmDisplayValue>
    <sysparmFields>{$ctx:sysparmFields}</sysparmFields>
    <sysparmView>{$ctx:sysparmView}</sysparmView>
    <sysparmExcludeReferenceLink>{$ctx:sysparmExcludeReferenceLink}</sysparmExcludeReferenceLink>
    <sysparmInputDisplayValue>{$ctx:sysparmInputDisplayValue}</sysparmInputDisplayValue>
   <sysparmQueryNoDomain>{$ctx:sysparmQueryNoDomain}</sysparmQueryNoDomain>
 </servicenow.patchRecordById>
```

**Properties**
* tableName: Name of the table you want to retrieve a record.
* apiVersion: API version to be embedded in the URL.
* sysparmDisplayValue: Determines the type of data returned ( actual values from the database or display values of the fields).
* sysparmFields: Comma-separated field names to return in the response.
* sysparmView: UI view for which to render the data.  
* sysparmExcludeReferenceLink: Exclude or include Table API links for reference fields.
* sysparmInputDisplayValue: Flag that indicates whether to set field values using the display value or the actual value
* sysparmQueryNoDomain: Restrict the record search to only the domains the logged in user is configured.

 

**Sample request**

Following is a sample request that can be handled by the patchRecordById operation.

```json
{
    "sysId":"e4265bd593040610e1dd70718bba1059",
    "short_description":"Patched problem record",
    "urgency":"1",
    "sysparmFields":"short_description,urgency"
}
```
**Sample response**

Given below is a sample response for the patchRecordById operation.

```json
{
    "result": {
        "short_description": "Patched problem record",
        "urgency": "1"
    }
}
```

**Related ServiceNow documentation**

https://developer.servicenow.com/dev.do#!/reference/api/utah/rest/c_TableAPI#table-PATCH

#### Update a record in a table using sysId of the record

The putRecordById operation put a record which is already in table according to the sys_id from a table.

**putRecordById**
```xml
<servicenow.putRecordById>
    <sysId>{$ctx:sysId}</sysId>
    <tableName>{$ctx:tableName}</tableName>
    <apiVersion>{$ctx:apiVersion}</apiVersion>
    <sysparmDisplayValue>{$ctx:sysparmDisplayValue}</sysparmDisplayValue>
    <sysparmFields>{$ctx:sysparmFields}</sysparmFields>
    <sysparmView>{$ctx:sysparmView}</sysparmView>
    <sysparmExcludeReferenceLink>{$ctx:sysparmExcludeReferenceLink}</sysparmExcludeReferenceLink>
    <sysparmInputDisplayValue>{$ctx:sysparmInputDisplayValue}</sysparmInputDisplayValue>
   <sysparmQueryNoDomain>{$ctx:sysparmQueryNoDomain}</sysparmQueryNoDomain>
 </servicenow.putRecordById>
```
**Properties**

* tableName: Name of the table you want to retrieve a record.
* apiVersion: API version to be embedded in the URL.
* sysparmDisplayValue: Determines the type of data returned ( actual values from the database or display values of the fields).
* sysparmFields: Comma-separated field names to return in the response.
* sysparmView: UI view for which to render the data.  
* sysparmExcludeReferenceLink: Exclude or include Table API links for reference fields.
* sysparmInputDisplayValue: Flag that indicates whether to set field values using the display value or the actual value
* sysparmQueryNoDomain: Restrict the record search to only the domains the logged in user is configured.

**Sample request**

Following is a sample request that can be handled by the putRecordById operation.

```json
{
  "sysId":"e4265bd593040610e1dd70718bba1059",
  "sysparmDisplayValue":"true",
  "sysparmFields":"short_description,number,sys_id",
  "sysparmView":"short_description,number,sys_id",
  "sysparmExcludeReferenceLink":"",
  "sysparmInputDisplayValue":"true",
  "number":"42",
  "short_description":"Test of Post Method",
  "active":"true",
  "approval":"owner",
  "category":"inquiry",
  "contact_type":"phone",
  "urgency":"2"
}
```
**Sample response**

Given below is a sample response for the putRecordById operation.

```json
{
    "result": {
        "short_description": "Test of Post Method",
        "number": "PRB0040006",
        "sys_id": "e4265bd593040610e1dd70718bba1059"
    }
}
```

**Related ServiceNow documentation**

https://developer.servicenow.com/dev.do#!/reference/api/utah/rest/c_TableAPI#table-PUT

#### Delete a record from table by specifying sysId

The delete record using sysId of the record.

**deleteRecordById**
```xml
<servicenow.deleteRecordById>
  <tableName>{$ctx:tableName}</tableName>
  <sysId>{$ctx:sysId}</sysId>
  <sysparmQueryNoDomain>{$ctx:sysparmQueryNoDomain}</sysparmQueryNoDomain>
  <apiVersion>{$ctx:apiVersion}</apiVersion>
</servicenow.deleteRecordById>
```
**Properties**
* tableName: Name of the table where deleting record is in.
* sysId: Id of the record to be deleted.
* sysparmQueryNoDomain: Restrict the record search to only the domains the logged in user is configured.
* apiVersion: API version to be embedded in the URL.


**Sample request**

Following is a sample request that can be handled by the deleteRecordById operation.

```json
{

  "tableName":"incident",
  "sysId":"a54ea0844f4e520021eeeb118110c75e"
 
}
```

**Related ServiceNow documentation**

https://developer.servicenow.com/dev.do#!/reference/api/utah/rest/c_TableAPI#table-DELETE

### Sample configuration

Following example illustrates how to connect to ServiceNow with the init operation and getRecords .

1. Create a sample REST API as below :
```xml
<?xml version="1.0" encoding="UTF-8"?>
<api context="/table" name="TableAPI" xmlns="http://ws.apache.org/ns/synapse">
    <resource methods="POST" uri-template="/readrecord">
        <inSequence>
            <servicenow.init>
                <serviceNowInstanceURL>https://your-instance.service-now.com</serviceNowInstanceURL>
                <username>admin</username>
                <password>your-password</password>
            </servicenow.init>
            <servicenow.getRecords>
                <tableName>{json-eval($.tableName)}</tableName>
                <sysparmQuery>{json-eval($.sysparmQuery)}</sysparmQuery>
                <sysparmDisplayValue>{json-eval($.sysparmDisplayValue)}</sysparmDisplayValue>
                <sysparmFields>{json-eval($.sysparmFields)}</sysparmFields>
                <sysparmView>{json-eval($.sysparmView)}</sysparmView>
                <sysparmLimit>{json-eval($.sysparmLimit)}</sysparmLimit>
                <sysparmOffset>{json-eval($.sysparmOffset)}</sysparmOffset>
                <sysparmExcludeReferenceLink>{json-eval($.sysparmExcludeReferenceLink)}</sysparmExcludeReferenceLink>
                <sysparmSupPaginationHeader>{json-eval($.sysparmSupPaginationHeader)}</sysparmSupPaginationHeader>
                <keyValuePairs>{json-eval($.keyValuePairs)}</keyValuePairs>
                <sysparmQueryNoDomain>{json-eval($.sysparmQueryNoDomain)}</sysparmQueryNoDomain>
                <sysparmQueryCategory>{json-eval($.sysparmQueryCategory)}</sysparmQueryCategory>
                <sysparmNoCount>{json-eval($.sysparmNoCount)}</sysparmNoCount>
                <apiVersion>{json-eval($.apiVersion)}</apiVersion>
            </servicenow.getRecords>
            <respond/>
        </inSequence>
        <outSequence/>
        <faultSequence/>
    </resource>
  
</api>
```

2. Create a JSON file named getRecords.json and add the configurations given below:

```json
{
  "tableName":"problem",
  "sysparmDisplayValue":"true",
  "sysparmFields":"short_description,number,sys_id",
  "sysparmView":"short_description,number,sys_id",
  "sysparmLimit":"10",
  "sysparmOffset":"4",
  "sysparmExcludeReferenceLink":"true",
  "keyValuePairs":{"active":"true"}
}
```
3. Replace the credentials with the the own values.

4. Execute the following curl command:

```bash
curl http://localhost:8290/table/readrecord -H "Content-Type: application/json" -d @getRecords.json
```
5. ServiceNow returns a JSON response similar to the one shown in the [section](#retrieving-a-set-of-records-from-a-table) above.
 
```
