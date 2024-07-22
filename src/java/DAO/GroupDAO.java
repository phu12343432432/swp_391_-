/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.DBContext;
import Model.Group;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GroupDAO extends DBContext {

    private Connection con;
    private List<User> user;
    PreparedStatement ps;
    ResultSet rs;

    public GroupDAO() {
        try {
            con = new DBContext().getConnection();
            System.out.println("Connect success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Group CreateGroupd(int leagueId, String groupName) {
        try {
            String sql = "INSERT INTO [Group] (LeagueId, Name, Description) VALUES (?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            ps.setString(2, groupName);
            ps.setString(3, "Group description");
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                sql = "SELECT * FROM [Group] ORDER BY Id DESC";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    Group group = new Group();
                    group.setId(rs.getInt(1));
                    group.setName(rs.getString("Name"));
                    group.setDescription(rs.getString("Description"));
                    group.setLeagueId(rs.getInt("LeagueId"));
                    return group;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Group getGroupById(int id) {
        try {
            String sql = "SELECT * FROM [Group] WHERE Id DESC";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                Group group = new Group();
                group.setId(rs.getInt(1));
                group.setName(rs.getString("Name"));
                group.setDescription(rs.getString("Description"));
                group.setLeagueId(rs.getInt("LeagueId"));
                return group;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        GroupDAO groupDAO = new GroupDAO();
        groupDAO.CreateGroupd(1013, "A");
    }
}
