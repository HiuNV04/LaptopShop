package controller;

import dal.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Stack;
import java.util.Vector;
import model.User;

/**
 *
 * @author Admin
 */
public class ManageCustomerController extends HttpServlet {

    private final String ADMIN_URL = "indexAdmin.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Vector<User> customers = (new UserDAO()).getAllCustomer();
        String service = req.getParameter("service");
//check xem service có là null ko, nếu là null thì cho serice= listAllCustomers
        if (service == null) {
            service = "listAllCustomers";
        }
//display danh sách customer 
        if (service.equals("listAllCustomers")) {
            req.setAttribute("manageCustomer", "YES");
            req.setAttribute("allCustomers", customers);
            req.getRequestDispatcher(ADMIN_URL).forward(req, resp);
        }
        /*
        lấy ban từ bên cutsomerManager.jsp
        manageCustomer?service=ban&id=${customer.id}
         */
        if (service.equals("ban")) {
            int id = Integer.parseInt(req.getParameter("id"));
            (new UserDAO()).banAnUser(id);

            customers = (new UserDAO()).getAllCustomer();
            req.setAttribute("manageCustomer", "Yes");
            req.setAttribute("allCustomers", customers);
            req.getRequestDispatcher(ADMIN_URL).forward(req, resp);
        }
        /*
        name="service" value="searchByKeywords
        lấy ban từ bên cutsomerManager.jsp
         */
        if (service.equals("searchByKeywords")) {
            /*
            lấy parameter keywords từ bên cutsomerManager.jsp
             name="keywords" 
             */
            String keywords = req.getParameter("keywords");
//set keywords rồi gửi sang bên jsp kia
            req.setAttribute("keywords", keywords);
            req.setAttribute("manageCustomer", "Yes");

            customers = (new UserDAO()).getCustomerByName(keywords);

            if (customers == null || customers.isEmpty()) {
                req.setAttribute("notFoundCustomer", "Your keywords do not match with any Customer Name");
                customers = (new UserDAO()).getAllCustomer();
            }
            req.setAttribute("allCustomers", customers);
            req.getRequestDispatcher(ADMIN_URL).forward(req, resp);
        }
    }

}
