package demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import connectors.debezium.psql.PostgresSourceConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        if (args.length < 4) {
            System.out.println("Insufficient arguments.");
            return;
        }

        String workflowID = args[0];
        String integrationID = args[1];
        String jsonConfigs = args[2];
        String waitTime = args[3];

        Configs configs = parseJsonToConfigs(jsonConfigs);
        if (configs == null) {
            System.out.println("unable to parse config");
            System.exit(1);
        }
        // This is a simple connector
        new Connector(workflowID, integrationID, configs, Long.parseLong(waitTime)).run();

        // This is Debezium Connector

//        new PSQLConnector(getTestPostgresConfig()).run();

    }

    private static PostgresSourceConfig getTestPostgresConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put("replication_slot", "slot_cf_demo");
        props.put("pgoutput_publication", "alltables");
        props.put("is_long_runnning", false);
        props.put("db_host", "127.0.0.1");
        props.put("db_port", "5432");
        props.put("db_user", "root");
        props.put("db_password", "root");
        props.put("db_name", "postgres");

        return new PostgresSourceConfig(props);
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
