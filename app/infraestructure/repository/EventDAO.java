package infraestructure.repository;

import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(EventMapperDAO.class)
public interface EventDAO {

    @SqlQuery("SELECT * FROM events")
    List<EventRecord> listAll();
}
