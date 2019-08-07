package infraestructure.repository;

import infraestructure.datasource.PostgresDB;

public class EventRepository {

    private PostgresDB db;

    public EventRepository(PostgresDB db) {
        this.db = db;
    }

}
