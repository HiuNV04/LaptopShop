package controller;

import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.GoogleAccount;
import model.User;

public class LoginGoogle extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String code = request.getParameter("code");
        String error = request.getParameter("error");
        
        HttpSession session = request.getSession();
        String returnUrl = request.getParameter("state");
        
        if (error != null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        
        Google gg = new Google();
        String accessToken = gg.getToken(code);
        GoogleAccount googleAccount = gg.getUserInfo(accessToken);
        
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByGoogleEmail(googleAccount.getEmail());
        
        if (user == null) {
            user = new User();
            user.setFullname(googleAccount.getFamily_name() + " " + googleAccount.getGiven_name());
            user.setPhone("");
            user.setUsername(googleAccount.getName());
            user.setEmail(googleAccount.getEmail());
            user.setRole_id(1);
            
            userDAO.insertUserFromGoogle(user);
            user = userDAO.getUserByGoogleEmail(googleAccount.getEmail());
        }
        
        session.setAttribute("user", user);
        response.sendRedirect("customer");
//        if (returnUrl != null && !returnUrl.isEmpty()) {
//            response.sendRedirect(returnUrl);
//        } else {
//            switch (user.getRole_id()) {
//                case 4:
//                    response.sendRedirect("customer");
//                    break;
//                case 1:
//                    response.sendRedirect("customer");
//                    break;
//                case 3:
//                    response.sendRedirect("customer");
//                    break;
//                case 5:
//                    response.sendRedirect("customer");
//                    break;
//                case 2:
//                    response.sendRedirect("customer");
//                    break;
//                default:
//                    break;
//            }
//        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
