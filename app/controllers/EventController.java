package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import controllers.dto.EventDTO;
import infraestructure.acl.EventMapper;
import infraestructure.acl.EventValidator;
import infraestructure.repository.EventRepository;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Try;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Results;

import static play.mvc.Http.Context.Implicit.request;
import static play.mvc.Results.internalServerError;
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

  public Result find(int index) {
    return repository.find(index)
      .map(EventMapper::eventToDTO)
      .map(Json::toJson)
      .toEither(getNotFoundJsonMessage(index))
      .fold(Results::notFound, Results::ok);
  }

  public Result save() {
    JsonNode json = request().body().asJson();

    return getEventDTO(json)
      .flatMap(EventValidator::validate)
      .mapLeft(this::getValidationErrorMessage)
      .mapLeft(Results::badRequest)
      .map(event -> repository.save(event))
      .flatMap( future -> future.onFailure(throwable -> Logger.error("Error saving event!", throwable))
        .toEither(internalServerError(getJsonErrorMessage("Error saving event!")))
      ).map(EventMapper::eventToDTO)
      .map(Json::toJson)
      .fold(result -> result, Results::ok);
  }

  private Either<List<String>, EventDTO> getEventDTO(JsonNode json) {
    return Try.of(() -> Json.fromJson(json, EventDTO.class))
      .onFailure(throwable -> Logger.error("Invalid Json", throwable))
      .toEither(List.of("Invalid Json"));
  }

  private ObjectNode getValidationErrorMessage(List<String> errors) {
    return Json.newObject()
      .put("message", "Validation errors!")
      .put("fields", String.join(", ", errors));
  }

  private ObjectNode getNotFoundJsonMessage(int index) {
    return Json.newObject().put("message", "id " + index + " not found");
  }

  private ObjectNode getJsonErrorMessage(String message) {
    return Json.newObject().put("message", message);
  }

}
