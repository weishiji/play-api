package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Category;
import org.apache.xerces.xs.datatypes.ObjectList;
import play.Logger;
import play.api.mvc.ResponseHeader;
import play.http.HttpEntity;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static play.mvc.Controller.response;
import static play.mvc.Http.Status.*;
import static play.mvc.Results.ok;
import static play.mvc.Results.status;


/**
 * Created by lxg on 24/04/2017.
 */
public class ResponseJson{
    static String OK_MESSAGE = "Success"; // 200
    static String CREATE_MESSAGE = "Create Success"; //201
    static String BAD_REQUEST_MESSAGE = "Bad Request"; // 400
    static String UNAUTHORIZED_MESSAGE = "Auth Failed"; // 401
    static String FORBIDDEN_MESSAGE = "Request Forbidden"; // 403
    static String NOT_FOUND_MESSAGE = "Not Found";//404
    static String METHOD_NOT_ALLOWED_MESSAGE = "Method Not Allowed";//405
    static String INTERNAL_SERVER_ERROR_MESSAGE = "Internal Server Error"; //500
    static String BAD_GATEWAY_MESSAGE = "Bad GateWay"; // 502

    /**
     * @param response e.g. Success
     * 很少用
     * */
    public static ObjectNode format(String response){
        ObjectNode result = Json.newObject();
        result.put("message",response);
        return result;
    }
    /**
     * @param status Http status
     * */
    public static ObjectNode format(Integer status){
        ObjectNode result = Json.newObject();

        switch (status){
            case OK://200
                result.put("message",OK_MESSAGE);
                break;
            case CREATED://201
                result.put("message",CREATE_MESSAGE);
                break;
            case BAD_REQUEST://400
                result.put("message",BAD_REQUEST_MESSAGE);
                break;
            case UNAUTHORIZED://401
                result.put("message",UNAUTHORIZED_MESSAGE);
                break;
            case FORBIDDEN://403
                result.put("message",FORBIDDEN_MESSAGE);
                break;
            case NOT_FOUND://404
                result.put("message",NOT_FOUND_MESSAGE);
            case METHOD_NOT_ALLOWED://405
                result.put("message",METHOD_NOT_ALLOWED_MESSAGE);
                break;
            case INTERNAL_SERVER_ERROR://500
                result.put("message",INTERNAL_SERVER_ERROR_MESSAGE);
                break;
            case BAD_GATEWAY://502
                result.put("message",BAD_GATEWAY_MESSAGE);
                break;
            default:
                result.put("message","Unknown Mistake");
                break;
        }

        return result;
    }
    /**
     * @param response JsonNode
     * @param status Http status
     * */
    public static ObjectNode format(JsonNode response, Integer status){
        ObjectNode result = Json.newObject();
        ObjectNode messageResult = format(status);

        result.set("message",messageResult.get("message"));
        result.set("data", response);

        return result;
    }
    /**
     * @param response Model Object List
     * @param status Http status
     * */
    public static ObjectNode format(List<?> response,Integer status){
        ObjectNode result = Json.newObject();
        ObjectNode messageResult = format(status);

        result.set("message",messageResult.get("message"));
        JsonNode jsonNode = Json.toJson(response);
        result.set("data", jsonNode);

        return result;
    }
    /**
     * @param response Model Object
     * @param status Http status
     * */
    public static ObjectNode format(Object response,Integer status){
        ObjectNode result = Json.newObject();
        ObjectNode messageResult = format(status);

        result.set("message",messageResult.get("message"));
        JsonNode jsonNode = Json.toJson(response);
        result.set("data", jsonNode);

        return result;
    }


    /*public static Result format(Callable<Result> func) throws Exception {
        Result result = func.call();
        Logger.info(result.status() + "");
        result.status();
        return  result;
    }
    public static Result format(Result res){
        Logger.info(res.status() + "");
        ObjectNode result = Json.newObject();
        result.set("data",Json.toJson(res));

        return res;
    }*/

}
