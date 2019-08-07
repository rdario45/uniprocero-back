package controllers;

import domain.Event;
import domain.Type;
import infraestructure.acl.EventMapper;
import org.joda.time.DateTime;
import play.libs.Json;
import play.mvc.*;

import static play.mvc.Results.ok;

public class EventController {

    public Result index() {
        return ok(
                Json.toJson(
                        EventMapper.eventToDTO(
                                new Event(
                                        "Test Event",
                                        Type.CURSO,
                                        "Some place",
                                        "Address",
                                        DateTime.now(),
                                        DateTime.now().plusMinutes(120)
                                )
                        )

                )
        );
    }

}
