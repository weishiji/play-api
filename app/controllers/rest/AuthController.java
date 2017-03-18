package controllers.rest;

import models.*;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

/**
 * Created by lxg on 18/03/2017.
 * 用户登录注册
 */
public class AuthController extends Controller {
    private FormFactory formFactory;

    @Inject
    public AuthController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }


    public Result save(){
        Form<User> userForm = formFactory.form(User.class).bindFromRequest();

        if(userForm.hasErrors()){
            return ok(
                userForm.errorsAsJson()
            );
        }
        return ok(
//                userForm.errorsAsJson()
                //Json.toJson(new User().getUserByEmail("cli@chicv.com"))
        );
    }
}
