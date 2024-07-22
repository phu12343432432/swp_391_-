/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.DBContext;
import Model.Team;
import Model.Team_Member;
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
 * @author ADMIN
 */
public class TeamDAO extends DBContext {

    private Connection con;
    private List<User> user;
    PreparedStatement ps;
    ResultSet rs;

    public TeamDAO() {
        try {
            con = new DBContext().getConnection();
            System.out.println("Connect success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Team getTeamById(int _teamId) {
        try {
            String sql = "SELECT * FROM Team WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, _teamId);
            rs = ps.executeQuery();
            if (rs.next()) {
                Team _team = new Team();
                int teamId = rs.getInt("Id");
                String Name = rs.getString("Name");
                String ShortName = rs.getString("ShortName");
                String Description = rs.getString("Description");
                int teamSize = rs.getInt("TeamSize");
                byte[] imgData = rs.getBytes("Image");
                String base64Image = Base64.getEncoder().encodeToString(imgData);

                _team.setImage(base64Image);
                _team.setName(Name);
                _team.setShortName(ShortName);
                _team.setDescription(Description);
                _team.setTeamSize(teamSize);
                _team.setId(teamId);
                return _team;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Team getTeamByUserId(int userId) {
        try {
            String sql = "SELECT * FROM Team WHERE UserId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                Team _team = new Team();
                int teamId = rs.getInt("Id");
                String Name = rs.getString("Name");
                String ShortName = rs.getString("ShortName");
                String Description = rs.getString("Description");
                int teamSize = rs.getInt("TeamSize");
                byte[] imgData = rs.getBytes("Image");
                String base64Image = Base64.getEncoder().encodeToString(imgData);

                _team.setImage(base64Image);
                _team.setName(Name);
                _team.setShortName(ShortName);
                _team.setDescription(Description);
                _team.setTeamSize(teamSize);
                _team.setId(teamId);
                _team.setUserId(rs.getInt("UserId"));
                return _team;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Team updateTeam(Team team, Part image) {
        try {
            Team _team = new Team();
            String sql = "";
            if (image != null) {
                sql = "UPDATE dbo.[Team] SET [Name] = ?, [ShortName] = ?, [TeamSize] = ?, "
                        + "[Description] = ?, UpdateAt = ?, Image = ? "
                        + "WHERE [Id] = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, team.getName());
                ps.setString(2, team.getShortName());
                ps.setInt(3, team.getTeamSize());
                ps.setString(4, team.getDescription());
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String currentDate = dateFormat.format(date);
                ps.setString(5, currentDate);
                // input steam. => Chuyen nah nay thanh ma binary.
                InputStream fileContent = image.getInputStream();
                ps.setBinaryStream(6, fileContent, (int) image.getSize());
                ps.setInt(7, team.getId());
            } else {
                sql = "UPDATE dbo.[Team] SET [Name] = ?, [ShortName] = ?, [TeamSize] = ?, "
                        + "[Description] = ?, UpdateAt = ? "
                        + "WHERE [Id] = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, team.getName());
                ps.setString(2, team.getShortName());
                ps.setInt(3, team.getTeamSize());
                ps.setString(4, team.getDescription());
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String currentDate = dateFormat.format(date);
                ps.setString(5, currentDate);
                ps.setInt(6, team.getId());
            }

            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                sql = "SELECT * FROM dbo.[Team] WHERE Id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, team.getId());
                rs = ps.executeQuery();
                if (rs.next()) {
                    int teamId = rs.getInt("Id");
                    String Name = rs.getString("Name");
                    String ShortName = rs.getString("ShortName");
                    String Description = rs.getString("Description");
                    int teamSize = rs.getInt("TeamSize");

                    byte[] imgData = rs.getBytes("Image");
                    String base64Image = Base64.getEncoder().encodeToString(imgData);

                    _team.setImage(base64Image);
                    _team.setName(Name);
                    _team.setShortName(ShortName);
                    _team.setDescription(Description);
                    _team.setTeamSize(teamSize);
                    _team.setId(teamId);
                    return _team;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean createTeam(Team team, Part image) {
        try {

            String sql = "INSERT  dbo.[Team] ([Name], [ShortName], [TeamSize], "
                    + "[Description], [CreateAt], [Image], [UserId], [IsActive]) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, team.getName());
            ps.setString(2, team.getShortName());
            ps.setInt(3, team.getTeamSize());
            ps.setString(4, team.getDescription());
            LocalDateTime now = LocalDateTime.now();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String currentDate = dateFormat.format(date);
            ps.setString(5, currentDate);
            InputStream fileContent = image.getInputStream();
            ps.setBinaryStream(6, fileContent, (int) image.getSize());
            ps.setInt(7, team.getUserId());
            ps.setBoolean(8, true);

            int affectedRow = ps.executeUpdate();
            return affectedRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getListTeamMemberByTeamIdSeachTotal(int teamId, String search) {
        try {
            List<Team_Member> listTeamMember = new ArrayList<>();
            String sql = "SELECT COUNT(*) FROM Team_Member WHERE TeamId = ? AND Name LIKE ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, teamId);
            ps.setString(2, "%" + search + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Team_Member> getListTeamMemberByTeamIdSearch(int teamId, String search, int index) {
        try {
            List<Team_Member> listTeamMember = new ArrayList<>();
            String sql = "SELECT * FROM Team_Member WHERE TeamId = ? AND Name LIKE ? ORDER BY Id DESC OFFSET ? ROW FETCH NEXT 10 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setInt(1, teamId);
            ps.setString(2, "%" + search + "%");
            ps.setInt(3, (index - 1) * 10);

            rs = ps.executeQuery();
            while (rs.next()) {
                Team_Member teamMember = new Team_Member();
                String Name = rs.getString("Name");
                int Number = rs.getInt("Number");
                int TeamId = rs.getInt("TeamId");
                int Id = rs.getInt("Id");

                teamMember.setId(Id);
                teamMember.setName(Name);
                teamMember.setNumber(Number);
                teamMember.setTeamId(TeamId);
                listTeamMember.add(teamMember);
            }
            return listTeamMember;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getListTeamMemberByTeamIdTotal(int teamId) {
        try {
            String sql = "SELECT COUNT(*) FROM Team_Member WHERE TeamId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, teamId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Team_Member> getListTeamMemberByTeamId(int teamId, int index) {
        try {
            List<Team_Member> listTeamMember = new ArrayList<>();
            String sql = "SELECT * FROM Team_Member WHERE TeamId = ? ORDER BY Id DESC OFFSET ? ROW FETCH NEXT 10 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setInt(1, teamId);
            ps.setInt(2, (index - 1) * 10);

            rs = ps.executeQuery();
            while (rs.next()) {
                Team_Member teamMember = new Team_Member();
                String Name = rs.getString("Name");
                int Number = rs.getInt("Number");
                int TeamId = rs.getInt("TeamId");
                int Id = rs.getInt("Id");
                byte[] imgData = rs.getBytes("Image");
                String base64Image = null;
                if (imgData != null) {
                    base64Image = Base64.getEncoder().encodeToString(imgData);
                }
                teamMember.setImage(base64Image);
                teamMember.setId(Id);
                teamMember.setName(Name);
                teamMember.setNumber(Number);
                teamMember.setTeamId(TeamId);
                listTeamMember.add(teamMember);
            }
            return listTeamMember;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isDuplicateNumber(Team_Member teamMember) {
        try {
            String sql = "SELECT * FROM  dbo.[Team_Member] WHERE  TeamId = ? AND Number = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, teamMember.getTeamId());
            ps.setInt(2, teamMember.getNumber());
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean AddTeam_Member(Team_Member teamMember, Part image) {
        try {
            String sql = "INSERT  dbo.[Team_Member] ([Name], [Number], [TeamId], Image) VALUES (?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, teamMember.getName());
            ps.setInt(2, teamMember.getNumber());
            ps.setInt(3, teamMember.getTeamId());
            InputStream fileContent = image.getInputStream();
            ps.setBinaryStream(4, fileContent, (int) image.getSize());
            int affectedRow = ps.executeUpdate();
            return affectedRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean UpdateTeam_Member(Team_Member teamMember) {
        try {
            String sql = "UPDATE dbo.[Team_Member] SET Name = ?, Number = ? WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, teamMember.getName());
            ps.setInt(2, teamMember.getNumber());
            ps.setInt(3, teamMember.getId());
            int affectedRow = ps.executeUpdate();
            return affectedRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean DeleteTeam_Member(int id) {
        try {
            String sql = "DELETE dbo.[Team_Member] WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int affectedRow = ps.executeUpdate();
            return affectedRow > 0;
        } catch (Exception e) {
            System.out.println("Cầu thủ đã ghi tham gia vào trận đấu không thể xóa thông tin cầu thủ này.");
            e.printStackTrace();
        }
        return false;
    }

    public int getTeamSize(int id) {
        try {
            String sql = "SELECT COUNT(*) FROM dbo.[Team_Member]  WHERE TeamId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTeamIdByTeamMemberId(int team_memberId) {
        try {
            String sql = "SELECT * FROM dbo.[Team_Member] WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, team_memberId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("TeamId");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean updateTeamSize(int teamId) {
        try {
            int teamSize = getTeamSize(teamId);
            System.out.println("teamSize " + teamSize);
            String sql = "UPDATE dbo.[Team] SET TeamSize = ? WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, teamSize);
            ps.setInt(2, teamId);
            int affectedRow = ps.executeUpdate();
            return affectedRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getUserIdByTeamID(int TeamId) {
        try {
            String sql = "SELECT * FROM dbo.[Team] WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, TeamId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("UserId");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        TeamDAO teamDAO = new TeamDAO();
        int teamID = teamDAO.getTeamIdByTeamMemberId(534);
        System.out.println("TeamTeam " + teamID);
    }
}
