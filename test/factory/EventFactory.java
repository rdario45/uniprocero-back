package factory;

import com.github.javafaker.Faker;
import domain.Event;
import domain.Type;
import org.joda.time.DateTime;

public class EventFactory {

    Faker faker ;
    private int id;
    private String name;
    private Type type;
    private String place;
    private String address;
    private DateTime startDate;
    private DateTime finishDate;

    public EventFactory() {
        Type[] typeValues = Type.values();
        faker = new Faker();
        this.id = faker.number().randomDigit();
        this.name = faker.gameOfThrones().house();
        this.type = Type.values()[typeValues.length-1] ;
        this.place = faker.gameOfThrones().city();
        this.address = faker.address().fullAddress();
        this.startDate = DateTime.now();
        this.finishDate = this.startDate.plusHours(1);
    }

    public Event get(){
        return new Event(
          this.id,
          this.name,
          this.type,
          this.place,
          this.address,
          this.startDate,
          this.finishDate
        );
    }

}
