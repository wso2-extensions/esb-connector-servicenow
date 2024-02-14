# Working with Aggregate API in ServiceNow

Retrieves records for the specified table and performs aggregate functions on the returned values.

**getAggregateRecord**
```xml
<servicenow.getAggregateRecord>
    <tableName>{$ctx:tableName}</tableName>
    <apiVersion>{$ctx:apiVersion}</apiVerion>
    <sysparmAvgFields>{$ctx:sysparmAvgFields}</sysparmAvgFields>
    <sysparmMinFields>{$ctx:sysparmMinFields}</sysparmMinFields>
    <sysparmMaxFields>{$ctx:sysparmMaxFields}</sysparmMaxFields>
    <sysparmSumFields>{$ctx:sysparmSumFields}</sysparmSumFields>
    <sysparmCount>{$ctx:sysparmCount}</sysparmCount>
    <sysparmFields>{$ctx:sysparmFields}</sysparmFields>
    <sysparmDisplayValue>{$ctx:sysparmDisplayValue}</sysparmDisplayValue>
    <sysparmGroupBy>{$ctx:sysparmGroupBy}</sysparmGroupBy>
    <sysparmHaving>{$ctx:sysparmHaving}</sysparmHaving>
    <sysparmOrderby>{$ctx:sysparmOrderby}</sysparmOrderby>
    <sysparmQuery>{$ctx:sysparmQuery}</sysparmQuery>
    <keyValuePairs>{$ctx:keyValuePairs}</keyValuePairs>
</servicenow.getAggregateRecord>
```

**Properties**
* tableName: Name of the data retrieving table
* apiVersion: Optional api version to be embeded in the url
* sysparmAvgFields: A comma-separated list of fields for which to calculate the average value
* sysparmMinFields: A comma-separated list of fields for which to calculate the minimum value
* sysparmMaxFields: A comma-separated list of fields for which to calculate the maximum value
* sysparmSumFields: A comma-separated list of fields for which to calculate the sum of the values
* sysparmCount: Flag that determines whether to return the number of records returned by the query
* sysparmFields: List of fields on which to perform each aggregate operation
* sysparmDisplayValue: Data retrieval operation when grouping by reference or choice fields
* sysparmGroupBy: Fields by which to group the returned data
* sysparmHaving : Query that enables you to filter the data based on an aggregate operation 
* sysparmOrderby: List of values by which to order grouped results
* sysparmQuery: An encoded query to filter response data
* keyValuePairs: Filter a query using key-value pairs, key is the name of a field

**Sample request**

Following is a sample request that can be handled by the getAggregateRecord operation:

```json
{
  "tableName":"incident",
  "sysparmAvgFields":"reassignment_count%2Cbusiness_stc",
  "sysparmGroupBy":"assignment_group"
}
```
**Sample response**

Given below is a sample response for the getAggregateRecord operation:

```json
{
    "result": [
        {
            "stats": {
                "avg": {
                    "business_stc": "519594.4545",
                    "reassignment_count": "0.1594"
                }
            },
            "groupby_fields": [
                {
                    "field": "assignment_group",
                    "value": ""
                }
            ]
        },
        {
            "stats": {
                "avg": {
                    "business_stc": "2037371.0000",
                    "reassignment_count": "1.5000"
                }
            },
            "groupby_fields": [
                {
                    "field": "assignment_group",
                    "value": "287ee6fea9fe198100ada7950d0b1b73"
                }
            ]
        },
        {
            "stats": {
                "avg": {
                    "business_stc": "1965080.1667",
                    "reassignment_count": "1.0000"
                }
            },
            "groupby_fields": [
                {
                    "field": "assignment_group",
                    "value": "8a5055c9c61122780043563ef53438e3"
                }
            ]
        },
        {
            "stats": {
                "avg": {
                    "business_stc": "1163148.0000",
                    "reassignment_count": "1.3333"
                }
            },
            "groupby_fields": [
                {
                    "field": "assignment_group",
                    "value": "287ebd7da9fe198100f92cc8d1d2154e"
                }
            ]
        },
        {
            "stats": {
                "avg": {
                    "business_stc": "",
                    "reassignment_count": "0.0000"
                }
            },
            "groupby_fields": [
                {
                    "field": "assignment_group",
                    "value": "36c741fa731313005754660c4cf6a70d"
                }
            ]
        },
        {
            "stats": {
                "avg": {
                    "business_stc": "1564478.6250",
                    "reassignment_count": "1.2500"
                }
            },
            "groupby_fields": [
                {
                    "field": "assignment_group",
                    "value": "d625dccec0a8016700a222a0f7900d06"
                }
            ]
        },
        {
            "stats": {
                "avg": {
                    "business_stc": "1512202.2500",
                    "reassignment_count": "1.1111"
                }
            },
            "groupby_fields": [
                {
                    "field": "assignment_group",
                    "value": "8a4dde73c6112278017a6a4baf547aa7"
                }
            ]
        },
        {
            "stats": {
                "avg": {
                    "business_stc": "",
                    "reassignment_count": "1.0000"
                }
            },
            "groupby_fields": [
                {
                    "field": "assignment_group",
                    "value": "cfcbad03d711110050f5edcb9e61038f"
                }
            ]
        }
    ]
}
```

**Related ServiceNow documentation**

https://developer.servicenow.com/app.do#!/rest_api_doc?v=jakarta&id=r_AggregateAPI-GET

### Sample configuration

Following example illustrates how to connect to ServiceNow with the init operation and getAggregateRecord operation.

1. Create a sample REST API as below :

```xml
<?xml version="1.0" encoding="UTF-8"?>
<api context="/aggtable" name="AggregateTableAPI" xmlns="http://ws.apache.org/ns/synapse">
    <resource methods="POST">
        <inSequence>
            <servicenow.init>
                <serviceNowInstanceurl>https://your-instance.service-now.com</serviceNowInstanceurl>
                <username>admin</username>
                <password>your-password</password>
            </servicenow.init>
            <servicenow.getAggregateRecord>
                <tableName>{json-eval($.tableName)}</tableName>
                <sysparmFields>{json-eval($.sysparmFields)}</sysparmFields>
                <sysparmAvgFields>{json-eval($.sysparmAvgFields)}</sysparmAvgFields>
                <sysparmGroupBy>{json-eval($.sysparmGroupBy)}</sysparmGroupBy>
            </servicenow.getAggregateRecord>
            <respond/>
        </inSequence>
        <outSequence/>
        <faultSequence/>
    </resource>
</api>
```

2. Create a JSON file named getAggregateRecord.json and add the configurations given below:

```json
{
  "tableName":"incident",
  "sysparmAvgFields":"reassignment_count%2Cbusiness_stc",
  "sysparmGroupBy":"assignment_group"
}                 
```
3. Replace the credentials with your values.

4. Execute the following curl command:

```bash
curl http://localhost:8290/aggtable -H "Content-Type: application/json" -d @getAggregateRecord.json
```
5. ServiceNow returns a JSON response similar to the one shown above at sample response section.
 

