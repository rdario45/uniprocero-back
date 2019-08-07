package infraestructure.repository;

import java.sql.Timestamp;

public class EventRecord {

    private int id;
    private String name;
    private String type;
    private String place;
    private String address;
    private Timestamp startDate;
    private Timestamp finishDate;

    public EventRecord(int id, String name, String type, String place, String address, Timestamp startDate, Timestamp finishDate) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Timestamp finishDate) {
        this.finishDate = finishDate;
    }
}
