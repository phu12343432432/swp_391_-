/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import DAO.UserManageDAO;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AdminController", urlPatterns = {"/admin"})
public class AdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            String action = request.getParameter("action") == null ? "" : request.getParameter("action");
            String url = "";
            if (session != null && session.getAttribute("USER") != null) {
                switch (action) {
                    case "dashboard": {
                        url = "admin/dashboard.jsp";
                        request.getRequestDispatcher(url).forward(request, response);
                        break;
                    }
                    case "list-request-create-league": {
                        viewListRequest(request, response);
                        break;
                    }
                    case "process-request": {
                        acceptRequest(request, response);
                        break;
                    }
                    case "reject": {
                        rejectRequest(request, response);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private void acceptRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            String userIdS = request.getParameter("userId");
            String action = request.getParameter("decide");
            if (userIdS != null) {
                int userId = Integer.parseInt(userIdS);
                UserManageDAO userManageDAO = new UserManageDAO();
                if (action.equals("accept")) {
                    boolean result = userManageDAO.acceptPermissionRequest(userId);
                    request.setAttribute("MESSAGE", "Accept user's request create league permission id - " + userIdS);
                } else {
                    boolean result = userManageDAO.rejectPermissionRequest(userId);
                    request.setAttribute("MESSAGE", "Reject user's request create league permission id - " + userIdS);
                }
                request.getRequestDispatcher("admin?action=list-request-create-league").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void rejectRequest(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void viewListRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = "";
            String indexS = request.getParameter("index");
            if (indexS == null) {
                indexS = "1";
            }
            int index = Integer.parseInt(indexS);
            UserManageDAO userMangeDAO = new UserManageDAO();
            int total = userMangeDAO.getListUserRequiredCreateLeaguePermissionTotal();
            List<User> listUser = userMangeDAO.getListUserRequiredCreateLeaguePermission(index);
            request.setAttribute("USER", listUser);
            url = "views/manage/manage.jsp";

            int lastPage = total / 10;
            if (total % 10 != 0) {
                lastPage++;
            }
            request.setAttribute("endP", lastPage);
            request.setAttribute("selectedPage", index);
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
