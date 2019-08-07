package controllers;

import play.mvc.*;

import static play.mvc.Results.ok;

public class EventController {

    public Result index() {
        return ok("It works!");
    }

}
