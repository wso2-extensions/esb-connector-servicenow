Integration tests for WSO2 EI ServiceNow connector

Pre-requisites:

 - Maven 3.x
 - Java 1.6 or above
 - The org.wso2.esb.integration.integration-base project is required. The test suite has been configured to download this project automatically. If the automatic download fails, download the following project and compile it using the mvn clean install command to update your local repository:
   https://github.com/wso2-extensions/esb-integration-base

Tested Platform: 

 - UBUNTU 15.10
 - WSO2 EI 6.5.0-m4

Steps to follow in setting integration test.

 1. Download EI 6.5.0-m4-m4 from official website.

 3. Compress modified EI as wso2ei-6.5.0-m4.zip and copy that zip file in to location "{SERVICENOW_CONNECTOR_HOME}/repository/".

 4. Follow the below mentioned steps for adding valid certificate to access serviceNow API over https.

   	    i)   Extract the certificate from browser(Mozilla Firefox) by navigating to https://{instance_name}.service-now.com
   	    ii)  Place the certificate in {SERVICENOW_CONNECTOR_HOME}/repository/ directory. 

 5. Create a ServiceNow instance using serviceNow developer site and derive the username and password.
 
        i)   Using the URL "https://developer.servicenow.com/app.do#!/home" register a ServiceNow account.
	    ii)  Login to the created Service-Now account and go to Manage >> Instance.
	    iii) Then you will get a username and password for your instance with instance URL. you can change your password by going Action >> Reset admin password.
	    iv)  In your instance you will get three types of APIs and some pre defined tables.

 6. Update the ServiceNow properties file at location "{SERVICENOW_CONNECTOR_HOME}/src/test/resources/artifacts/EI/connector/config" and esb-connector-servicenow.properties in repository directory as below.
	
	    i)      serviceNowInstanceURL            -  Use "https://instancename.service-now.com".
	    ii)     username                         -  Use the username obtained under Step 5 (iii).
	    iii)    password                         -  Use the password obtained under Step 5 (iii).
	    iv)     tableName                        -  Use a tablename which is available on your instance.
	    v)      sysIdStaging                     -  Use a valid sys_id from the Import set API table (https://{instance name}.service-now.com/sys_import_set.do).
	    vi)     tableNameStaging                 -  Use a valid table name from the Import set API table.
	    vii)    number                           -  Use a valid number in the format of Integer.
	    viii)   shortDescription                 -  Use a valid string as the short description of the record.
	    ix)     active                           -  Use true or false.
	    x)      approval                         -  Use a valid string as the short description of the record.
	    xi)     category                         -  Use a valid category in the format of String.
	    xii)    sysparmQuery                     -  Use a valid attribute name and value in the table.
	    xiii)   sysparmDisplayValue              -  Use true or false.
	    xiv)    sysparmFields                    -  Use valid attribute names of the table.
	    xv)     sysparmView                      -  Use valid attribute names of the table.
	    xvi)    sysparmLimit                     -  Use a valid integer number within 10,000.
	    xvii)   sysparmOffset                    -  Use a valid integer number within 10,000.
	    xviii)  KeyValuePairs                    -  Use valid attribute names and values in the table.
	    xix)    sysparmInputDisplayValue         -  Use true or false.
	    xx)     sysparmAvgFields                 -  Use valid attribute names of the table.
	    xxi)    sysparmMinFields                 -  Use valid attribute names of the table.
	    xxii)   sysparmMaxFields                 -  Use valid attribute names of the table.
	    xxiii)  sysparmSumFields                 -  Use valid attribute names of the table.
	    xxiv)   sysparmCount                     -  Use true or false.
	
 7. Navigate to "{SERVICENOW_CONNECTOR_HOME}/" and run the following command.<br/>
 `$ mvn clean install -Dskip-tests=false` 
	  
	  

		
