package domain;

import static org.junit.Assert.*;

import factory.EventFactory;
import infraestructure.acl.EventMapper;
import org.junit.Before;
import org.junit.Test;
import play.libs.Json;

public class EventTest {

    private Event event;

    @Before
    public void forEach(){
        event = new EventFactory().get();
    }

    @Test
    public void testDates() {
        System.out.println(Json.toJson(EventMapper.eventToDTO(event)));
        assertTrue(event.getStartDate().isBefore(event.getFinishDate()));
    }
}