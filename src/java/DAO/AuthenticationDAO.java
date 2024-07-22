/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.DBContext;
import Model.CreateModel.UserSignUp;
import Model.Role;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class AuthenticationDAO extends DBContext {

    private Connection con;
    private List<User> user;
    PreparedStatement ps;
    ResultSet rs;

    public AuthenticationDAO() {
        try {
            con = new DBContext().getConnection();
            System.out.println("Connect success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User Login(String username, String password) {
        String sql = "SELECT * FROM [User] WHERE [UserName] = ? AND [Password] = ?";
        User user = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
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
                boolean isRequest = rs.getBoolean("IsRequest");
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
                user.setIsRequest(isRequest);
                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot found");
        }
        return user;
    }

    public int Register(UserSignUp userSignUp) {

        try {
            boolean isDuplicateUserName = CheckUserName(userSignUp.getUserName());
            if (isDuplicateUserName) {
                return 2;
            }

            boolean isDuplicateEmail = CheckEmail(userSignUp.getEmail());
            if (isDuplicateEmail) {
                return 3;
            }
            String sql = "INSERT INTO [User] (UserName, Password, Email, FirstName, LastName, Phone, IsActive, RoleId, IsConfirm, CreateAt)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, userSignUp.getUserName());
            ps.setString(2, userSignUp.getPassword());
            ps.setString(3, userSignUp.getEmail());
            ps.setString(4, userSignUp.getFirstName());
            ps.setString(5, userSignUp.getLastName());
            ps.setString(6, userSignUp.getPhone());
            ps.setBoolean(7, true);
            // default role is 1 is user
            ps.setInt(8, 1);
            ps.setBoolean(9, false);
            LocalDateTime now = LocalDateTime.now();
            ps.setString(10, now.toString());

            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Register Cannot Register");
        }
        return 0;
    }

    public String GetUserRole(int RoleId) {
        String sql = "SELECT * FROM [Role] WHERE [RoleId] = ? ";
        User user = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, RoleId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("RoleName");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("GetUserRole Cannot found");
        }
        return null;
    }

    public boolean CheckUserName(String userName) {
        String sql = "SELECT * FROM [User] WHERE [UserName] = ? ";
        User user = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("CheckUserName Cannot find User");
        }
        return false;
    }

    public boolean CheckEmail(String email) {
        String sql = "SELECT * FROM [User] WHERE [Email] = ? ";
        User user = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("CheckUserName Cannot find User");
        }
        return false;
    }

    public boolean ConfirmEmail(String userName) {
        String sql = "UPDATE [User] SET IsConfirm = ? WHERE UserName = ?";
        User user = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, true);
            ps.setString(2, userName);

            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return true;
            } else {
                System.out.println("Confirm email Cannot found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ConfirmEmail Cannot found");
        }
        return false;
    }

    public boolean ForgotPassWord(String password, String email) {
        String sql = "UPDATE [User] SET Password = ? WHERE Email = ?";
        User user = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, email);

            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return true;
            } else {
                System.out.println("ForgotPassWord Cannot found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ForgotPassWord Cannot found");
        }
        return false;
    }

    public User getUserById(int Id) {
        String sql = "SELECT * FROM [User] WHERE [Id] = ?";
        User user = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, Id);
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
                String _password = rs.getString("Password");
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
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot found");
        }
        return user;
    }

    public boolean checkValidateEmail(String email) {
        try {
            String sql = "SELECT COUNT(*) FROM [User] WHERE Email = ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
