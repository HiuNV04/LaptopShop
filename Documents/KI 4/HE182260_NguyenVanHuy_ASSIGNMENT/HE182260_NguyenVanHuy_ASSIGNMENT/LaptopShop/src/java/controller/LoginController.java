package controller;

import dal.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import model.User;

/**
 *
 * @author Admin
 */
public class LoginController extends HttpServlet {

    String err = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                    

//        response.setContentType("text/html;charset=UTF-8");
//        String code = request.getParameter("code");
//        String error = request.getParameter("error");
//        //neu nguoi dung huy uy quyen
//        if (error != null) {
//            response.sendRedirect("customer");
//        }
//        GoogleLogin gg = new GoogleLogin();
//        String accessToken = gg.getToken(code);
//        User acc = gg.getUserInfo(accessToken);
//        System.out.println(acc);
        //check tk da dky chua, làm method getAccountByEmail,if emial != null, lưu vào session, nếu tk chưa tồn tại thì gọi đến insertAccount để thêm tk

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // User user = (new UserDAO()).login(username, password);
        UserDAO u = new UserDAO();
        User user = u.login(username, password);
        //Login flow
        if (user == null) {
            req.setAttribute("usernameOrPasswordWrong", "Username or Password is invalid!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else if (user.getRole_id() == 0) {
            session.setAttribute("user", user);
            resp.sendRedirect("indexAdmin.jsp");

        } else if (user.getRole_id() == 1 && user.getBanned() == 0) {
            session.setAttribute("user", user);
            resp.sendRedirect("customer");
        } else if (user.getRole_id() == 1 && user.getBanned() == 1) {
            req.getRequestDispatcher("accessDenied.jsp").forward(req, resp);
            session.removeAttribute("user");
        }
    }
}
