/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.DBContext;
import Model.League;
import Model.User;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ProfileDAO extends DBContext {

    private Connection con;
    private List<User> user;
    PreparedStatement ps;
    ResultSet rs;

    public ProfileDAO() {
        try {
            con = new DBContext().getConnection();
            System.out.println("Connect success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User updateProfile(User user, Part image) {
        try {
            User userUpdate = new User();
            String sql = "";
            if (image != null) {
                sql = "UPDATE dbo.[User] SET [FirstName] = ?, [LastName] = ?, [Phone] = ?, "
                        + "[Email] = ?, UpdateAt = ?, Image = ? "
                        + "WHERE [Id] = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getPhone());
                ps.setString(4, user.getEmail());

                LocalDateTime now = LocalDateTime.now();
                ps.setString(5, now.toString());
                InputStream fileContent = image.getInputStream();
                ps.setBinaryStream(6, fileContent, (int) image.getSize());
                ps.setInt(7, user.getId());
            } else {
                sql = "UPDATE dbo.[User] SET [FirstName] = ?, [LastName] = ?, [Phone] = ?, "
                        + "[Email] = ?, UpdateAt = ? "
                        + "WHERE [Id] = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getPhone());
                ps.setString(4, user.getEmail());

                LocalDateTime now = LocalDateTime.now();
                ps.setString(5, now.toString());
                ps.setInt(6, user.getId());
            }

            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                System.out.println("Oke");
                sql = "SELECT * FROM dbo.[User] WHERE Id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, user.getId());
                rs = ps.executeQuery();
                if (rs.next()) {
                    int UserId = rs.getInt("Id");
                    String firstName = rs.getString("FirstName");
                    String lastName = rs.getString("LastName");
                    String phone = rs.getString("Phone");
                    String email = rs.getString("Email");
                    
byte[] imgData = rs.getBytes("Image");
                    String base64Image = Base64.getEncoder().encodeToString(imgData);
                    userUpdate.setFirstName(firstName);
                    userUpdate.setLastName(lastName);
                    userUpdate.setPhone(phone);
                    userUpdate.setEmail(email);
                    userUpdate.setId(UserId);
                    userUpdate.setImage(base64Image);
                    return userUpdate;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean changePassword(User user, String newPassword) {
        String sql = "SELECT * FROM [User] WHERE [UserName] = ? AND [Password] = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                sql = "UPDATE dbo.[User] SET [Password] = ? "
                        + "WHERE [UserName] = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, newPassword);
                ps.setString(2, user.getUserName());
                int affectedRow = ps.executeUpdate();
                if (affectedRow > 0) {
                    return true;

                }
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean createLeaguePermissionRequest(int userId) {
        try {
            String sql = "UPDATE [USER] SET IsRequest = 1 WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            int affectedRow = ps.executeUpdate();
            if(affectedRow > 0) {
                 NotificationDAO notiDAO = new NotificationDAO();
                String title = "YÊU CẦU QUYỀN TẠO GIẢI ĐẤU";
                String contentNoti = "Yêu cầu trở thành nhà tạo giải của bạn đã được gửi, vui lòng đợi admin phê duyệt!";
                notiDAO.createNotification(userId, title, contentNoti);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args) {
        ProfileDAO proleDAO = new ProfileDAO();
        boolean result = proleDAO.createLeaguePermissionRequest(4);
        System.out.println("result" + result);
    }
}
