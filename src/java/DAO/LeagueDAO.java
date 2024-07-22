/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.DBContext;
import Model.Card;
import Model.Goal;
import Model.Group;
import Model.League;
import Model.LeagueRegister;
import Model.LeagueRegisterVM;
import Model.User;
import Model.UserWallet;
import Model.ViewModel.BlogLeagueVM;
import Model.ViewModel.CommentViewModel;
import Model.ViewModel.GoalVM;
import Model.ViewModel.MatchVM;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
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
            String sql = "SELECT COUNT(*) FROM League  WHERE Status != 0 AND Status != 1 AND Status != 3";
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

    public int getTotalLeague(int userId) {
        try {
            String sql = "SELECT COUNT(*) FROM League WHERE Status != 0 AND Status != 1 AND Status != 3 AND UserId != ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTotalUserLeague(int UserId, int status) {
        try {
            if (status == 7) {
                String sql = "SELECT COUNT(*)  FROM League WHERE UserId = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, UserId);
            } else {
                String sql = "SELECT COUNT(*)  FROM League WHERE UserId = ? AND Status = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, UserId);
                ps.setInt(2, status);
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTotalUserLeagueSearch(int UserId, int status, String search) {
        try {
            if (status == 7) {
                String sql = "SELECT COUNT(*)  FROM League WHERE UserId = ? AND Name LIKE ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, UserId);
                ps.setString(2, "%" + search + "%");

            } else {
                String sql = "SELECT COUNT(*)  FROM League WHERE UserId = ? AND Status = ? AND Name LIKE ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, UserId);
                ps.setInt(2, status);
                ps.setString(3, "%" + search + "%");

            }
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
                league.setTeamMemberSize(rs.getInt("TeamMemberSize"));
                byte[] imgData = rs.getBytes("Image");
                String base64Image = Base64.getEncoder().encodeToString(imgData);
                league.setImage(base64Image);
                return league;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<League> getUserLeaguePaged(int index, int UserId, int status) {
        List<League> listLeague = new ArrayList<>();
        try {
            if (status == 7) {
                String sql = "SELECT * FROM League WHERE UserId = ? ORDER BY CreateAt DESC OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setInt(1, UserId);
                ps.setInt(2, (index - 1) * 4);
            } else {
                String sql = "SELECT * FROM League WHERE UserId = ? AND Status = ? ORDER BY CreateAt DESC OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setInt(1, UserId);
                ps.setInt(2, status);
                ps.setInt(3, (index - 1) * 4);
            }
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
                league.setTeamMemberSize(rs.getInt("TeamMemberSize"));
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

    public int searchUserLeagueLeagueByNameTotal(int index, String search, int UserId, int status) {
        List<League> listLeague = new ArrayList<>();
        try {
            if (status == 4) {
                String sql = "SELECT COUNT(*) FROM League WHERE Name LIKE ? AND UserId = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + search + "%");
                ps.setInt(2, UserId);
                ps.setInt(3, (index - 1) * 4);
            } else {
                String sql = "SELECT COUNT(*) FROM League WHERE Name LIKE ? AND UserId = ? AND Status = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + search + "%");
                ps.setInt(2, UserId);
                ps.setInt(3, status);
                ps.setInt(4, (index - 1) * 4);
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<League> searchUserLeagueLeagueByName(int index, String search, int UserId, int status) {
        List<League> listLeague = new ArrayList<>();
        try {
            if (status == 4) {
                String sql = "SELECT * FROM League WHERE Name LIKE ? AND UserId = ?  ORDER BY CreateAt DESC OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + search + "%");
                ps.setInt(2, UserId);
                ps.setInt(3, (index - 1) * 4);
            } else {
                String sql = "SELECT * FROM League WHERE Name LIKE ? AND UserId = ? AND Status = ? ORDER BY CreateAt DESC OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + search + "%");
                ps.setInt(2, UserId);
                ps.setInt(3, status);
                ps.setInt(4, (index - 1) * 4);
            }
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
                league.setTeamMemberSize(rs.getInt("TeamMemberSize"));
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
            String sql = "SELECT * FROM League WHERE Status != 0 AND Status != 1 AND Status != 3 ORDER BY CreateAt DESC OFFSET ? ROWS FETCH NEXT 9 ROWS ONLY";
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
                System.out.println("REgistered Date" + rs.getDate("DateRegister"));
                league.setType(rs.getString("Type"));
                league.setTeamMemberSize(rs.getInt("TeamMemberSize"));
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

    public List<League> getLeaguePaged(int index, int userId) {
        List<League> listLeague = new ArrayList<>();
        try {
            String sql = "SELECT * FROM League WHERE Status != 0 AND Status != 1 AND Status != 3 AND UserId != ? ORDER BY CreateAt DESC OFFSET ? ROWS FETCH NEXT 9 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, (index - 1) * 9);

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
                System.out.println("REgistered Date" + rs.getDate("DateRegister"));
                league.setType(rs.getString("Type"));
                league.setTeamMemberSize(rs.getInt("TeamMemberSize"));
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

    public int searchLeagueByNameTotal(String search) {
        List<League> listLeague = new ArrayList<>();
        try {
            String sql = "SELECT COUNT(*) FROM League WHERE Name LIKE ? AND Status = 2 ";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<League> searchLeagueByName(int index, String search) {
        List<League> listLeague = new ArrayList<>();
        try {
            String sql = "SELECT * FROM League WHERE Name LIKE ? AND Status = 2 ORDER BY CreateAt OFFSET ? ROWS FETCH NEXT 9 ROWS ONLY";
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
                league.setTeamMemberSize(rs.getInt("TeamMemberSize"));
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

            // Taoj giai dau mac dinh la 0; 
            // Duyet la 1 
            // reject la 2.
            // cancle là 3.
            String sql = "INSERT  dbo.[League] ([Name], [Description], [TeamSize], "
                    + "[StartDate], [EndDate], [Address], [Image] ,[CreateAt], [UserId], [Type], [DateRegister], [TeamMemberSize],  [Status]) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";
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
            ps.setInt(12, league.getTeamMemberSize());

            int affectedRow = ps.executeUpdate();
            return affectedRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean cancleRequestLeague(int leagueId) {
        try {
            String sql = "UPDATE dbo.[League] SET Status = 3 WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            int affectedRow = ps.executeUpdate();
            return affectedRow > 0;
        } catch (Exception e) {
            e.printStackTrace();;
        }
        return false;
    }

    public boolean isFullTeam(int leagueId) {
        try {
            int totalTeam = 0;
            String sql = "SELECT COUNT(*) FROM dbo.[Team_League] WHERE LeagueId = ? AND Status = 1";
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

    public boolean isRegisterLeague(int teamId, int leagueId) {
        try {
            String sql = "SELECT * FROM dbo.[Team_League] WHERE TeamId = ? AND LeagueId = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, teamId);
            ps.setInt(2, leagueId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
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
            boolean isRegistered = isRegisterLeague(teamId, leagueId);
            if (isRegistered) {
                return 2;
            } else {
                String sql = "INSERT  dbo.[Team_League] (TeamId, LeagueId, RegisterAt, Point, Status) VALUES (?, ?, ?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setInt(1, teamId);
                ps.setInt(2, leagueId);
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String currentDate = dateFormat.format(date);
                ps.setString(3, currentDate);
                ps.setInt(4, 0);
                ps.setInt(5, 0);

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

    public int getLeagueRegisteredTotal(int teamId, int status) {
        List<League> listLeague = new ArrayList<>();
        try {
            String sql = "SELECT COUNT(*) FROM League le JOIN Team_League tl ON tl.LeagueId = le.Id WHERE tl.TeamId = ? AND le.Status = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, teamId);
            ps.setInt(2, status);

            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<League> getLeagueRegistered(int index, int teamId, int status) {
        List<League> listLeague = new ArrayList<>();
        try {
            if (status == 7) {
                String sql = "SELECT * FROM League le JOIN Team_League tl ON tl.LeagueId = le.Id WHERE  tl.TeamId = ? ORDER BY tl.RegisterAt DESC "
                        + "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setInt(1, teamId);
                ps.setInt(2, (index - 1) * 4);
            } else {
                String sql = "SELECT * FROM League le JOIN Team_League tl ON tl.LeagueId = le.Id WHERE  le.Status = ? AND tl.TeamId = ? ORDER BY tl.RegisterAt DESC "
                        + "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setInt(1, status);
                ps.setInt(2, teamId);
                ps.setInt(3, (index - 1) * 4);
            }
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
                league.setStatus(rs.getInt("Status"));
                league.setTeamMemberSize(rs.getInt("TeamMemberSize"));
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

    public List<League> getLeagueRegisteredSearch(int index, int teamId, int status, String search) {
        List<League> listLeague = new ArrayList<>();
        try {
            if (status == 7) {
                String sql = "SELECT * FROM League le JOIN Team_League tl ON tl.LeagueId = le.Id WHERE  tl.TeamId = ? AND le.Name LIKE ? ORDER BY tl.RegisterAt DESC "
                        + "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setInt(1, teamId);
                ps.setString(2, "%" + search + "%");

                ps.setInt(3, (index - 1) * 4);
            } else {
                String sql = "SELECT * FROM League le JOIN Team_League tl ON tl.LeagueId = le.Id WHERE  le.Status = ? AND tl.TeamId = ? AND le.Name LIKE ? ORDER BY tl.RegisterAt DESC "
                        + "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setInt(1, status);
                ps.setInt(2, teamId);
                ps.setString(3, "%" + search + "%");

                ps.setInt(4, (index - 1) * 4);
            }
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
                league.setStatus(rs.getInt("Status"));
                byte[] imgData = rs.getBytes("Image");
                league.setTeamMemberSize(rs.getInt("TeamMemberSize"));
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

    public int getTotalUserLeagueRegister(int teamId, int status) {
        try {
            if (status == 7) {
                String sql = "SELECT COUNT(*) FROM League le JOIN Team_League tl ON tl.LeagueId = le.Id WHERE  tl.TeamId = ? ";
                ps = con.prepareStatement(sql);
                ps.setInt(1, teamId);

            } else {
                String sql = "SELECT * FROM League le JOIN Team_League tl ON tl.LeagueId = le.Id WHERE  le.Status = ? AND tl.TeamId = ? ";
                ps = con.prepareStatement(sql);
                ps.setInt(1, teamId);
                ps.setInt(2, status);
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTotalUserLeagueRegisterSearch(int teamId, int status, String search) {
        try {
            if (status == 7) {
                String sql = "SELECT COUNT(*) FROM League le JOIN Team_League tl ON tl.LeagueId = le.Id WHERE  tl.TeamId = ? AND le.Name LIKE ? ";
                ps = con.prepareStatement(sql);
                ps.setInt(1, teamId);
                ps.setString(2, "%" + search + "%");

            } else {
                String sql = "SELECT * FROM League le JOIN Team_League tl ON tl.LeagueId = le.Id WHERE  le.Status = ? AND tl.TeamId = ? AND le.Name LIKE ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, teamId);
                ps.setInt(2, status);
                ps.setString(3, "%" + search + "%");

            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<LeagueRegister> getTeamRegisterLeagueConfirm(int leagueId) {
        List<LeagueRegister> listTeam = new ArrayList<>();
        try {
            String sql = "SELECT tl.Status, tl.TeamId, tl.RegisterAt, tl.Point, t.Name, t.Image FROM League le JOIN Team_League tl ON tl.LeagueId = le.Id "
                    + "JOIN Team t ON tl.TeamId = t.Id WHERE le.Id = ? AND tl.Status = 1";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            while (rs.next()) {
                LeagueRegister tl = new LeagueRegister();
                tl.setStatus(rs.getInt("Status"));
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

    public List<LeagueRegister> getTeamRegisterLeague(int leagueId) {
        List<LeagueRegister> listTeam = new ArrayList<>();
        try {
            String sql = "SELECT tl.Status, tl.TeamId, tl.RegisterAt, tl.Point, t.Name, t.Image FROM League le JOIN Team_League tl ON tl.LeagueId = le.Id "
                    + "JOIN Team t ON tl.TeamId = t.Id WHERE le.Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            while (rs.next()) {
                LeagueRegister tl = new LeagueRegister();
                tl.setStatus(rs.getInt("Status"));
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

    // Hamf nayf get ra nhung team co status = 1 => Da duoc duyet.
    public List<LeagueRegister> getTeamRegisterLeagueApprove(int leagueId) {
        List<LeagueRegister> listTeam = new ArrayList<>();
        try {
            String sql = "SELECT tl.Status, tl.TeamId, tl.RegisterAt, tl.Point, t.Name, t.Image FROM League le JOIN Team_League tl ON tl.LeagueId = le.Id "
                    + "JOIN Team t ON tl.TeamId = t.Id WHERE le.Id = ? AND tl.Status = 1";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            while (rs.next()) {
                LeagueRegister tl = new LeagueRegister();
                tl.setStatus(rs.getInt("Status"));
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

    public List<LeagueRegisterVM> getTeamLeaugeRank(int leagueId) {
        List<LeagueRegisterVM> listTeam = new ArrayList<>();
        try {
//            String sql = "SELECT tl.TeamId, tl.RegisterAt, tl.Point, t.Name, t.Image, t.ShortName, tl.Loses, tl.Wins, tl.Ties FROM League le JOIN Team_League tl ON tl.LeagueId = le.Id "
//                    + "JOIN Team t ON tl.TeamId = t.Id WHERE le.Id = ? AND tl.Status = 1 ORDER BY tl.Point DESC";
            String sql = "WITH CardPoints AS (\n"
                    + "    SELECT\n"
                    + "        TeamId,\n"
                    + "        SUM(CASE WHEN c.Type = 'yellow' THEN 1 ELSE 0 END) AS YellowCards,\n"
                    + "        SUM(CASE WHEN c.Type = 'red' THEN 3 ELSE 0 END) AS RedCards,\n"
                    + "        SUM(CASE WHEN c.Type = 'yellow' THEN 1 ELSE 0 END) + SUM(CASE WHEN c.Type = 'red' THEN 3 ELSE 0 END) AS TotalCardPoints\n"
                    + "    FROM\n"
                    + "      Card  c JOIN Match m ON m.Id = c.MatchId\n"
                    + "		JOIN League l ON m.LeagueId = l.Id WHERE l.Id = ? "
                    + "    GROUP BY\n"
                    + "        TeamId\n"
                    + ")\n"
                    + "\n"
                    + "SELECT \n"
                    + "    tl.TeamId, \n"
                    + "    tl.RegisterAt, \n"
                    + "    tl.Point, \n"
                    + "    t.Name, \n"
                    + "    t.Image, \n"
                    + "    t.ShortName, \n"
                    + "    tl.Loses, \n"
                    + "    tl.Wins, \n"
                    + "    tl.Ties,\n"
                    + "    cp.TotalCardPoints\n"
                    + "FROM \n"
                    + "    League le \n"
                    + "    JOIN Team_League tl ON tl.LeagueId = le.Id\n"
                    + "    JOIN Team t ON tl.TeamId = t.Id\n"
                    + "    LEFT JOIN CardPoints cp ON tl.TeamId = cp.TeamId\n"
                    + "WHERE \n"
                    + "    le.Id = ? \n"
                    + "    AND tl.Status = 1 \n"
                    + "ORDER BY \n"
                    + "    tl.Point DESC, \n"
                    + "    cp.TotalCardPoints ASC;";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            ps.setInt(2, leagueId);

            rs = ps.executeQuery();
            while (rs.next()) {
                LeagueRegisterVM tl = new LeagueRegisterVM();
                tl.setTeamId(rs.getInt("TeamId"));
                tl.setTeamName(rs.getString("Name"));
                tl.setRegisterAt(rs.getDate("RegisterAt").toString());
                tl.setPoint(rs.getInt("Point"));
                tl.setShortName(rs.getString("ShortName"));
                tl.setWins(rs.getInt("Wins"));
                tl.setLoses(rs.getInt("Loses"));
                tl.setTies(rs.getInt("Ties"));
                tl.setTotalCardPoint(rs.getInt("TotalCardPoints"));

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

    public List<LeagueRegisterVM> getTeamLeaugeRankInGroupId(int leagueId, int groupdId) {
        List<LeagueRegisterVM> listTeam = new ArrayList<>();
        try {
            String sql = "	WITH CardPoints AS (\n"
                    + "    SELECT\n"
                    + "        TeamId,\n"
                    + "        SUM(CASE WHEN c.Type = 'yellow' THEN 1 ELSE 0 END) AS YellowCards,\n"
                    + "        SUM(CASE WHEN c.Type = 'red' THEN 3 ELSE 0 END) AS RedCards,\n"
                    + "        SUM(CASE WHEN c.Type = 'yellow' THEN 1 ELSE 0 END) + SUM(CASE WHEN c.Type = 'red' THEN 3 ELSE 0 END) AS TotalCardPoints\n"
                    + "    FROM\n"
                    + "      Card  c JOIN Match m ON m.Id = c.MatchId\n"
                    + "		JOIN League l ON m.LeagueId = l.Id WHERE l.Id = ? "
                    + "    GROUP BY\n"
                    + "        TeamId\n"
                    + ")\n"
                    + "\n"
                    + "SELECT \n"
                    + "    tl.TeamId, \n"
                    + "    tl.RegisterAt, \n"
                    + "    tl.Point, \n"
                    + "    t.Name, \n"
                    + "    t.Image, \n"
                    + "    t.ShortName, \n"
                    + "    tl.Loses, \n"
                    + "    tl.Wins, \n"
                    + "    tl.Ties,\n"
                    + "    cp.TotalCardPoints\n"
                    + "FROM \n"
                    + "    League le \n"
                    + "    JOIN Team_League tl ON tl.LeagueId = le.Id\n"
                    + "    JOIN Team t ON tl.TeamId = t.Id\n"
                    + "    LEFT JOIN CardPoints cp ON tl.TeamId = cp.TeamId\n"
                    + "WHERE \n"
                    + "    le.Id = ?\n"
                    + "    AND tl.Status = 1\n"
                    + "	AND tl.GroupId = ?\n"
                    + "ORDER BY \n"
                    + "    tl.Point DESC, \n"
                    + "    cp.TotalCardPoints ASC;";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            ps.setInt(2, leagueId);

            ps.setInt(3, groupdId);

            rs = ps.executeQuery();
            while (rs.next()) {
                LeagueRegisterVM tl = new LeagueRegisterVM();
                tl.setTeamId(rs.getInt("TeamId"));
                tl.setTeamName(rs.getString("Name"));
                tl.setRegisterAt(rs.getDate("RegisterAt").toString());
                tl.setPoint(rs.getInt("Point"));
                tl.setShortName(rs.getString("ShortName"));
                tl.setWins(rs.getInt("Wins"));
                tl.setLoses(rs.getInt("Loses"));
                tl.setTies(rs.getInt("Ties"));
                tl.setTotalCardPoint(rs.getInt("TotalCardPoints"));

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
                league.setTeamMemberSize(rs.getInt("TeamMemberSize"));
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

    private LocalDateTime adjustDateTime(LocalDateTime dateTime, int startHour, int startMin) {

        if (dateTime.toLocalTime().isBefore(LocalTime.of(startHour, startMin))) {
            // neues thoi gian truoc thoi gian bat dau thi se chuyen thanh thoi gian bat dau
            return dateTime.withHour(startHour).withMinute(startMin);
        } else if (dateTime.toLocalTime().isAfter(LocalTime.of(20, 0))) {
            // Nếu sau 8 PM, điều chỉnh thành ngày bắt đầu của ngày sau
            return dateTime.plusDays(1).withHour(startHour).withMinute(startMin);
            //
        }
//        } else if (dateTime.toLocalTime().isAfter(LocalTime.of(11, 0)) && dateTime.toLocalTime().isBefore(LocalTime.of(13, 0))) {
//            // neu nam trong khoang tu 11 h toi 13h thi chueyn 13h
//            return dateTime.withHour(13).withMinute(0);
//        }
        return dateTime.plusHours(2);
    }

    public List<MatchVM> generateMatch(int leagueId) {
        try {
            boolean _isFullTeam = isFullTeam(leagueId);
            if (!_isFullTeam) {
                return null;
            }
            League league = getLeagueById(leagueId);
            List<MatchVM> listmatchVM = new ArrayList<>();
            String sql = "UPDATE League SET Status = ? WHERE Id = ?";
            ps = con.prepareStatement(sql);
            // 4 la bat dau
            ps.setInt(1, 4);
            ps.setInt(2, leagueId);

            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {

                List<LeagueRegister> listLeagueRegister = getTeamRegisterLeagueConfirm(leagueId);
                int registerTeamSize = listLeagueRegister.size();
                if (registerTeamSize == 0) {
                    return null;
                }
                LocalDateTime startDate = league.getStartDate();
                LocalDateTime endDate = league.getEndDate();

                // lấy giờ bắt đầu của giải đấu
                int startHour = startDate.getHour();
                int startMin = startDate.getMinute();

                List<LeagueRegister[]> matchups = new ArrayList<>();
                // Create match 
                for (int i = 0; i < registerTeamSize; i++) {
                    for (int j = i + 1; j < registerTeamSize; j++) {
                        matchups.add(new LeagueRegister[]{listLeagueRegister.get(i), listLeagueRegister.get(j)});
                    }
                }
                Collections.shuffle(matchups);
                long totalDays = ChronoUnit.DAYS.between(startDate.toLocalDate(), endDate.toLocalDate()) + 1;
                int matchesPerDay = (int) Math.max(1, Math.min(4, Math.ceil((double) matchups.size() / totalDays)));
                int matchIndex = 1;

                LocalDateTime startTimeMatch = null;
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

                    if (matchIndex > matchesPerDay) {
                        matchIndex = 1;
                        startDate = startDate.plusDays(1).withHour(startHour).withMinute(startMin);
                    }
                    if (endDate.toLocalTime().isBefore(LocalTime.of(startHour, startMin))) {
                        // neues thoi gian truoc thoi gian bat dau thi se chuyen thanh thoi gian bat dau
                        startDate.withHour(startHour).withMinute(startMin);
                    } else if (endDate.toLocalTime().isAfter(LocalTime.of(endDate.getHour(), endDate.getMinute()))) {
                        // Nếu sau 8 PM, điều chỉnh thành ngày bắt đầu của ngày sau
                        startDate.plusDays(1).withHour(startHour).withMinute(startMin);
                    }

                    endDate = startDate.plusHours(2);

                    // truong hop chung 1 khoan
                    int startHourMatch = startDate.getHour();
                    int endHourMatch = endDate.getHour();
                    System.out.println("before " + matchIndex + "Start: " + startDate.getHour());
                    System.out.println("before " + matchIndex + "End: " + endHourMatch);

                    ps.setTimestamp(7, java.sql.Timestamp.valueOf(startDate));
                    endDate = startDate.plusHours(2);
                    ps.setTimestamp(8, java.sql.Timestamp.valueOf(endDate));

                    if ((startHourMatch >= 11 && endHourMatch <= 13)
                            || (endHourMatch >= 11 && endHourMatch <= 13)) {
                        startTimeMatch = startDate.withHour(13).withMinute(0);
                        endDate = startTimeMatch.plusHours(2);
                        ps.setTimestamp(7, java.sql.Timestamp.valueOf(startTimeMatch));
                        ps.setTimestamp(8, java.sql.Timestamp.valueOf(endDate));
                    }

                    // gan xong gan lai
                    startDate = endDate;

                    System.out.println("Sau khi add xong" + startDate);

                    int afftedRow = ps.executeUpdate();
                    MatchVM matchVM = new MatchVM();
                    if (afftedRow > 0) {
                        sql = "SELECT t.Id, t.Image, t.Name, t.ShortName, m.Id as mId, m.StartAt, m.EndAt FROM dbo.[Match] m JOIN dbo.Team t ON m.HomeTeamId = t.Id WHERE LeagueId = ?";
                        ps = con.prepareStatement(sql);
                        ps.setInt(1, leagueId);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            matchVM.setAddress(league.getAddress());
                            matchVM.setLeagueId(league.getId());
                            matchVM.setId(rs.getInt("mId"));
                            matchVM.setHomeTeamId(matchup[0].getTeamId());
                            matchVM.setStartAt(rs.getString("StartAt"));
                            matchVM.setEndAt(rs.getString("EndAt"));
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

    public List<MatchVM> getMatchByLeagueType1(int leagueId) {
        try {
            League league = getLeagueById(leagueId);
            List<MatchVM> listmatchVM = new ArrayList<MatchVM>();
            String sql = "SELECT t.Id, t.Image, t.Name, t.ShortName, m.HomeTeamId, m.Id as mId, m.Status, m.ScoreHome, m.ScoreAway, "
                    + "m.StartAt, m.EndAt "
                    + " FROM dbo.[Match] m JOIN dbo.Team t ON m.HomeTeamId = t.Id WHERE LeagueId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            while (rs.next()) {
                MatchVM matchVM = new MatchVM();
                matchVM.setAddress(league.getAddress());
                matchVM.setScoreHome(rs.getInt("ScoreHome"));
                matchVM.setScoreAway(rs.getInt("ScoreAway"));

                matchVM.setStartAt(rs.getString("StartAt"));
                matchVM.setEndAt(rs.getString("EndAt"));

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

    public List<MatchVM> getMatchByLeagueType2(int leagueId, int groupdId) {
        try {
            League league = getLeagueById(leagueId);
            List<MatchVM> listmatchVM = new ArrayList<MatchVM>();
            String sql = "SELECT t.Id, t.Image, t.Name, t.ShortName, m.HomeTeamId, m.Id as mId, m.Status, m.ScoreHome, m.ScoreAway, "
                    + "m.StartAt, m.EndAt "
                    + " FROM dbo.[Match] m JOIN dbo.Team t ON m.HomeTeamId = t.Id WHERE LeagueId = ? AND m.GroupId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            ps.setInt(2, groupdId);

            rs = ps.executeQuery();
            while (rs.next()) {
                MatchVM matchVM = new MatchVM();
                matchVM.setAddress(league.getAddress());
                matchVM.setScoreHome(rs.getInt("ScoreHome"));
                matchVM.setScoreAway(rs.getInt("ScoreAway"));

                matchVM.setStartAt(rs.getString("StartAt"));
                matchVM.setEndAt(rs.getString("EndAt"));

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
            sql = "SELECT t.Id, t.Image, t.Name, t.ShortName, m.AwayTeamId"
                    + " FROM dbo.[Match] m JOIN dbo.Team t ON m.AwayTeamId = t.Id WHERE m.LeagueId = ? AND m.GroupId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            ps.setInt(2, groupdId);

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

    public List<MatchVM> getMatchByLeagueKnowoutStage(int leagueId, int groupdId) {
        try {
            League league = getLeagueById(leagueId);
            List<MatchVM> listmatchVM = new ArrayList<MatchVM>();
            String sql = "SELECT t.Id, t.Image, t.Name, t.ShortName, m.HomeTeamId, m.Id as mId, m.Status, m.ScoreHome, m.ScoreAway, "
                    + "m.StartAt, m.EndAt "
                    + " FROM dbo.[Match] m JOIN dbo.Team t ON m.HomeTeamId = t.Id WHERE LeagueId = ? AND m.Knockout = 1";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            while (rs.next()) {
                MatchVM matchVM = new MatchVM();
                matchVM.setAddress(league.getAddress());
                matchVM.setScoreHome(rs.getInt("ScoreHome"));
                matchVM.setScoreAway(rs.getInt("ScoreAway"));

                matchVM.setStartAt(rs.getString("StartAt"));
                matchVM.setEndAt(rs.getString("EndAt"));

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
            sql = "SELECT t.Id, t.Image, t.Name, t.ShortName, m.AwayTeamId"
                    + " FROM dbo.[Match] m JOIN dbo.Team t ON m.AwayTeamId = t.Id WHERE m.LeagueId = ? AND m.Knockout = 1";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            int matchIndex = 0;
            if (listmatchVM.size() > 0) {
                while (rs.next()) {
                    System.out.println("matchIndex" + matchIndex);
                    listmatchVM.get(matchIndex).setAwayTeamId(rs.getInt("AwayTeamId"));
                    listmatchVM.get(matchIndex).setAwayteamName(rs.getString("Name"));
                    listmatchVM.get(matchIndex).setAwayteamShortName(rs.getString("ShortName"));
                    byte[] imgData = rs.getBytes("Image");
                    String base64Image = Base64.getEncoder().encodeToString(imgData);
                    listmatchVM.get(matchIndex).setAwayteamImage(base64Image);
                    matchIndex++;
                }
            }
            return listmatchVM;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MatchVM> getFinalMatchByLeagueKnowoutStage(int leagueId) {
        try {
            League league = getLeagueById(leagueId);
            List<MatchVM> listmatchVM = new ArrayList<MatchVM>();
            String sql = "SELECT t.Id, t.Image, t.Name, t.ShortName, m.HomeTeamId, m.Id as mId, m.Status, m.ScoreHome, m.ScoreAway, "
                    + "m.StartAt, m.EndAt "
                    + " FROM dbo.[Match] m JOIN dbo.Team t ON m.HomeTeamId = t.Id WHERE LeagueId = ? AND m.Knockout = 1 ORDER BY m.Id DESC";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            if (rs.next()) {
                MatchVM matchVM = new MatchVM();
                matchVM.setAddress(league.getAddress());
                matchVM.setScoreHome(rs.getInt("ScoreHome"));
                matchVM.setScoreAway(rs.getInt("ScoreAway"));

                matchVM.setStartAt(rs.getString("StartAt"));
                matchVM.setEndAt(rs.getString("EndAt"));

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
            sql = "SELECT t.Id, t.Image, t.Name, t.ShortName, m.AwayTeamId"
                    + " FROM dbo.[Match] m JOIN dbo.Team t ON m.AwayTeamId = t.Id WHERE m.LeagueId = ? AND m.Knockout = 1 ORDER BY m.Id DESC";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            int matchIndex = 0;
            if (listmatchVM.size() > 0) {
                if (rs.next()) {
                    listmatchVM.get(0).setAwayTeamId(rs.getInt("AwayTeamId"));
                    listmatchVM.get(0).setAwayteamName(rs.getString("Name"));
                    listmatchVM.get(0).setAwayteamShortName(rs.getString("ShortName"));
                    byte[] imgData = rs.getBytes("Image");
                    String base64Image = Base64.getEncoder().encodeToString(imgData);
                    listmatchVM.get(0).setAwayteamImage(base64Image);
                }
            }
            return listmatchVM;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MatchVM> getFinalMatchByLeagueId(int leagueId) {
        try {
            League league = getLeagueById(leagueId);
            List<MatchVM> listmatchVM = new ArrayList<MatchVM>();
            String sql = "SELECT t.Id, t.Image, t.Name, t.ShortName, m.HomeTeamId, m.Id as mId, m.Status, m.ScoreHome, m.ScoreAway, "
                    + "m.StartAt, m.EndAt "
                    + " FROM dbo.[Match] m JOIN dbo.Team t ON m.HomeTeamId = t.Id WHERE LeagueId = ? AND m.Knockout = 2";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            if (rs.next()) {
                MatchVM matchVM = new MatchVM();
                matchVM.setAddress(league.getAddress());
                matchVM.setScoreHome(rs.getInt("ScoreHome"));
                matchVM.setScoreAway(rs.getInt("ScoreAway"));

                matchVM.setStartAt(rs.getString("StartAt"));
                matchVM.setEndAt(rs.getString("EndAt"));

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
            sql = "SELECT t.Id, t.Image, t.Name, t.ShortName, m.AwayTeamId"
                    + " FROM dbo.[Match] m JOIN dbo.Team t ON m.AwayTeamId = t.Id WHERE m.LeagueId = ? AND m.Knockout = 2";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            int matchIndex = 0;
            if (listmatchVM.size() > 0) {
                if (rs.next()) {
                    listmatchVM.get(0).setAwayTeamId(rs.getInt("AwayTeamId"));
                    listmatchVM.get(0).setAwayteamName(rs.getString("Name"));
                    listmatchVM.get(0).setAwayteamShortName(rs.getString("ShortName"));
                    byte[] imgData = rs.getBytes("Image");
                    String base64Image = Base64.getEncoder().encodeToString(imgData);
                    listmatchVM.get(0).setAwayteamImage(base64Image);
                }
            }
            return listmatchVM;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public MatchVM getMatchByMatchId(int matchId) {
        try {
            String sql = "SELECT m.Id, t.Id as tId, t.Image, t.Name, t.ShortName, m.HomeTeamId, m.Status, m.LeagueId, m.ScoreHome, m.ScoreAway, m.StartAt, m.EndAt "
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
                matchVM.setStartAt(rs.getString("StartAt"));
                matchVM.setEndAt(rs.getString("EndAt"));
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
                    sql = "UPDATE Team_League SET Point += 3, Wins += 1 WHERE TeamId = ? AND LeagueId = ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, homeId);
                    ps.setInt(2, leagueId);
                    afftedRow = ps.executeUpdate();

                    sql = "UPDATE Team_League SET Loses += 1 WHERE TeamId = ? AND LeagueId = ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, awayId);
                    ps.setInt(2, leagueId);
                    afftedRow = ps.executeUpdate();

                } else if (scoreA == scoreB) {
                    sql = "UPDATE Team_League SET Point += 1, Ties += 1 WHERE TeamId = ? AND LeagueId = ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, awayId);
                    ps.setInt(2, leagueId);
                    afftedRow = ps.executeUpdate();

                    sql = "UPDATE Team_League SET Point += 1, Ties += 1 WHERE TeamId = ? AND LeagueId = ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, homeId);
                    ps.setInt(2, leagueId);
                    afftedRow = ps.executeUpdate();
                } else if (scoreA < scoreB) {
                    sql = "UPDATE Team_League SET Point += 3, Wins += 1 WHERE TeamId = ? AND LeagueId = ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, awayId);
                    ps.setInt(2, leagueId);
                    afftedRow = ps.executeUpdate();

                    sql = "UPDATE Team_League SET Loses += 1 WHERE TeamId = ? AND LeagueId = ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, homeId);
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
            String sql = "UPDATE League Set Status = 5 WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            int afftedRow = ps.executeUpdate();
            return afftedRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isFinishAllMatch(int leagueId) {
        try {
            String sql = "SELECT * FROM Match WHERE LeagueId = ? AND Status = 0";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    
        public boolean isFinishAllMatchInGroup(int leagueId) {
        try {
            String sql = "SELECT * FROM Match WHERE LeagueId = ? AND ScoreHome IS NULL";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean startAtLeastOneMatch(int leagueId) {
        try {
            String sql = "SELECT * FROM Match WHERE LeagueId = ? AND Status = 1";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // check isFull roi moi cho accept
    public boolean acceptToJoinLeague(int teamId, int leagueId) {
        try {
            String sql = "UPDATE [Team_League] Set Status = 1 WHERE TeamId = ? AND LeagueId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, teamId);
            ps.setInt(2, leagueId);
            int afftedRow = ps.executeUpdate();
            if (afftedRow > 0) {
                TeamDAO teamDAO = new TeamDAO();
                int userId = teamDAO.getUserIdByTeamID(teamId);
                League league = getLeagueById(leagueId);
                NotificationDAO notiDAO = new NotificationDAO();
                String title = "YÊU CẦU THAM GIA GIẢI ĐẤU";
                String contentNoti = "Yêu cầu tham gia giải đấu [" + league.getName() + "] của bạn đã được phê duyệt.";
                notiDAO.createNotification(userId, title, contentNoti);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Comment
    public boolean feedbackLeague(int leagueId, String Content, int UserId) {
        try {
            String sql = "INSERT INTO [Feedback] (LeagueId, Content, UserId, CreateAt)"
                    + " VALUES (?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            ps.setString(2, Content);
            ps.setInt(3, UserId);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String currentDate = dateFormat.format(date);
            ps.setString(4, currentDate);
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // get lisst comment
    public int getListUserFeedbackInLeagueTotal(int postId) {
        try {
            String sql = "SELECT COUNT(*) FROM [Feedback] WHERE LeagueId = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, postId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // get lisst comment
    public List<CommentViewModel> getListUserFeedbackInLeague(int leaugeId, int index) {
        try {
            int userId = 0;
            List<CommentViewModel> listComment = new ArrayList<>();
            String sql = "SELECT * FROM [Feedback] WHERE LeagueId = ? ORDER BY Id DESC OFFSET ? ROW FETCH NEXT 12 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leaugeId);
            ps.setInt(2, (index - 1) * 12);
            rs = ps.executeQuery();
            while (rs.next()) {
                CommentViewModel commentVM = new CommentViewModel();
                commentVM.setId(rs.getInt("Id"));
                commentVM.setPostId(rs.getInt("LeagueId"));
                commentVM.setContent(rs.getString("Content"));
                commentVM.setUserId(rs.getInt("UserId"));
                commentVM.setCreateAt(rs.getString("CreateAt"));
                // luu cai userId cua nguoi comment
                userId = rs.getInt("UserId");
                sql = "SELECT * FROM [User] WHERE Id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                ResultSet rs1 = ps.executeQuery();
                while (rs1.next()) {
                    String firstName = rs1.getString("FirstName");
                    String lastName = rs1.getString("LastName");
                    String fullName = firstName + " " + lastName;
                    commentVM.setUserName(fullName);
                    String base64Image = null;
                    byte[] imgData = rs1.getBytes("Image");
                    if (imgData != null) {
                        base64Image = Base64.getEncoder().encodeToString(imgData);
                    }
                    commentVM.setAvatar(base64Image);
                }
                listComment.add(commentVM);
            }
            return listComment;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // get for confirm
    public int getLeaguePendingStatusTotal() {
        try {
            String sql = "SELECT COUNT(*) FROM League WHERE Status = 0 ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<League> getLeaguePendingStatus(int index) {
        List<League> listLeauge = new ArrayList();
        try {
            String sql = "SELECT * FROM League WHERE Status = 0  ORDER BY Id DESC OFFSET ? ROW FETCH NEXT 8 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 8);
            rs = ps.executeQuery();
            while (rs.next()) {
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
                listLeauge.add(league);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listLeauge;
    }

    public int searchLeaguePendingStatusTotalByName(String search) {
        try {
            String sql = "SELECT COUNT(*) FROM League WHERE Status = 0  AND Name LIKE ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<League> searhLeaguePendingStatusByName(String search, int index) {
        List<League> listLeauge = new ArrayList();
        try {
            String sql = "SELECT * FROM League WHERE Status = 0 AND Name LIKE ? ORDER BY Id DESC OFFSET ? ROW FETCH NEXT 8 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ps.setInt(2, (index - 1) * 8);

            rs = ps.executeQuery();
            while (rs.next()) {
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
                listLeauge.add(league);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listLeauge;
    }

    public int getUserIdByLeagueId(int leagueId) {
        try {
            String sql = "SELECT * FROM League WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("UserId");
            };

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean confirmLeagueById(int leagueId) {
        try {
            String sql = "UPDATE League SET Status = 2 WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                // update user wallet
                int userId = getUserIdByLeagueId(leagueId);
                League league = getLeagueById(leagueId);
                UserWalletDAO userWalletDAO = new UserWalletDAO();
                UserWallet userWallet = userWalletDAO.getUserWalletById(userId);
                float ammount = userWallet.getAmmount() * 1000;
                System.out.println("");
                ammount = ammount - 10000;
                sql = "UPDATE UserWallet SET Ammount = ? WHERE Id = ?";
                ps = con.prepareStatement(sql);
                ps.setFloat(1, ammount);
                ps.setInt(2, userWallet.getWalletId());
                affectedRow = ps.executeUpdate();
                String content = "Tạo giải đấu " + league.getName() + " với giá 10 điểm";
                boolean result = userWalletDAO.addTransitionHistory(content, userWallet.getWalletId());
                if (result) {
                    // Taoj noti
                    int _userId = getUserIdByLeagueId(leagueId);
                    NotificationDAO notiDAO = new NotificationDAO();
                    String title = "QUẢN LÍ GIẢI ĐẤU";
                    String contentNoti = "Giải đấu của bạn đã được tạo thành công!";
                    notiDAO.createNotification(_userId, title, contentNoti);

                    return true;
                }
            };

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean rejectLeagueById(int leagueId) {
        try {
            String sql = "UPDATE League SET Status = 1 WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0);
            {
                int _userId = getUserIdByLeagueId(leagueId);
                NotificationDAO notiDAO = new NotificationDAO();
                League league = getLeagueById(leagueId);
                String title = "QUẢN LÍ GIẢI ĐẤU";
                String contentNoti = "Yêu cầu tạo giải đấu của bạn đã bị từ chối , vui lòng kiểm tra lại!";
                notiDAO.createNotification(_userId, title, contentNoti);
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getActiveLeasguesCount() {
        try {
            String sql = "SELECT COUNT(*) FROM League WHERE Status = 2";
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
    // Add the following method to the LeagueDAO class

    public List<BlogLeagueVM> getActiveBlogsByLeagueId(int leagueId) {
        BlogLeagueDAO blogLeagueDAO = new BlogLeagueDAO();
        return blogLeagueDAO.getListBlogByLeagueId(leagueId);
    }

    public boolean hasTeamRegisteredOtherLeague(int teamId, LocalDateTime startDate, LocalDateTime endDate) {
        try {
            String sql = "SELECT COUNT(*) FROM Team_League tl "
                    + "JOIN League l ON tl.LeagueId = l.Id "
                    + "WHERE tl.TeamId = ? "
                    + "AND (? BETWEEN l.StartDate AND l.EndDate OR ? BETWEEN l.StartDate AND l.EndDate)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, teamId);
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(startDate));
            ps.setTimestamp(3, java.sql.Timestamp.valueOf(endDate));
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Goal> getGoalsByLeagueId(int leagueId) {
        List<Goal> goals = new ArrayList<>();
        try {
            String sql = "SELECT g.Goal_Time, g.Team_MemberId, tm.Name AS PlayerName, t.Name AS TeamName "
                    + "FROM Goal g " + "JOIN Team_Member tm ON g.Team_MemberId = tm.Id "
                    + "JOIN Team t ON g.TeamId = t.Id " + "JOIN Match m ON g.MatchId = m.Id "
                    + "WHERE m.LeagueId = ? " + "ORDER BY g.Goal_Time DESC";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Goal goal = new Goal();
                goal.setGoal_Time(rs.getInt("Goal_Time"));
                goal.setTeamMemberId(rs.getInt("Team_MemberId"));
                goal.setPlayerName(rs.getString("PlayerName"));
                goal.setTeamName(rs.getString("TeamName"));
                goals.add(goal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goals;
    }

    public GoalVM getTopGoalScorersByLeagueId(int leagueId) {
        List<GoalVM> topGoalScorers = new ArrayList<>();
        try {
            String sql = "SELECT tm.Name AS PlayerName, COUNT(*) AS TotalGoals, t.Name AS TeamName "
                    + "FROM Goal g "
                    + "JOIN Team_Member tm ON g.Team_MemberId = tm.Id "
                    + "JOIN Team t ON g.TeamId = t.Id "
                    + "WHERE g.MatchId IN (SELECT Id FROM Match WHERE LeagueId = ?) "
                    + "GROUP BY tm.Name, t.Name "
                    + "ORDER BY TotalGoals DESC";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            if (rs.next()) {
                GoalVM goalVM = new GoalVM();
                goalVM.setPlayerName(rs.getString("PlayerName"));
                goalVM.setTotalGoals(rs.getInt("TotalGoals"));
                goalVM.setTeamName(rs.getString("TeamName"));
                return goalVM;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Card> getCardsByLeagueId(int leagueId) {
        List<Card> cards = new ArrayList<>();
        try {
            String sql = "SELECT c.Card_Time, c.Type, c.Team_MemberId, tm.Name AS PlayerName, t.Name AS TeamName " + "FROM Card c "
                    + "JOIN Team_Member tm ON c.Team_MemberId = tm.Id " + "JOIN Team t ON c.TeamId = t.Id "
                    + "JOIN Match m ON c.MatchId = m.Id " + "WHERE m.LeagueId = ? "
                    + "ORDER BY c.Card_Time DESC";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Card card = new Card();
                card.setCard_Time(rs.getInt("Card_Time"));
                card.setType(rs.getString("Type"));
                card.setTeamMemberId(rs.getInt("Team_MemberId"));
                card.setPlayerName(rs.getString("PlayerName"));
                card.setTeamName(rs.getString("TeamName"));
                cards.add(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cards;
    }

    public LeagueRegisterVM getTeamLeagueRankByGoals(int leagueId) {
        List<LeagueRegisterVM> listTeam = new ArrayList<>();
        try {
            String sql = "SELECT tl.TeamId, tl.RegisterAt, tl.Point, tl.Wins, tl.Loses, tl.Ties, t.Name, t.Image, t.ShortName, "
                    + "(SELECT COUNT(*) FROM Goal g WHERE g.TeamId = tl.TeamId AND g.MatchId IN (SELECT Id FROM Match WHERE LeagueId = ?)) AS TotalGoals "
                    + "FROM League le JOIN Team_League tl ON tl.LeagueId = le.Id " + "JOIN Team t ON tl.TeamId = t.Id WHERE le.Id = ? AND tl.Status = 1 "
                    + "ORDER BY TotalGoals DESC, tl.Point DESC, tl.Loses ASC, tl.Wins DESC";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            ps.setInt(2, leagueId);
            rs = ps.executeQuery();
            if (rs.next()) {
                LeagueRegisterVM tl = new LeagueRegisterVM();
                tl.setTeamId(rs.getInt("TeamId"));
                tl.setTeamName(rs.getString("Name"));
                tl.setShortName(rs.getString("ShortName"));
                tl.setRegisterAt(rs.getDate("RegisterAt").toString());
                tl.setPoint(rs.getInt("Point"));
                tl.setWins(rs.getInt("Wins"));
                tl.setLoses(rs.getInt("Loses"));
                tl.setTies(rs.getInt("Ties"));
                tl.setTotalGoals(rs.getInt("TotalGoals"));
                byte[] imgData = rs.getBytes("Image");
                String base64Image = Base64.getEncoder().encodeToString(imgData);
                tl.setImage(null);
                return tl;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public LeagueRegisterVM getTeamLeagueRankByFairPlay(int leagueId) {
        List<LeagueRegisterVM> listTeam = new ArrayList<>();
        try {
            String sql = "SELECT tl.TeamId, tl.RegisterAt, tl.Point, tl.Wins, tl.Loses, tl.Ties, t.Name, t.Image, t.ShortName, "
                    + "(SELECT COUNT(*) FROM Card c WHERE c.TeamId = tl.TeamId AND c.MatchId IN (SELECT Id FROM Match WHERE LeagueId = ?)) AS TotalCards "
                    + "FROM League le JOIN Team_League tl ON tl.LeagueId = le.Id " + "JOIN Team t ON tl.TeamId = t.Id WHERE le.Id = ? AND tl.Status = 1 "
                    + "ORDER BY TotalCards ASC, tl.Point DESC, tl.Loses ASC, tl.Wins DESC";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            ps.setInt(2, leagueId);
            rs = ps.executeQuery();
            if (rs.next()) {
                LeagueRegisterVM tl = new LeagueRegisterVM();
                tl.setTeamId(rs.getInt("TeamId"));
                tl.setTeamName(rs.getString("Name"));
                tl.setShortName(rs.getString("ShortName"));
                tl.setRegisterAt(rs.getDate("RegisterAt").toString());
                tl.setPoint(rs.getInt("Point"));
                tl.setWins(rs.getInt("Wins"));
                tl.setLoses(rs.getInt("Loses"));
                tl.setTies(rs.getInt("Ties"));
                tl.setTotalCards(rs.getInt("TotalCards"));
                byte[] imgData = rs.getBytes("Image");
                String base64Image = Base64.getEncoder().encodeToString(imgData);
                tl.setImage(null);
                return tl;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LeagueRegisterVM> getTeamLeagueRankByTotalGoals(int leagueId) {
        List<LeagueRegisterVM> listTeam = new ArrayList<>();
        try {
            String sql = "SELECT tl.TeamId, tl.RegisterAt, tl.Point, tl.Wins, tl.Loses, tl.Ties, t.Name, t.Image, t.ShortName, " + "(SELECT COUNT(*) FROM Goal g WHERE g.TeamId = tl.TeamId AND g.MatchId IN (SELECT Id FROM Match WHERE LeagueId = ?)) AS TotalGoals " + "FROM League le JOIN Team_League tl ON tl.LeagueId = le.Id " + "JOIN Team t ON tl.TeamId = t.Id WHERE le.Id = ? AND tl.Status = 1 " + "ORDER BY TotalGoals DESC, tl.Point DESC, tl.Loses ASC, tl.Wins DESC";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            ps.setInt(2, leagueId);
            rs = ps.executeQuery();
            while (rs.next()) {
                LeagueRegisterVM tl = new LeagueRegisterVM();
                tl.setTeamId(rs.getInt("TeamId"));
                tl.setTeamName(rs.getString("Name"));
                tl.setShortName(rs.getString("ShortName"));
                tl.setRegisterAt(rs.getDate("RegisterAt").toString());
                tl.setPoint(rs.getInt("Point"));
                tl.setWins(rs.getInt("Wins"));
                tl.setLoses(rs.getInt("Loses"));
                tl.setTies(rs.getInt("Ties"));
                tl.setTotalGoals(rs.getInt("TotalGoals"));
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

        public List<MatchVM> generateMatchWithGroup(int leagueId) {
        try {
            boolean _isFullTeam = isFullTeam(leagueId);
            if (!_isFullTeam) {
                return null;
            }
            League league = getLeagueById(leagueId);
            List<MatchVM> listmatchVM = new ArrayList<>();
            String sql = "UPDATE League SET Status = ? WHERE Id = ?";
            ps = con.prepareStatement(sql);
            // 4 la bat dau       
            ps.setInt(1, 4);
            ps.setInt(2, leagueId);
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                List<LeagueRegister> listLeagueRegister = getTeamRegisterLeagueApprove(leagueId);
                int registerTeamSize = listLeagueRegister.size();
                if (registerTeamSize == 0) {
                    return null;
                }

                // chia ra bảng = số đội chia 2.
                int groupTotal = (int) Math.ceil((double) listLeagueRegister.size() / 4);

                List<LeagueRegister[]> matchups = new ArrayList<>();
                List<Group> groups = new ArrayList<>();

                // đảo danh sách các đội tham gia
                Collections.shuffle(listLeagueRegister);
                // Create groups      
                for (int i = 0; i < groupTotal; i++) {
                    Group group = new Group();
                    group.setName("Group " + (char) ('A' + i));
                    group.setLeagueId(leagueId);
                    group.setDescription("Group description");
                    GroupDAO groupDAO = new GroupDAO();
                    group = groupDAO.CreateGroupd(leagueId, group.getName());
                    groups.add(group);
                }

                int groupIndex = 0;
                // gán group
                for (int i = 0; i < registerTeamSize; i++) {
                    int groupId = groups.get(groupIndex).getId();
                    int teamId = listLeagueRegister.get(i).getTeamId();
                    boolean result = updateTeamLeagueGroupId(teamId, leagueId, groupId);
                    if (result) {
                        listLeagueRegister.get(i).setGroupId(groupId);
                        groupIndex++;
                        if (groupIndex > groupTotal - 1) {
                            groupIndex = 0;
                        }
                    } else {
                        System.out.println("false");
                    }
                }

                // gán từng groupId cho các đội
                for (int i = 0; i < registerTeamSize; i++) {
                    for (int j = i + 1; j < registerTeamSize; j++) {
                        // cùng groupId
                        var teamAGroupId = listLeagueRegister.get(i).getGroupId();
                        var teamBGroupId = listLeagueRegister.get(j).getGroupId();

                        // tạo trận đáu theo groupId
                        if (teamAGroupId == teamBGroupId) {
                            LeagueRegister teamA = listLeagueRegister.get(i);
                            LeagueRegister teamB = listLeagueRegister.get(j);
                            teamA.setGroupId(teamAGroupId);
                            teamB.setGroupId(teamBGroupId);
                            // tạo trận
                            matchups.add(new LeagueRegister[]{teamA, teamB});
                        }

                    }
                }
                LocalDateTime startDate = league.getStartDate();
                LocalDateTime endDate = league.getEndDate();

                // lấy giờ bắt đầu của giải đấu      
                int startHour = startDate.getHour();
                int startMin = startDate.getMinute();

                Collections.shuffle(matchups);
                long totalDays = ChronoUnit.DAYS.between(startDate.toLocalDate(), endDate.toLocalDate()) + 1;
                int matchesPerDay = (int) Math.max(1, Math.min(4, Math.ceil((double) matchups.size() / totalDays)));
                int matchIndex = 1;

                int groupMatchIndex = 1;
                LocalDateTime startTimeMatch = null;
                for (LeagueRegister[] matchup : matchups) {
                    String matchName = "Trận đấu Group " + matchIndex;
                    sql = "INSERT INTO Match (LeagueId, HomeTeamId, AwayTeamId, Status, Name, Address, StartAt, EndAt, GroupId) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, league.getId());
                    ps.setInt(2, matchup[0].getTeamId());
                    ps.setInt(3, matchup[1].getTeamId());
                    ps.setInt(4, 0);
                    ps.setString(5, matchName);
                    ps.setString(6, league.getAddress());

                    if (matchIndex > matchesPerDay) {
                        matchIndex = 1;
                        startDate = startDate.plusDays(1).withHour(startHour).withMinute(startMin);
                    }
                    if (endDate.toLocalTime().isBefore(LocalTime.of(startHour, startMin))) {
                        // neues thoi gian truoc thoi gian bat dau thi se chuyen thanh thoi gian bat dau
                        startDate.withHour(startHour).withMinute(startMin);
                    } else if (endDate.toLocalTime().isAfter(LocalTime.of(endDate.getHour(), endDate.getMinute()))) {
                        // Nếu sau 8 PM, điều chỉnh thành ngày bắt đầu của ngày sau
                        startDate.plusDays(1).withHour(startHour).withMinute(startMin);
                    }

                    endDate = startDate.plusHours(2);

                    // truong hop chung 1 khoan
                    int startHourMatch = startDate.getHour();
                    int endHourMatch = endDate.getHour();

                    ps.setTimestamp(7, java.sql.Timestamp.valueOf(startDate));
                    endDate = startDate.plusHours(2);
                    ps.setTimestamp(8, java.sql.Timestamp.valueOf(endDate));

                    if ((startHourMatch >= 11 && endHourMatch <= 13)
                            || (endHourMatch >= 11 && endHourMatch <= 13)) {
                        startTimeMatch = startDate.withHour(13).withMinute(0);
                        endDate = startTimeMatch.plusHours(2);
                        ps.setTimestamp(7, java.sql.Timestamp.valueOf(startTimeMatch));
                        ps.setTimestamp(8, java.sql.Timestamp.valueOf(endDate));
                    }

                    ps.setInt(9, matchup[1].getGroupId());
                    startDate = endDate;
                    int afftedRow = ps.executeUpdate();
                    MatchVM matchVM = new MatchVM();
                    if (afftedRow > 0) {
                        sql = "SELECT t.Id, t.Image, t.Name, t.ShortName, m.Id as mId, m.StartAt, m.EndAt FROM dbo.[Match] m JOIN dbo.Team t ON m.HomeTeamId = t.Id WHERE LeagueId = ?";
                        ps = con.prepareStatement(sql);
                        ps.setInt(1, leagueId);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            matchVM.setAddress(league.getAddress());
                            matchVM.setLeagueId(league.getId());
                            matchVM.setId(rs.getInt("mId"));
                            matchVM.setHomeTeamId(matchup[0].getTeamId());
                            matchVM.setStartAt(rs.getString("StartAt"));
                            matchVM.setEndAt(rs.getString("EndAt"));
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
                    groupMatchIndex++;
                }
            }
            return listmatchVM;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean generateMatchWithGroupInKnockoutStage(List<LeagueRegisterVM[]> matchups, int leagueId, LocalDateTime startTime, LocalDateTime endTime) {
        try {

            int afftedRow = 0;
            LocalDateTime _startTime = startTime;
            LocalDateTime _endTime = endTime;
            for (LeagueRegisterVM[] matchup : matchups) {
                String matchName = "Trận đấu vòng knock out";
                String sql = "INSERT INTO Match (LeagueId, HomeTeamId, AwayTeamId, Status, Name, Address, StartAt, EndAt, Knockout) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, 1)";
                ps = con.prepareStatement(sql);
                ps.setInt(1, leagueId);
                ps.setInt(2, matchup[0].getTeamId());
                ps.setInt(3, matchup[1].getTeamId());
                ps.setInt(4, 0);
                ps.setString(5, matchName);
                ps.setString(6, "");

                // Chỗ này hiện tại em chỉ làm kịp cộng tiếp 2 giờ.
                ps.setTimestamp(7, java.sql.Timestamp.valueOf(_startTime));
                _endTime = _startTime.plusHours(2);
                ps.setTimestamp(8, java.sql.Timestamp.valueOf(_endTime));
                _startTime = _endTime;

                afftedRow = ps.executeUpdate();
                if (afftedRow > 0) {
                    // status 6 la conf giai vong tiep theo
                    sql = "UPDATE League Set Status = 6 WHERE Id = ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, leagueId);
                    afftedRow = ps.executeUpdate();
                }
            }
            return afftedRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean generateFinalMatchWithGroupInKnockoutStage(List<LeagueRegisterVM[]> matchups, int leagueId, LocalDateTime startTime, LocalDateTime endTime) {
        try {

            int afftedRow = 0;
            LocalDateTime _startTime = startTime;
            LocalDateTime _endTime = endTime;
            for (LeagueRegisterVM[] matchup : matchups) {
                String matchName = "Trận đấu vòng knock out";
                String sql = "INSERT INTO Match (LeagueId, HomeTeamId, AwayTeamId, Status, Name, Address, StartAt, EndAt, Knockout) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, 1)";
                ps = con.prepareStatement(sql);
                ps.setInt(1, leagueId);
                ps.setInt(2, matchup[0].getTeamId());
                ps.setInt(3, matchup[1].getTeamId());
                ps.setInt(4, 0);
                ps.setString(5, matchName);
                ps.setString(6, "");

                // Chỗ này hiện tại em chỉ làm kịp cộng tiếp 2 giờ.
                ps.setTimestamp(7, java.sql.Timestamp.valueOf(_startTime));
                _endTime = _startTime.plusHours(2);
                ps.setTimestamp(8, java.sql.Timestamp.valueOf(_endTime));
                _startTime = _endTime;

                afftedRow = ps.executeUpdate();
                if (afftedRow > 0) {
                    // status 8 là trận chung kết.
                    sql = "UPDATE League Set Status = 8 WHERE Id = ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, leagueId);
                    afftedRow = ps.executeUpdate();
                    if (afftedRow > 0) {
                        sql = "SELECT * FROM Match WHERE LeagueId = ? ORDER BY Id DESC";
                        ps = con.prepareStatement(sql);
                        ps.setInt(1, leagueId);
                        rs = ps.executeQuery();
                        if (rs.next()) {
                            // get matchFianl Id de update knockout status
                            int finalMatchId = rs.getInt(1);
                            sql = "UPDATE Match Set Knockout = 2 WHERE Id = ?";
                            ps = con.prepareStatement(sql);
                            ps.setInt(1, finalMatchId);
                            afftedRow = ps.executeUpdate();
                            return afftedRow > 0;
                        }
                    }
                }
            }
            return afftedRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateTeamLeagueGroupId(int teamLeagueId, int leagueId, int groupId) {
        try {
            String sql = "UPDATE Team_League SET GroupId = ? WHERE TeamId = ? AND LeagueId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, groupId);
            ps.setInt(2, teamLeagueId);
            ps.setInt(3, leagueId);

            int affectedRow = ps.executeUpdate();
            return affectedRow > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Group> getListGroup(int leagueId) {
        List<Group> listGroup = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [Group] WHERE LeagueId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Group group = new Group();
                group.setId(rs.getInt("Id"));
                group.setLeagueId(rs.getInt("LeagueId"));
                group.setName(rs.getString("Name"));
                listGroup.add(group);
            }
            return listGroup;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listGroup;
    }

    public List<MatchVM> getWinnerInKnockoutStage(int leagueId) {
        List<MatchVM> listMatch = new ArrayList();
        try {
            String sql = "SELECT \n"
                    + "    Id, \n"
                    + "    LeagueId, \n"
                    + "    HomeTeamId, \n"
                    + "    AwayTeamId, \n"
                    + "    Status, \n"
                    + "    Name, \n"
                    + "    Address, \n"
                    + "    StartAt, \n"
                    + "    EndAt, \n"
                    + "    Knockout, \n"
                    + "    ScoreHome, \n"
                    + "    ScoreAway, \n"
                    + "    GroupId,\n"
                    + "    CASE \n"
                    + "        WHEN ScoreHome > ScoreAway THEN HomeTeamId\n"
                    + "        WHEN ScoreHome < ScoreAway THEN AwayTeamId\n"
                    + "        ELSE NULL\n"
                    + "    END AS WinningTeam\n"
                    + "FROM \n"
                    + "    Match WHERE Knockout = 1 AND LeagueId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            while (rs.next()) {
                MatchVM matchVM = new MatchVM();
                matchVM.setWinningId(rs.getInt("WinningTeam"));
                listMatch.add(matchVM);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listMatch;
    }

    public List<MatchVM> getLoserInKnockoutStage(int leagueId) {
        List<MatchVM> listMatch = new ArrayList();
        try {
            String sql = "SELECT \n"
                    + "    Id, \n"
                    + "    LeagueId, \n"
                    + "    HomeTeamId, \n"
                    + "    AwayTeamId, \n"
                    + "    Status, \n"
                    + "    Name, \n"
                    + "    Address, \n"
                    + "    StartAt, \n"
                    + "    EndAt, \n"
                    + "    Knockout, \n"
                    + "    ScoreHome, \n"
                    + "    ScoreAway, \n"
                    + "    GroupId,\n"
                    + "    CASE \n"
                    + "        WHEN ScoreHome < ScoreAway THEN HomeTeamId\n"
                    + "        WHEN ScoreHome > ScoreAway THEN AwayTeamId\n"
                    + "        ELSE NULL\n"
                    + "    END AS LosingTeam\n"
                    + "FROM \n"
                    + "    Match WHERE Knockout = 1 AND LeagueId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            while (rs.next()) {
                MatchVM matchVM = new MatchVM();
                matchVM.setLosingId(rs.getInt("LosingTeam"));
                listMatch.add(matchVM);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listMatch;
    }

    public League updateLeagueDetail(League _league) {
        try {
            String sql = "UPDATE League SET Name = ?, Description = ? WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, _league.getName());
            ps.setString(2, _league.getDescription());
            ps.setInt(1, _league.getId());
            int afftectedRow = ps.executeUpdate();
            if (afftectedRow > 0) {
                sql = "SELECT * FROM League WHERE Id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, _league.getId());
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
                    league.setTeamMemberSize(rs.getInt("TeamMemberSize"));
                    byte[] imgData = rs.getBytes("Image");
                    String base64Image = Base64.getEncoder().encodeToString(imgData);
                    league.setImage(base64Image);
                    return league;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean removeTeamRegsiterFromLeague(int teamId, int leagueId) {
        try {
            String sql = "DELETE FROM Team_League WHERE TeamId = ? AND LeagueId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, teamId);      
            ps.setInt(2, leagueId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    public boolean changeStatusTeamRegsiterFromLeague(int teamId, int leagueId) {
        try {
            String sql = "UPDATE Team_League SET Status = 0 WHERE TeamId = ? AND LeagueId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, teamId);      
            ps.setInt(2, leagueId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static void main(String[] args) {
        LeagueDAO leageDAO = new LeagueDAO();
        for (MatchVM vm : leageDAO.getFinalMatchByLeagueId(1013)) {
            System.out.println("home" + vm.getHomeTeamId());  
            System.out.println("ây" + vm.getAwayTeamId());
        }
    }
}
