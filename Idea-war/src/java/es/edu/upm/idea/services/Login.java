/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.edu.upm.idea.services;

import es.edu.upm.idea.controllers.UsuarioController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author allancalderon
 */
@ManagedBean(name = "login")
@SessionScoped
public class Login {

    private String nombre;
    private String password;
    private String dbPassword;
    private String dbName;
    DataSource ds;

    boolean isLoginPage = (FacesContext.getCurrentInstance().getViewRoot()
            .getViewId().lastIndexOf("login.xhtml") > -1);

    public Login() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("jdbc/IdeaDS");
            //ds = (DataSource) ctx.lookup("java:comp/env/jdbc/database");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getDbName() {
        return dbName;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String add() {
        
        UsuarioController controller = new UsuarioController();
        return controller.create(nombre, nombre, password);
        
        /*
        int i = 0;
        if (nombre != null) {
            PreparedStatement ps = null;
            Connection con = null;
            try {
                if (ds != null) {
                    con = ds.getConnection();
                    if (con != null) {
                        //create procedure user_insert(in id_perfil int, in nombre_ text, in correo_ text, in password_ text, out id int)
                        String sql = "{call user_insert(?,?,?,?,?)}";
                        ps = con.prepareCall(sql);
                        ps.setInt(1, 2);
                        ps.setString(2, nombre);
                        ps.setString(3, "corrego@gmail.com");
                        ps.setString(4, password);                        
                        ps.setInt(5, i);
                        ps.execute();
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    con.close();
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (i > 0) {
            return "success";
        } else {
            return "unsuccess";
        }
                */
    }

    public void dbData(String uName) {
        if (uName != null) {
            PreparedStatement ps = null;
            Connection con = null;
            ResultSet rs = null;

            if (ds != null) {
                try {
                    con = ds.getConnection();
                    if (con != null) {
                        String sql = "select nombre,password from usuario where nombre = '"
                                + uName + "'";
                        ps = con.prepareStatement(sql);
                        rs = ps.executeQuery();
                        rs.next();
                        dbName = rs.getString("nombre");
                        dbPassword = rs.getString("password");
                    }
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    public String login() {
        dbData(nombre);
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        if (isLoginPage && (nombre.equals(dbName) && password.equals(dbPassword))) {
            FacesContext.getCurrentInstance().getExternalContext()
                    .getSessionMap().put("username", nombre);
            if (session == null) {
                FacesContext
                        .getCurrentInstance()
                        .getApplication()
                        .getNavigationHandler()
                        .handleNavigation(FacesContext.getCurrentInstance(),
                                null, "/login.xhtml");
            } else {
                Object currentUser = session.getAttribute("nombre");
                if (!isLoginPage && (currentUser == null || currentUser == "")) {
                    FacesContext
                            .getCurrentInstance()
                            .getApplication()
                            .getNavigationHandler()
                            .handleNavigation(
                                    FacesContext.getCurrentInstance(), null,
                                    "/login.xhtml");
                }
            }
            return "success";
        } else {
            return "unsuccess";
        }
    }

    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();
        FacesContext
                .getCurrentInstance()
                .getApplication()
                .getNavigationHandler()
                .handleNavigation(FacesContext.getCurrentInstance(), null,
                        "/login.xhtml");
    }
}
