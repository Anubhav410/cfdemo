package connectors.debezium.psql;

import connectors.debezium.DebeziumConstants;
import io.debezium.embedded.Connect;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.RecordChangeEvent;
import io.debezium.engine.format.ChangeEventFormat;
import org.apache.kafka.connect.source.SourceRecord;

import java.util.Objects;
import java.util.Properties;


public class PSQLConnector {
    private static final String DEBEZIUM_POSTGRES_CONNECTOR_NAME_CONFIG_VALUE = "postgres-connector";
    private static final String DEBEZIUM_POSTGRES_CONNECTOR_CLASS_NAME_CONFIG_VALUE =
            "io.debezium.connector.postgresql.PostgresConnector";

    protected final PostgresSourceConfig postgresSourceConfig;

    public PSQLConnector(PostgresSourceConfig postgresSourceConfig) {
        this.postgresSourceConfig = postgresSourceConfig;
    }


    protected DebeziumEngine<RecordChangeEvent<SourceRecord>> createDebeziumEngine() {
        return DebeziumEngine.create(ChangeEventFormat.of(Connect.class))
                .using(getDebeziumProperties())
                .notifying(new PSQLRecordProcessor())
                .using(this::debeziumShutdownCallback)
                .build();
    }

    public void run() {
        try {
            System.out.println("ANUBHAV RUNNING");
            createDebeziumEngine().run();
            System.out.println("ANUBHAV RUNNING...");
        } catch (Exception e) {
            System.out.printf("EXCEPTION: %s%n", e.getMessage());
        }

    }

    protected Properties getDebeziumProperties() {
        Properties properties = new Properties();

        properties.setProperty(
                DebeziumConstants.CONNECTOR_NAME_CONFIG, DEBEZIUM_POSTGRES_CONNECTOR_NAME_CONFIG_VALUE);
        properties.setProperty(
                DebeziumConstants.CONNECTOR_CLASS_CONFIG,
                DEBEZIUM_POSTGRES_CONNECTOR_CLASS_NAME_CONFIG_VALUE);

        properties.setProperty(
                DebeziumConstants.DATABASE_HOST_CONFIG, postgresSourceConfig.getDBHost());
        properties.setProperty(
                DebeziumConstants.DATABASE_PORT_CONFIG, postgresSourceConfig.getDBPort().toString());
        properties.setProperty(
                DebeziumConstants.DATABASE_USER_CONFIG, postgresSourceConfig.getDBUser());
        properties.setProperty(
                DebeziumConstants.DATABASE_PASSWORD_CONFIG, postgresSourceConfig.getDBPassword());
        properties.setProperty(
                DebeziumConstants.DATABASE_NAME_CONFIG, postgresSourceConfig.getDBName());
        properties.setProperty(DebeziumConstants.INCLUDED_TABLES_CONFIG, "test_table_2");

        properties.setProperty(
                DebeziumConstants.REPLICATION_SLOT_NAME_CONFIG,
                postgresSourceConfig.getReplicationSlotName());
        properties.setProperty(
                DebeziumConstants.OUTPUT_PLUGIN_NAME_CONFIG, PostgresConstants.OUTPUT_PLUGIN_CONFIG_VALUE);
        properties.setProperty(
                DebeziumConstants.PUBLICATION_NAME_CONFIG, postgresSourceConfig.getPublication());
        properties.setProperty(
                DebeziumConstants.TOAST_DATUM_PLACEHOLDER_CONFIG,
                DebeziumConstants.TOAST_DATUM_PLACEHOLDER_VALUE);

        // setting this as false to manually flush lsn when offset is committed
        properties.setProperty(
                DebeziumConstants.FLUSH_LSN_CONFIG, DebeziumConstants.FLUSH_LSN_CONFIG_VALUE);

        properties.setProperty(
                DebeziumConstants.TOPIC_PREFIX_CONFIG, DebeziumConstants.TOPIC_PREFIX_CONFIG_VALUE);
        properties.setProperty(
                DebeziumConstants.SNAPSHOT_MODE_CONFIG, DebeziumConstants.SNAPSHOT_MODE_CONFIG_VALUE);
        properties.setProperty(
                DebeziumConstants.TIME_PRECISION_MODE_CONFIG,
                DebeziumConstants.TIME_PRECISION_MODE_CONFIG_VALUE);

//        properties.setProperty(
//                DebeziumConstants.PROPAGATE_COLUMN_INFO_CONFIG, getColumnInfoPropagationRegex());

        properties.setProperty("offset.storage.file.filename", "/Users/anubhavshrivastava/code/cf_demo/offsets/data");
        properties.setProperty(
                DebeziumConstants.OFFSET_FLUSH_INTERVAL_CONFIG, String.valueOf(5 * 1000));

        properties.setProperty(
                DebeziumConstants.MAX_RETRIES_CONFIG, DebeziumConstants.MAX_RETRIES_CONFIG_VALUE);
        return properties;
    }

    protected void debeziumShutdownCallback(boolean success, String message, Throwable error) {
        System.out.println("Debezium engine shutdown.");
        System.out.println(message);
        if (Objects.nonNull(error)) {
            System.out.printf("ERROR: Debezium engine stopped with error: %o %n", error);
        }
    }
}
