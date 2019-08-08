package domain;

import factory.EventFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EventTest {

    private Event event;

    @Before
    public void forEach(){
        event = new EventFactory().get();
    }

    @Test
    public void testDates() {
        assertTrue(event.getStartDate().isBefore(event.getFinishDate()));
    }
}