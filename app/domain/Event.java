package domain;

import org.joda.time.DateTime;

public class Event {

    private int id;
    private String name;
    private Type type;
    private String place;
    private String address;
    private DateTime startDate;
    private DateTime finishDate;

    public Event() {
    }

    public Event(int id, String name, Type type, String place, String address, DateTime startDate, DateTime finishDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.place = place;
        this.address = address;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(DateTime finishDate) {
        this.finishDate = finishDate;
    }
}
