package infraestructure.repository;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventMapperDAO implements ResultSetMapper<EventRecord> {
    @Override
    public EventRecord map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new EventRecord(
                r.getString("name"),
                r.getString("type"),
                r.getString("place"),
                r.getString("address"),
                r.getTimestamp("start_date"),
                r.getTimestamp("finish_date")
        );
    }
}
