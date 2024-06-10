/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ProfileDAO;
import DAO.TeamDAO;
import Model.Team;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.IOException;


@MultipartConfig
public class UserTeamController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        String url = "views/common/index.jsp";
        if (session != null && session.getAttribute("USER") != null) {
            User user = (User) session.getAttribute("USER");
            switch (action) {
                case "":
                    userTeam(request, response);
                    break;
                case "team-history":
                    viewHistory(request, response);
                    break;
            }
        } else {
            url = "views/common/sign-in.jsp";
            request.getRequestDispatcher("views/common/sign-in.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        String url = "views/common/index.jsp";
        switch (action) {
            case "update":
                System.out.println("Vao day roi");
                updateTeam(request, response);
                break;
        }

    }

    private void userTeam(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("USER");
            TeamDAO teamDAO = new TeamDAO();
            Team userTeam = teamDAO.getTeamByUserId(user.getId());
            if (userTeam != null) {
                request.setAttribute("TEAM", userTeam);
            }
            request.getRequestDispatcher("views/user/user-team.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // update user team
    private void updateTeam(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            User userLogin = (User) session.getAttribute("USER");

            String teamName = request.getParameter("teamName");
            String shortName = request.getParameter("shortName");
            String teamsizeS = request.getParameter("teamsize");
            String teamIdS = request.getParameter("teamId");
            String desc = request.getParameter("desc");
            Part image = request.getPart("image");

            TeamDAO teamDAO = new TeamDAO();
            Team _team = new Team();
            _team.setName(teamName);
            _team.setShortName(shortName);
            _team.setTeamSize(Integer.parseInt(teamsizeS));
            _team.setDescription(desc);
            _team.setUserId(userLogin.getId());
            Team teamUpdated = new Team();
            if (!(teamIdS.equals(""))) {
                int teamId = Integer.parseInt(teamIdS);
                _team.setId(teamId);
                System.out.println("TeamId " + teamId);
                teamUpdated = teamDAO.updateTeam(_team, image);
            } else {
                teamDAO.createTeam(_team, image); 
                teamUpdated = teamDAO.getTeamByUserId(userLogin.getId());
            }
            if (teamUpdated != null) {
                session.setAttribute("TEAM", teamUpdated);
                request.setAttribute("MESSAGE", "Cập nhật team thành công");
            } else {
                request.setAttribute("ERRORMESSAGE", "Cập nhật team không thành công");
            }
            request.getRequestDispatcher("views/user/user-team.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Update Team Cannot update");
            e.printStackTrace();
        }
    }

    private void viewHistory(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
