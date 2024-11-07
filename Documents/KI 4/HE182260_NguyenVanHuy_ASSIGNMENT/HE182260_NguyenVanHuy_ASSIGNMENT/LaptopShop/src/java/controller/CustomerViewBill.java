/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BillDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.*;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CustomerViewBill", urlPatterns = {"/view"})
public class CustomerViewBill extends HttpServlet {

    private final String VIEW_URL = "orderOfUser.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String service = request.getParameter("service");
        if (service == null) {
            service = "showListOrder";
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user"); // lấy session của user hiện tại để lấy id user đó
        if (user == null) {
            response.sendRedirect("login");
            return;
        } else {
            if (service.equals("showListOrder")) {
                Vector<BillForCustomer> billDetailForUsers = (new BillDAO()).showBillDetailForUser(user.getId());
                request.setAttribute("billDetailForUsers", billDetailForUsers);
                request.getRequestDispatcher(VIEW_URL).forward(request, response);
            }
        }
    }

}
