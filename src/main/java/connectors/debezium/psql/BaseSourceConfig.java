package connectors.debezium.psql;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;

import java.util.Map;

public abstract class BaseSourceConfig extends AbstractConfig {

  protected BaseSourceConfig(ConfigDef definition, Map<?, ?> originals) {
    super(definition, originals, false);
  }
}
