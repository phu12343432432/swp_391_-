/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.LeagueDAO;
import DAO.TeamDAO;
import Model.Team;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class LeagueCommentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("USER") != null) {
                User userLogin = (User) session.getAttribute("USER");
                String content = request.getParameter("feedback");
                String leagueIdS = request.getParameter("leagueId");
                int leagueId = Integer.parseInt(leagueIdS);

                TeamDAO teamDAO = new TeamDAO();

                Team userTeam = teamDAO.getTeamByUserId(userLogin.getId());
                if (userTeam != null) {
                    LeagueDAO leagueDAO = new LeagueDAO();
                    if (leagueDAO.isRegisterLeague(userTeam.getId(), leagueId)) {
                        boolean result = leagueDAO.feedbackLeague(leagueId, content, userLogin.getId());
                        if (result) {
                            response.sendRedirect("league?action=view-league&leagueId=" + leagueId);
                            return;
                        }
                        request.setAttribute("Failed", "Your comment post failed");
                        response.sendRedirect("league?action=view-league&leagueId=" + leagueId);
                    } 
                }

            } else {
                response.sendRedirect("auth?action=login");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
