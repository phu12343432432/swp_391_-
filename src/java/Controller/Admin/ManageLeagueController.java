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
import java.io.IOException;
import java.util.List;


public class ManageLeagueController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String indexS = request.getParameter("index");
            String searchS = request.getParameter("search");
            if (indexS == null) {
                indexS = "1";
            }
            if (searchS == null) {
                searchS = "";
            }
            int index = Integer.parseInt(indexS);
            LeagueDAO leagueDAO = new LeagueDAO();
            List<League> listLeague = leagueDAO.getLeaguePendingStatus(index);
            int total = leagueDAO.getLeaguePendingStatusTotal();

            if (searchS != "") {
                total = leagueDAO.searchLeaguePendingStatusTotalByName(searchS);
                listLeague = leagueDAO.searhLeaguePendingStatusByName(searchS, index);
                request.setAttribute("search", searchS);
            }
            int lastPage = total / 8;
            if (total % 8 != 0) {
                lastPage++;
            }
            request.setAttribute("LIST_LEAGUE", listLeague);
            request.setAttribute("endP", lastPage);
            request.setAttribute("selectedPage", index);
            request.getRequestDispatcher("/views/manage/manage-league.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
