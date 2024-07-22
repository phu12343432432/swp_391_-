/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.LeagueDAO;
import DAO.MatchDAO;
import DAO.NotificationDAO;
import DAO.TeamDAO;
import DAO.UserWalletDAO;
import Model.Card;
import Model.Goal;
import Model.Group;
import Model.League;
import Model.LeagueRegister;
import Model.LeagueRegisterVM;
import Model.Match;
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
import java.util.ArrayList;
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
            case "view-final":
                viewFinalMatch(request, response);
                isRedirect = true;
                break;
            case "league-group-result":
                leagueGroupTypeResult(request, response);
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
                case "update-league":
                    updateLeague(request, response);
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
                case "remove-team-from-league":
                    removeTeamRegisterFromLeague(request, response);
                    break;
                case "reject-team":
                    rejectRegisterTeamToLeague(request, response);
                    break;
                case "league-match":
                    viewLeagueMatch(request, response);
                    isRedirect = true;
                    break;
                case "cancle-league":
                    cancleLeague(request, response);
                    break;
                case "start-knockout-stage":
                    startKnockoutStage(request, response);
                    isRedirect = true;
                    break;
                case "knockout-stage":
                    ViewKnockoutMatch(request, response);
                    isRedirect = true;
                    break;
                case "start-final":
                    generateFinalMatch(request, response);
                    isRedirect = true;
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

            if (searchS != "") {
                listLeague = leagueDAO.searchLeagueByName(index, searchS);
                total = leagueDAO.searchLeagueByNameTotal(searchS);
            }

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
                String teamMemberSize = request.getParameter("team_member_size");
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
                        _league.setTeamMemberSize(Integer.parseInt(teamMemberSize));

                        _league.setUserId(userLogin.getId());
                        boolean result = leagueDAO.createLeague(_league, image);
                        if (result) {
                            // Tao notify
                            NotificationDAO notiDAO = new NotificationDAO();
                            String title = "QUẢN LÍ GIẢI ĐẤU";
                            String content = "Yêu cầu tạo giải của bạn đã được gửi, vui lòng đợi admin phê duyệt!";
                            notiDAO.createNotification(userLogin.getId(), title, content);

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

                    // số lượng cầu thủ hiện tại của đội.
                    int currentTeamSize = userTeam.getTeamSize();
                    int requiredTeamSize = league.getTeamMemberSize();

                    String dateRemains = league.getDateRegister();

                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();
                    Date currentDate = dateFormat.parse(dateRemains);
                    if (date.compareTo(currentDate) > 0) {
                        request.setAttribute("ERROR", "Đã hết hạn đăng kí giải đấu");
                    } else if (currentTeamSize < requiredTeamSize) {
                        request.setAttribute("ERROR", "Số lượng thành viên của đội bóng bạn không đủ để tham gia. Vui lòng tạo thêm");
                    } else {
//                        boolean hasConflict = leagueDAO.hasTeamRegisteredOtherLeague(userTeam.getId(), league.getStartDate(), league.getEndDate());
//                        if (hasConflict) {
//                            request.setAttribute("ERROR", "Đội bóng của bạn đã đăng ký tham gia một giải đấu khác trong khoảng thời gian này.");
//                        } else {
                        int status = leagueDAO.registerLeague(userTeam.getId(), leagueId);
                        if (status == 0) {
                            request.setAttribute("MESSAGE", "Đăng kí thành công");

                            NotificationDAO notiDAO = new NotificationDAO();
                            String title = "THAM GIA GIẢI ĐẤU";
                            String contentNoti = "Yêu cầu tham gia giải đấu [" + league.getName() + "] của bạn đã được gửi!";
                            notiDAO.createNotification(userTeam.getUserId(), title, contentNoti);

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

                // get top 1 top 2 top 3
                if (league.getType().equals("2")) {
                    MatchDAO matchDAO = new MatchDAO();
                    if (league.getStatus() == 5) {
                        // gồm info id của 2 đội giải 3
                        List<MatchVM> listTop3Team = leagueDAO.getLoserInKnockoutStage(leagueId);
                        Match match = matchDAO.getLastMatchInLeague(leagueId);
                        int top1TeamId = 0;
                        int top2TeamId = 0;

                        if (match.getScoreAway() > match.getScoreHome()) {
                            top1TeamId = match.getAwayTeamId();
                            top2TeamId = match.getHomeTeamId();
                        } else {
                            top1TeamId = match.getHomeTeamId();
                            top2TeamId = match.getAwayTeamId();
                        }

                        Team top1 = teamDAO.getTeamById(top1TeamId);
                        Team top2 = teamDAO.getTeamById(top2TeamId);

                        List<Team> listTop3 = new ArrayList<>();
                        for (MatchVM team : listTop3Team) {
                            Team top3 = teamDAO.getTeamById(team.getLosingId());
                            listTop3.add(top3);
                        }

                        request.setAttribute("TEAM1", top1);
                        request.setAttribute("TEAM2", top2);
                        request.setAttribute("TEAM3", listTop3);
                    }

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

                // set groupdIDs 
                if (league.getType().equals("2")) {
                    List<Group> listGroup = leagueDAO.getListGroup(leagueId);
                    if (listGroup.size() > 0) {
                        System.out.println("LeagueId tu trang view detail " + listGroup.get(0).getId());
                        request.setAttribute("groupId", listGroup.get(0).getId());
                    }
                }
            } else {
                System.out.println("Cannto get value in ViewLeagueDetail");
            }

            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateLeague(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = request.getParameter("name");
            String description = request.getParameter("desc");
            String ids = request.getParameter("leagueId");
            LeagueDAO leagueDAO = new LeagueDAO();
            League _league = new League();
            _league.setId(Integer.parseInt(ids));
            _league.setName(name);
            _league.setDescription(name);

            League league = leagueDAO.updateLeagueDetail(_league);
            request.setAttribute("USER_LEAGUE", league);
            String url = "views/user/league-owner/league-details.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void viewLeagueDetail_Owner(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = "views/user/league-owner/league-details.jsp";
            HttpSession session = request.getSession();
            String idS = request.getParameter("leagueId");
            int id = Integer.parseInt(idS);
            LeagueDAO leagueDAO = new LeagueDAO();
            League league = leagueDAO.getLeagueById(id);
            if (league.getType().equals("2")) {
                List<Group> listGroup = leagueDAO.getListGroup(id);
                if (listGroup.size() > 0) {
                    request.setAttribute("groupId", listGroup.get(0).getId());
                }
            }
            if (league != null) {
                TeamDAO teamDAO = new TeamDAO();
                MatchDAO matchDAO = new MatchDAO();
                // gồm info id của 2 đội giải 3

                if (league.getType().equals("2") && league.getStatus() == 5) {
                    List<MatchVM> listTop3Team = leagueDAO.getLoserInKnockoutStage(id);
                    Match match = matchDAO.getLastMatchInLeague(id);
                    int top1TeamId = 0;
                    int top2TeamId = 0;

                    if (match.getScoreAway() > match.getScoreHome()) {
                        top1TeamId = match.getAwayTeamId();
                        top2TeamId = match.getHomeTeamId();
                    } else {
                        top1TeamId = match.getHomeTeamId();
                        top2TeamId = match.getAwayTeamId();
                    }

                    Team top1 = teamDAO.getTeamById(top1TeamId);
                    Team top2 = teamDAO.getTeamById(top2TeamId);

                    List<Team> listTop3 = new ArrayList<>();
                    for (MatchVM team : listTop3Team) {
                        Team top3 = teamDAO.getTeamById(team.getLosingId());
                        listTop3.add(top3);
                    }

                    request.setAttribute("TEAM1", top1);
                    request.setAttribute("TEAM2", top2);
                    request.setAttribute("TEAM3", listTop3);
                }

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
                List<MatchVM> listMatch = new ArrayList<>();
                List<Group> listGroup = new ArrayList<>();
                System.out.println("");

                if (league.getType().equals("2")) {
                    listMatch = leagueDAO.generateMatchWithGroup(id);
                    listGroup = leagueDAO.getListGroup(id);
                    session.setAttribute("GROUPS", listGroup);
                } else {
                    listMatch = leagueDAO.generateMatch(id);
                }

                List<LeagueRegister> listTeamRegister = leagueDAO.getTeamRegisterLeague(id);
                request.setAttribute("LEAGUE_TEAM", listTeamRegister);
                request.setAttribute("USER_LEAGUE", league);
                if (listMatch == null) {
                    // validate check nếu giải chưa full
                    request.setAttribute("ERROR", "Số đội bóng tham gia chưa đủ bạn có chắc là sẽ bắt đầu giải đấu");
                } else if (listMatch.size() == 0) {
                    request.setAttribute("ERROR", "Chưa có đội bóng nào tham gia giải đấu của bạn");
                } else if (listMatch.size() > 0) {
                    if (league.getType().equals("2")) {
                        listMatch = leagueDAO.getMatchByLeagueType2(id, listGroup.get(0).getId());
                        request.setAttribute("groupId", listGroup.get(0).getId());
                    } else {
                        listMatch = leagueDAO.getMatchByLeagueType1(id);
                    }
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
            String groupIds = request.getParameter("groupId");
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

                List<MatchVM> listMatch = new ArrayList<>();
                if (league.getType().equals("2")) {
                    listMatch = leagueDAO.getMatchByLeagueType2(id, Integer.parseInt(groupIds));
                    List<Group> listGroup = leagueDAO.getListGroup(id);
                    request.setAttribute("GROUPS", listGroup);
                    request.setAttribute("groupId", groupIds);
                } else {
                    listMatch = leagueDAO.getMatchByLeagueType1(id);
                }
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
            String groupIds = request.getParameter("groupId");
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
                    request.setAttribute("groupId", groupIds);
                    request.setAttribute("TEAMA", teamA);
                    request.setAttribute("TEAMB", teamB);
                    request.setAttribute("MATCH", match);
                    url = "views/user/league-owner/match-detail.jsp";
                } else {
                    request.setAttribute("ERROR", "Chưa có đội bóng nào tham gia giải đấu của bạn");
                }

                request.setAttribute("groupId", groupIds);
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

            String groupIds = request.getParameter("groupId");

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

                League league = leagueDAO.getLeagueById(leagueId);
                if (league.getType().equals("2") && league.getStatus() == 8) {
                    url = "league?action=view-final&leagueId=" + leagueIds + "&groupId=" + groupIds;
                } else {
                    url = "league?action=league-match&leagueId=" + leagueIds + "&groupId=" + groupIds;
                }
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
            String groupIds = request.getParameter("groupId");
            if (leagueIds != null) {
                int leagueId = Integer.parseInt(leagueIds);
                LeagueDAO leagueDAO = new LeagueDAO();

                // validate các trận đấu điều đã kết thúc mới kết thúc giải được.
                boolean isFinishAllMatch = leagueDAO.isFinishAllMatch(leagueId);
                if (isFinishAllMatch) {
                    // trường hợp này check cả 2 loại giải số trận hết thì mới cho kết thúc giải.
                    boolean result = false;
                    League league = leagueDAO.getLeagueById(leagueId);

                    // trường hợp giải xoay vòng thi coi như kết thúc.
                    result = leagueDAO.finishLeague(leagueId);

                    if (league.getType().equals("2")) {
                        MatchDAO matchDAO = new MatchDAO();

                        // gồm info id của 2 đội giải 3
                        List<MatchVM> listTop3Team = leagueDAO.getLoserInKnockoutStage(leagueId);
                        Match match = matchDAO.getLastMatchInLeague(leagueId);
                        int top1TeamId = 0;
                        int top2TeamId = 0;

                        if (match.getScoreAway() > match.getScoreHome()) {
                            top1TeamId = match.getAwayTeamId();
                            top2TeamId = match.getHomeTeamId();
                        } else {
                            top1TeamId = match.getHomeTeamId();
                            top2TeamId = match.getAwayTeamId();
                        }
                        System.out.println("TeamTop 1" + top1TeamId);
                        System.out.println("TeamTop 2" + top2TeamId);

                        TeamDAO teamDAO = new TeamDAO();
                        Team top1 = teamDAO.getTeamById(top1TeamId);
                        Team top2 = teamDAO.getTeamById(top2TeamId);

                        List<Team> listTop3 = new ArrayList<>();
                        for (MatchVM team : listTop3Team) {
                            Team top3 = teamDAO.getTeamById(team.getLosingId());
                            listTop3.add(top3);
                        }

                        request.setAttribute("TEAM1", top1);
                        request.setAttribute("TEAM2", top2);
                        request.setAttribute("TEAM3", listTop3);
                    }

                    if (result) {
                        request.setAttribute("MESSAGE", "Giải đấu của bạn đã hoàn thành cảm ơn bạn");
                    } else {
                        request.setAttribute("ERROR", "Không thể kết thúc giải đấu");
                    }
                } else {
                    request.setAttribute("ERROR", "Các trận đấu vẫn chưa được diễn ra, không thể kết thúc giải");
                }
                request.setAttribute("groupId", groupIds);

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
            String groupIds = request.getParameter("groupId");
            if (leagueIds != null) {
                int leagueId = Integer.parseInt(leagueIds);
                LeagueDAO leagueDAO = new LeagueDAO();
                List<LeagueRegisterVM> listRank = new ArrayList<LeagueRegisterVM>();
                League league = leagueDAO.getLeagueById(leagueId);

                // xet theo loaij giai chia lam 2 truong hop 
                // 1 la giai xoay vong 
                // 2 la giai chia theo bang
                if (league.getType().equals("2")) {
                    List<Group> listGroup = leagueDAO.getListGroup(leagueId);
                    request.setAttribute("GROUPS", listGroup);
                    request.setAttribute("groupId", listGroup.get(0).getId());
                    int groupId = 0;
                    if (groupIds == null) {
                        groupId = listGroup.get(0).getId();
                    } else {
                        groupId = Integer.parseInt(groupIds);
                        request.setAttribute("groupId", groupId);
                    }
                    listRank = leagueDAO.getTeamLeaugeRankInGroupId(leagueId, groupId);
                } else {
                    listRank = leagueDAO.getTeamLeaugeRank(leagueId);
                }

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
                League league = leagueDAO.getLeagueById(leagueId);
                if (league != null) {

                    List<LeagueRegister> listConfirm = leagueDAO.getTeamRegisterLeagueApprove(leagueId);
                    int leagueTeamRegisterSize = league.getTeamSize();

                    // get teamLeagueSize();
                    if (listConfirm.size() < leagueTeamRegisterSize) {
                        boolean result = leagueDAO.acceptToJoinLeague(teamId, leagueId);
                        if (result) {
                            int userId = teamDAO.getUserIdByTeamID(teamId);
                            NotificationDAO notiDAO = new NotificationDAO();
                            String title = "THAM GIA GIẢI ĐẤU";
                            String contentNoti = "Yêu cầu tham gia giải đấu [" + league.getName() + "] đã được duyệt!";
                            notiDAO.createNotification(userId, title, contentNoti);

                            request.setAttribute("MESSAGE", "Bạn đã đồng ý coho " + teamRegister.getName() + " tham gia vào giải đấu này");
                        } else {
                            request.setAttribute("ERROR", "Cho đội - " + teamIds + " tham gia vào giải không thành công.");
                        }
                    } else {
                        request.setAttribute("ERROR", "Bạn đã duyệt đủ số lượng yêu cầu tối đa của giải đấu");
                    }
                    List<LeagueRegister> listTeamRegister = leagueDAO.getTeamRegisterLeague(leagueId);
                    // getlist confirm
                    request.setAttribute("USER_LEAGUE", league);
                    request.setAttribute("LEAGUE_TEAM", listTeamRegister);
                    request.setAttribute("leagueId", leagueIds);

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

    private void ViewKnockoutMatch(HttpServletRequest request, HttpServletResponse response) {
        try {
            LeagueDAO leagueDAO = new LeagueDAO();
            HttpSession session = request.getSession();
            String leagueIds = request.getParameter("leagueId");
            String groupIds = request.getParameter("groupId");
            String url = "";
            List<MatchVM> listMatch = new ArrayList<>();
            int leagueId = Integer.parseInt(leagueIds);
            League league = leagueDAO.getLeagueById(leagueId);
            if (leagueIds != null) {

                if (session != null && session.getAttribute("USER") != null) {
                    User user = (User) session.getAttribute("USER");
                    if (user.getId() == league.getUserId()) {
                        request.setAttribute("OWNER", true);
                    }
                }
            }
            listMatch = leagueDAO.getMatchByLeagueKnowoutStage(leagueId, Integer.parseInt(groupIds));
            List<Group> listGroup = leagueDAO.getListGroup(leagueId);
            request.setAttribute("GROUPS", listGroup);
            request.setAttribute("groupId", groupIds);
            request.setAttribute("USER_LEAGUE", league);
            if (listMatch.size() > 0) {
                request.setAttribute("LIST_MATCH", listMatch);
                request.setAttribute("ISKNOCKOUT", true);
                url = "views/user/league-owner/league-match.jsp";
            } else {
                request.setAttribute("ERROR", "Chưa có đội bóng nào tham gia giải đấu của bạn");
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateFinalMatch(HttpServletRequest request, HttpServletResponse response) {
        try {
            LeagueDAO leagueDAO = new LeagueDAO();
            HttpSession session = request.getSession();
            String leagueIds = request.getParameter("leagueId");
            String groupIds = request.getParameter("groupId");
            String url = "";
            List<MatchVM> listMatch = new ArrayList<>();
            int leagueId = Integer.parseInt(leagueIds);
            League league = leagueDAO.getLeagueById(leagueId);

            if (leagueIds != null) {
                if (session != null && session.getAttribute("USER") != null) {
                    User user = (User) session.getAttribute("USER");
                    if (user.getId() == league.getUserId()) {
                        request.setAttribute("OWNER", true);
                    }
                }
            }

            // validate các trận đấu điều đã kết thúc mới kết thúc giải được.
            boolean isFinishAllMatch = leagueDAO.isFinishAllMatchInGroup(leagueId);
            if (isFinishAllMatch) {
                MatchDAO matchDAO = new MatchDAO();
                boolean result = false;
                List<LeagueRegisterVM[]> matchups = new ArrayList<>();
                TeamDAO teamDAO = new TeamDAO();

                Team team = teamDAO.getTeamById(leagueId);

                // trả về id của team đã thắng ở các bảng knock out.
                listMatch = leagueDAO.getWinnerInKnockoutStage(leagueId);
                for (var i = 0; i < listMatch.size(); i += 2) {

                    String endDate = league.getEndDate().toString();

                    Match match = matchDAO.getLastMatchInLeague(leagueId);

                    LeagueRegisterVM teamWin1 = new LeagueRegisterVM();
                    MatchVM matchVM1 = listMatch.get(i);
                    teamWin1.setTeamId(matchVM1.getWinningId());

                    LeagueRegisterVM teamWin2 = new LeagueRegisterVM();
                    MatchVM matchVM2 = listMatch.get(i + 1);
                    teamWin2.setTeamId(matchVM2.getWinningId());

                    matchups.add(new LeagueRegisterVM[]{teamWin1, teamWin2});
                    LocalDateTime _startTime = match.getEndAt();
                    LocalDateTime _endTime = _startTime.plusHours(2);
                    int groupId = Integer.parseInt(groupIds);
                    result = leagueDAO.generateFinalMatchWithGroupInKnockoutStage(matchups, leagueId, _startTime, _endTime);

                    // update thong tin thanh cong
                    if (result) {
                        listMatch = leagueDAO.getFinalMatchByLeagueId(leagueId);
                        league = leagueDAO.getLeagueById(leagueId);
                        //update thanh congf xong get lai
                        if (listMatch.size() > 0) {
                            List<Group> listGroup = leagueDAO.getListGroup(leagueId);
                            request.setAttribute("USER_LEAGUE", league);
                            request.setAttribute("LIST_MATCH", listMatch);
                            request.setAttribute("ISKNOCKOUT", true);
                            request.setAttribute("GROUPS", listGroup);
                            request.setAttribute("groupId", groupIds);
                            url = "views/user/league-owner/league-match.jsp";
                        } else {
                            request.setAttribute("ERROR", "Chưa có đội bóng nào tham gia giải đấu của bạn");
                        }
                    }
                }
            } else {
                request.setAttribute("ERROR", "Các trận đấu vẫn chưa được diễn ra, không thể kết thúc giải");
                url = "league?action=knockout-stage&leagueId=" + leagueIds + "&groupId=" + groupIds;
            }

            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void viewFinalMatch(HttpServletRequest request, HttpServletResponse response) {
        try {
            LeagueDAO leagueDAO = new LeagueDAO();
            HttpSession session = request.getSession();
            String leagueIds = request.getParameter("leagueId");
            String groupIds = request.getParameter("groupId");
            String url = "";
            List<MatchVM> listMatch = new ArrayList<>();
            int leagueId = Integer.parseInt(leagueIds);
            League league = leagueDAO.getLeagueById(leagueId);
            if (leagueIds != null) {

                if (session != null && session.getAttribute("USER") != null) {
                    User user = (User) session.getAttribute("USER");
                    if (user.getId() == league.getUserId()) {
                        request.setAttribute("OWNER", true);
                    }
                }
            }
            MatchDAO matchDAO = new MatchDAO();
            boolean result = false;
            List<LeagueRegisterVM[]> matchups = new ArrayList<>();
            TeamDAO teamDAO = new TeamDAO();

            Team team = teamDAO.getTeamById(leagueId);

            Match match = matchDAO.getLastMatchInLeague(leagueId);
            List<Group> listGroup = leagueDAO.getListGroup(leagueId);
            request.setAttribute("GROUPS", listGroup);
            request.setAttribute("groupId", groupIds);
            request.setAttribute("USER_LEAGUE", league);
            listMatch = leagueDAO.getFinalMatchByLeagueId(leagueId);
            if (listMatch.size() > 0) {
                // bắt đầu trân chugn kết xong update lại status của giải thành 7 => Đã bắt đầu trận chung kết.

                request.setAttribute("LIST_MATCH", listMatch);
                request.setAttribute("ISKNOCKOUT", true);
                url = "views/user/league-owner/league-match.jsp";
            } else {
                request.setAttribute("ERROR", "Chưa có đội bóng nào tham gia giải đấu của bạn");
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void leagueGroupTypeResult(HttpServletRequest request, HttpServletResponse response) {
        try {
            LeagueDAO leagueDAO = new LeagueDAO();
            HttpSession session = request.getSession();
            String leagueIds = request.getParameter("leagueId");
            String groupIds = request.getParameter("groupId");
            String url = "";
            List<MatchVM> listMatch = new ArrayList<>();
            int leagueId = Integer.parseInt(leagueIds);
            League league = leagueDAO.getLeagueById(leagueId);
            if (leagueIds != null) {
                if (session != null && session.getAttribute("USER") != null) {
                    User user = (User) session.getAttribute("USER");
                    if (user.getId() == league.getUserId()) {
                        request.setAttribute("OWNER", true);
                    }
                }
            }
            MatchDAO matchDAO = new MatchDAO();
            boolean result = false;
            List<LeagueRegisterVM[]> matchups = new ArrayList<>();
            TeamDAO teamDAO = new TeamDAO();

            // gồm info id của 2 đội giải 3
            List<MatchVM> listTop3Team = leagueDAO.getLoserInKnockoutStage(leagueId);
            Match match = matchDAO.getLastMatchInLeague(leagueId);
            int top1TeamId = 0;
            int top2TeamId = 0;

            if (match.getScoreAway() > match.getScoreHome()) {
                top1TeamId = match.getAwayTeamId();
                top2TeamId = match.getHomeTeamId();
            } else {
                top1TeamId = match.getHomeTeamId();
                top2TeamId = match.getAwayTeamId();
            }

            Team top1 = teamDAO.getTeamById(top1TeamId);
            Team top2 = teamDAO.getTeamById(top2TeamId);

            List<Team> listTop3 = new ArrayList<>();
            for (MatchVM team : listTop3Team) {
                Team top3 = teamDAO.getTeamById(team.getLosingId());
                listTop3.add(top3);
            }

            request.setAttribute("TEAM1", top1);
            request.setAttribute("TEAM2", top2);
            request.setAttribute("TEAM3", listTop3);

            Team team = teamDAO.getTeamById(leagueId);

            List<Group> listGroup = leagueDAO.getListGroup(leagueId);
            request.setAttribute("GROUPS", listGroup);
            request.setAttribute("groupId", groupIds);
            request.setAttribute("USER_LEAGUE", league);
            listMatch = leagueDAO.getFinalMatchByLeagueId(leagueId);
            if (listMatch.size() > 0) {
                // bắt đầu trân chugn kết xong update lại status của giải thành 7 => Đã bắt đầu trận chung kết.

                request.setAttribute("LIST_MATCH", listMatch);
                request.setAttribute("ISKNOCKOUT", true);
                url = "views/user/user/league-group-result.jsp";
            } else {
                request.setAttribute("ERROR", "Chưa có đội bóng nào tham gia giải đấu của bạn");
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startKnockoutStage(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = "views/user/league-owner/league-details.jsp";
            String leagueIds = request.getParameter("leagueId");
            String groupIds = request.getParameter("groupId");
            if (leagueIds != null) {
                int leagueId = Integer.parseInt(leagueIds);
                LeagueDAO leagueDAO = new LeagueDAO();

                // validate các trận đấu điều đã kết thúc mới kết thúc giải được.
                boolean isFinishAllMatch = leagueDAO.isFinishAllMatch(leagueId);
                if (isFinishAllMatch) {
                    // trường hợp này check cả 2 loại giải số trận hết thì mới cho kết thúc giải.
                    boolean result = false;
                    League league = leagueDAO.getLeagueById(leagueId);
                    if (league.getType().equals("2")) {

                        // nếu là vòng bảng thì sẽ lấy nhất bảng của thằng bảng 1 tạo cặp trận với thằng trận kia bảng B 
                        List<LeagueRegisterVM[]> matchups = new ArrayList<>();
                        List<Group> listGroup = leagueDAO.getListGroup(leagueId);
                        // chạy 1 lần 
                        for (int i = 0; i < listGroup.size(); i += 2) {
                            // doi ban A_C
                            List<LeagueRegisterVM> listRankTable1 = leagueDAO.getTeamLeaugeRankInGroupId(leagueId, listGroup.get(i).getId());
                            // doi bang B_D
                            List<LeagueRegisterVM> listRankTable2 = leagueDAO.getTeamLeaugeRankInGroupId(leagueId, listGroup.get(i + 1).getId());

                            // Tạo trận đấu giữa nhất bảng A và nhì bảng B
                            matchups.add(new LeagueRegisterVM[]{listRankTable1.get(0), listRankTable2.get(1)});
                            matchups.add(new LeagueRegisterVM[]{listRankTable1.get(1), listRankTable2.get(0)});

                            // tạo hàm generate trận đấy.
                            // 1.get thoi gian startDate = end day cua tran đấu cuối cùng.
                            MatchDAO matchDAO = new MatchDAO();
                            String endDate = league.getEndDate().toString();
                            Match match = matchDAO.getLastMatchInLeague(leagueId);

                            LocalDateTime _startTime = match.getEndAt();
                            LocalDateTime _endTime = _startTime.plusHours(2);
                            int groupId = Integer.parseInt(groupIds);
                            result = leagueDAO.generateMatchWithGroupInKnockoutStage(matchups, leagueId, _startTime, _endTime);

                        }

                    }
                    if (result) {
                        request.setAttribute("MESSAGE", "Giải đấu của bạn đã hoàn thành cảm ơn bạn");
                    } else {
                        request.setAttribute("ERROR", "Không thể kết thúc giải đấu");
                    }
                } else {
                    request.setAttribute("ERROR", "Các trận đấu vẫn chưa được diễn ra, không thể kết thúc giải");
                }
                request.setAttribute("groupId", groupIds);

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

    private void removeTeamRegisterFromLeague(HttpServletRequest request, HttpServletResponse response) {
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
                League league = leagueDAO.getLeagueById(leagueId);
                if (league != null) {
                    // get teamLeagueSize();
                    boolean result = leagueDAO.changeStatusTeamRegsiterFromLeague(teamId, leagueId);
                    if (result) {
                        List<LeagueRegister> listTeamRegister = leagueDAO.getTeamRegisterLeague(leagueId);
                        request.setAttribute("USER_LEAGUE", league);
                        request.setAttribute("LEAGUE_TEAM", listTeamRegister);
                        request.setAttribute("leagueId", leagueIds);
                        request.setAttribute("MESSAGE", "Bạn đã loại bỏ " + teamRegister.getName() + "khỏi danh sách phê duyệt");
                    } else {
                        request.setAttribute("ERROR", "Cho đội - " + teamIds + " tham gia vào giải không thành công.");
                    }

                    request.getRequestDispatcher(url).forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void rejectRegisterTeamToLeague(HttpServletRequest request, HttpServletResponse response) {
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
                League league = leagueDAO.getLeagueById(leagueId);
                if (league != null) {
                    // get teamLeagueSize();
                    boolean result = leagueDAO.removeTeamRegsiterFromLeague(teamId, leagueId);
                    if (result) {
                        List<LeagueRegister> listTeamRegister = leagueDAO.getTeamRegisterLeague(leagueId);
                        request.setAttribute("USER_LEAGUE", league);
                        request.setAttribute("LEAGUE_TEAM", listTeamRegister);
                        request.setAttribute("leagueId", leagueIds);
                        request.setAttribute("MESSAGE", "Bạn đã loại bỏ " + teamRegister.getName() + " ra khỏi giải đấu");

                        int userId = teamDAO.getUserIdByTeamID(teamId);
                        NotificationDAO notiDAO = new NotificationDAO();
                        String title = "THAM GIA GIẢI ĐẤU";
                        String contentNoti = "Yêu cầu tham gia giải đấu [" +league.getName()+"] đã bị từ chối. Vui lòng kiểm tra lại!";
                        notiDAO.createNotification(userId, title, contentNoti);

                    } else {
                        request.setAttribute("ERROR", "Cho đội - " + teamIds + " tham gia vào giải không thành công.");
                    }

                    request.getRequestDispatcher(url).forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
