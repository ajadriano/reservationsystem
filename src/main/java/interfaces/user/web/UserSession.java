/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.user.web;

import java.io.Serializable;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
 
@ManagedBean
@SessionScoped
public class UserSession implements Serializable {
 
    private static final long serialVersionUID = 1094801825228386363L;
 
    public String getMsg() {
        String username = SessionBean.getUsername();
        if (username != null && !username.isEmpty())
        {
            return "User: " + username;
        }
        
        return "";
    }
    
    public boolean isLoggedIn() {
        String username = SessionBean.getUsername();
        return username != null && !username.isEmpty();
    }
    
    public boolean isLoggedOut() {
        return !isLoggedIn();
    }
    
    public boolean isOrdinaryUser() {
        String username = SessionBean.getUsername();
        return username == null || username.isEmpty() ||
                !username.equals("administrator");
    }
    
    public boolean isNotAdministrator() {
        String username = SessionBean.getUsername();
        return username != null && !username.isEmpty() &&
                !username.equals("administrator");
    }
    
    public boolean isAdministrator() {
        String username = SessionBean.getUsername();
        return username != null && !username.isEmpty() &&
                username.equals("administrator");
    }
 
    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "/admin/index.xhtml?faces-redirect=true";
    }
}
