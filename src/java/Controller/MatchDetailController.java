/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.LeagueDAO;
import DAO.MatchDAO;
import DAO.TeamDAO;
import Model.League;
import Model.Team_Member;
import Model.ViewModel.MatchVM;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MatchDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String matchIds = request.getParameter("matchId");
            String groupIds = request.getParameter("groupId");
            int matchId = Integer.parseInt(matchIds);
            LeagueDAO leagueDAO = new LeagueDAO();
            TeamDAO teamDAO = new TeamDAO();

            MatchVM match = leagueDAO.getMatchByMatchId(matchId);
            int homeTeamId = match.getHomeTeamId();
            int awayTeamId = match.getAwayTeamId();

            List<Map<String, String>> events;
            List<Map<String, String>> teamAEvents = new ArrayList<>();
            List<Map<String, String>> teamBEvents = new ArrayList<>();

            MatchDAO dao = new MatchDAO();
            events = dao.getMatchDetailByMatchId(matchId);

            if (events != null) {
                for (Map<String, String> event : events) {
                    if (homeTeamId == Integer.parseInt(event.get("TeamId"))) {
                        teamAEvents.add(event);
                    } else if (awayTeamId == Integer.parseInt(event.get("TeamId"))) {
                        teamBEvents.add(event);
                    }
                }
            }
            request.setAttribute("MATCH", match);

            request. setAttribute("groupId", groupIds);
            request.setAttribute("teamAEvents", teamAEvents);
            request.setAttribute("teamBEvents", teamBEvents);
            request.getRequestDispatcher("views/user/match-result.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
