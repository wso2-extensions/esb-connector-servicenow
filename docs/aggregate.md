# Working with Aggregate API in ServiceNow

The getAggregateRecord operation Allow to compute aggregate statistics about existing table and column data.

**getAggregateRecord**
```xml
<servicenow.getAggregateRecord>
    <tableName>{$ctx:tableName}</tableName>
    <sysparmAvgFields>{$ctx:sysparmAvgFields}</sysparmAvgFields>
    <sysparmMinFields>{$ctx:sysparmMinFields}</sysparmMinFields>
    <sysparmMaxFields>{$ctx:sysparmMaxFields}</sysparmMaxFields>
    <sysparmCount>{$ctx:sysparmCount}</sysparmCount>
    <sysparmSumFields>{$ctx:sysparmSumFields}</sysparmSumFields>
</servicenow.getAggregateRecord>
```

**Properties**
* tableName: Name of the table you want to retrieve a record.
* sysparmAvgFields: A comma-separated list of fields for which to calculate the average value.
* sysparmMinFields: A comma-separated list of fields for which to calculate the minimum value.
* sysparmMaxFields: A comma-separated list of fields for which to calculate the maximum value.
* sysparmCount: You can set this parameter to true for the number of records returned by the query.
* sysparmSumFields: A comma-separated list of fields for which to calculate the sum of the values.

**Sample request**

Following is a sample request that can be handled by the getAggregateRecord operation.

```json
{
  "serviceNowInstanceURL":"https://dev17686.service-now.com", 
  "username":"admin",
  "password":"12345",
  "tableName":"incident",
  "sysparmAvgFields":"category,active",
  "sysparmMinFields":"number",
  "sysparmMaxFields":"number",
  "sysparmCount":"true",
  "sysparmSumFields":"priority"
}
```
**Sample response**

Given below is a sample response for the getAggregateRecord operation.

```json
{
   "result":{
      "stats":{
         "count":"89"
      }
   }
}
```

**Related ServiceNow documentation**

https://developer.servicenow.com/app.do#!/rest_api_doc?v=jakarta&id=r_AggregateAPI-GET

### Sample configuration

Following example illustrates how to connect to ServiceNow with the init operation and getAggregateRecord operation.

1. Create a sample proxy as below :

```xml
<?xml version="1.0" encoding="UTF-8"?>
<proxy xmlns="http://ws.apache.org/ns/synapse"
       name="getAggregateRecord"
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
         <property name="sysparmAvgFields" expression="json-eval($.sysparmAvgFields)"/>
         <property name="sysparmMinFields" expression="json-eval($.sysparmMinFields)"/>
         <property name="sysparmMaxFields" expression="json-eval($.sysparmMaxFields)"/>
         <property name="sysparmCount" expression="json-eval($.sysparmCount)"/>
         <property name="sysparmSumFields" expression="json-eval($.sysparmSumFields)"/>
         <servicenow.init>
            <serviceNowInstanceURL>{$ctx:serviceNowInstanceURL}</serviceNowInstanceURL>
            <username>{$ctx:username}</username>
            <password>{$ctx:password}</password>
         </servicenow.init>
         <servicenow.getAggregateRecord>
            <tableName>{$ctx:tableName}</tableName>
            <sysparmAvgFields>{$ctx:sysparmAvgFields}</sysparmAvgFields>
            <sysparmMinFields>{$ctx:sysparmMinFields}</sysparmMinFields>
            <sysparmMaxFields>{$ctx:sysparmMaxFields}</sysparmMaxFields>
            <sysparmCount>{$ctx:sysparmCount}</sysparmCount>
            <sysparmSumFields>{$ctx:sysparmSumFields}</sysparmSumFields>
         </servicenow.getAggregateRecord>
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

2. Create a JSON file named getAggregateRecord.json and add the configurations given below:

```json
{
  "serviceNowInstanceURL":"https://dev17686.service-now.com", 
  "username":"admin",
  "password":"12345",
  "tableName":"incident",
  "sysparmAvgFields":"category,active",
  "sysparmMinFields":"number",
  "sysparmMaxFields":"number",
  "sysparmCount":"true",
  "sysparmSumFields":"priority"
}                      
```
3. Replace the credentials with your values.

4. Execute the following curl command:

```bash
curl http://localhost:8280/services/getAggregateRecord -H "Content-Type: application/json" -d @getAggregateRecord.json
```
5. ServiceNow returns a JSON response similar to the one shown below:
 
```json
{
   "result":{
      "stats":{
         "count":"89"
      }
   }
}
```
