/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.LeagueDAO;
import DAO.TeamDAO;
import Model.League;
import Model.LeagueRegister;
import Model.LeagueRegisterVM;
import Model.Team;
import Model.User;
import Model.ViewModel.MatchVM;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ADMIN
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
                case "league-rank":
                    viewLeagueRank(request, response);
                    break;
                case "accept-team":
                    acceptRegisterToLeague(request, response);
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
            String url = "views/user/create-league.jsp";
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
            if (total % 9 != 0) {
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
            if (userLogin.getRoleId() == 3) {
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
                LocalDate date = LocalDate.parse(registerDate);
                LocalDateTime _dateRegister = date.atStartOfDay();

                // validate ngay cach ngay ket thuc dang ki voi ngay bat dau
                boolean isValid = validateDate(_dateRegister, _startDate);
                // validate them ngay bat dau voi ngay ket thuc
                
                
                if (!isValid) {
                    request.setAttribute("ERRORMESSAGE", "Ngày bắt đầu giải và hạn chót đăng kí phải cách nhau ít nhất 2 ngày");
                } else {
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
                }
            } else {
                request.setAttribute("ERRORMESSAGE", "Bạn không có quyền được tạo giải đấu vui lòng đăng kí với quản trị viên");
            }
            request.getRequestDispatcher("views/user/create-league.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Update Team Cannot update");
            e.printStackTrace();
        }
    }

    public static boolean validateDate(LocalDateTime start, LocalDateTime end) {
        // Tính toán khoảng thời gian giữa ngày bắt đầu và ngày kết thúc
        Duration duration = Duration.between(start, end);
        return duration.toHours() >= 48;
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
                if (userTeam == null) {
//                    url = "views/user/user-team.jsp";
                    request.setAttribute("TEAMERROR", "Bạn chưa có team để tham gia giải đấu, vui lòng tạo team!");
                } else {
                    LeagueDAO leagueDAO = new LeagueDAO();
                    League league = leagueDAO.getLeagueById(leagueId);
                    String dateRemains = league.getDateRegister();

                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();
                    Date currentDate = dateFormat.parse(dateRemains);
                    if (date.compareTo(currentDate) > 0) {
                        request.setAttribute("ERROR", "Đã hết hạn đăng kí giải đấu");
                    } else {
                        int status = leagueDAO.registerLeague(userTeam.getId(), leagueId);
                        if (status == 0) {
                            request.setAttribute("MESSAGE", "Đăng kí thành công");
                        } else if (status == 1) {
                            request.setAttribute("ERROR", "Giải đấu đã đủ cảm ơn bạn đã quan tâm");
                        } else if (status == 2) {
                            request.setAttribute("ERROR", "Bạn đã đăng kí giải đấu này rồi");
                        }
                    }
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
            String url = "views/user/league-owner/user-league.jsp";
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
            String url = "views/user/league-details.jsp";
            HttpSession session = request.getSession();
            User userLogin = (User) session.getAttribute("USER");
            String idS = request.getParameter("leagueId");
            int id = Integer.parseInt(idS);
            LeagueDAO leagueDAO = new LeagueDAO();
            League league = leagueDAO.getLeagueById(id);
            if (league != null) {
                List<LeagueRegister> listTeamRegister = leagueDAO.getTeamRegisterLeagueApprove(id);
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

    private void updateLeague(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
            request.setAttribute("leagueId", idS);

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
                List<LeagueRegister> listTeamRegister = leagueDAO.getTeamRegisterLeague(id);
                request.setAttribute("LEAGUE_TEAM", listTeamRegister);
                request.setAttribute("USER_LEAGUE", league);
                if (listMatch == null) {
                    // validate check nếu giải chưa full
                    request.setAttribute("ERROR", "Số đội bóng tham gia chưa đủ bạn có chắc là sẽ bắt đầu giải đấu");
                } else if (listMatch.size() == 0) {
                    request.setAttribute("ERROR", "Chưa có đội bóng nào tham gia giải đấu của bạn");
                } else if (listMatch.size() > 0) {
                    request.setAttribute("LIST_MATCH", listMatch);
                    url = "views/user/league-owner/league-match.jsp";
                }
                request.setAttribute("leagueId", leagueIds);
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

    private void viewLeagueRank(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = "views/user/league-ranking.jsp";
            String leagueIds = request.getParameter("leagueId");
            if (leagueIds != null) {
                int leagueId = Integer.parseInt(leagueIds);
                LeagueDAO leagueDAO = new LeagueDAO();
                List<LeagueRegisterVM> listRank = leagueDAO.getTeamLeaugeRank(leagueId);
                if (listRank != null) {
                    request.setAttribute("LIST_RANK", listRank);
                } else {
                    request.setAttribute("ERROR", "Cannot get list rank of leagueid - " + leagueIds);
                }
                request.getRequestDispatcher(url).forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void acceptRegisterToLeague(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = "views/user/league-owner/league-details.jsp";
            String leagueIds = request.getParameter("leagueId");
            String teamIds = request.getParameter("teamId");
            if (leagueIds != null && teamIds != null) {
                int leagueId = Integer.parseInt(leagueIds);
                int teamId = Integer.parseInt(teamIds);
                TeamDAO teamDAO = new TeamDAO();
                Team teamRegister = teamDAO.getTeamById(teamId);

                LeagueDAO leagueDAO = new LeagueDAO();
                boolean result = leagueDAO.acceptToJoinLeague(teamId, leagueId);
                League league = leagueDAO.getLeagueById(leagueId);
                if (league != null) {
                    List<LeagueRegister> listTeamRegister = leagueDAO.getTeamRegisterLeague(leagueId);
                    request.setAttribute("USER_LEAGUE", league);
                    request.setAttribute("LEAGUE_TEAM", listTeamRegister);
                    request.setAttribute("leagueId", leagueIds);
                    if (result) {
                        request.setAttribute("MESSAGE", "You was accept " + teamRegister.getName() + " to join for your league");
                    } else {
                        request.setAttribute("ERROR", "Cannot accept teamid  - " + teamIds);
                    }
                    request.getRequestDispatcher(url).forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
