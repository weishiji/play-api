package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.Logger;
import play.api.mvc.ResponseHeader;
import play.http.HttpEntity;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
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
            case OK:
                result.put("message",OK_MESSAGE);
                break;
            case BAD_REQUEST:
                result.put("message",BAD_REQUEST_MESSAGE);
                break;
            case UNAUTHORIZED:
                result.put("message",UNAUTHORIZED_MESSAGE);
                break;
            case FORBIDDEN:
                result.put("message",FORBIDDEN_MESSAGE);
                break;
            case NOT_FOUND:
                result.put("message",NOT_FOUND_MESSAGE);
            case METHOD_NOT_ALLOWED:
                result.put("message",METHOD_NOT_ALLOWED_MESSAGE);
                break;
            case INTERNAL_SERVER_ERROR:
                result.put("message",INTERNAL_SERVER_ERROR_MESSAGE);
                break;
            case BAD_GATEWAY:
                result.put("message",BAD_GATEWAY_MESSAGE);
                break;
            default:
                result.put("message","Unknown Mistake");
                break;
        }

        return result;
    }
    /**
     * @param response Model Object
     * @param status Http status
     * */
    public static ObjectNode format(List<Object> response, Integer status){
        ObjectNode result = Json.newObject();
        ObjectNode messageResult = format(status);


        result.set("message",messageResult.get("message"));
        JsonNode jsonNode = Json.toJson(response);
        result.set("data", jsonNode);

        return result;
    }


    public static Result format(Callable<Result> func) throws Exception {
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
    }

}
