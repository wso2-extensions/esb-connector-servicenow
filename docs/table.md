# Working with Table API in ServiceNow

[[Overview]](#overview)  [[Operation details]](#operation-details)  [[Sample configuration]](#sample-configuration)

### Overview 

The following operations allow you to work with Table API. Click an operation name to see details on how to use it.
For a sample proxy service that illustrates how to work with tables, see [Sample configuration](#sample-configuration).

| Operation        | Description |
| ------------- |-------------|
| [getRecords](#retrieving-a-set-of-records-from-a-table)    | Retrieves set of records from table. |
| [getRecordById](#retrieves-a-record-according-to-the-sys_id-from-a-table)      | Retrieves a record according to the sys_id from table.|
| [postRecord](#insert-a-record-into-a-table)      |  Insert a record into a table.|
| [patchRecordById](#patch-a-record-from-table-by-specifying-sys_id-from-a-table)    | Patch a record from table by specifying sys_id. |
| [putRecordById](#put-a-record-from-table-by-specifying-sys_id-from-a-table)      | Put a record to table by specifying sys_id. |
| [deleteRecordById](#delete-a-record-from-table-by-specifying sys_id)   | Delete a record from table by specifying sys_id.|

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
    <KeyValuePairs>{$ctx:KeyValuePairs}</KeyValuePairs>
</servicenow.getRecords>
```
**Properties**
* tableName:  Name of the table you want to retrieve records.
* sysparmQuery: Optional Parameter. The encoded query provides support for order by and filter out records.
* sysparmDisplayValue: Optional Parameter. Data retrieval operation for reference and choice fields.
* sysparmFields: Optional Parameter. Comma-separated field names to return in the response.
* sysparmView: Optional Parameter. UI view to determine fields returned in the response.
* sysparmLimit: Optional Parameter. Limit to be applied on pagination. The default is 10000.  
* sysparmOffset: Optional Parameter. A number of records to skip before returning records.
* sysparmExcludeReferenceLink: Optional Parameter. Additional information provided for reference fields.
* sysparmReadReplicaCategory: Optional Parameter. The category value to read data from read replicas.
* KeyValuePairs: Optional Parameter. Can filter a query using key-value pairs where the key is the name of a field.  

**Sample request**

Following is a sample request that can be handled by the getRecords operation.

```json
{
  "serviceNowInstanceURL":"https://dev17686.service-now.com",
  "username":"admin",
  "password":"12345",
  "tableName":"incident",
  "sysparmQuery":"active=true",
  "sysparmDisplayValue":"true",
  "sysparmFields":"short_description,number,sys_id",
  "sysparmView":"short_description,number,sys_id",
  "sysparmLimit":"10",
  "sysparmOffset":"4",
  "sysparmExcludeReferenceLink":"",
  "sysparmReadReplicaCategory":"",
  "KeyValuePairs":"active=true"
}
```
**Sample response**

Given below is a sample response for the getRecords operation.

```json
{
    "result": [
        {
            "sys_import_state_comment": "wwww",
            "template_import_log": "",
            "sys_updated_on": "2019-04-15 15:09:40",
            "sys_class_name": "imp_computer",
            "cpu_count": "234",
            "manufacturer": "owner",
            "sys_target_sys_id": {
                "link": "https://dev62519.service-now.com/api/now/table/cmdb_ci_computer/201bea46dbb033004f281120399619c1",
                "value": "201bea46dbb033004f281120399619c1"
            },
            "sys_id": "601bea46dbb033004f2811203996199d",
            "sys_updated_by": "admin",
            "sys_created_on": "2019-04-15 15:09:39",
            "cpu_type": "",
            "sys_import_set": {
                "link": "https://dev62519.service-now.com/api/now/table/sys_import_set/681bea46dbb033004f2811203996199d",
                "value": "681bea46dbb033004f2811203996199d"
            },
            "operating_system": "ubunthu",
            "sys_transform_map": {
                "link": "https://dev62519.service-now.com/api/now/table/sys_transform_map/4e3548e2c0a80094002e0c1550d11a26",
                "value": "4e3548e2c0a80094002e0c1550d11a26"
            },
            "sys_created_by": "admin",
            "ram": "",
            "sys_import_row": "1",
            "sys_row_error": "",
            "sys_target_table": "cmdb_ci_computer",
            "sys_mod_count": "1",
            "cpu_speed": "",
            "serial_number": "282",
            "import_set_run": {
                "link": "https://dev62519.service-now.com/api/now/table/sys_import_set_run/e01bea46dbb033004f2811203996199f",
                "value": "e01bea46dbb033004f2811203996199f"
            },
            "model_id": "",
            "disk_space": "",
            "sys_tags": "",
            "sys_import_state": "inserted",
            "name": "Mac"
        }
    ]
}
```

**Related ServiceNow documentation**

[https://developer.salesforce.com/docs/atlas.en-us.api_rest.meta/api_rest/dome_sobject_create.htm](https://developer.salesforce.com/docs/atlas.en-us.api_rest.meta/api_rest/dome_sobject_create.htm)

#### Retrieves a record according to the sys_id from a table.

The getRecordById operation retrieves a record according to the sys_id from a table.

**getRecordById**
```xml
<servicenow.getRecordById>
     <tableName>{$ctx:tableName}</tableName>
     <sysId>{$ctx:sysId}</sysId>
     <sysparmDisplayValue>{$ctx:sysparmDisplayValue}</sysparmDisplayValue>
     <sysparmFields>{$ctx:sysparmFields}</sysparmFields>
     <sysparmView>{$ctx:sysparmView}</sysparmView>
     <sysparmExcludeReferenceLink>{$ctx:sysparmExcludeReferenceLink}</sysparmExcludeReferenceLink>
</servicenow.getRecordById>
```
**Properties**
* tableName:  Name of the table you want to retrieve a record.
* sysId: The Id which is automatically generated by ServiceNow. It is unique value for each record.
* sysparmDisplayValue: Optional Parameter. Data retrieval operation for reference and choice fields.
* sysparmFields: Optional Parameter. Comma-separated field names to return in the response.
* sysparmView: Optional Parameter. UI view to determine fields returned in the response.  
* sysparmExcludeReferenceLink: Optional Parameter. Additional information provided for reference fields.

**Sample request**

Following is a sample request that can be handled by the getRecordById operation.

```json
{
  "serviceNowInstanceURL":"https://dev17686.service-now.com",
  "username":"admin",
  "password":"12345",
  "tableName":"incident",
  "sysId":"46e57642a9fe1981000b96a5dca501ff",
  "sysparmDisplayValue":"true",
  "sysparmFields":"short_description,number,sys_id",
  "sysparmView":"short_description,number,sys_id",
  "sysparmExcludeReferenceLink":""
}
```
**Sample response**

Given below is a sample response for the getRecordById operation.

```json

```

**Related ServiceNow documentation**

https://developer.servicenow.com/app.do#!/rest_api_doc?v=jakarta&id=r_TableAPI-GETid

#### Insert a record into a table.

The postRecord operation insert a record with given attributes into a table.

**postRecord**
```xml
<servicenow.postRecord>
    <tableName>{$ctx:tableName}</tableName>
    <sysparmDisplayValue>{$ctx:sysparmDisplayValue}</sysparmDisplayValue>
    <sysparmFields>{$ctx:sysparmFields}</sysparmFields>
    <sysparmView>{$ctx:sysparmView}</sysparmView>
    <sysparmExcludeReferenceLink>{$ctx:sysparmExcludeReferenceLink}</sysparmExcludeReferenceLink>
    <sysparmInputDisplayValue>{$ctx:sysparmInputDisplayValue}</sysparmInputDisplayValue>
    <number>{$ctx:number}</number>
    <shortDescription>{$ctx:shortDescription}</shortDescription>
    <active>{$ctx:active}</active>
    <approval>{$ctx:approval}</approval>
    <category>{$ctx:category}</category>
    <contactType>{$ctx:contactType}</contactType>
    <apiColumns>{$ctx:apiColumns}</apiColumns>
 </servicenow.postRecord>
```
**Properties**

* tableName: Name of the table you want to retrieve a record.
* sysparmDisplayValue: Optional Parameter. Data retrieval operation for reference and choice fields.
* sysparmFields: Optional Parameter. Comma-separated field names to return in the response.
* sysparmView: Optional Parameter. UI view to determine fields returned in the response.  
* sysparmExcludeReferenceLink: Optional Parameter. Additional information provided for reference fields.
* sysparmInputDisplayValue:Data insert or update operations.
* number: This is an attribute in the table. Specify the row value for number.
* shortDescription: This is an attribute in the table. Specify the row value for short_description.
* active: This is an attribute in the table. Specify the row value for active. 
* approval: This is an attribute in the table. Specify the row value for approval.
* category: This is an attribute in the table. Specify the row value for category.
* contactType: This is an attribute in the table. Specify the row value for contactType.
* apiColumns: The attribute values of your table in your instance. 

**Sample request**

Following is a sample request that can be handled by the postRecord operation.

```json
{
  "serviceNowInstanceURL":"https://dev17686.service-now.com",
  "username":"admin",
  "password":"12345",
  "tableName":"incident",
  "sysparmDisplayValue":"true",
  "sysparmFields":"short_description,number,sys_id",
  "sysparmView":"short_description,number,sys_id",
  "sysparmExcludeReferenceLink":"",
  "sysparmInputDisplayValue":"true",
  "number":"42",
  "shortDescription":"Test of Post Method",
  "active":"true",
  "approval":"owner",
  "category":"inquiry",
  "contactType":"phone",
  "apiColumns": {"close_notes":"close","correlation_display":"abc"}
 }
```
**Sample response**

Given below is a sample response for the postRecord operation.

```json
{
  "result": {
    "upon_approval": "proceed",
    "location": "",
    "expected_start": "",
    "reopen_count": "0",
    "close_notes": "",
    "additional_assignee_list": "",
    "impact": "2",
    "urgency": "2",
    "correlation_id": "",
    "sys_tags": "",
    "sys_domain": {
      "link": "https://instance.service-now.com/api/now/table/sys_user_group/global",
      "value": "global"
    },
    "description": "",
    "group_list": "",
    "priority": "3",
    "delivery_plan": "",
    "sys_mod_count": "0",
    "work_notes_list": "",
    "business_service": "",
    "follow_up": "",
    "closed_at": "",
    "sla_due": "",
    "delivery_task": "",
    "sys_updated_on": "2016-01-22 14:28:24",
    "parent": "",
    "work_end": "",
    "number": "INC0010002",
    "closed_by": "",
    "work_start": "",
    "calendar_stc": "",
    "category": "inquiry",
    "business_duration": "",
    "incident_state": "1",
    "activity_due": "",
    "correlation_display": "",
    "company": "",
    "active": "true",
    "due_date": "",
    "assignment_group": {
      "link": "https://instance.service-now.com/api/now/table/sys_user_group/287ebd7da9fe198100f92cc8d1d2154e",
      "value": "287ebd7da9fe198100f92cc8d1d2154e"
    },
    "caller_id": "",
    "knowledge": "false",
    "made_sla": "true",
    "comments_and_work_notes": "",
    "parent_incident": "",
    "state": "1",
    "user_input": "",
    "sys_created_on": "2016-01-22 14:28:24",
    "approval_set": "",
    "reassignment_count": "0",
    "rfc": "",
    "child_incidents": "0",
    "opened_at": "2016-01-22 14:28:24",
    "short_description": "Unable to connect to office wifi",
    "order": "",
    "sys_updated_by": "admin",
    "resolved_by": "",
    "notify": "1",
    "upon_reject": "cancel",
    "approval_history": "",
    "problem_id": "",
    "work_notes": "",
    "calendar_duration": "",
    "close_code": "",
    "sys_id": "c537bae64f411200adf9f8e18110c76e",
    "approval": "not requested",
    "caused_by": "",
    "severity": "3",
    "sys_created_by": "admin",
    "resolved_at": "",
    "assigned_to": "",
    "business_stc": "",
    "wf_activity": "",
    "sys_domain_path": "/",
    "cmdb_ci": "",
    "opened_by": {
      "link": "https://instance.service-now.com/api/now/table/sys_user/6816f79cc0a8016401c5a33be04be441",
      "value": "6816f79cc0a8016401c5a33be04be441"
    },
    "subcategory": "",
    "rejection_goto": "",
    "sys_class_name": "incident",
    "watch_list": "",
    "time_worked": "",
    "contact_type": "phone",
    "escalation": "0",
    "comments": ""
  }
}
```

**Related ServiceNow documentation**

https://developer.servicenow.com/app.do#!/rest_api_doc?v=jakarta&id=r_TableAPI-POST

#### Patch a record from table by specifying sys_id from a table.

The patchRecordById operation patch a record which is already in table according to the sys_id from a table.

**update**
```xml
<servicenow.patchRecordById>
    <sysId>{$ctx:sysId}</sysId>
    <tableName>{$ctx:tableName}</tableName>
    <sysparmDisplayValue>{$ctx:sysparmDisplayValue}</sysparmDisplayValue>
    <sysparmFields>{$ctx:sysparmFields}</sysparmFields>
    <sysparmView>{$ctx:sysparmView}</sysparmView>
    <sysparmExcludeReferenceLink>{$ctx:sysparmExcludeReferenceLink}</sysparmExcludeReferenceLink>
    <sysparmInputDisplayValue>{$ctx:sysparmInputDisplayValue}</sysparmInputDisplayValue>
    <number>{$ctx:number}</number>
    <shortDescription>{$ctx:shortDescription}</shortDescription>
    <active>{$ctx:active}</active>
    <approval>{$ctx:approval}</approval>
    <category>{$ctx:category}</category>
    <contactType>{$ctx:contactType}</contactType>
    <apiColumns>{$ctx:apiColumns}</apiColumns>
 </servicenow.patchRecordById>
```

**Properties**

* tableName: Name of the table you want to retrieve a record.
* sysId: The Id which is automatically generated by ServiceNow. It is unique value for each record.
* sysparmDisplayValue: Optional Parameter. Data retrieval operation for reference and choice fields.
* sysparmFields: Optional Parameter. Comma-separated field names to return in the response.
* sysparmView: Optional Parameter. UI view to determine fields returned in the response.  
* sysparmExcludeReferenceLink: Optional Parameter. Additional information provided for reference fields.
* number: This is an attribute in the table. Specify the row value for number.
* shortDescription: This is an attribute in the table. Specify the row value for short_description.
* active: This is an attribute in the table. Specify the row value for active. 
* approval: This is an attribute in the table. Specify the row value for approval.
* category: This is an attribute in the table. Specify the row value for category.
* contactType: This is an attribute in the table. Specify the row value for contactType. 
* apiColumns: The attribute values of your table in your instance. 

**Sample request**

Following is a sample REST/JSON request that can be handled by the patchRecordById operation.

```json
{
  "serviceNowInstanceURL":"https://dev17686.service-now.com",
  "username":"admin",
  "password":"12345",
  "tableName":"incident",
  "sysId":"bfdc80fd4fce920021eeeb118110c74e",
  "sysparmDisplayValue":"true",
  "sysparmFields":"short_description,number,sys_id",
  "sysparmView":"short_description,number,sys_id",
  "sysparmExcludeReferenceLink":"",
  "sysparmInputDisplayValue":"true",
  "number":"42",
  "shortDescription":"Test of Post Method",
  "active":"true",
  "approval":"owner",
  "category":"inquiry",
  "contactType":"phone",
  "apiColumns": {"close_notes":"close","correlation_display":"abc"}
}
```
**Sample response**

Given below is a sample response for the patchRecordById operation.

```json
{
  "result": {
    "upon_approval": "proceed",
    "location": {
      "link": "https://instance.service-now.com/api/now/table/cmn_location/108752c8c611227501d4ab0e392ba97f",
      "value": "108752c8c611227501d4ab0e392ba97f"
    },
    "expected_start": "",
    "reopen_count": "",
    "close_notes": "",
    "additional_assignee_list": "",
    "impact": "1",
    "urgency": "1",
    "correlation_id": "",
    "sys_tags": "",
    "sys_domain": {
      "link": "https://instance.service-now.com/api/now/table/sys_user_group/global",
      "value": "global"
    },
    "description": "",
    "group_list": "",
    "priority": "1",
    "delivery_plan": "",
    "sys_mod_count": "7",
    "work_notes_list": "",
    "business_service": "",
    "follow_up": "",
    "closed_at": "",
    "sla_due": "2017-07-05 05:58:24",
    "delivery_task": "",
    "sys_updated_on": "2016-01-22 14:12:37",
    "parent": "",
    "work_end": "",
    "number": "INC0000050",
    "closed_by": "",
    "work_start": "",
    "calendar_stc": "",
    "category": "hardware",
    "business_duration": "",
    "incident_state": "2",
    "activity_due": "2016-01-22 16:12:37",
    "correlation_display": "",
    "company": {
      "link": "https://instance.service-now.com/api/now/table/core_company/31bea3d53790200044e0bfc8bcbe5dec",
      "value": "31bea3d53790200044e0bfc8bcbe5dec"
    },
    "active": "true",
    "due_date": "",
    "assignment_group": {
      "link": "https://instance.service-now.com/api/now/table/sys_user_group/8a5055c9c61122780043563ef53438e3",
      "value": "8a5055c9c61122780043563ef53438e3"
    },
    "caller_id": {
      "link": "https://instance.service-now.com/api/now/table/sys_user/5b7c200d0a640069006b3845b5d0fa7c",
      "value": "5b7c200d0a640069006b3845b5d0fa7c"
    },
    "knowledge": "false",
    "made_sla": "true",
    "comments_and_work_notes": "",
    "parent_incident": "",
    "state": "2",
    "user_input": "",
    "sys_created_on": "2015-11-02 18:05:40",
    "approval_set": "",
    "reassignment_count": "0",
    "rfc": "",
    "child_incidents": "",
    "opened_at": "2015-11-02 21:58:24",
    "short_description": "Can't access Exchange server - is it down?",
    "order": "",
    "sys_updated_by": "admin",
    "resolved_by": "",
    "notify": "1",
    "upon_reject": "cancel",
    "approval_history": "",
    "problem_id": "",
    "work_notes": "",
    "calendar_duration": "",
    "close_code": "",
    "sys_id": "ef43c6d40a0a0b5700c77f9bf387afe3",
    "approval": "not requested",
    "caused_by": "",
    "severity": "3",
    "sys_created_by": "glide.maint",
    "resolved_at": "",
    "assigned_to": {
      "link": "https://instance.service-now.com/api/now/table/sys_user/681b365ec0a80164000fb0b05854a0cd",
      "value": "681b365ec0a80164000fb0b05854a0cd"
    },
    "business_stc": "",
    "wf_activity": "",
    "sys_domain_path": "/",
    "cmdb_ci": {
      "link": "https://instance.service-now.com/api/now/table/cmdb_ci/281190e3c0a8000b003f593aa3f20ca6",
      "value": "281190e3c0a8000b003f593aa3f20ca6"
    },
    "opened_by": {
      "link": "https://instance.service-now.com/api/now/table/sys_user/glide.maint",
      "value": "glide.maint"
    },
    "subcategory": "",
    "rejection_goto": "",
    "sys_class_name": "incident",
    "watch_list": "",
    "time_worked": "",
    "contact_type": "phone",
    "escalation": "3",
    "comments": ""
  }
}
```

**Related ServiceNow documentation**

https://developer.servicenow.com/app.do#!/rest_api_doc?v=jakarta&id=r_TableAPI-PATCH

#### Put a record from table by specifying sys_id from a table.

The putRecordById operation put a record which is already in table according to the sys_id from a table.

**putRecordById**
```xml
<servicenow.putRecordById>
  <tableName>{$ctx:tableName}</tableName>
  <sysId>{$ctx:sysId}</sysId>
  <sysparmDisplayValue>{$ctx:sysparmDisplayValue}</sysparmDisplayValue>
  <sysparmFields>{$ctx:sysparmFields}</sysparmFields>
  <sysparmView>{$ctx:sysparmView}</sysparmView>
  <sysparmExcludeReferenceLink>{$ctx:sysparmExcludeReferenceLink}</sysparmExcludeReferenceLink>
  <sysparmInputDisplayValue>{$ctx:sysparmInputDisplayValue}</sysparmInputDisplayValue>
  <number>{$ctx:number}</number>
  <shortDescription>{$ctx:shortDescription}</shortDescription>
  <active>{$ctx:active}</active>
  <approval>{$ctx:approval}</approval>
  <category>{$ctx:category}</category>
  <contactType>{$ctx:contactType}</contactType>
  <apiColumns>{$ctx:apiColumns}</apiColumns>
</servicenow.putRecordById>
```
**Properties**

* tableName: Name of the table you want to retrieve a record.
* sysId: The Id which is automatically generated by ServiceNow. It is unique value for each record.
* sysparmDisplayValue: Optional Parameter. Data retrieval operation for reference and choice fields.
* sysparmFields: Optional Parameter. Comma-separated field names to return in the response.
* sysparmView: Optional Parameter. UI view to determine fields returned in the response.  
* sysparmExcludeReferenceLink: Optional Parameter. Additional information provided for reference fields.
* number: This is an attribute in the table. Specify the row value for number.
* shortDescription: This is an attribute in the table. Specify the row value for short_description.
* active: This is an attribute in the table. Specify the row value for active. 
* approval: This is an attribute in the table. Specify the row value for approval.
* category: This is an attribute in the table. Specify the row value for category.
* contactType: This is an attribute in the table. Specify the row value for contactType. 
* apiColumns: The attribute values of your table in your instance. 

**Sample request**

Following is a sample request that can be handled by the putRecordById operation.

```json
{
  "serviceNowInstanceURL":"https://dev17686.service-now.com",
  "username":"admin",
  "password":"12345",
  "tableName":"incident",
  "sysId":"bfdc80fd4fce920021eeeb118110c74e",
  "sysparmDisplayValue":"true",
  "sysparmFields":"short_description,number,sys_id",
  "sysparmView":"short_description,number,sys_id",
  "sysparmExcludeReferenceLink":"",
  "sysparmInputDisplayValue":"true",
  "number":"42",
  "shortDescription":"Test of Post Method",
  "active":"true",
  "approval":"owner",
  "category":"inquiry",
  "contactType":"phone",
  "apiColumns": {"close_notes":"close","correlation_display":"abc"}
}
```
**Sample response**

Given below is a sample response for the putRecordById operation.

```json
{
  "result": {
    "upon_approval": "proceed",
    "location": {
      "link": "https://instance.service-now.com/api/now/table/cmn_location/108752c8c611227501d4ab0e392ba97f",
      "value": "108752c8c611227501d4ab0e392ba97f"
    },
    "expected_start": "",
    "reopen_count": "",
    "close_notes": "",
    "additional_assignee_list": "",
    "impact": "1",
    "urgency": "1",
    "correlation_id": "",
    "sys_tags": "",
    "sys_domain": {
      "link": "https://instance.service-now.com/api/now/table/sys_user_group/global",
      "value": "global"
    },
    "description": "",
    "group_list": "",
    "priority": "1",
    "delivery_plan": "",
    "sys_mod_count": "7",
    "work_notes_list": "",
    "business_service": "",
    "follow_up": "",
    "closed_at": "",
    "sla_due": "2017-07-05 05:58:24",
    "delivery_task": "",
    "sys_updated_on": "2016-01-22 14:12:37",
    "parent": "",
    "work_end": "",
    "number": "INC0000050",
    "closed_by": "",
    "work_start": "",
    "calendar_stc": "",
    "category": "hardware",
    "business_duration": "",
    "incident_state": "2",
    "activity_due": "2016-01-22 16:12:37",
    "correlation_display": "",
    "company": {
      "link": "https://instance.service-now.com/api/now/table/core_company/31bea3d53790200044e0bfc8bcbe5dec",
      "value": "31bea3d53790200044e0bfc8bcbe5dec"
    },
    "active": "true",
    "due_date": "",
    "assignment_group": {
      "link": "https://instance.service-now.com/api/now/table/sys_user_group/8a5055c9c61122780043563ef53438e3",
      "value": "8a5055c9c61122780043563ef53438e3"
    },
    "caller_id": {
      "link": "https://instance.service-now.com/api/now/table/sys_user/5b7c200d0a640069006b3845b5d0fa7c",
      "value": "5b7c200d0a640069006b3845b5d0fa7c"
    },
    "knowledge": "false",
    "made_sla": "true",
    "comments_and_work_notes": "",
    "parent_incident": "",
    "state": "2",
    "user_input": "",
    "sys_created_on": "2015-11-02 18:05:40",
    "approval_set": "",
    "reassignment_count": "0",
    "rfc": "",
    "child_incidents": "",
    "opened_at": "2015-11-02 21:58:24",
    "short_description": "Can't access Exchange server - is it down?",
    "order": "",
    "sys_updated_by": "admin",
    "resolved_by": "",
    "notify": "1",
    "upon_reject": "cancel",
    "approval_history": "",
    "problem_id": "",
    "work_notes": "",
    "calendar_duration": "",
    "close_code": "",
    "sys_id": "ef43c6d40a0a0b5700c77f9bf387afe3",
    "approval": "not requested",
    "caused_by": "",
    "severity": "3",
    "sys_created_by": "glide.maint",
    "resolved_at": "",
    "assigned_to": {
      "link": "https://instance.service-now.com/api/now/table/sys_user/681b365ec0a80164000fb0b05854a0cd",
      "value": "681b365ec0a80164000fb0b05854a0cd"
    },
    "business_stc": "",
    "wf_activity": "",
    "sys_domain_path": "/",
    "cmdb_ci": {
      "link": "https://instance.service-now.com/api/now/table/cmdb_ci/281190e3c0a8000b003f593aa3f20ca6",
      "value": "281190e3c0a8000b003f593aa3f20ca6"
    },
    "opened_by": {
      "link": "https://instance.service-now.com/api/now/table/sys_user/glide.maint",
      "value": "glide.maint"
    },
    "subcategory": "",
    "rejection_goto": "",
    "sys_class_name": "incident",
    "watch_list": "",
    "time_worked": "",
    "contact_type": "phone",
    "escalation": "3",
    "comments": ""
  }
}
```

**Related ServiceNow documentation**

https://developer.servicenow.com/app.do#!/rest_api_doc?v=jakarta&id=r_TableAPI-PUT

#### Delete a record from table by specifying sys_id.

The deleteRecordById operation retrieves a record according to the sys_id from a table.

**deleteRecordById**
```xml
<servicenow.deleteRecordById>
  <tableName>{$ctx:tableName}</tableName>
  <sysId>{$ctx:sysId}</sysId>
</servicenow.deleteRecordById>
```
**Properties**
* tableName: Name of the table you want to retrieve a record.
* sysId: The Id which is automatically generated by ServiceNow. It is unique value for each record.

**Sample request**

Following is a sample request that can be handled by the deleteRecordById operation.

```json
{
  "serviceNowInstanceURL":"https://dev17686.service-now.com",
  "username":"admin",
  "password":"12345",
  "tableName":"incident",
  "sysId":"a54ea0844f4e520021eeeb118110c75e"
 
}
```

**Related ServiceNow documentation**

https://developer.servicenow.com/app.do#!/rest_api_doc?v=jakarta&id=r_TableAPI-DELETE

### Sample configuration

Following example illustrates how to connect to ServiceNow with the init operation and getRecords operation.

1. Create a sample proxy as below :
```xml
<proxy xmlns="http://ws.apache.org/ns/synapse"
       name="getRecords"
       transports="https,http"
       statistics="disable"
       trace="disable"
       startOnLoad="true">
   <target>
      <inSequence>
         <property name="serviceNowInstanceURL" expression="json-eval($.serviceNowInstanceURL)"/>
         <property name="username" expression="json-eval($.username)"/>
         <property name="password" expression="json-eval($.password)"/>
         <property name="tableName" expression="json-eval($.tableName)"/>
         <property name="sysparmQuery" expression="json-eval($.sysparmQuery)"/>
         <property name="sysparmDisplayValue"
                   expression="json-eval($.sysparmDisplayValue)"/>
         <property name="sysparmFields" expression="json-eval($.sysparmFields)"/>
         <property name="sysparmView" expression="json-eval($.sysparmView)"/>
         <property name="sysparmLimit" expression="json-eval($.sysparmLimit)"/>
         <property name="sysparmOffset" expression="json-eval($.sysparmOffset)"/>
         <property name="sysparmExcludeReferenceLink" expression="json-eval($.sysparmExcludeReferenceLink)"/>
         <property name="sysparmReadReplicaCategory"
                   expression="json-eval($.sysparmReadReplicaCategory)"/>
         <property name="KeyValuePairs" expression="json-eval($.KeyValuePairs)"/>
         <servicenow.init>
            <serviceNowInstanceURL>{$ctx:serviceNowInstanceURL}</serviceNowInstanceURL>
            <username>{$ctx:username}</username>
            <password>{$ctx:password}</password>
         </servicenow.init>
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
            <KeyValuePairs>{$ctx:KeyValuePairs}</KeyValuePairs>
         </servicenow.getRecords>
         <respond/>
      </inSequence>
      <outSequence>
         <log/>
         <send/>
      </outSequence>
   </target>
   <description/>
</proxy>
```

2. Create a JSON file named getRecords.json and add the configurations given below:

```json
{
  "serviceNowInstanceURL":"https://dev17686.service-now.com",
  "username":"admin",
  "password":"12345",
  "tableName":"incident",
  "sysparmQuery":"active=true",
  "sysparmDisplayValue":"true",
  "sysparmFields":"short_description,number,sys_id",
  "sysparmView":"short_description,number,sys_id",
  "sysparmLimit":"10",
  "sysparmOffset":"4",
  "sysparmExcludeReferenceLink":"",
  "sysparmReadReplicaCategory":"",
  "KeyValuePairs":"active=true"
}
```
3. Replace the credentials with your values.

4. Execute the following curl command:

```bash
curl http://localhost:8280/services/getRecords -H "Content-Type: application/json" -d @getRecords.json
```
5. ServiceNow returns a JSON response similar to the one shown below:
 
```json
{
    "result": [
        {
            "sys_import_state_comment": "wwww",
            "template_import_log": "",
            "sys_updated_on": "2019-04-15 15:09:40",
            "sys_class_name": "imp_computer",
            "cpu_count": "234",
            "manufacturer": "owner",
            "sys_target_sys_id": {
                "link": "https://dev62519.service-now.com/api/now/table/cmdb_ci_computer/201bea46dbb033004f281120399619c1",
                "value": "201bea46dbb033004f281120399619c1"
            },
            "sys_id": "601bea46dbb033004f2811203996199d",
            "sys_updated_by": "admin",
            "sys_created_on": "2019-04-15 15:09:39",
            "cpu_type": "",
            "sys_import_set": {
                "link": "https://dev62519.service-now.com/api/now/table/sys_import_set/681bea46dbb033004f2811203996199d",
                "value": "681bea46dbb033004f2811203996199d"
            },
            "operating_system": "ubunthu",
            "sys_transform_map": {
                "link": "https://dev62519.service-now.com/api/now/table/sys_transform_map/4e3548e2c0a80094002e0c1550d11a26",
                "value": "4e3548e2c0a80094002e0c1550d11a26"
            },
            "sys_created_by": "admin",
            "ram": "",
            "sys_import_row": "1",
            "sys_row_error": "",
            "sys_target_table": "cmdb_ci_computer",
            "sys_mod_count": "1",
            "cpu_speed": "",
            "serial_number": "282",
            "import_set_run": {
                "link": "https://dev62519.service-now.com/api/now/table/sys_import_set_run/e01bea46dbb033004f2811203996199f",
                "value": "e01bea46dbb033004f2811203996199f"
            },
            "model_id": "",
            "disk_space": "",
            "sys_tags": "",
            "sys_import_state": "inserted",
            "name": "Mac"
        }
    ]
}
```
