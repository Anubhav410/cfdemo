package connectors.debezium.psql;

import org.apache.kafka.common.config.ConfigDef;

import java.util.Map;

public abstract class JDBCSourceConfig extends BaseSourceConfig {

    public static final String DB_HOST_CONFIG = "db_host";
    private static final String DB_HOST_DOC = "JDBC connection host.";

    public static final String DB_PORT_CONFIG = "db_port";
    private static final String DB_PORT_DOC = "JDBC connection host port.";

    public static final String DB_USER_CONFIG = "db_user";
    private static final String DB_USER_DOC = "JDBC connection db user";

    public static final String DB_PASSWORD_CONFIG = "db_password";
    private static final String DB_PASSWORD_DOC = "JDBC connection db password.";

    public static final String DB_NAME_CONFIG = "db_name";
    private static final String DB_NAME_DOC = "JDBC connection db name.";

    protected JDBCSourceConfig(ConfigDef definition, Map<?, ?> originals) {
        super(definition, originals);
    }

    public static ConfigDef defaultSourceConfigDef() {
        return new ConfigDef()
                .define(DB_HOST_CONFIG, ConfigDef.Type.STRING, ConfigDef.Importance.MEDIUM, DB_HOST_DOC)
                .define(DB_PORT_CONFIG, ConfigDef.Type.INT, ConfigDef.Importance.MEDIUM, DB_PORT_DOC)
                .define(DB_USER_CONFIG, ConfigDef.Type.STRING, ConfigDef.Importance.HIGH, DB_USER_DOC)
                .define(
                        DB_PASSWORD_CONFIG, ConfigDef.Type.PASSWORD, ConfigDef.Importance.HIGH, DB_PASSWORD_DOC)
                .define(
                        DB_NAME_CONFIG, ConfigDef.Type.STRING, null, ConfigDef.Importance.MEDIUM, DB_NAME_DOC);
    }

    public String getDBHost() {
        return this.getString(DB_HOST_CONFIG);
    }

    public Integer getDBPort() {
        return this.getInt(DB_PORT_CONFIG);
    }

    public String getDBUser() {
        return this.getString(DB_USER_CONFIG);
    }

    public String getDBPassword() {
        return this.getPassword(DB_PASSWORD_CONFIG).value();
    }

    public String getDBName() {
        return this.getString(DB_NAME_CONFIG);
    }
}
