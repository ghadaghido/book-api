package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class HomeController extends Controller {

    public Result index() {
        return ok("📚 Books API is running successfully 🚀");
    }
}
