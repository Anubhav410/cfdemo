package connectors.debezium;

public class DebeziumConstants {

  // Connector configs
  public static final String CONNECTOR_NAME_CONFIG = "name";
  public static final String CONNECTOR_CLASS_CONFIG = "connector.class";
  public static final String OFFSET_STORAGE_CLASS_CONFIG = "offset.storage";
  public static final String OFFSET_STORAGE_CLASS_CONFIG_VALUE =
      "io.hevo.debezium.connector.offsetmanagement.HevoOffsetBackingStore";
  public static final String OFFSET_FLUSH_INTERVAL_CONFIG = "offset.flush.interval.ms";
  public static final String MAX_RETRIES_CONFIG = "errors.max.retries";
  public static final String MAX_RETRIES_CONFIG_VALUE = "0";

  // Source database configs
  public static final String DATABASE_HOST_CONFIG = "database.hostname";
  public static final String DATABASE_PORT_CONFIG = "database.port";
  public static final String DATABASE_USER_CONFIG = "database.user";
  public static final String DATABASE_PASSWORD_CONFIG = "database.password";
  public static final String DATABASE_NAME_CONFIG = "database.dbname";
  public static final String INCLUDED_TABLES_CONFIG = "table.include.list";

  // Postgres-specific configs
  public static final String REPLICATION_SLOT_NAME_CONFIG = "slot.name";

  public static final String FLUSH_LSN_CONFIG = "flush.lsn.source";
  public static final String FLUSH_LSN_CONFIG_VALUE = "true";

  public static final String OUTPUT_PLUGIN_NAME_CONFIG = "plugin.name";
  public static final String PUBLICATION_NAME_CONFIG = "publication.name";
  public static final String TOAST_DATUM_PLACEHOLDER_CONFIG = "unavailable.value.placeholder";
  public static final String TOAST_DATUM_PLACEHOLDER_VALUE = "__hevo_postgres_toast_column";

  // misc configs
  public static final String TOPIC_PREFIX_CONFIG = "topic.prefix";
  public static final String TOPIC_PREFIX_CONFIG_VALUE = "debezium";

  public static final String SNAPSHOT_MODE_CONFIG = "snapshot.mode";
  public static final String SNAPSHOT_MODE_CONFIG_VALUE = "never";

  public static final String TIME_PRECISION_MODE_CONFIG = "time.precision.mode";
  public static final String TIME_PRECISION_MODE_CONFIG_VALUE = "connect";

  public static final String PROPAGATE_COLUMN_INFO_CONFIG = "column.propagate.source.type";
  public static final String COLUMN_TYPE_CONFIG = "__debezium.source.column.type";
  public static final String COLUMN_LENGTH_CONFIG = "__debezium.source.column.length";
  public static final String COLUMN_SCALE_CONFIG = "__debezium.source.column.scale";

  // Hevo specific configs
  public static final String HEVO_INTEGRATION_ID_CONFIG = "hevo.integration.id";
  public static final String HEVO_SERVICES_CLIENT_URL_CONFIG = "hevo.services.client.url";
  public static final String HEVO_SERVICES_CLIENT_USERNAME_CONFIG = "hevo.services.client.username";
  public static final String HEVO_SERVICES_CLIENT_PASSWORD_CONFIG = "hevo.services.client.password";
  public static final String HEVO_RECORD_WRITER_CLASS_NAME_CONFIG = "hevo.records.writer.className";
  public static final String HEVO_KAFKA_BOOTSTRAP_SERVERS_CONFIG = "hevo.kafka.bootstrap.servers";

  // Change event related constants
  public static final String CHANGE_EVENT_UPDATED_RECORD_KEY = "after";
  public static final String CHANGE_EVENT_SOURCE_METADATA_KEY = "source";
  public static final String DB_NAME_KEY = "db";
  public static final String SCHEMA_NAME_KEY = "schema";
  public static final String TABLE_NAME_KEY = "table";
}
