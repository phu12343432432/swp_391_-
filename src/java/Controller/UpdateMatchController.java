/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.LeagueDAO;
import DAO.MatchDAO;
import DAO.TeamDAO;
import Model.ViewModel.MatchVM;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Datnt
 */
@WebServlet(name = "UpdateMatchController", urlPatterns = {"/UpdateMatchController"})
public class UpdateMatchController extends HttpServlet {

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
            request.setAttribute("groupId", groupIds);

            HttpSession session = request.getSession();
            if (session.getAttribute("SCHEDULE") != null) {
                String message = (String) session.getAttribute("SCHEDULE");
                request.setAttribute("ERROR", message);
                session.removeAttribute("SCHEDULE");
            }
            request.setAttribute("teamAEvents", teamAEvents);
            request.setAttribute("teamBEvents", teamBEvents);
            request.getRequestDispatcher("views/user/league-owner/update-match.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String matchIds = request.getParameter("matchId");
            String groupdIds = request.getParameter("groupId");
            int matchId = Integer.parseInt(matchIds);
            String startDateS = request.getParameter("startAt");
            String endDateS = request.getParameter("endAt");
            LocalDateTime startDate = LocalDateTime.parse(startDateS);
            LocalDateTime endDate = LocalDateTime.parse(endDateS);

            MatchDAO matchDAO = new MatchDAO();
            // Validate if the updated time is not conflicting with other matches         
            boolean hasConflict = matchDAO.hasMatchTimeConflict(matchId, startDate, endDate);
            String url = "UpdateMatchController?matchId=" + matchIds + "&groupId=" + groupdIds;
            if (hasConflict) {
                session.setAttribute("SCHEDULE", "Thời gian này đã trùng với trận đấu khác");
                response.sendRedirect(url);
                return;
            }            // Update the match detail       
            boolean result = matchDAO.updateMatchTime(matchId, startDate, endDate);
            if (result) {
                request.setAttribute("MESSAGE", "Cập nhật giờ thi đấu thành công.");
            } else {
                request.setAttribute("ERROR", "Failed to update the match.");
            }

            response.sendRedirect(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
