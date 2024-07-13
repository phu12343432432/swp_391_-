/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.LeagueDAO;
import DAO.MatchDAO;
import DAO.TeamDAO;
import DAO.UserWalletDAO;
import Model.Card;
import Model.Goal;
import Model.League;
import Model.LeagueRegister;
import Model.LeagueRegisterVM;
import Model.Team;
import Model.Team_Member;
import Model.User;
import Model.ViewModel.BlogLeagueVM;

import Model.UserWallet;
import Model.ViewModel.CommentViewModel;
import Model.ViewModel.GoalVM;
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
import java.time.format.DateTimeFormatter;
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
        boolean isRedirect = false;
        switch (action) {
            case "listLeague":
                viewLeague(request, response);
                isRedirect = true;
                break;
            case "search-by-name":
                searchByName(request, response);
                isRedirect = true;
                break;
            case "view-league":
                viewLeagueDetail(request, response);
                isRedirect = true;
                break;
            case "league-rank":
                viewLeagueRank(request, response);
                isRedirect = true;
                break;
            case "match-detail":
                viewMatchDetail(request, response);
                isRedirect = true;
                break;
            case "league-match":
                viewLeagueMatch(request, response);
                isRedirect = true;
                break;

        }
        if (session != null && session.getAttribute("USER") != null) {
            User user = (User) session.getAttribute("USER");
            switch (action) {
                case "create":
                    createLeaguePage(request, response);
                    break;
                case "register":
                    RegisterLeague(request, response);
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
                case "start":
                    startLeague(request, response);
                    break;
                case "finish-league":
                    finishLeague(request, response);
                    break;
                case "accept-team":
                    acceptRegisterToLeague(request, response);
                    break;
                case "league-match":
                    viewLeagueMatch(request, response);
                    isRedirect = true;
                    break;
                case "cancle-league":
                    cancleLeague(request, response);
                    break;
            }
        } else {
            // trang login
            if (!isRedirect) {
                url = "views/common/sign-in.jsp";
                request.getRequestDispatcher(url).forward(request, response);
            }
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
            String url = "";
            // check validate user co quyen tao giai hay khong.
            HttpSession session = request.getSession();
            User userLogin = (User) session.getAttribute("USER");
            url = "views/user/create-league.jsp";
            if (userLogin.getRoleId() != 3) {
                request.setAttribute("ERROR", "Bạn cần được cấp quyền tạo giải đấu trước");
                url = "views/common/index.jsp";
            }
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
            String searchS = request.getParameter("search");
            if (indexS == null) {
                indexS = "1";
            }
            if (searchS == null) {
                searchS = "";
            }

            // get leauge cho nay khong co trong danh sach thi khong get.
            int index = Integer.parseInt(indexS);
            List<League> listLeague = leagueDAO.getLeaguePaged(index);
            int total = leagueDAO.getTotalLeague();
            int lastPage = total / 9;
            if (total % 9 != 0) {
                lastPage++;
            }

            User userLogin = (User) session.getAttribute("USER");
            if (userLogin != null) {
                index = Integer.parseInt(indexS);
                listLeague = leagueDAO.getLeaguePaged(index, userLogin.getId());
                total = leagueDAO.getTotalLeague(userLogin.getId());
                lastPage = total / 9;
                if (total % 9 != 0) {
                    lastPage++;
                }
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
//                LocalDateTime _dateRegister = LocalDateTime.parse(registerDate);

                LocalDateTime now = LocalDateTime.now();
                if (_startDate.getDayOfYear() < now.getDayOfYear()) {
                    request.setAttribute("ERRORMESSAGE", "Thời gian tạo giải không hợp lệ");
                    request.getRequestDispatcher("views/user/create-league.jsp").forward(request, response);
                    return;
                }
                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

                // Parse the string to LocalDate
                LocalDate localDate = LocalDate.parse(registerDate, formatter);

                // Convert to LocalDateTime at start of day
                LocalDateTime _dateRegister = localDate.atStartOfDay();

                boolean isValid = validateDate(_dateRegister, _startDate);

                // validate them ngay bat dau voi ngay ket thuc
                int status = validateDateStartAndDateEdn(_startDate, _endDate);
                if (status == 0) {
                    request.setAttribute("ERRORMESSAGE", "Ngày bắt đầu giải và và ngày kết thúc không hợp lệ");
                    request.getRequestDispatcher("views/user/create-league.jsp").forward(request, response);
                    return;
                }
                if (!isValid) {
                    request.setAttribute("ERRORMESSAGE", "Ngày bắt đầu giải và hạn chót đăng kí phải cách nhau ít nhất 2 ngày");
                } else {

                    // valdiate vi tien cua user co du tien khong
                    UserWalletDAO walletDAO = new UserWalletDAO();
                    UserWallet userWallet = walletDAO.getUserWalletById(userLogin.getId());
                    if (userWallet == null || (userWallet.getAmmount() * 1000) < 10000) {
                        request.setAttribute("ERRORMESSAGE", "Số tiền trong ví của bạn không đủ vui lòng nạp thêm!");
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
                            // tao giai thanh cong tru tien

                            request.setAttribute("MESSAGE", "Tạo giải đấu thành công thành công, vui lòng đợi kiểm duyệt");
                        } else {
                            request.setAttribute("ERRORMESSAGE", "Tại giải đấu không thành công");
                        }
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

    public static int validateDateStartAndDateEdn(LocalDateTime start, LocalDateTime end) {
        // Tính toán khoảng thời gian giữa ngày bắt đầu và ngày kết thúc
        Duration duration = Duration.between(start, end);
        if (duration.toHours() >= 0) {
            return 1;
        }
        return 0;
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

//                        boolean hasConflict = leagueDAO.hasTeamRegisteredOtherLeague(userTeam.getId(), league.getStartDate(), league.getEndDate());
//                        if (hasConflict) {
//                            request.setAttribute("ERROR", "Đội bóng của bạn đã đăng ký tham gia một giải đấu khác trong khoảng thời gian này.");
//                        } else {
                        int status = leagueDAO.registerLeague(userTeam.getId(), leagueId);
                        if (status == 0) {
                            request.setAttribute("MESSAGE", "Đăng kí thành công");
                        } else if (status == 1) {
                            request.setAttribute("ERROR", "Giải đấu đã đủ cảm ơn bạn đã quan tâm");
                        } else if (status == 2) {
                            request.setAttribute("ERROR", "Bạn đã đăng kí giải đấu này rồi");
                        }
//                        }
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
                int total = leagueDAO.searchLeagueByNameTotal(search);
                int lastPage = total / 9;
                if (total % 9 != 0) {
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
            String statusS = request.getParameter("status");
            if (statusS == null || statusS == "") {
                // neu truong hop k truyen gi hoac vao mac dinh se lay het request.
                statusS = "7";
            }
            int status = Integer.parseInt(statusS);
            String url = "views/user/league-owner/user-league.jsp";
            if (indexS == null) {
                indexS = "1";
            }
            if (search == null) {
                search = "";
            }
            LeagueDAO leagueDAO = new LeagueDAO();
            int index = Integer.parseInt(indexS);
            List<League> listLeague = leagueDAO.getUserLeaguePaged(index, userLogin.getId(), status);
            int total = leagueDAO.getTotalUserLeague(userLogin.getId(), status);

            if (search != "") {
                listLeague = leagueDAO.searchUserLeagueLeagueByName(index, search, userLogin.getId(), status);
                total = leagueDAO.searchUserLeagueLeagueByNameTotal(index, search, userLogin.getId(), status);
            }

            int lastPage = total / 4;
            if (total % 4 != 0) {
                lastPage++;
            }
            request.setAttribute("USER_LEAGUE", listLeague);
            request.setAttribute("endP", lastPage);
            request.setAttribute("search", search);
            request.setAttribute("selectedPage", index);
            request.setAttribute("status", status);
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

            String search = request.getParameter("search");
            String indexS = request.getParameter("index");
            String statusS = request.getParameter("status");
            if (statusS == null || statusS == "") {
                // neu truong hop k truyen gi hoac vao mac dinh se lay het request.
                statusS = "7";
            }
            int status = Integer.parseInt(statusS);
            if (indexS == null) {
                indexS = "1";
            }
            if (search == null) {
                search = "";
            }
            if (userTeam != null) {
                int index = Integer.parseInt(indexS);
                List<League> league = leagueDAO.getLeagueRegistered(index, userTeam.getId(), status);
                if (league != null) {
                    int total = leagueDAO.getTotalUserLeagueRegister(userTeam.getId(), status);

                    if (search != "") {
                        league = leagueDAO.getLeagueRegisteredSearch(index, userTeam.getId(), status, search);
                        total = leagueDAO.getTotalUserLeagueRegisterSearch(userTeam.getId(), status, search);
                    }

                    int lastPage = total / 4;
                    if (total % 4 != 0) {
                        lastPage++;
                    }
//            request.setAttribute("USER_LEAGUE", listLeague);
                    request.setAttribute("endP", lastPage);
                    request.setAttribute("search", search);
                    request.setAttribute("selectedPage", index);
                    request.setAttribute("status", status);
                    if (league != null) {
                        request.setAttribute("REGISTER_LEAGUE", league);
                    } else {
                        System.out.println("Cannot found");
                    }
                    request.getRequestDispatcher(url).forward(request, response);

                }
            } else {
                response.sendRedirect("team");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void viewLeagueDetail(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = "views/user/league-details.jsp";
            HttpSession session = request.getSession();
            User userLogin = (User) session.getAttribute("USER");
            String indexS = request.getParameter("index");
            if (indexS == null) {
                indexS = "1";
            }
            int index = Integer.parseInt(indexS);
            String idS = request.getParameter("leagueId");
            int leagueId = Integer.parseInt(idS);
            LeagueDAO leagueDAO = new LeagueDAO();
            League league = leagueDAO.getLeagueById(leagueId);
            if (league != null) {
                int total = leagueDAO.getListUserFeedbackInLeagueTotal(leagueId);
                List<CommentViewModel> listComment = leagueDAO.getListUserFeedbackInLeague(leagueId, index);
                List<LeagueRegister> listTeamRegister = leagueDAO.getTeamRegisterLeagueApprove(leagueId);
                int lastPage = total / 12;
                if (total % 12 != 0) {
                    lastPage++;
                }

                // check xem user do co duoc duyet giai dau chua.
                TeamDAO teamDAO = new TeamDAO();
                if (userLogin != null) {
                    Team userTeam = teamDAO.getTeamByUserId(userLogin.getId());
                    if (userTeam != null) {
                        boolean isRegistered = leagueDAO.isRegisterLeague(userTeam.getId(), leagueId);
                        request.setAttribute("REGISTERED", isRegistered);
                    }
                }

                boolean finishOneMatch = leagueDAO.startAtLeastOneMatch(leagueId);
                // choox nay phai validate la giai da bat dau va da cot it nhat 1 tran dau dien ra.
                if (finishOneMatch) {
                    // get leaguedetail
                    GoalVM topPlayerGoal = leagueDAO.getTopGoalScorersByLeagueId(leagueId);
                    LeagueRegisterVM topTeamGoal = leagueDAO.getTeamLeagueRankByGoals(leagueId);
                    LeagueRegisterVM topTeamFairPlay = leagueDAO.getTeamLeagueRankByFairPlay(leagueId);

                    request.setAttribute("TOP_SOCCER", topPlayerGoal);
                    request.setAttribute("TOP_TEAM_GOAL", topTeamGoal);
                    request.setAttribute("TOP_TEAM_FL", topTeamFairPlay);
                }

                // get blog league
                List<BlogLeagueVM> blogList = leagueDAO.getActiveBlogsByLeagueId(leagueId);

                request.setAttribute("BLOG_LEAGUE", blogList);
                request.setAttribute("selectedPage", index);
                request.setAttribute("endP", lastPage);
                request.setAttribute("selectedPage", index);
                request.setAttribute("COMMENTS", listComment);
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
            HttpSession session = request.getSession();
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

                if (session != null && session.getAttribute("USER") != null) {
                    User user = (User) session.getAttribute("USER");
                    if (user.getId() == league.getUserId()) {
                        request.setAttribute("OWNER", true);
                    }
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
            HttpSession session = request.getSession();

            if (leagueIds != null) {
                int id = Integer.parseInt(leagueIds);
                LeagueDAO leagueDAO = new LeagueDAO();
                League league = leagueDAO.getLeagueById(id);

                if (session != null && session.getAttribute("USER") != null) {
                    User user = (User) session.getAttribute("USER");
                    if (user.getId() == league.getUserId()) {
                        request.setAttribute("OWNER", true);
                    }
                }

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
                TeamDAO teamDAO = new TeamDAO();
                League league = leagueDAO.getLeagueById(id);
                MatchVM match = leagueDAO.getMatchByMatchId(id);

                // đonạ này chưa có phân trnag
                List<Team_Member> teamA = teamDAO.getListTeamMemberByTeamId(match.getHomeTeamId(), 1);
                List<Team_Member> teamB = teamDAO.getListTeamMemberByTeamId(match.getAwayTeamId(), 1);
                request.setAttribute("USER_LEAGUE", league);
                if (match != null) {
                    request.setAttribute("TEAMA", teamA);
                    request.setAttribute("TEAMB", teamB);
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
            String cardQuantityS = request.getParameter("cardQuantity");

            int scoreA = Integer.parseInt(scoreAs);
            int scoreB = Integer.parseInt(scoreBs);
            int matchId = Integer.parseInt(matchIds);
            int teamAId = Integer.parseInt(teamAIds);
            int teamBId = Integer.parseInt(teamBIds);
            int leagueId = Integer.parseInt(leagueIds);
            int cardQuantity = Integer.parseInt(cardQuantityS);

            int totalScore = scoreA + scoreB;

            LeagueDAO leagueDAO = new LeagueDAO();
            boolean result = leagueDAO.UpdateMatchResult(scoreA, scoreB, matchId, teamAId, teamBId, leagueId);
            if (result) {
                MatchDAO matchDAO = new MatchDAO();

                for (int i = 0; i < totalScore; i++) {
                    String goalPlayerId = request.getParameter("goalPlayerId[" + i + "]");
                    String goalTeamId = request.getParameter("goalTeamId[" + i + "]");
                    String goalTime = request.getParameter("goalTime[" + i + "]");
                    Goal goal = new Goal();
                    int playerId = Integer.parseInt(goalPlayerId);
                    int teamId = Integer.parseInt(goalTeamId);
                    int time = Integer.parseInt(goalTime);
                    goal.setGoal_Time(time);
                    goal.setTeamId(teamId);
                    goal.setMatchId(matchId);
                    goal.setTeamId(teamId);
                    goal.setTeamMemberId(playerId);
                    matchDAO.InsertMatchGoal(goal);
                }

                for (int i = 0; i < cardQuantity; i++) {
                    String cardPlayerId = request.getParameter("cardPlayerId[" + i + "]");
                    String cardTeamId = request.getParameter("cardTeamId[" + i + "]");
                    String cardTime = request.getParameter("cardTime[" + i + "]");
                    String cardType = request.getParameter("cardType[" + i + "]");

                    Card card = new Card();
                    int playerId = Integer.parseInt(cardPlayerId);
                    int teamId = Integer.parseInt(cardTeamId);
                    int time = Integer.parseInt(cardTime);
                    card.setCard_Time(time);
                    card.setTeamId(teamId);
                    card.setMatchId(matchId);
                    card.setTeamMemberId(playerId);
                    card.setType(cardType);
                    matchDAO.InsertMatchCard(card);
                }

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

                // validate các trận đấu điều đã kết thúc mới kết thúc giải được.
                boolean isFinishAllMatch = leagueDAO.isFinishAllMatch(leagueId);
                if (isFinishAllMatch) {
                    System.out.println("Goi vao day luon roi");
                    boolean result = leagueDAO.finishLeague(leagueId);
                    if (result) {
                        request.setAttribute("MESSAGE", "Giải đấu của bạn đã hoàn thành cảm ơn bạn");
                    } else {
                        request.setAttribute("ERROR", "Không thể kết thúc giải đấu");
                    }
                } else {
                    request.setAttribute("ERROR", "Các trận đấu vẫn chưa được diễn ra, không thể kết thúc giải");
                }
                League league = leagueDAO.getLeagueById(leagueId);
                if (league != null) {
                    List<LeagueRegister> listTeamRegister = leagueDAO.getTeamRegisterLeague(leagueId);
                    request.setAttribute("USER_LEAGUE", league);
                    request.setAttribute("LEAGUE_TEAM", listTeamRegister);
                    request.setAttribute("leagueId", leagueId);
                }
                request.getRequestDispatcher(url).forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
                request.setAttribute("leagueId", leagueIds);

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

    private void cancleLeague(HttpServletRequest request, HttpServletResponse response) {
        try {
            String leagueIds = request.getParameter("leagueId");
            if (leagueIds != null) {
                int leagueId = Integer.parseInt(leagueIds);
                LeagueDAO leagueDAO = new LeagueDAO();
                boolean result = leagueDAO.cancleRequestLeague(leagueId);
            }
            request.getRequestDispatcher("league?action=user-league").forward(request, response);
        } catch (Exception e) {
        }
    }

}
