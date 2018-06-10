package servlet;

import com.google.gson.JsonParseException;
import manager.DBConnection;
import manager.MakeRequest;

import com.google.gson.Gson;

import manager.JsonResponse;
import model.Response;
import model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RegisterUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Gson gson = new Gson();
        System.out.println(req.getContentType());

        if (req.getContentType().equals("application/json; charset=UTF-8") || req.getContentType().equals("application/json")){
            try {
                User usr = gson.fromJson(req.getReader(), User.class);
                if (usr.getEmail() == null) {
                    badRequest(resp);
                }
                apiResponse(resp, usr);
            } catch (JsonParseException ex) {
                badRequest(resp);
                System.out.println(ex.getLocalizedMessage());
            }
        } else {
            badRequest(resp);
        }
    }

    public void badRequest(HttpServletResponse resp){
        Gson gson = new Gson();
        Response responseJson = new Response();
        responseJson.setMessage("Bad Request");
        responseJson.setCode(400);
        responseJson.setStatus("error");
        try {
            JsonResponse.responseJson(resp,gson.toJson(responseJson));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void badRequest(HttpServletResponse resp, Response json){
        Gson gson = new Gson();
        try {
            JsonResponse.responseJson(resp,gson.toJson(json));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void apiResponse(final HttpServletResponse resp, User usr) throws IOException{
        System.out.println("Api response");
        User xmppUser = new User(usr.getUsername(), usr.getPassword());
        DBConnection dbConnection = new DBConnection();
        if (dbConnection.checkEmailAdressExist(usr.getEmail())){
            Response responseJson = new Response();
            responseJson.setMessage("Email exist");
            responseJson.setCode(401);
            responseJson.setStatus("error");
            badRequest(resp, responseJson);
        } else {
            String response = MakeRequest.registerUser(xmppUser);
            Gson gson = new Gson();
            try {
                Response responseJson = gson.fromJson(response, Response.class);
                System.out.println(responseJson.getStatus());
                System.out.println(responseJson.getCode());
                System.out.println(responseJson.getMessage());

                JsonResponse.responseJson(resp, response);
            } catch (JsonParseException ex) {
                System.out.println("json parse exception: " + ex.getLocalizedMessage());
                System.out.println("response is: " + response);

                if (response.contains("successfully registered")) {

                    usr.setJid(usr.getUsername() + "@" + xmppUser.getHost());
                    dbConnection.registerUser(usr);
                    String userID = dbConnection.getUserID(usr.getJid());

                    Response responseObject = new Response("true",200,response, userID);
                    JsonResponse.responseJson(resp, gson.toJson(responseObject));
                }
            }
        }

    }
}
