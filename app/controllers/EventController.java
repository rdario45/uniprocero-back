package controllers;

import com.google.inject.Inject;
import domain.Event;
import infraestructure.acl.EventMapper;
import infraestructure.repository.EventRepository;
import io.vavr.collection.List;
import play.Logger;
import play.libs.Json;
import play.mvc.*;


import static play.mvc.Results.ok;

public class EventController {

  private EventRepository repository;

  @Inject
  public EventController(EventRepository repository) {
    this.repository = repository;
  }

  public Result index() {
    return ok(Json.toJson(repository.listAll().map(EventMapper::eventToDTO)));
  }

}
