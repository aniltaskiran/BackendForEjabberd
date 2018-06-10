package manager;

import com.google.gson.JsonObject;
import model.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonResponse {

     static public void responseJson(HttpServletResponse response, String json) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println(json);
        out.close();
    }
}
