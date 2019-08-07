package infraestructure.acl;


import controllers.dto.EventDTO;
import domain.Event;
import domain.Type;
import infraestructure.repository.EventRecord;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.sql.Timestamp;

public class EventMapper {

    public static EventDTO eventToDTO(Event event) {
        return new EventDTO(
          event.getId(),
          event.getName(),
          event.getType().getName(),
          event.getPlace(),
          event.getAddress(),
          event.getStartDate().toString(),
          event.getFinishDate().toString()
        );
    }

    public static EventRecord eventToRecord(Event event) {
        return new EventRecord(
          event.getId(),
          event.getName(),
          event.getType().getName(),
          event.getPlace(),
          event.getAddress(),
          new Timestamp(event.getStartDate().getMillis()),
          new Timestamp(event.getFinishDate().getMillis())
        );
    }

    public static Event recordToEvent(EventRecord record) {
        return new Event(
          record.getId(),
          record.getName(),
          Type.indexOf(record.getType()),
          record.getPlace(),
          record.getAddress(),
          new DateTime(record.getStartDate(), DateTimeZone.forID("America/Bogota")),
          new DateTime(record.getFinishDate(), DateTimeZone.forID("America/Bogota"))
        );
    }
}
