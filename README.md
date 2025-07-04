### ServiceNow WSO2 EI Connector

The ServiceNow [connector](https://docs.wso2.com/display/EI650/Working+with+Connectors) allows you to access the [ServiceNow REST API](https://developer.servicenow.com/app.do#!/rest_api_doc?v=madrid&id=c_TableAPI) through WSO2 EI. ServiceNow is a software platform that supports IT service management and automates common business processes. This software as a service (SaaS) platform contains a number of modular applications that can vary by instance and user.
## Compatibility

| Connector version                                                                                                 |  Supported WSO2 ESB/EI version |
|-------------------------------------------------------------------------------------------------------------------|-------------------------------|
| 2.0.0 | MI 4.4.0                      |
| 1.0.2| EI 6.5.0                      |
| 1.0.1 | ESB 4.9.0, 5.0.0                |

## Getting started

#### Download and install the connector

1. Download the connector from the [WSO2 Store](https://store.wso2.com/store/assets/esbconnector/details/74999163-6706-41f6-a564-61479b99918f) by clicking the Download Connector button.
2. Then you can follow this [Documentation](https://docs.wso2.com/display/EI650/Working+with+Connectors+via+the+Management+Console) to add and enable the connector via the Management Console in your EI instance.
3. For more information on using connectors and their operations in your EI configurations, see [Using a Connector](https://docs.wso2.com/display/EI650/Using+a+Connector).
4. If you want to work with connectors via EI tooling, see [Working with Connectors via Tooling](https://docs.wso2.com/display/EI650/Working+with+Connectors+via+Tooling).

#### Configuring the connector operations

To get started with ServiceNow connector and their operations, see [Configuring ServiceNow Operations](docs/config.md).


## Building From the Source

Follow the steps given below to build the ServiceNow connector from the source code:

1. Get a clone or download the source from [Github](https://github.com/wso2-extensions/esb-connector-servicenow).
2. Run the following Maven command from the `esb-connector-servicenow` directory: `mvn clean install`.
3. The ServiceNow connector zip file is created in the `esb-connector-servicenow/target` directory

## How You Can Contribute

As an open source project, WSO2 extensions welcome contributions from the community.
Check the [issue tracker](https://github.com/wso2-extensions/esb-connector-servicenow/issues) for open issues that interest you. We look forward to receiving your contributions.
