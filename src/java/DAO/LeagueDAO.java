/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.DBContext;
import Model.League;
import Model.Team;
import Model.User;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Datnt
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
        List<League> listLeague = new ArrayList<>();
        try {
            String sql = "SELECT * FROM League WHERE Id = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            if (rs.next()) {
                League league = new League();
                league.setId(rs.getInt("Id"));
                league.setAddress(rs.getString("Address"));
                league.setName(rs.getString("Name"));
                league.setDescription(rs.getString("Description"));
                league.setStartDate(rs.getString("StartDate"));
                league.setEndDate(rs.getString("EndDate"));
                league.setTeamSize(rs.getInt("TeamSize"));
                league.setType(rs.getString("Type"));
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
                league.setDescription(rs.getString("Description"));
                league.setStartDate(rs.getString("StartDate"));
                league.setEndDate(rs.getString("EndDate"));
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
                league.setDescription(rs.getString("Description"));
                league.setStartDate(rs.getString("StartDate"));
                league.setEndDate(rs.getString("EndDate"));
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
                league.setStartDate(rs.getString("StartDate"));
                league.setEndDate(rs.getString("EndDate"));
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
                    + "[StartDate], [EndDate], [Address], [Image] ,[CreateAt], [UserId], [Type]) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, league.getName());
            ps.setString(2, league.getDescription());
            ps.setInt(3, league.getTeamSize());
            ps.setString(4, league.getStartDate());
            ps.setString(5, league.getEndDate());
            ps.setString(6, league.getAddress());
            InputStream fileContent = image.getInputStream();
            ps.setBinaryStream(7, fileContent, (int) image.getSize());
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String currentDate = dateFormat.format(date);
            ps.setString(8, currentDate);
            ps.setInt(9, league.getUserID());
            ps.setString(10, league.getType());

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
            String sql = "INSERT  dbo.[Team_League] (TeamId, LeagueId, RegisterAt) VALUES (?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, teamId);
            ps.setInt(2, leagueId);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String currentDate = dateFormat.format(date);
            ps.setString(3, currentDate);
            int affectedRow = ps.executeUpdate();
            if(affectedRow >0) {
                return 0;
            }
//            if (affectedRow > 0) {
//                sql = "UPDATE  dbo.[League] SET TeamSize = TeamSize + 1 WHERE Id = ?";
//                ps = con.prepareStatement(sql);
//                ps.setInt(1, leagueId);
//                affectedRow = ps.executeUpdate();
//                if (affectedRow > 0) {
//                    return 0;
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    // chỗ này phân trang
    public List<League> getLeagueRegisted(int teamId) {
        List<League> listLeague = new ArrayList<>();
        try {
            String sql = "SELECT * FROM League le JOIN Team_League tl ON tl.LeagueId = le.Id";
            ps = con.prepareStatement(sql);
            ps.setInt(1, (teamId - 1) * 9);
            rs = ps.executeQuery();
            while (rs.next()) {
                League league = new League();
                league.setId(rs.getInt("Id"));
                league.setAddress(rs.getString("Address"));
                league.setName(rs.getString("Name"));
                league.setDescription(rs.getString("Description"));
                league.setStartDate(rs.getString("StartDate"));
                league.setEndDate(rs.getString("EndDate"));
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
}
