# Configuring Servicenow REST Operations

[[Prerequisites]](#Prerequisites) [[Initializing the connector]](#initializing-the-connector)

## Prerequisites

To use the ServiceNow connector, add the <servicenow.init> element in your configuration before carrying out any other ServiceNow operations.

The ServiceNow API requires all requests to be authenticated as an user. The user has to create an own instance with the user credentials. For more information, see https://developer.servicenow.com/app.do#!/home

### Obtaining user credentials

* **Create a ServiceNow instance using serviceNow developer site and setup the username and password**
 
  1. Using the url "https://developer.servicenow.com/app.do#!/home" signup to a ServiceNow account or signin to existing account.
	2. Login to the ServiceNow account and request an instance.
	3. Then you will get an username and password for your instance with instance url. You can change your password by going to My Instance -> Instance Action -> Manage Instance Password.
	
	    
	    
### Importing the ServiceNow Certificate

ServiceNow API support basic authentication using username,passsword to access APIs. For additional secure connection, import the ServiceNow certificate to your MI client keystore.

* Follow the article https://ei.docs.wso2.com/en/latest/micro-integrator/setup/security/importing_ssl_certificate/  to import the ServiceNow certificate into the MI client keystore.
 

## Initializing the connector

Add the following <servicenow.init> method in your configuration:
 
#### init
```xml
<servicenow.init>
    <serviceNowInstanceurl>{$ctx:serviceNowInstanceurl}</serviceNowInstanceurl>
    <username>{$ctx:username}</username>
    <password>{$ctx:password}</password>
</servicenow.init>
```
**Properties** 
* serviceNowInstanceurl: Developer instance url.  
* username : The username of the developer instance (default:admin).  
* password : The password of the  developer instance. 

**Sample Request**

Following is a sample REST request that can be handled by the init operation.

```json
{
  "serviceNowInstanceurl":"https://your-instance.service-now.com", 
  "username":"admin",
  "password":"<password>"
}
```

**Related  documentation**

http://wiki.servicenow.com/index.php?title=REST_API#gsc.tab=0

Now that you have connected to ServiceNow, use the information in the following topics to perform required operations with the connector. Also, see [Configuring the ServiceNow Connector Fault Handler Sequence](fault_handler_sequence.md).

[Working with Aggregate API in ServiceNow](aggregate.md)

[Working with Import Set API in ServiceNow](import_set.md)

[Working with Table API in ServiceNow](table.md)
