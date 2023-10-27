package connectors.debezium.psql;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;

import java.util.Map;

public class PostgresSourceConfig extends JDBCSourceConfig {

    private static final String LONG_RUNNING_JOB = "is_long_runnning";
    private static final String LONG_RUNNING_JOB_DOC = "Is this a long running job?";
    // TODO: make this false after testing
    private static final boolean LONG_RUNNING_JOB_DEFAULT = true;

    private static final String PGOUTPUT_PUBLICATION_CONFIG = "pgoutput_publication";
    private static final String PGOUTPUT_PUBLICATION_CONFIG_DOC =
            "Publication to pull incremental data from";

    private static final String REPLICATION_SLOT_NAME_CONFIG_DOC = "Name of replication slot";
    private static final String REPLICATION_SLOT_NAME_CONFIG = "replication_slot";

    private static final ConfigDef CONFIG_DEF = baseConfigDef();

    public PostgresSourceConfig(Map<String, Object> props) {
        super(CONFIG_DEF, props);
    }

    public static ConfigDef baseConfigDef() {
        return new ConfigDef(JDBCSourceConfig.defaultSourceConfigDef())
                .define(
                        REPLICATION_SLOT_NAME_CONFIG,
                        Type.STRING,
                        null,
                        Importance.MEDIUM,
                        REPLICATION_SLOT_NAME_CONFIG_DOC)
                .define(
                        PGOUTPUT_PUBLICATION_CONFIG,
                        Type.STRING,
                        null,
                        Importance.MEDIUM,
                        PGOUTPUT_PUBLICATION_CONFIG_DOC)
                .define(
                        LONG_RUNNING_JOB,
                        Type.BOOLEAN,
                        LONG_RUNNING_JOB_DEFAULT,
                        Importance.LOW,
                        LONG_RUNNING_JOB_DOC);
    }

    public String getReplicationSlotName() {
        return this.getString(REPLICATION_SLOT_NAME_CONFIG);
    }

    public String getPublication() {
        return this.getString(PGOUTPUT_PUBLICATION_CONFIG);
    }

    public boolean isLongRunningJob() {
        return this.getBoolean(LONG_RUNNING_JOB);
    }
}
