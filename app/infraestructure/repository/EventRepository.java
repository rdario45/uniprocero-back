package infraestructure.repository;

import com.google.inject.Inject;
import domain.Event;
import infraestructure.acl.EventMapper;
import io.vavr.collection.List;
import org.skife.jdbi.v2.DBI;
import play.api.db.Database;

public class EventRepository {

    private DBI db;

    @Inject
    public EventRepository(Database db) {
        this.db = new DBI(db.dataSource());
    }

    public List<Event> listAll(){
        List<EventRecord> eventRecords = List.ofAll(db.onDemand(EventDAO.class).listAll());
        return eventRecords.map(EventMapper::recordToEvent);
    }

}
