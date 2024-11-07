 
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.User;

/**
 *
 * @author Admin
 */
public class AdminController extends HttpServlet {

    private final String ADMIN_URL = "indexAdmin.jsp";

    @Override
    /*
 req.getSession():lấy ra đối tượng HttpSession liên kết với yêu cầu hiện tại. 
    Đối tượng HttpSession cho phép lưu trữ thông tin trạng thái của phiên làm việc giữa các yêu cầu từ cùng một người dùng.
Nếu phiên làm việc chưa tồn tại, nó sẽ tạo mới một phiên làm việc.
User user = (User) session.getAttribute("user");

session.getAttribute("user") lấy ra đối tượng User được lưu trong phiên làm việc với tên "user".
Đối tượng User thường là đối tượng đại diện cho người dùng hiện đang đăng nhập vào hệ thống.
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getRole_id() == 1 && user.getBanned() == 1) {
            session.setAttribute("user", user);
            req.getRequestDispatcher("accessDenied.jsp").forward(req, resp);
            session.removeAttribute("user");

        } else {
            resp.sendRedirect(ADMIN_URL);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getRole_id() == 1 && user.getBanned() == 1) {
            req.getRequestDispatcher("accessDenied.jsp").forward(req, resp);
            session.removeAttribute("user");
        } else {
            resp.sendRedirect(ADMIN_URL);
        }
    }

}
