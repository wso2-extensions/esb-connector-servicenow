# Configuring Salesforce REST Operations

[[Prerequisites]](#Prerequisites) [[Initializing the connector]](#initializing-the-connector)

## Prerequisites

To use the ServiceNow connector, add the <servicenow.init> element in your configuration before carrying out any other ServiceNow operations.

The ServiceNow API requires all requests to be authenticated as a user. User has to create a own instance with his user credentials. When u create a account in ServiceNow Developer page then you are enable to create your own instance. For more information, see https://developer.servicenow.com/app.do#!/home

### Obtaining user credentials

* **Create a ServiceNow instance using serviceNow developer site and derive the username and password**
 
    1. Using the URL "https://developer.servicenow.com/app.do#!/home" register a ServiceNow account.
	2. Login to the created Service-Now account and go to Manage >> Instance and request an instance.
	3. Then you will get a username and password for your instance with instance URL. you can change your password by going Action >> Reset admin password.
	4. In your instance you will get three types of APIs and some pre defined tables.
	    
	    
### Importing the ServiceNow Certificate

Before you start configuring the connector, import the ServiceNow certificate to your EI client keystore.

* Follow the steps below to import the ServiceNow certificate into the EI client keystore:

    1. To view the certificate, log in to your ServiceNow account using your browser (e.g., https://dev62519.service-now.com/), and click the lock on the address bar.
    2. Export the certificate to the file system.
    3. Import the certificate to the EI client keystore using either the following command or the EI Management Console:
    ```
    keytool -importcert -file <certificate file> -keystore <EI>/repository/resources/security/client-truststore.jks -alias "ServiceNow"
    ```
    4. Restart the server and deploy the ServiceNow configuration. 

## Initializing the connector

Add the following <servicenow.init> method in your configuration:
 
#### init
```xml
<servicenow.init>
    <serviceNowInstanceURL>{$ctx:serviceNowInstanceURL}</serviceNowInstanceURL>
    <username>{$ctx:username}</username>
    <password>{$ctx:password}</password>
</servicenow.init>
```
**Properties** 
* serviceNowInstanceURL: The base endpoint URL of the ServiceNow API.  
* username : The user Name of the own instance.  
* password : The Password of the own instance. 

**Sample Request**

Following is a sample REST request that can be handled by the init operation.

```json
{
  "serviceNowInstanceURL":"https://dev17686.service-now.com", 
  "username":"admin",
  "password":"12345"
}
```

**Related  documentation**

http://wiki.servicenow.com/index.php?title=REST_API#gsc.tab=0

Now that you have connected to ServiceNow, use the information in the following topics to perform various operations with the connector. Also, see [Configuring the ServiceNow Connector Fault Handler Sequence]((fault_handler_sequence.md)).

[Working with Aggregate API in ServiceNow](aggregate.md)

[Working with Import Set API in ServiceNow](import_set.md)

[Working with Table API in ServiceNow](table.md)
