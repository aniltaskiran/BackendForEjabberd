package model;

public class Response {
    String status;
    int code;
    String message;
    String userID;

    public Response (String  status, int code, String message, String userID) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.userID = userID;
    }
    public  Response(){

    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String  getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getUserID() {
        return userID;
    }
}