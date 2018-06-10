package manager;

import model.MatchRequestModel;
import model.ReturnUser;
import model.User;

import java.sql.*;

public class DBConnection {
    enum DB_TABLE_NAMES{
        TB_MATCH, TB_MATCH_LOG, TB_SUBSCRIBER,TB_IP_ADDR;
    }
    enum TB_MATCH{
        ID,SUBSCRIBER_ID,ROSTER_ID,COUNT;
    }
    enum TB_MATCH_LOG{
        SUBSCRIBER_ID,ROSTER_ID,STATUS;
    }
    enum TB_SUBSCRIBER{
        ID,NAME,EMAIL,PASSWORD,STEAM_LINK,CREATE_USER;
    }

    private  String jdbcDriverStr = "com.mysql.cj.jdbc.Driver";
    private  String jdbcURL = "jdbc:mysql://localhost:3306/ejabberdBackend?useUnicode=true&characterEncoding=UTF-8";
    private  String localHostUser = "root";
    private  String localHostPassword = "M1405.010041m";
    private  String remoteHostUser = "jkglj";
    private  String remoteHostPassword = "a8f38f726da3c612cf9dfd0a2aa686425c6919367b0bf6c2";

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private void startConnection(){
        try {
            Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection(jdbcURL,localHostUser,localHostPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUserID(String jID) {
        int userID = -100;
        String sqlStatement = String.format("Select " + TB_SUBSCRIBER.ID.toString() + " from "+ DB_TABLE_NAMES.TB_SUBSCRIBER.toString() +" where JID='%s';", jID);
        ResultSet resultSet = executeQuery(sqlStatement);
        userID = getUserID(resultSet);
        if (userID < 0){
           return "null";
        }

        close();

        return String.valueOf(userID);
    }
    public Boolean checkEmailAdressExist(String email){
        String sqlStatement = String.format("Select " +
                TB_SUBSCRIBER.EMAIL.toString() + " from "+
                DB_TABLE_NAMES.TB_SUBSCRIBER.toString() +
                " where EMAIL='%s';", email);
        ResultSet resultSet = executeQuery(sqlStatement);
        Boolean isExist = checkEmailAdress(resultSet);
        close();
        return isExist;
    }
    public Boolean registerUser(User usr){
            startConnection();
                    /*    INSERT INTO TB_SUBSCRIBER
                    (JID, NAME, HOST, EMAIL, PASSWORD, STEAM_LINK, CREATE_TIME)
            VALUES ("anilss@","Anıl T.","124.523","a@ad.com","1234",null,NOW());
*/
            String sqlStatement =
                    String.format("INSERT INTO TB_SUBSCRIBER " +
                            "(JID, NAME, HOST, EMAIL, PASSWORD, STEAM_LINK, CREATE_TIME)" +
                            "VALUES ('%s','%s', '%s','%s','%s','%s',NOW());",
                            usr.getJid(),
                            usr.getName(),
                            usr.getHost(),
                            usr.getEmail(),
                            usr.getPassword(),
                            usr.getSteam_link());

            return executeUpdate(sqlStatement);
    }

    public Boolean newMatchRequest(MatchRequestModel model){
            startConnection();
            String sqlStatement =
                    String.format("INSERT INTO "+ DB_TABLE_NAMES.TB_MATCH_LOG.toString()
                                    +" values ('%s','%s',false,NOW());", model.getFrom(), model.getTo());
            return executeUpdate(sqlStatement);
    }

    private int getUserID(ResultSet resultSet) {
            try {
                while(resultSet.next()) {
                    return resultSet.getInt(TB_SUBSCRIBER.ID.toString());
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return -100;
            }
        return -100;
    }

    private Boolean checkEmailAdress(ResultSet resultSet){
        try {
            while(resultSet.next()){
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private ResultSet executeQuery(String query) {
        try {
            startConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Boolean executeUpdate(String query){
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
            return false;
        } finally{
            close();
        }
    }

    private void close(){
        try {
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        } catch(Exception e){}
    }
}