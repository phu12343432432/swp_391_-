/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.DBContext;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class UserManageDAO extends DBContext {

    private Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public UserManageDAO() {
        try {
            con = new DBContext().getConnection();
            System.out.println("Connect success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean createLeaguePermissionRequest(int userId) {
        try {
            String sql = "UPDATE [USER] SET IsRequest = 1 WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            int affectedRow = ps.executeUpdate();
            return affectedRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean acceptPermissionRequest(int userId) {
        try {
            String sql = "UPDATE [USER] SET IsRequest = ?, RoleId = ? WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setInt(2, 3);
            ps.setInt(3, userId);
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                NotificationDAO notiDAO = new NotificationDAO();
                String title = "YÊU CẦU QUYỀN TẠO GIẢI ĐẤU";
                String contentNoti = "Bạn đã được Quản trị viên chấp nhận bạn có thể tạo giải đấu cho riêng bạn";
                notiDAO.createNotification(userId, title, contentNoti);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean rejectPermissionRequest(int userId) {
        try {
            String sql = "UPDATE [USER] SET IsRequest = ?, RoleId = ? WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setInt(2, 1);
            ps.setInt(3, userId);
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                NotificationDAO notiDAO = new NotificationDAO();
                String title = "YÊU CẦU QUYỀN TẠO GIẢI ĐẤU";
                String contentNoti = "Yêu cầu trở thành nhà tạo giải của bạn đã bị từ chối, vui lòng kiểm tra lại!";
                notiDAO.createNotification(userId, title, contentNoti);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getListUserRequiredCreateLeaguePermissionTotal() {
        try {
            String sql = "SELECT COUNT(*) FROM  [USER] WHERE IsRequest = 1";
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

    public List<User> getListUserRequiredCreateLeaguePermission(int index) {
        try {
            List<User> listUserRequest = new ArrayList();

            String sql = "SELECT * FROM [USER] WHERE IsRequest = 1 ORDER BY Id DESC OFFSET ? ROW FETCH NEXT 10 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 10);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                int userId = rs.getInt("Id");
                String userName = rs.getString("UserName");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                boolean isActive = rs.getBoolean("IsActive");
                boolean IsConfirm = rs.getBoolean("IsConfirm");
                int roleId = rs.getInt("RoleId");
                boolean isRequest = rs.getBoolean("IsRequest");

                user.setId(userId);
                user.setUserName(userName);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setPhone(phone);
                user.setEmail(email);
                user.setIsActive(isActive);
                user.setIsCofirm(IsConfirm);
                user.setRoleId(roleId);
                user.setIsRequest(isRequest);
                listUserRequest.add(user);
            }
            return listUserRequest;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> GetAllUser(int index) {
        String sql = "SELECT * FROM [User] WHERE RoleId != 2 ORDER BY Id DESC OFFSET ? ROW FETCH NEXT 12 ROWS ONLY";
        User user = null;
        List<User> listUser = new ArrayList();
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 12);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                int userId = rs.getInt("Id");
                String userName = rs.getString("UserName");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                boolean isActive = rs.getBoolean("IsActive");
                boolean IsConfirm = rs.getBoolean("IsConfirm");
                int roleId = rs.getInt("RoleId");
                byte[] imgData = rs.getBytes("Image");
                String base64Image = null;
                if (imgData != null) {
                    base64Image = Base64.getEncoder().encodeToString(imgData);
                }

                user.setId(userId);
                user.setUserName(userName);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setPhone(phone);
                user.setEmail(email);
                user.setIsActive(isActive);
                user.setIsCofirm(IsConfirm);
                user.setImage(base64Image);
                user.setRoleId(roleId);
                listUser.add(user);
            }
            return listUser;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot found");
        }
        return listUser;
    }

    public int GetAllUserTotal() {
        String sql = "SELECT COUNT(*) FROM [User] WHERE RoleId != 2";
        User user = null;
        List<User> listUser = new ArrayList();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot found");
        }
        return 0;
    }

    public List<User> GetAllUserByUserName(String search, int index) {
        String sql = "SELECT * FROM [User] WHERE RoleId != 2 AND UserName LIKE ? ORDER BY Id DESC OFFSET ? ROW FETCH NEXT 12 ROWS ONLY";
        User user = null;
        List<User> listUser = new ArrayList();
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ps.setInt(2, (index - 1) * 12);

            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                int userId = rs.getInt("Id");
                String userName = rs.getString("UserName");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                boolean isActive = rs.getBoolean("IsActive");
                boolean IsConfirm = rs.getBoolean("IsConfirm");
                int roleId = rs.getInt("RoleId");
                byte[] imgData = rs.getBytes("Image");
                String base64Image = null;
                if (imgData != null) {
                    base64Image = Base64.getEncoder().encodeToString(imgData);
                }

                user.setId(userId);
                user.setUserName(userName);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setPhone(phone);
                user.setEmail(email);
                user.setIsActive(isActive);
                user.setIsCofirm(IsConfirm);
                user.setImage(base64Image);
                user.setRoleId(roleId);
                listUser.add(user);
            }
            return listUser;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot found");
        }
        return listUser;
    }

    public int GetAllUserTotalByUserName(String search) {
        String sql = "SELECT COUNT(*) FROM [User] WHERE RoleId != 2 AND UserName LIKE ?";
        User user = null;
        List<User> listUser = new ArrayList();
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot found");
        }
        return 0;
    }

    public boolean changeStatus(int UserId) {

        try {
            User user = new User();
            String sql = "SELECT * FROM [User] WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, UserId);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                int userId = rs.getInt("Id");
                String userName = rs.getString("UserName");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                boolean isActive = rs.getBoolean("IsActive");
                boolean IsConfirm = rs.getBoolean("IsConfirm");
                int roleId = rs.getInt("RoleId");
                byte[] imgData = rs.getBytes("Image");
                String base64Image = null;
                if (imgData != null) {
                    base64Image = Base64.getEncoder().encodeToString(imgData);
                }
                user.setId(userId);
                user.setUserName(userName);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setPhone(phone);
                user.setEmail(email);
                user.setIsActive(isActive);
                user.setIsCofirm(IsConfirm);
                user.setImage(base64Image);
                user.setRoleId(roleId);
            };
            if (user != null) {
                boolean userStatus = user.isIsActive();
                sql = "UPDATE [User] SET IsActive = ? WHERE Id = ?";
                ps = con.prepareStatement(sql);
                ps.setBoolean(1, !userStatus);
                ps.setInt(2, UserId);
                int afftecRow = ps.executeUpdate();
                return afftecRow > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {

    }

}
