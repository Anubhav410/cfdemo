package connectors.debezium.psql;

import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.RecordChangeEvent;
import org.apache.kafka.connect.source.SourceRecord;

import java.util.List;

public class PSQLRecordProcessor implements DebeziumEngine.ChangeConsumer<RecordChangeEvent<SourceRecord>> {


    @Override
    public void handleBatch(List<RecordChangeEvent<SourceRecord>> list, DebeziumEngine.RecordCommitter<RecordChangeEvent<SourceRecord>> recordCommitter) throws InterruptedException {
        System.out.println("I have some event!!!!!!!!!!");
    }

    @Override
    public boolean supportsTombstoneEvents() {
        return DebeziumEngine.ChangeConsumer.super.supportsTombstoneEvents();
    }
}
