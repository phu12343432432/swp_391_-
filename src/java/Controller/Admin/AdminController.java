/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import DAO.DashboardDAO;
import DAO.LeagueDAO;
import DAO.UserManageDAO;
import DAO.UserWalletDAO;
import Model.User;
import Model.UserWalletOrder;
import Model.ViewModel.UserWalletOrderVM;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

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
                        url = "views/manage/dashboard.jsp";
                        // Get the total number of users     
                        UserManageDAO userManageDAO = new UserManageDAO();
                        int totalUsers = userManageDAO.GetAllUserTotal();
                        // Get the total number of active leagues    
                        LeagueDAO leagueDAO = new LeagueDAO();
                        int totalActiveLeagues = leagueDAO.getActiveLeasguesCount();
                        // Get the total amount in user wallets                   
                        UserWalletDAO userWalletDAO = new UserWalletDAO();
                        float totalWalletBalance = userWalletDAO.getTotalWalletBalance();
                        // Set the attributes and forward to the dashboard.jsp                 

                        LocalDateTime now = LocalDateTime.now();
                        int currentMonth = now.getMonthValue();
                        int currentYear = now.getYear();
                        String months = request.getParameter("month");
                        if (months != null) {
                            currentMonth = Integer.parseInt(months);
                        }

                        float monthlyWalletRevenueCurentMonth = 0.0f;
                        List<UserWalletOrder> monthlyWalletOrders = userWalletDAO.getMonthlyWalletOrders(currentMonth, currentYear);
                        for (int i = 1; i <= 12; i++) {
                            float revenueMoneth = 0.0f;
                            List<UserWalletOrder> monthlyWalletOrdersMonth = userWalletDAO.getMonthlyWalletOrders(i, currentYear);
                            for (UserWalletOrder order : monthlyWalletOrdersMonth) {
                                System.out.println("Month" + i + " " + order.getAmmount());
                                if (order.getAmmount() == 0) {
                                    revenueMoneth += 0;
                                } else {
                                    revenueMoneth += order.getAmmount();
                                }
                                if (i == currentMonth) {
                                    monthlyWalletRevenueCurentMonth += order.getAmmount();
                                }
                            }
                            request.setAttribute("REVENUE_MOUNTH_" + i, revenueMoneth);
                        }
                        UserWalletDAO walletDAO = new UserWalletDAO();
                        List<UserWalletOrderVM> orders = walletDAO.getWalletOrdersPending();

                        DashboardDAO dashboardDAO = new DashboardDAO();
                        Map<YearMonth, Integer> monthlyLeagueFinish = dashboardDAO.getMonthlyFinishedLeague();
                        Map<YearMonth, Integer> monthlyLeague = dashboardDAO.getMonthlyLeague();
                        request.setAttribute("MONTHLY_FINISHED_LEAGUE", monthlyLeagueFinish);
                        request.setAttribute("MONTHLY_LEAGUE", monthlyLeague);
                        request.setAttribute("currentMonth", currentMonth);
                        request.setAttribute("MONTHLY_WALLET_REVENUE", monthlyWalletRevenueCurentMonth);
                        request.setAttribute("WALLET_ORDERS", monthlyWalletOrders);
                        request.setAttribute("TOTAL_USERS", totalUsers);
                        request.setAttribute("TOTAL_ACTIVE_LEAGUES", totalActiveLeagues);
                        request.setAttribute("TOTAL_WALLET_BALANCE", totalWalletBalance);
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
            int lastPage = total / 10;
            if (total % 10 != 0) {
                lastPage++;
            }
            request.setAttribute("LIST_USER", listUser);
            request.setAttribute("endP", lastPage);
            request.setAttribute("selectedPage", index);
            request.getRequestDispatcher("views/manage/manage.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
