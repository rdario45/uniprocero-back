package infraestructure.repository;

import com.google.inject.Inject;
import domain.Event;
import infraestructure.acl.EventMapper;
import io.vavr.collection.List;
import io.vavr.concurrent.Future;
import io.vavr.control.Option;
import org.skife.jdbi.v2.DBI;
import play.Logger;
import play.api.db.Database;
import play.libs.Json;

public class EventRepository {

  private DBI db;

  @Inject
  public EventRepository(Database db) {
    this.db = new DBI(db.dataSource());
  }

  public List<Event> listAll() {
    List<EventRecord> eventRecords = List.ofAll(db.onDemand(EventDAO.class).listAll());
    return eventRecords.map(EventMapper::recordToEvent);
  }

  public Option<Event> find(int id) {
    Option<EventRecord> result = Option.of(db.onDemand(EventDAO.class).find(id));
    return result.map(EventMapper::recordToEvent);
  }

  public Future<Event> save(Event event) {
    EventRecord record = EventMapper.eventToRecord(event);
    return Future.of(() -> db.onDemand(EventDAO.class).insert(record)).map(EventMapper::recordToEvent);
  }

  public Future<Option<Event>> update(Event event){
    EventRecord record = EventMapper.eventToRecord(event);
    return Future.of(() -> Option.of(db.onDemand(EventDAO.class).update(record)).map(EventMapper::recordToEvent));
  }

  public Future<Option<Event>> delete(int id){
    return Future.of(() -> Option.of(db.onDemand(EventDAO.class).delete(id)).map(EventMapper::recordToEvent));
  }

}
