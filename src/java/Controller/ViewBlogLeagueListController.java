/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.BlogLeagueDAO;
import DAO.LeagueDAO;
import Model.League;
import Model.LeagueRegister;
import Model.ViewModel.BlogLeagueVM;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewBlogLeagueListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String leagueIdS = request.getParameter("leagueId");
            int leagueId = Integer.parseInt(leagueIdS);
            BlogLeagueDAO blogLeaagueDAO = new BlogLeagueDAO();
            List<BlogLeagueVM> list = blogLeaagueDAO.getListBlogByLeagueId(leagueId);
            if (list.size() > 0) {
                request.setAttribute("LIST_BLOG", list);
            }
            LeagueDAO leagueDAO = new LeagueDAO();
            League league = leagueDAO.getLeagueById(leagueId);
            if (league != null) {
                List<LeagueRegister> listTeamRegister = leagueDAO.getTeamRegisterLeague(leagueId);
                request.setAttribute("USER_LEAGUE", league);
                request.setAttribute("LEAGUE_TEAM", listTeamRegister);
                request.setAttribute("leagueId", leagueId);
            } else {
                System.out.println("Cannto get value in ViewLeagueDetail");
            }
            request.getRequestDispatcher("views/user/league-owner/blog-league.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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

}
