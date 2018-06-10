package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import manager.JsonResponse;
import manager.MakeRequest;
import model.Response;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        System.out.println(req.getContentType());

        if (req.getContentType().equals("application/json; charset=UTF-8") || req.getContentType().equals("application/json")){
            try {
                User usr = gson.fromJson(req.getReader(), User.class);
                apiResponse(resp, usr);
            } catch (JsonParseException ex) {
                System.out.println(ex.getLocalizedMessage());
                JsonResponse.responseJson(resp, "Bad Request");
            }
        } else {
            JsonResponse.responseJson(resp,"Bad Request");
        }
    }


    public void apiResponse(final HttpServletResponse resp, User usr) throws IOException{
        System.out.println("Api response");
// FIXME önce maili kontrol edip id'yi getirmeli

        String response = MakeRequest.checkUser(usr);
        Gson gson = new Gson();
        Response responseJson = new Response();

        if (response == null){
            responseJson.setStatus("error");
            responseJson.setCode(400);
            responseJson.setMessage("null exception");
        } else if (response.contains("0")){
            responseJson.setStatus("true");
            responseJson.setCode(200);
            responseJson.setMessage("success");
        } else if (response.contains("1")){
            responseJson.setStatus("error");
            responseJson.setCode(400);
            responseJson.setMessage("false");
        }

        JsonResponse.responseJson(resp, gson.toJson(responseJson));
    }
}
