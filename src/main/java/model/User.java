package model;

public class User {
    private String user;
    private String host = "174.138.12.182";
    private String password;
    private String email;
    private String id;
    private String name;
    private String steam_link;
    private String jid;



    public User (String username, String password) {
        this.user = username;
        this.password = password;
        this.host = "174.138.12.182";
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHostname(String hostname) {
        this.host = hostname;
    }

    public void setUsername(String username) {
        this.user = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public String getHost() {
        return host;
    }

    public String getJid() {
        return jid;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return user;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSteam_link() {
        return steam_link;
    }
}
