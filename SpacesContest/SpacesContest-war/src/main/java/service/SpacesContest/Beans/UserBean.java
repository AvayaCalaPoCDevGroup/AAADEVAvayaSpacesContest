package service.SpacesContest.Beans;

import java.io.Serializable;

/**
 *
 * @author umansilla
 */
public class UserBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userName;
    private String rol;
    private String JWTAccess;
    private String JWTRefresh;

    public UserBean() {
    }

    public UserBean(String userName, String rol, String JWTAccess, String JWTRefresh) {
        this.userName = userName;
        this.rol = rol;
        this.JWTAccess = JWTAccess;
        this.JWTRefresh = JWTRefresh;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getJWTAccess() {
        return JWTAccess;
    }

    public void setJWTAccess(String JWTAccess) {
        this.JWTAccess = JWTAccess;
    }

    public String getJWTRefresh() {
        return JWTRefresh;
    }

    public void setJWTRefresh(String JWTRefresh) {
        this.JWTRefresh = JWTRefresh;
    }
}