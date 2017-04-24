package utils;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;

/**
 * Created by lxg on 24/04/2017.
 */
public class ResponseJson {
    public static ObjectNode format(Object response){
        ObjectNode result = Json.newObject();
        if(response instanceof String){
            result.put("data",(String) response);
        }else{
            JsonNode jsonNode = Json.toJson(response);
            result.set("data", jsonNode);
        }
        return  result;
    }
}
