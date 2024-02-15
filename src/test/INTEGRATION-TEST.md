# Integration tests for WSO2 EI ServiceNow connector

## Pre-requisites:

 - Maven 3.x
 - Java 1.6 or above
 - The org.wso2.esb.integration.integration-base project is required. The test suite has been configured to download this project automatically. If the automatic download fails, download the following project and compile it using the mvn clean install command to update your local repository:
   https://github.com/wso2-extensions/esb-integration-base

## Tested Platform: 

 - UBUNTU 22.04
 - WSO2 EI 6.5.0

## Steps to follow in setting integration test.

 1. Download EI 6.5.0 from official [repository](https://github.com/wso2/product-ei).

 3. Unzip the downloaded repository and build it using "mvn install" command.

 4. Copy the built .zip of WSO2 EI to {SERVICENOW_CONNECTOR_HOME}/repository.
 
 5. Follow the below mentioned steps for adding valid certificate to access serviceNow API over https.

   	    i)   Extract the certificate from browser(Mozilla Firefox) by navigating to https://{instance_name}.service-now.com
   	    ii)  Place the certificate in {SERVICENOW_CONNECTOR_HOME}/repository. 

 6. Create a ServiceNow instance using serviceNow developer site and derive the username and password.
 
        i). Using the url "https://developer.servicenow.com/app.do#!/home" signup to a ServiceNow account or signin to existing account.
		ii). Login to the ServiceNow account and request an instance.
		iii). Then you will get an username and password for your instance with instance url. You can change your password by going to My Instance -> Instance Action -> Manage Instance Password.
	    

 7. Update the ServiceNow properties file at location "{SERVICENOW_CONNECTOR_HOME}/src/test/resources/artifacts/EI/connector/config" and esb-connector-servicenow.properties.

 8. Change the main pom.xml <skip-test>true<skip-test> to <skip-test>false<skip-test>
	
 9. Navigate to "{SERVICENOW_CONNECTOR_HOME}/" and run the following command.<br/>
 `$ mvn clean install` 
	  
	  

		
