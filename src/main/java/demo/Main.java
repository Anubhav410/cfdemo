package demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import connectors.debezium.psql.PSQLConnector;
import connectors.debezium.psql.PostgresSourceConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length < 5) {
            System.out.println("Insufficient arguments.");
            return;
        }

        String workflowID = args[0];
        String taskId = args[1];
        String integrationID = args[2];
        String jsonConfigs = args[3];
        String connectorArgs = args[4];

        Configs configs = parseJsonToConfigs(jsonConfigs);
        if (configs == null) {
            System.out.println("unable to parse config");
            System.exit(1);
        }
        // This is a simple connector
//        new Connector(workflowID,taskId,  integrationID, configs, connectorArgs).run();

        // This is Debezium Connector
        new PSQLConnector(workflowID, taskId, integrationID, configs, connectorArgs).run();
    }

    private static Map<String, Object> getTestPostgresConfig() {

//        "{\"db_host\":\"127.0.0.1\",\"db_port\":\"5432\",\"replication_slot\":\"slot_cf_demo\",\"db_password\":\"root\",\"db_name\":\"postgres\",\"is_long_running\":true,\"db_user\":\"root\",\"pgoutput_publication\":\"alltables\"}"

        Map<String, Object> props = new HashMap<>();
        props.put("replication_slot", "slot_cf_demo");
        props.put("pgoutput_publication", "alltables");
        props.put("is_long_runnning", true);
        props.put("db_host", "127.0.0.1");
        props.put("db_port", "5432");
        props.put("db_user", "root");
        props.put("db_password", "root");
        props.put("db_name", "postgres");

        return props;
    }

    public static Configs parseJsonToConfigs(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, Configs.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
