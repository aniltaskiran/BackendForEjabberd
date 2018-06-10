package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import manager.DBConnection;
import manager.JsonResponse;
import manager.MakeRequest;
import model.MatchRequestModel;
import model.Response;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewMatchServlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        System.out.println(req.getContentType());

        if (req.getContentType().equals("application/json; charset=UTF-8") || req.getContentType().equals("application/json")){
            try {
                MatchRequestModel model = gson.fromJson(req.getReader(), MatchRequestModel.class);
                apiResponse(resp, model);
            }
            catch (JsonParseException ex){
                JsonResponse.responseJson(resp,"Bad Request");
            }
        } else {
            JsonResponse.responseJson(resp,"Bad Request");
        }
    }


    public void apiResponse(final HttpServletResponse resp, MatchRequestModel model) throws IOException{
        System.out.println("Api response");

        DBConnection dbConnection = new DBConnection();
        Boolean response = dbConnection.newMatchRequest(model);
        Response responseJson = new Response();

        if (response){
            responseJson.setStatus("true");
            responseJson.setCode(200);
            responseJson.setMessage("success");
        } else {
            responseJson.setStatus("error");
            responseJson.setCode(400);
            responseJson.setMessage("false");
        }
        Gson gson = new Gson();
        JsonResponse.responseJson(resp, gson.toJson(responseJson));
    }
}
