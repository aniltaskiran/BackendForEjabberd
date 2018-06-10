package manager;

import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import manager.JsonResponse;
import model.User;


public class MakeRequest {
    static String endpoint = "http://174.138.12.182:5281/api/";
    static public String checkUser(User usr) {
        JsonResponse json = new JsonResponse();
        Gson gson = new Gson();
        try {
            com.mashape.unirest.http.HttpResponse<String> jsonResponse = Unirest.post(endpoint + "check_password")
                    .header("accept", "application/json")
                    .body(gson.toJson(usr))
                    .asString();
            System.out.println(jsonResponse.getBody());
            return jsonResponse.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
            return null;
        }
    }
    static public String registerUser(User usr) {
        JsonResponse json = new JsonResponse();
        Gson gson = new Gson();
        try {
            com.mashape.unirest.http.HttpResponse<String> jsonResponse = Unirest.post(endpoint + "register")
                    .header("accept", "application/json")
                    .body(gson.toJson(usr))
                    .asString();
            System.out.println(jsonResponse.getBody());
            return jsonResponse.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
            return null;
        }
    }
}
