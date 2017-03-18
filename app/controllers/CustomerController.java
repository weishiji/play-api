package controllers;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by lxg on 18/03/2017.
 */
public class CustomerController extends Controller {
    @Security.Authenticated
    public Result index(){
        session("hello", "user@gmail.com");
        String user = session("hello");
        return ok(
                user
        );
    }
}
