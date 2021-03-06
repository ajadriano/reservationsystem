/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.user.web;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 
public class SessionBean {
 
    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }
 
    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }
 
    public static String getUsername() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        
        Object attribute = session.getAttribute("username");
        if (attribute != null) {
            return attribute.toString();
        }
        return null;
    }
    
    public static void setUsername(String username) {
        HttpSession session = getSession();
        session.setAttribute("username", username);
    }
 
    public static String getUserId() {
        HttpSession session = getSession();
        if (session != null)
            return (String) session.getAttribute("userid");
        else
            return null;
    }
}
