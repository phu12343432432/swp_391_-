/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.LeagueDAO;
import DAO.TeamDAO;
import Model.League;
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
import java.util.List;

/**
 *
 * @author Datnt
 */
@MultipartConfig
public class UserLeagueController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        String url = "views/common/index.jsp";
        if (session != null && session.getAttribute("USER") != null) {
            User user = (User) session.getAttribute("USER");
            switch (action) {
                case "create":
                    createLeaguePage(request, response);
                    break;
                case "listLeague":
                    viewLeague(request, response);
                    break;
                case "register":
                    RegisterLeague(request, response);
                    break;
                case "search-by-name":
                    searchByName(request, response);
                    break;
                case "user-league":
                    System.out.println("Vaoi dya roi");
                    getUserLeague(request, response);
                    break;
                case "list-registered":
                    getUserLeagueRegistered(request, response);
                    break;
                case "detail":
                    viewLeagueDetail(request, response);
                    break;
            }
        } else {
            // trang login
            url = "views/common/sign-in.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        String url = "views/common/index.jsp";
        if (session != null && session.getAttribute("USER") != null) {
            User user = (User) session.getAttribute("USER");
            switch (action) {
                case "create":
                    createLeague(request, response);
                    break;
                case "logout":
                    session.removeAttribute("USER");
            }
        } else {
            // trang login
            url = "views/common/sign-in.jsp";
            request.getRequestDispatcher(url).forward(request, response);

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

    private void createLeaguePage(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = "v+iews/user/create-league.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void viewLeague(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            String url = "views/user/list-league.jsp";
            LeagueDAO leagueDAO = new LeagueDAO();
            String indexS = request.getParameter("index");
            if (indexS == null) {
                indexS = "1";
            }
            int index = Integer.parseInt(indexS);
            List<League> listLeague = leagueDAO.getLeaguePaged(index);
            int total = leagueDAO.getTotalLeague();
            int lastPage = total / 9;
            if (total % 12 != 0) {
                lastPage++;
            }
            session.setAttribute("LEAGUE", listLeague);
            request.setAttribute("endP", lastPage);
            request.setAttribute("selectedPage", index);
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createLeague(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            User userLogin = (User) session.getAttribute("USER");

            String name = request.getParameter("name");
            String startDate = request.getParameter("start_date");
            String endDate = request.getParameter("end_date");
            String desc = request.getParameter("desc");
            String address = request.getParameter("address");
            String teamsizeS = request.getParameter("teamsize");
            Part image = request.getPart("image");
            String leagueType = request.getParameter("type");

            LeagueDAO leagueDAO = new LeagueDAO();
            League _league = new League();
            _league.setType(leagueType);
            _league.setName(name);
            _league.setAddress(address);
            _league.setTeamSize(Integer.parseInt(teamsizeS));
            _league.setDescription(desc);
            _league.setStartDate(startDate);
            _league.setEndDate(endDate);
            _league.setUserID(userLogin.getId());
            boolean result = leagueDAO.createLeague(_league, image);
            if (result) {
                request.setAttribute("MESSAGE", "Tạo giải đấu thành công thành công, vui lòng đợi kiểm duyệt");
            } else {
                request.setAttribute("ERRORMESSAGE", "Cập nhật team không thành công");
            }
            request.getRequestDispatcher("views/user/create-league.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Update Team Cannot update");
            e.printStackTrace();
        }
    }

    private void RegisterLeague(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = "views/user/list-league.jsp";
            TeamDAO teamDAO = new TeamDAO();
            HttpSession session = request.getSession();
            User userLogin = (User) session.getAttribute("USER");
            String leagueIdS = request.getParameter("leagueId");
            if (leagueIdS != null) {
                int leagueId = Integer.parseInt(leagueIdS);
                Team userTeam = teamDAO.getTeamByUserId(userLogin.getId());
                LeagueDAO leagueDAO = new LeagueDAO();
                int status = leagueDAO.registerLeague(userTeam.getId(), leagueId);
                if (status == 0) {
                    request.setAttribute("MESSAGE", "Đăng kí thành công");
                } else if (status == 1) {
                    request.setAttribute("ERROR", "Giải đấu đã đủ cảm ơn bạn đã quan tâm");
                }
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchByName(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            String search = request.getParameter("search");
            String indexS = request.getParameter("index");
            String url = "views/user/list-league.jsp";
            if (search != null) {
                if (indexS == null) {
                    indexS = "1";
                }
                LeagueDAO leagueDAO = new LeagueDAO();
                int index = Integer.parseInt(indexS);
                List<League> listLeague = leagueDAO.searchLeagueByName(index, search);
                int total = leagueDAO.getTotalLeague();
                int lastPage = total / 9;
                if (total % 12 != 0) {
                    lastPage++;
                }
                session.setAttribute("LEAGUE", listLeague);
                request.setAttribute("endP", lastPage);
                request.setAttribute("search", search);
                request.setAttribute("selectedPage", index);
                request.getRequestDispatcher(url).forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getUserLeague(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            User userLogin = (User) session.getAttribute("USER");
            String search = request.getParameter("search");
            String indexS = request.getParameter("index");
            String url = "views/user/user-league.jsp";
            if (indexS == null) {
                indexS = "1";
            }
            LeagueDAO leagueDAO = new LeagueDAO();
            int index = Integer.parseInt(indexS);
            List<League> listLeague = leagueDAO.getUserLeaguePaged(index, userLogin.getId());
            int total = leagueDAO.getTotalUserLeague(userLogin.getId());
            int lastPage = total / 4;
            if (total % 4 != 0) {
                lastPage++;
            }
            request.setAttribute("USER_LEAGUE", listLeague);
            request.setAttribute("endP", lastPage);
            request.setAttribute("search", search);
            request.setAttribute("selectedPage", index);
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getUserLeagueRegistered(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void viewLeagueDetail(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = "views/user/edit-league.jsp";
            HttpSession session = request.getSession();
            User userLogin = (User) session.getAttribute("USER");
            String idS = request.getParameter("leagueId");
            int id = Integer.parseInt(idS);
            LeagueDAO leagueDAO = new LeagueDAO();
            League league = leagueDAO.getLeagueById(id);
            if (league != null) {
                request.setAttribute("USER_LEAGUE", league);
                System.out.println("Oke");
            } else {
                System.out.println("Khong oke");
            }

            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
