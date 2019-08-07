package infraestructure.acl;

import domain.Event;
import domain.Type;
import org.joda.time.DateTime;

public class EventBuilder {

  public static Event build(String name,
                            Type type,
                            String place,
                            String address,
                            DateTime startDate,
                            DateTime finishDate) {
    return new Event(0, name, type, place, address, startDate, finishDate);
  }

  public static Event build(int id,
                            String name,
                            Type type,
                            String place,
                            String address,
                            DateTime startDate,
                            DateTime finishDate) {
    return new Event(id, name, type, place, address, startDate, finishDate);
  }
}
