package controller;

import dal.BillDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;
import model.Bill;
import model.BillDetail;
import model.BillDetailForAdmin;
import model.User;

/**
 *
 * @author Admin
 */
public class ManageBillController extends HttpServlet {

    private final String ADMIN_URL = "indexAdmin.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String service = req.getParameter("service");
        req.setAttribute("manageBill", "Yes");
        if (service == null) {
            service = "displayAllBill";
        }
        if (service.equals("displayAllBill")) {
            Vector<BillDetailForAdmin> bill = (new BillDAO()).showBillDetailForAdmin();
            req.setAttribute("billDetailForAdmins", bill);
            req.getRequestDispatcher(ADMIN_URL).forward(req, resp);
        }
        /*
        lấy bên billManager.jsp
        href="manageBill?service=showDetailBill&billId=${b.id}&status=${b.status}">Show Detail</a></td>
         */
        if (service.equals("showDetailBill")) {
            int billId = Integer.parseInt(req.getParameter("billId"));
            String status = req.getParameter("status");

            Vector<BillDetail> billDetails = (new BillDAO()).showBillDetail(billId);

            req.setAttribute("status", status);
            req.setAttribute("billDetails", billDetails);

            req.getRequestDispatcher(ADMIN_URL).forward(req, resp);
        }

        /*
        lấy statusInShowDetail từ ${statusInShowDetail} mà,  <c:set var="statusInShowDetail" value="${status}"/>
        <td><a href="manageBill?service=changeStatusToWait&billId=${billId}&statusInShowDetail=${statusInShowDetail}">Wait</a></td>
                            <td><a href="manageBill?service=changeStatusToProcess&billId=${billId}&statusInShowDetail=${statusInShowDetail}">Process</a></td>
                            <td><a href="manageBill?service=changeStatusToDone&billId=${billId}&statusInShowDetail=${statusInShowDetail}">Done</a></td>
         */
        if (service.startsWith("changeStatusTo")) {
            int billId = Integer.parseInt(req.getParameter("billId"));
            String statusInShowDetail = req.getParameter("statusInShowDetail");
            //check if status is done

//nếu status đnag là done
            if (statusInShowDetail.equals("done")) {
                req.setAttribute("changeStatus", "Status of this bill is done, you can not change it!");
            } else {
                // nếu status đang done, và service lâys từ = changeStatusToWait&billId=${billId}&statusInShowDetail=${statusInShowDetail} mà end with ...
                if (service.endsWith("Wait")) {
                    (new BillDAO()).updateStatus("wait", billId);
                    req.setAttribute("changeStatus", "Admin change status of Bill (ID = " + billId + ") to Wait");
                }

                if (service.endsWith("Process")) {
                    (new BillDAO()).updateStatus("process", billId);
                    req.setAttribute("changeStatus", "Admin change status of Bill (ID = " + billId + ") to Process");

                }

                if (service.endsWith("Done")) {
                    (new BillDAO()).updateStatus("done", billId);
                    req.setAttribute("changeStatus", "Admin change status of Bill (ID = " + billId + ") to Done");
                }

            }
            req.getRequestDispatcher(ADMIN_URL).forward(req, resp);
        }

//                <input type="hidden" name="service" value="searchByAddress"/> 
        if (service.equals("searchByAddress")) {
            /* name="keywords" 
                        value="${keywords}"
             */
            String keywords = req.getParameter("keywords");
            if (keywords != null) {
                keywords = keywords.trim().toLowerCase(); // Xử lý chuỗi tìm kiếm
            }

            Vector<BillDetailForAdmin> billByAddress = (new BillDAO()).getBillByAddress(keywords);
            Vector<BillDetailForAdmin> bill = (new BillDAO()).showBillDetailForAdmin();

            req.setAttribute("keywords", keywords);
            req.setAttribute("manageBill", "YES");
            if (billByAddress == null || billByAddress.isEmpty()) {
                req.setAttribute("billDetailForAdmins", bill);
                req.setAttribute("notFoundBill", "Your keywords do not match with any Address");
            } else {
                req.setAttribute("billDetailForAdmins", billByAddress);
            }
            req.getRequestDispatcher(ADMIN_URL).forward(req, resp);
        }
        if (service.equals("filterStatus")) {
            String filter = req.getParameter("filter");
            Vector<BillDetailForAdmin> bdfas = new Vector<>();
            if (filter.equals("all")) {
                bdfas = (new BillDAO()).showBillDetailForAdmin();
            } else {
                bdfas = (new BillDAO()).showBillDetailForAdminFilterByStatus(filter);
            }
            req.setAttribute("billDetailForAdmins", bdfas);
            req.getRequestDispatcher(ADMIN_URL).forward(req, resp);
        }     
    }
}
