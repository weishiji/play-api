package controllers.rest;

import models.*;
import play.Logger;
import play.data.DynamicForm;
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
        User user = new User();
        Form<User> userForm = formFactory.form(User.class).bindFromRequest();
        /**
        *   基本类型的错误，无法验证通过
        * */
        if(userForm.hasErrors()){
            return ok(
                userForm.errorsAsJson()
            );
        }

        DynamicForm requestData = formFactory.form().bindFromRequest();
        String email = requestData.get("email");
        /**
         * 邮箱已被占用
         * */
        if(user.getUserByEmail(email) != null){
            userForm.reject("email","This e-mail is already registered.");
            return ok(
                    userForm.errorsAsJson()
            );
        }

        return ok(
                //Json.toJson(new User().getUserByEmail("cli@chicv.com"))
                Json.toJson(userForm)
        );
    }
}
