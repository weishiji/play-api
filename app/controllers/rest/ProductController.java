package controllers.rest;

import models.*;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;


import javax.sound.sampled.Control;

/**
 * Created by lxg on 17/03/2017.
 */
public class ProductController extends Controller{

    public Result list(){
        Product product = new Product();

        return ok(
                //"hello World"
                Json.toJson(product.list())
        );
    }
}
