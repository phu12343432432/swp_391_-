/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.DBContext;
import Model.League;
import Model.LeagueRegister;
import Model.MatchVM;
import Model.User;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class LeagueDAO {

    private Connection con;
    private List<User> user;
    PreparedStatement ps;
    ResultSet rs;

    public LeagueDAO() {
        try {
            con = new DBContext().getConnection();
            System.out.println("Connect success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTotalLeague() {
        try {
            String sql = "SELECT COUNT(*) FROM League";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTotalUserLeague(int UserId) {
        try {
            String sql = "SELECT COUNT(*)  FROM League WHERE UserId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, UserId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public League getLeagueById(int leagueId) {
        try {
            String sql = "SELECT * FROM League WHERE Id = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            if (rs.next()) {
                League league = new League();
                league.setId(rs.getInt("Id"));
                league.setStatus(rs.getInt("Status"));
                league.setAddress(rs.getString("Address"));
                league.setName(rs.getString("Name"));
                league.setDateRegister(rs.getString("DateRegister"));
                league.setDescription(rs.getString("Description"));
                league.setStartDate(rs.getTimestamp("StartDate").toLocalDateTime());
                league.setEndDate(rs.getTimestamp("EndDate").toLocalDateTime());
                league.setTeamSize(rs.getInt("TeamSize"));
                league.setType(rs.getString("Type"));
                league.setUserId(rs.getInt("UserId"));
                byte[] imgData = rs.getBytes("Image");
                String base64Image = Base64.getEncoder().encodeToString(imgData);
                league.setImage(base64Image);
                return league;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<League> getUserLeaguePaged(int index, int UserId) {
        List<League> listLeague = new ArrayList<>();
        try {
            String sql = "SELECT * FROM League WHERE UserId = ? ORDER BY CreateAt OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setInt(1, UserId);
            ps.setInt(2, (index - 1) * 4);
            rs = ps.executeQuery();
            while (rs.next()) {
                League league = new League();
                league.setId(rs.getInt("Id"));
                league.setAddress(rs.getString("Address"));
                league.setName(rs.getString("Name"));
                league.setStatus(rs.getInt("Status"));
                league.setDescription(rs.getString("Description"));
                league.setDateRegister(rs.getString("DateRegister"));
                league.setStartDate(rs.getTimestamp("StartDate").toLocalDateTime());
                league.setEndDate(rs.getTimestamp("EndDate").toLocalDateTime());
                league.setTeamSize(rs.getInt("TeamSize"));
                league.setType(rs.getString("Type"));
                league.setUserId(rs.getInt("UserId"));
                byte[] imgData = rs.getBytes("Image");
                String base64Image = Base64.getEncoder().encodeToString(imgData);
                league.setImage(base64Image);
                listLeague.add(league);
            }
            return listLeague;
        } catch (Exception e) {
        }
        return null;
    }

    public List<League> getLeaguePaged(int index) {
        List<League> listLeague = new ArrayList<>();
        try {
            String sql = "SELECT * FROM League ORDER BY CreateAt OFFSET ? ROWS FETCH NEXT 9 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 9);
            rs = ps.executeQuery();
            while (rs.next()) {
                League league = new League();
                league.setId(rs.getInt("Id"));
                league.setAddress(rs.getString("Address"));
                league.setName(rs.getString("Name"));
                league.setStatus(rs.getInt("Status"));
                league.setDescription(rs.getString("Description"));
                league.setStartDate(rs.getTimestamp("StartDate").toLocalDateTime());
                league.setEndDate(rs.getTimestamp("EndDate").toLocalDateTime());
                league.setTeamSize(rs.getInt("TeamSize"));
                league.setDateRegister(rs.getString("DateRegister"));
                league.setType(rs.getString("Type"));
                league.setUserId(rs.getInt("UserId"));

                byte[] imgData = rs.getBytes("Image");
                String base64Image = Base64.getEncoder().encodeToString(imgData);
                league.setImage(base64Image);
                listLeague.add(league);
            }
            return listLeague;
        } catch (Exception e) {
        }
        return null;
    }

    public List<League> searchLeagueByName(int index, String search) {
        List<League> listLeague = new ArrayList<>();
        try {
            String sql = "SELECT * FROM League WHERE Name LIKE ? ORDER BY CreateAt OFFSET ? ROWS FETCH NEXT 9 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ps.setInt(2, (index - 1) * 9);

            rs = ps.executeQuery();
            while (rs.next()) {
                League league = new League();
                league.setId(rs.getInt("Id"));
                league.setAddress(rs.getString("Address"));
                league.setName(rs.getString("Name"));
                league.setDescription(rs.getString("Description"));
                league.setStartDate(rs.getTimestamp("StartDate").toLocalDateTime());
                league.setEndDate(rs.getTimestamp("EndDate").toLocalDateTime());
                league.setDateRegister(rs.getString("DateRegister"));
                league.setTeamSize(rs.getInt("TeamSize"));
                league.setType(rs.getString("Type"));
                byte[] imgData = rs.getBytes("Image");
                String base64Image = Base64.getEncoder().encodeToString(imgData);
                league.setImage(base64Image);
                listLeague.add(league);
            }
            return listLeague;
        } catch (Exception e) {
        }
        return null;
    }

    public boolean createLeague(League league, Part image) {
        try {

            String sql = "INSERT  dbo.[League] ([Name], [Description], [TeamSize], "
                    + "[StartDate], [EndDate], [Address], [Image] ,[CreateAt], [UserId], [Type], [DateRegister]) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, league.getName());
            ps.setString(2, league.getDescription());
            ps.setInt(3, league.getTeamSize());

            ps.setTimestamp(4, java.sql.Timestamp.valueOf(league.getStartDate()));
            ps.setTimestamp(5, java.sql.Timestamp.valueOf(league.getEndDate()));
            ps.setString(6, league.getAddress());
            InputStream fileContent = image.getInputStream();
            ps.setBinaryStream(7, fileContent, (int) image.getSize());
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String currentDate = dateFormat.format(date);
            ps.setString(8, currentDate);
            ps.setInt(9, league.getUserId());
            ps.setString(10, league.getType());
            ps.setString(11, league.getDateRegister());
            int affectedRow = ps.executeUpdate();
            return affectedRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isFullTeam(int leagueId) {
        try {
            int totalTeam = 0;
            String sql = "SELECT COUNT(*) FROM League WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            if (rs.next()) {
                totalTeam = rs.getInt(1);
            }

            sql = "SELECT * FROM League WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int limitSize = rs.getInt("TeamSize");
                if (totalTeam == limitSize) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int registerLeague(int teamId, int leagueId) {
        try {
            boolean isFull = isFullTeam(leagueId);
            if (isFull) {
                return 1;
            }

            String sql = "SELECT * FROM dbo.[Team_League] WHERE TeamId = ? AND LeagueId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, teamId);
            ps.setInt(2, leagueId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return 2;
            } else {
                sql = "INSERT  dbo.[Team_League] (TeamId, LeagueId, RegisterAt, Point) VALUES (?, ?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setInt(1, teamId);
                ps.setInt(2, leagueId);
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String currentDate = dateFormat.format(date);
                ps.setString(3, currentDate);
                ps.setInt(4, 0);

                int affectedRow = ps.executeUpdate();
                if (affectedRow > 0) {
                    return 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    // chỗ này phân trang
    public List<League> getLeagueRegistered(int teamId) {
        List<League> listLeague = new ArrayList<>();
        try {
            String sql = "SELECT * FROM League le JOIN Team_League tl ON tl.LeagueId = le.Id WHERE tl.TeamId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, teamId);
            rs = ps.executeQuery();
            while (rs.next()) {
                League league = new League();
                league.setId(rs.getInt("Id"));
                league.setAddress(rs.getString("Address"));
                league.setName(rs.getString("Name"));
                league.setDescription(rs.getString("Description"));
                league.setStartDate(rs.getTimestamp("StartDate").toLocalDateTime());
                league.setEndDate(rs.getTimestamp("EndDate").toLocalDateTime());
                league.setTeamSize(rs.getInt("TeamSize"));
                league.setType(rs.getString("Type"));
                byte[] imgData = rs.getBytes("Image");
                String base64Image = Base64.getEncoder().encodeToString(imgData);
                league.setImage(base64Image);
                listLeague.add(league);
            }
            return listLeague;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LeagueRegister> getTeamRegisterLeague(int leagueId) {
        List<LeagueRegister> listTeam = new ArrayList<>();
        try {
            String sql = "SELECT tl.TeamId, tl.RegisterAt, tl.Point, t.Name, t.Image FROM League le JOIN Team_League tl ON tl.LeagueId = le.Id "
                    + "JOIN Team t ON tl.TeamId = t.Id WHERE le.Id = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            while (rs.next()) {
                LeagueRegister tl = new LeagueRegister();
                tl.setTeamId(rs.getInt("TeamId"));
                tl.setTeamName(rs.getString("Name"));
                tl.setRegisterAt(rs.getDate("RegisterAt").toString());
                tl.setPoint(rs.getInt("Point"));
                byte[] imgData = rs.getBytes("Image");
                String base64Image = Base64.getEncoder().encodeToString(imgData);
                tl.setImage(base64Image);
                listTeam.add(tl);
            }
            return listTeam;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<League> getLeaguePagedConfirm(int index) {
        List<League> listLeague = new ArrayList<>();
        try {
            String sql = "SELECT * FROM League WHERE Status = 0 ORDER BY CreateAt OFFSET ? ROWS FETCH NEXT 9 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 9);
            rs = ps.executeQuery();
            while (rs.next()) {
                League league = new League();
                league.setId(rs.getInt("Id"));
                league.setAddress(rs.getString("Address"));
                league.setName(rs.getString("Name"));
                league.setDescription(rs.getString("Description"));
                league.setStartDate(rs.getTimestamp("StartDate").toLocalDateTime());
                league.setEndDate(rs.getTimestamp("EndDate").toLocalDateTime());
                league.setTeamSize(rs.getInt("TeamSize"));
                league.setDateRegister(rs.getString("DateRegister"));
                league.setType(rs.getString("Type"));
                league.setUserId(rs.getInt("UserId"));

                byte[] imgData = rs.getBytes("Image");
                String base64Image = Base64.getEncoder().encodeToString(imgData);
                league.setImage(base64Image);
                listLeague.add(league);
            }
            return listLeague;
        } catch (Exception e) {
        }
        return null;
    }

    private LocalDateTime adjustDateTime(LocalDateTime dateTime) {
        // Kiểm tra nếu thời gian nằm ngoài khoảng từ 8 AM đến 8 PM
        if (dateTime.toLocalTime().isBefore(LocalTime.of(8, 0))) {
            // Nếu trước 8 AM, điều chỉnh thành 8 AM
            return dateTime.withHour(8).withMinute(0);
        } else if (dateTime.toLocalTime().isAfter(LocalTime.of(20, 0))) {
            // Nếu sau 8 PM, điều chỉnh thành 8 AM của ngày tiếp theo
            return dateTime.plusDays(1).withHour(8).withMinute(0);
        }
        return dateTime;
    }

    public List<MatchVM> generateMatch(int leagueId) {
        try {
            League league = getLeagueById(leagueId);
            List<MatchVM> listmatchVM = new ArrayList<>();
            String sql = "UPDATE League SET Status = ? WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, 2);
            ps.setInt(2, leagueId);

            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {

                List<LeagueRegister> listLeagueRegister = getTeamRegisterLeague(leagueId);
                int registerTeamSize = listLeagueRegister.size();
                if (registerTeamSize == 0) {
                    return null;
                }
                LocalDateTime startDate = league.getStartDate();
                LocalDateTime endDate = league.getEndDate();

                List<LeagueRegister[]> matchups = new ArrayList<>();
                // Create match 
                for (int i = 0; i < registerTeamSize; i++) {
                    for (int j = i + 1; j < registerTeamSize; j++) {
                        matchups.add(new LeagueRegister[]{listLeagueRegister.get(i), listLeagueRegister.get(j)});
                    }
                }
                Collections.shuffle(matchups);
                int matchIndex = 1;
                for (LeagueRegister[] matchup : matchups) {
                    String matchName = "Trận đấu thứ " + matchIndex;
                    sql = "INSERT INTO Match (LeagueId, HomeTeamId, AwayTeamId, Status, Name, Address, StartAt, EndAt) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, league.getId());
                    ps.setInt(2, matchup[0].getTeamId());
                    ps.setInt(3, matchup[1].getTeamId());
                    ps.setInt(4, 0);
                    ps.setString(5, matchName);
                    ps.setString(6, league.getAddress());
                    ps.setTimestamp(7, java.sql.Timestamp.valueOf(startDate));
                    startDate = startDate.plusHours(2); // Tăng 2 giờ
                    ps.setTimestamp(8, java.sql.Timestamp.valueOf(startDate));
                    int afftedRow = ps.executeUpdate();
                    MatchVM matchVM = new MatchVM();
                    if (afftedRow > 0) {
                        sql = "SELECT t.Id, t.Image, t.Name, t.ShortName, m.Id as mId FROM dbo.[Match] m JOIN dbo.Team t ON m.HomeTeamId = t.Id WHERE LeagueId = ?";
                        ps = con.prepareStatement(sql);
                        ps.setInt(1, leagueId);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            matchVM.setAddress(league.getAddress());
                            matchVM.setLeagueId(league.getId());
                            matchVM.setId(rs.getInt("mId"));
                            matchVM.setHomeTeamId(matchup[0].getTeamId());
                            matchVM.setHometeamName(rs.getString("Name"));
                            matchVM.setHometeamShortName(rs.getString("ShortName"));
                            byte[] imgData = rs.getBytes("Image");
                            String base64Image = Base64.getEncoder().encodeToString(imgData);
                            matchVM.setHometeamImage(base64Image);
                        }

                        sql = "SELECT t.Id, t.Image, t.Name, t.ShortName FROM dbo.[Match] m JOIN dbo.Team t ON m.AwayTeamId = t.Id WHERE LeagueId = ?";
                        ps = con.prepareStatement(sql);
                        ps.setInt(1, leagueId);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            matchVM.setAwayTeamId(matchup[1].getTeamId());
                            matchVM.setAwayteamName(rs.getString("Name"));
                            matchVM.setAwayteamShortName(rs.getString("ShortName"));
                            byte[] imgData = rs.getBytes("Image");
                            String base64Image = Base64.getEncoder().encodeToString(imgData);
                            matchVM.setAwayteamImage(base64Image);
                        }
                        listmatchVM.add(matchVM);
                    }
                    matchIndex++;
                }
            }
            return listmatchVM;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<MatchVM> getMatchByLeague(int leagueId) {
        try {
            League league = getLeagueById(leagueId);
            List<MatchVM> listmatchVM = new ArrayList<MatchVM>();
            String sql = "SELECT t.Id, t.Image, t.Name, t.ShortName, m.HomeTeamId, m.Id as mId, m.Status, m.ScoreHome, m.ScoreAway "
                    + " FROM dbo.[Match] m JOIN dbo.Team t ON m.HomeTeamId = t.Id WHERE LeagueId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            while (rs.next()) {
                MatchVM matchVM = new MatchVM();
                matchVM.setAddress(league.getAddress());
                matchVM.setScoreHome(rs.getInt("ScoreHome"));     
                matchVM.setScoreAway(rs.getInt("ScoreAway"));
                
                matchVM.setLeagueId(leagueId);
                matchVM.setId(rs.getInt("mId"));
                matchVM.setStatus(rs.getInt("Status"));
                matchVM.setHomeTeamId(rs.getInt("HomeTeamId"));
                matchVM.setHometeamName(rs.getString("Name"));
                matchVM.setHometeamShortName(rs.getString("ShortName"));
                byte[] imgData = rs.getBytes("Image");
                String base64Image = Base64.getEncoder().encodeToString(imgData);
                matchVM.setHometeamImage(base64Image);
                listmatchVM.add(matchVM);
            }
            System.out.println(listmatchVM);
            sql = "SELECT t.Id, t.Image, t.Name, t.ShortName, m.AwayTeamId"
                    + " FROM dbo.[Match] m JOIN dbo.Team t ON m.AwayTeamId = t.Id WHERE LeagueId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            int matchIndex = 0;
            if (listmatchVM.size() > 0) {
                while (rs.next()) {
                    listmatchVM.get(matchIndex).setAwayTeamId(rs.getInt("AwayTeamId"));
                    listmatchVM.get(matchIndex).setAwayteamName(rs.getString("Name"));
                    listmatchVM.get(matchIndex).setAwayteamShortName(rs.getString("ShortName"));
                    byte[] imgData = rs.getBytes("Image");
                    String base64Image = Base64.getEncoder().encodeToString(imgData);
                    listmatchVM.get(matchIndex).setAwayteamImage(base64Image);
                    matchIndex++;
                }
            } else {
                System.out.println("Null");
            }

            return listmatchVM;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public MatchVM getMatchByMatchId(int matchId) {
        try {
            String sql = "SELECT m.Id, t.Id as tId, t.Image, t.Name, t.ShortName, m.HomeTeamId, m.Status, m.LeagueId, m.ScoreHome, m.ScoreAway "
                    + " FROM dbo.[Match] m JOIN dbo.Team t ON m.HomeTeamId = t.Id WHERE m.Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, matchId);
            MatchVM matchVM = new MatchVM();
            rs = ps.executeQuery();
            if (rs.next()) {
                matchVM.setId(rs.getInt("Id"));
                matchVM.setScoreHome(rs.getInt("ScoreHome")); 
                matchVM.setScoreAway(rs.getInt("ScoreAway"));
                matchVM.setHomeTeamId(rs.getInt("tId"));
                matchVM.setLeagueId(rs.getInt("LeagueId"));
                matchVM.setStatus(rs.getInt("Status"));
                matchVM.setHomeTeamId(rs.getInt("HomeTeamId"));
                matchVM.setHometeamName(rs.getString("Name"));
                matchVM.setHometeamShortName(rs.getString("ShortName"));
                byte[] imgData = rs.getBytes("Image");
                String base64Image = Base64.getEncoder().encodeToString(imgData);
                matchVM.setHometeamImage(base64Image);
            }
            sql = "SELECT t.Image, t.Id as tId, t.Name, t.ShortName, m.AwayTeamId"
                    + " FROM dbo.[Match] m JOIN dbo.Team t ON m.AwayTeamId = t.Id WHERE m.Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, matchId);
            rs = ps.executeQuery();
            if (rs.next()) {
                matchVM.setAwayTeamId(rs.getInt("tId"));
                matchVM.setAwayTeamId(rs.getInt("AwayTeamId"));
                matchVM.setAwayteamName(rs.getString("Name"));
                matchVM.setAwayteamShortName(rs.getString("ShortName"));
                byte[] imgData = rs.getBytes("Image");
                String base64Image = Base64.getEncoder().encodeToString(imgData);
                matchVM.setAwayteamImage(base64Image);
            }

            return matchVM;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean UpdateMatchResult(int scoreA, int scoreB, int matchId, int homeId, int awayId, int leagueId) {
        try {
            String sql = "UPDATE Match Set ScoreHome = ?, ScoreAway = ?, Status = 1 WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, scoreA);
            ps.setInt(2, scoreB);
            ps.setInt(3, matchId);
            int afftedRow = ps.executeUpdate();
            if (afftedRow > 0) {
                if (scoreA > scoreB) {
                    sql = "UPDATE Team_League SET Point += 3 WHERE TeamId = ? AND LeagueId = ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, homeId);
                    ps.setInt(2, leagueId);
                    afftedRow = ps.executeUpdate();

                } else if (scoreA == scoreB) {
                    sql = "UPDATE Team_League SET Point += 1 WHERE TeamId = ? AND LeagueId = ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, awayId);
                    ps.setInt(2, leagueId);
                    afftedRow = ps.executeUpdate();

                    sql = "UPDATE Team_League SET Point += 1 WHERE TeamId = ? AND LeagueId = ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, homeId);
                    ps.setInt(2, leagueId);
                    afftedRow = ps.executeUpdate();
                } else {
                    sql = "UPDATE Team_League SET Point += 3 WHERE TeamId = ? AND LeagueId = ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, awayId);
                    ps.setInt(2, leagueId);
                    afftedRow = ps.executeUpdate();
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean finishLeague(int leagueId) {
        try {
            String sql = "UPDATE League Set Status = 3 WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            int afftedRow = ps.executeUpdate();
            return afftedRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        LeagueDAO leageDAO = new LeagueDAO();
        boolean result = leageDAO.UpdateMatchResult(4, 6, 74, 1, 3, 3);
        System.out.println("");
    }
}
