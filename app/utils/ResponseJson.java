package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import static play.mvc.Http.Status.*;


/**
 * Created by lxg on 24/04/2017.
 */
public class ResponseJson {
    static String OK_MESSAGE = "Success"; // 200
    static String BAD_REQUEST_MESSAGE = "Bad Request"; // 400
    static String UNAUTHORIZED_MESSAGE = "Auth Failed"; // 401
    static String FORBIDDEN_MESSAGE = "Request Forbidden"; // 403
    static String NOT_FOUND_MESSAGE = "Not Found";//404
    static String METHOD_NOT_ALLOWED_MESSAGE = "Method Not Allowed";//405
    static String INTERNAL_SERVER_ERROR_MESSAGE = "Internal Server Error"; //500
    static String BAD_GATEWAY_MESSAGE = "Bad GateWay"; // 502

    public static ObjectNode format(Object response){
        ObjectNode result = Json.newObject();
        if(response instanceof String){
            result.put("data",(String) response);
        }else if(response instanceof Integer){
            switch ((Integer) response){
                //case HTTP.BAD_REQUEST
                case OK:
                    result.put("data",OK_MESSAGE);
                    break;
                case BAD_REQUEST:
                    result.put("data",BAD_REQUEST_MESSAGE);
                    break;
                case UNAUTHORIZED:
                    result.put("data",UNAUTHORIZED_MESSAGE);
                    break;
                case FORBIDDEN:
                    result.put("data",FORBIDDEN_MESSAGE);
                    break;
                case NOT_FOUND:
                    result.put("data",NOT_FOUND_MESSAGE);
                case METHOD_NOT_ALLOWED:
                    result.put("data",METHOD_NOT_ALLOWED_MESSAGE);
                    break;
                case INTERNAL_SERVER_ERROR:
                    result.put("data",INTERNAL_SERVER_ERROR_MESSAGE);
                    break;
                case BAD_GATEWAY:
                    result.put("data",BAD_GATEWAY_MESSAGE);
                    break;
                default:
                    result.put("data","Unknown Mistake");
                    break;
            }
        }else{
            JsonNode jsonNode = Json.toJson(response);
            result.set("data", jsonNode);
        }
        return  result;
    }
}
