/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import DAO.LeagueDAO;
import Model.League;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ChangeStatusLeagueController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        if (session != null && session.getAttribute("USER") != null) {
            switch (action) {
                case "accept":
                    approveLeague(request, response);
                    break;
                case "reject":
                    rejectLeague(request, response);
                    break;
            }
        } else {
            response.sendRedirect("views/common/sign-in.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void approveLeague(HttpServletRequest request, HttpServletResponse response) {
        try {
            String leagueIds = request.getParameter("leagueId");
            int leagueId = Integer.parseInt(leagueIds);
            LeagueDAO leagueDAO = new LeagueDAO();
            // regject
            boolean result = leagueDAO.confirmLeagueById(leagueId);
            String indexS = request.getParameter("index");
            if (indexS == null) {
                indexS = "1";
            }
            int index = Integer.parseInt(indexS);
            int total = leagueDAO.getLeaguePendingStatusTotal();
            List<League> listPost = leagueDAO.getLeaguePendingStatus(index);
            int lastPage = total / 8;
            if (total % 8 != 0) {
                lastPage++;
            }
            request.setAttribute("LIST_LEAGUE", listPost);
            request.setAttribute("endP", lastPage);
            request.setAttribute("selectedPage", index);
            request.getRequestDispatcher("/views/manage/manage-league.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void rejectLeague(HttpServletRequest request, HttpServletResponse response) {
        try {
            String leagueIds = request.getParameter("leagueId");
            int leagueId = Integer.parseInt(leagueIds);
            LeagueDAO leagueDAO = new LeagueDAO();
            // regject
            boolean result = leagueDAO.rejectLeagueById(leagueId);
            String indexS = request.getParameter("index");
            if (indexS == null) {
                indexS = "1";
            }
            int index = Integer.parseInt(indexS);
            int total = leagueDAO.getLeaguePendingStatusTotal();
            List<League> listPost = leagueDAO.getLeaguePendingStatus(index);
            int lastPage = total / 8;
            if (total % 8 != 0) {
                lastPage++;
            }
            request.setAttribute("LIST_LEAGUE", listPost);
            request.setAttribute("endP", lastPage);
            request.setAttribute("selectedPage", index);
            request.getRequestDispatcher("/views/manage/manage-league.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
