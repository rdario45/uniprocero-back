package infraestructure.repository;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(EventMapperDAO.class)
public interface EventDAO {

    @SqlQuery("SELECT * FROM events")
    List<EventRecord> listAll();

    @SqlQuery("SELECT * FROM events WHERE id = :id")
    EventRecord find(@Bind("id") int id);

    @SqlQuery("INSERT INTO events " +
      "(name, " +
      "type, " +
      "place, " +
      "address, " +
      "start_date, " +
      "finish_date) " +
      "VALUES(" +
      ":r.name, " +
      ":r.type, " +
      ":r.place, " +
      ":r.address, " +
      ":r.startDate, " +
      ":r.finishDate) RETURNING *")
    EventRecord insert(@BindBean("r") EventRecord record);

}
