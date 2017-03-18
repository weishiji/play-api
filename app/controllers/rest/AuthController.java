package controllers.rest;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by lxg on 18/03/2017.
 * 用户登录注册
 */
public class AuthController extends Controller {
    
    public Result save(){

        return ok(
                "Hello"
        );
    }
}
