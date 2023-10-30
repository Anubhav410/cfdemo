package demo;

import connectors.BaseConnector;

public class Connector extends BaseConnector {
    Long waitTime;

    Connector(String workflowId, String taskId, String integrationID, Configs configs, String connectorArgs) {
        super(workflowId, taskId, integrationID, configs);
        this.waitTime = Long.parseLong(connectorArgs);
    }

    public void dataPullStart() throws InterruptedException {
        System.out.println("Starting Data Pull");
        Thread.sleep(waitTime);
        System.out.println("Completing Data Pull");
    }

}