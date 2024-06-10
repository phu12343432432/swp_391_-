/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.LeagueDAO;
import DAO.TeamDAO;
import Model.League;
import Model.LeagueRegister;
import Model.MatchVM;
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
import java.time.LocalDateTime;
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
                    viewLeagueDetail_Owner(request, response);
                    break;
                case "view-league":
                    viewLeagueDetail(request, response);
                    break;
                case "start":
                    startLeague(request, response);
                    break;
                case "league-match":
                    viewLeagueMatch(request, response);
                    break;
                case "match-detail":
                    viewMatchDetail(request, response);
                    break;
                case "finish-league":
                    finishLeague(request, response);
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
                case "update":
                    updateLeague(request, response);
                    break;
                case "match-detail":
                    updateMatchDetail(request, response);
                    break;
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
            String registerDate = request.getParameter("date_register");
            String desc = request.getParameter("desc");
            String address = request.getParameter("address");
            String teamsizeS = request.getParameter("teamsize");
            Part image = request.getPart("image");
            String leagueType = request.getParameter("type");

            var _startDate = LocalDateTime.parse(startDate);
            var _endDate = LocalDateTime.parse(endDate);

            LeagueDAO leagueDAO = new LeagueDAO();
            League _league = new League();
            _league.setType(leagueType);
            _league.setName(name);
            _league.setDateRegister(registerDate);
            _league.setAddress(address);
            _league.setTeamSize(Integer.parseInt(teamsizeS));
            _league.setDescription(desc);
            _league.setStartDate(_startDate);
            _league.setEndDate(_endDate);
            _league.setUserId(userLogin.getId());
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

    private void updateLeague(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

    // Lam update function
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
        try {
            String url = "views/user/league-owner/register-league.jsp";
            HttpSession session = request.getSession();
            Team userTeam = (Team) session.getAttribute("TEAM");
            LeagueDAO leagueDAO = new LeagueDAO();
            List<League> league = leagueDAO.getLeagueRegistered(userTeam.getId());
            if (league != null) {
                request.setAttribute("REGISTER_LEAGUE", league);
            } else {
                System.out.println("Cannot found");
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private void viewLeagueDetail_Owner(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = "views/user/league-owner/league-details.jsp";
            HttpSession session = request.getSession();
            User userLogin = (User) session.getAttribute("USER");
            String idS = request.getParameter("leagueId");
            int id = Integer.parseInt(idS);
            LeagueDAO leagueDAO = new LeagueDAO();
            League league = leagueDAO.getLeagueById(id);
            if (league != null) {
                List<LeagueRegister> listTeamRegister = leagueDAO.getTeamRegisterLeague(id);
                request.setAttribute("USER_LEAGUE", league);
                request.setAttribute("LEAGUE_TEAM", listTeamRegister);
                request.setAttribute("leagueId", idS);
            } else {
                System.out.println("Cannto get value in ViewLeagueDetail");
            }

            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startLeague(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = "views/user/league-owner/league-details.jsp";
            String leagueIds = request.getParameter("leagueId");
            if (leagueIds != null) {
                int id = Integer.parseInt(leagueIds);
                LeagueDAO leagueDAO = new LeagueDAO();
                League league = leagueDAO.getLeagueById(id);
                List<MatchVM> listMatch = leagueDAO.generateMatch(id);
                request.setAttribute("USER_LEAGUE", league);
                if (listMatch.size() > 0) {
                    request.setAttribute("LIST_MATCH", listMatch);
                    url = "views/user/league-owner/league-match.jsp";
                } else {
                    request.setAttribute("ERROR", "Chưa có đội bóng nào tham gia giải đấu của bạn");
                }
                request.getRequestDispatcher(url).forward(request, response);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void viewLeagueMatch(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = "views/user/league-owner/league-details.jsp";
            String leagueIds = request.getParameter("leagueId");
            if (leagueIds != null) {
                int id = Integer.parseInt(leagueIds);
                LeagueDAO leagueDAO = new LeagueDAO();
                League league = leagueDAO.getLeagueById(id);
                List<MatchVM> listMatch = leagueDAO.getMatchByLeague(id);
                request.setAttribute("USER_LEAGUE", league);
                if (listMatch.size() > 0) {
                    request.setAttribute("LIST_MATCH", listMatch);
                    url = "views/user/league-owner/league-match.jsp";
                } else {
                    request.setAttribute("ERROR", "Chưa có đội bóng nào tham gia giải đấu của bạn");
                }
                request.getRequestDispatcher(url).forward(request, response);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void viewMatchDetail(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = "views/user/league-owner/league-details.jsp";
            String matchIds = request.getParameter("matchId");
            if (matchIds != null) {
                int id = Integer.parseInt(matchIds);
                LeagueDAO leagueDAO = new LeagueDAO();
                League league = leagueDAO.getLeagueById(id);
                MatchVM match = leagueDAO.getMatchByMatchId(id);
                request.setAttribute("USER_LEAGUE", league);
                if (match != null) {
                    request.setAttribute("MATCH", match);
                    url = "views/user/league-owner/match-detail.jsp";
                } else {
                    request.setAttribute("ERROR", "Chưa có đội bóng nào tham gia giải đấu của bạn");
                }
                request.getRequestDispatcher(url).forward(request, response);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateMatchDetail(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = "";
            String scoreAs = request.getParameter("scoreA");
            String scoreBs = request.getParameter("scoreB");
            String matchIds = request.getParameter("matchId");
            String teamAIds = request.getParameter("teamAId");
            String teamBIds = request.getParameter("teamBId");
            String leagueIds = request.getParameter("leagueId");

            int scoreA = Integer.parseInt(scoreAs);
            int scoreB = Integer.parseInt(scoreBs);
            int matchId = Integer.parseInt(matchIds);
            int teamAId = Integer.parseInt(teamAIds);
            int teamBId = Integer.parseInt(teamBIds);
            int leagueId = Integer.parseInt(leagueIds);

            LeagueDAO leagueDAO = new LeagueDAO();
            boolean result = leagueDAO.UpdateMatchResult(scoreA, scoreB, matchId, teamAId, teamBId, leagueId);
            if (result) {
                request.setAttribute("MESSAGE", "Cập nhật tỉ số thành công");
                url = "league?action=league-match&leagueId=" + leagueIds;
            } else {
                request.setAttribute("ERROR", "Cập nhật tỉ số thất bại");
            }
            response.sendRedirect(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void finishLeague(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = "views/user/league-owner/league-details.jsp";
            String leagueIds = request.getParameter("leagueId");
            if (leagueIds != null) {
                int leagueId = Integer.parseInt(leagueIds);
                LeagueDAO leagueDAO = new LeagueDAO();
                boolean result = leagueDAO.finishLeague(leagueId);
                if (result) {
                    League league = leagueDAO.getLeagueById(leagueId);
                    if (league != null) {
                        List<LeagueRegister> listTeamRegister = leagueDAO.getTeamRegisterLeague(leagueId);
                        request.setAttribute("USER_LEAGUE", league);
                        request.setAttribute("LEAGUE_TEAM", listTeamRegister);
                        request.setAttribute("leagueId", leagueId);
                    }
                    request.setAttribute("MESSAGE", "Giải đấu của bạn đã hoàn thành cảm ơn bạn");
                } else {
                    request.setAttribute("ERROR", "Không thể kết thúc giải đấu");
                }
                request.getRequestDispatcher(url).forward(request, response);
            }
        } catch (Exception e) {
        }
    }

}
