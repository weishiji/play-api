package controllers.rest;

import play.mvc.Controller;
import models.*;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by lxg on 05/04/2017.
 */
public class CategoryController  extends Controller{

    public Result list(){
        Category category = new Category();
        return ok(
                //"hello World"
                Json.toJson(category.list())
        );

    }
}
