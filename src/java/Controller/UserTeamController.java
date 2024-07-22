/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.TeamDAO;
import Model.Team;
import Model.Team_Member;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
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
                case "delete-team-member":
                    deleteTeamMember(request, response);
                    break;

            }
        }
        switch (action) {
            case "view-team":
                viewTeamDetails(request, response);
                break;
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
                updateTeam(request, response);
                break;
            case "add-team-member":
                addTeamMember(request, response);
                break;
            case "update-team-member":
                updateTeamMember(request, response);
                break;
        }

    }

    private void userTeam(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("USER"); // object
            TeamDAO teamDAO = new TeamDAO();
            String indexS = request.getParameter("index");
            String searchS = request.getParameter("search");
            if (indexS == null) {
                indexS = "1";
            }
            if (searchS == null) {
                searchS = "";
            }
            int index = Integer.parseInt(indexS);
            int total = teamDAO.getListTeamMemberByTeamIdTotal(user.getId());
            Team userTeam = teamDAO.getTeamByUserId(user.getId());
            List<Team_Member> teamMember = new ArrayList();
            if (userTeam != null) {
                teamMember = teamDAO.getListTeamMemberByTeamId(userTeam.getId(), index);
                request.setAttribute("TEAM", userTeam);
                request.setAttribute("TEAM_MEMBERS", teamMember);
                request.setAttribute("userTeamId", userTeam.getId());
            }
            if (searchS != "") {
                total = teamDAO.getListTeamMemberByTeamIdSeachTotal(userTeam.getId(), searchS);
                teamMember = teamDAO.getListTeamMemberByTeamIdSearch(userTeam.getId(), searchS, index);
                request.setAttribute("search", searchS);
                request.setAttribute("TEAM_MEMBERS", teamMember);
            }
            int lastPage = total / 10;
            if (total % 10 != 0) {
                lastPage++;
            }

            if (session.getAttribute("ERROR") != null) {
                request.setAttribute("ERROR", (String) session.getAttribute("ERROR"));
                session.removeAttribute("ERROR");
            }
            request.setAttribute("endP", lastPage);
            request.setAttribute("selectedPage", index);
            request.getRequestDispatcher("views/user/user-team.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateTeam(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            User userLogin = (User) session.getAttribute("USER");

            // mac dinh kieu tra ve cua request.getParam => Auto String.
            String teamName = request.getParameter("teamName");
            String shortName = request.getParameter("shortName");
            String teamsizeS = request.getParameter("teamsize");
            String teamIdS = request.getParameter("teamId");
            String desc = request.getParameter("desc");

            // lu anh dan binary
            Part image = request.getPart("image");

            TeamDAO teamDAO = new TeamDAO();
            Team _team = new Team();
            _team.setTeamSize(0);
            _team.setName(teamName);
            _team.setShortName(shortName);

            _team.setDescription(desc);
            _team.setUserId(userLogin.getId());

            Team teamUpdated = new Team();
            // ton tai teamid co team roi.
            if (!(teamIdS.equals(""))) {
                _team.setTeamSize(Integer.parseInt(teamsizeS));
                // goi phunog thuc DAO update team.
                int teamId = Integer.parseInt(teamIdS);
                _team.setId(teamId);
                // Thang vua duoc update
                teamUpdated = teamDAO.updateTeam(_team, image);
            } else {
                // goi phuong thuc tao team
                teamDAO.createTeam(_team, image);
                teamUpdated = teamDAO.getTeamByUserId(userLogin.getId());
            }
            if (teamUpdated != null) {
                session.setAttribute("TEAM", teamUpdated);
                request.setAttribute("MESSAGE", "Cập nhật team thành công");

                Team userTeam = teamDAO.getTeamByUserId(userLogin.getId());
                List<Team_Member> teamMember = new ArrayList();
                if (userTeam != null) {
                    teamMember = teamDAO.getListTeamMemberByTeamId(userTeam.getId(), 1);
                    request.setAttribute("TEAM", userTeam);
                    request.setAttribute("TEAM_MEMBERS", teamMember);
                    request.setAttribute("userTeamId", userTeam.getId());
                }
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

    private void viewTeamDetails(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            String team = request.getParameter("id");
            int teamId = Integer.parseInt(team);
            TeamDAO teamDAO = new TeamDAO();
            Team userTeam = teamDAO.getTeamById(teamId);
            if (userTeam != null) {
                request.setAttribute("TEAM", userTeam);
            }

            String indexS = request.getParameter("index");
            String searchS = request.getParameter("search");
            if (indexS == null) {
                indexS = "1";
            }
            if (searchS == null) {
                searchS = "";
            }
            int index = Integer.parseInt(indexS);

            List<Team_Member> teamMember = new ArrayList();
            if (userTeam != null) {
                int total = teamDAO.getListTeamMemberByTeamIdTotal(teamId);
                teamMember = teamDAO.getListTeamMemberByTeamId(userTeam.getId(), index);
                int lastPage = total / 9;
                if (total % 9 != 0) {
                    lastPage++;
                }
                request.setAttribute("endP", lastPage);
                request.setAttribute("selectedPage", index);

                request.setAttribute("TEAM", userTeam);
                request.setAttribute("TEAM_MEMBERS", teamMember);
                request.setAttribute("userTeamId", userTeam.getId());
            }

            if (session.getAttribute("ERROR") != null) {
                request.setAttribute("ERROR", (String) session.getAttribute("ERROR"));
                session.removeAttribute("ERROR");
            }

            request.getRequestDispatcher("views/user/team-details.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addTeamMember(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = "team";
            HttpSession session = request.getSession(false);
            User userLogin = (User) session.getAttribute("USER");

            // mac dinh kieu tra ve cua request.getParam => Auto String.
            String name = request.getParameter("name");
            String numberS = request.getParameter("number");
            String teamIdS = request.getParameter("teamId");
            Part image = request.getPart("file");

            Team_Member teamMember = new Team_Member();
            teamMember.setName(name);
            teamMember.setNumber(Integer.parseInt(numberS));
            teamMember.setTeamId(Integer.parseInt(teamIdS));

            TeamDAO teamDAO = new TeamDAO();
            boolean isDuplicated = teamDAO.isDuplicateNumber(teamMember);
            if (isDuplicated) {
                session.setAttribute("ERROR", "Số áo này đã được sử dụng trong team");
            } else {
                boolean result = teamDAO.AddTeam_Member(teamMember, image);
                if (result) {
                    request.setAttribute("MESSAGE", "Add team member sucessfully");
                    teamDAO.updateTeamSize(Integer.parseInt(teamIdS));
                } else {
                    request.setAttribute("ERROR", "Add team member failed");
                }
            }
            response.sendRedirect(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteTeamMember(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = "team";
            HttpSession session = request.getSession(false);
            User userLogin = (User) session.getAttribute("USER");

            // mac dinh kieu tra ve cua request.getParam => Auto String.
            String id = request.getParameter("id");
            int teamMemberId = Integer.parseInt(id);

            System.out.println("TeamMember ID" + teamMemberId);
            TeamDAO teamDAO = new TeamDAO();
            int teamId = teamDAO.getTeamIdByTeamMemberId(teamMemberId);
            boolean result = teamDAO.DeleteTeam_Member(teamMemberId);
            if (result) {
                teamDAO.updateTeamSize(teamId);
                request.setAttribute("MESSAGE", "Delete team member sucessfully");
            } else {
                session.setAttribute("ERROR", "Cầu thủ trên đã tham giải đấu bạn không thể xóa cầu thủ này!");
            }
            response.sendRedirect(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateTeamMember(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = "team";
            HttpSession session = request.getSession(false);
            User userLogin = (User) session.getAttribute("USER");

            // mac dinh kieu tra ve cua request.getParam => Auto String.
            String name = request.getParameter("name");
            String numberS = request.getParameter("number");
            String ids = request.getParameter("id");

            Team_Member teamMember = new Team_Member();
            teamMember.setName(name);
            teamMember.setNumber(Integer.parseInt(numberS));
            teamMember.setId(Integer.parseInt(ids));
            TeamDAO teamDAO = new TeamDAO();
            boolean result = teamDAO.UpdateTeam_Member(teamMember);
            if (result) {
                request.setAttribute("MESSAGE", "Add team member sucessfully");
            } else {
                request.setAttribute("ERROR", "Add team member failed");
            }
            response.sendRedirect(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
