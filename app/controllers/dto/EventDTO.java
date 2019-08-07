package controllers.dto;

public class EventDTO {

    private int id;
    private String name;
    private String type;
    private String place;
    private String address;
    private String startDate;
    private String finishDate;

    public EventDTO() {
        // to json serialize
    }

    public EventDTO(int id, String name, String type, String place, String address, String startDate, String finishDate) {
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }
}
