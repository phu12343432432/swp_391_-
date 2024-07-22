package DAO;

import DAL.DBContext;
import Model.Blog;
import Model.ViewModel.CommentViewModel;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class BlogDAO extends DBContext {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public BlogDAO() {
        try {
            con = new DBContext().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean createBlog(Blog blog, Part image, int userId) throws IOException {
        try {
            String sql = "INSERT INTO Blog (Title, Description, Image, CreateAt, Status, UserId) VALUES (?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, blog.getTitle());
            ps.setString(2, blog.getDescription());
            InputStream fileContent = image.getInputStream();
            ps.setBinaryStream(3, fileContent, (int) image.getSize());
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String currentDate = dateFormat.format(date);
            ps.setString(4, currentDate);
            ps.setInt(5, 1);
            ps.setInt(6, userId);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Blog> getAllBlogs() {
        List<Blog> blogs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Blog WHERE Status = 1";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt("Id"));
                blog.setTitle(rs.getString("Title"));
                blog.setDescription(rs.getString("Description"));
                byte[] imgData = rs.getBytes("Image");
                String base64Image = Base64.getEncoder().encodeToString(imgData);
                blog.setImage(base64Image);
                blog.setCreateAt(rs.getString("CreateAt"));
                blog.setStatus(rs.getInt("Status"));
                blogs.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

    public Blog getBlogById(int blogId) {
        List<Blog> blogs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Blog WHERE Status = 1 AND Id = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, blogId);
            rs = ps.executeQuery();
            if (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt("Id"));
                blog.setTitle(rs.getString("Title"));
                blog.setDescription(rs.getString("Description"));
                byte[] imgData = rs.getBytes("Image");
                String base64Image = Base64.getEncoder().encodeToString(imgData);
                blog.setImage(base64Image);
                blog.setLeagueId(rs.getInt("LeagueId"));
                blog.setCreateAt(rs.getString("CreateAt"));
                blog.setStatus(rs.getInt("Status"));
                return blog;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteCommentBlog(int commentId, int userId) {
        List<Blog> blogs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Comment WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, commentId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int ownerCommentId = rs.getInt("UserId");
                if (userId == ownerCommentId) {
                    sql = "DELETE FROM Comment WHERE Id = ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, commentId);
                    int affectedRow = ps.executeUpdate();
                    return affectedRow > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    

    public boolean deleteCommentBlogOwnerRole(int commentId) {
        List<Blog> blogs = new ArrayList<>();
        try {
            String sql = "DELETE FROM Comment WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, commentId);
            int affectedRow = ps.executeUpdate();
            return affectedRow > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean commentLeague(int BlogId, String Content, int UserId) {
        try {
            String sql = "INSERT INTO [Comment] (BlogId, Content, UserId, CreateAt)"
                    + " VALUES (?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, BlogId);
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

    // get list comment
    // Comment Blog
    public boolean commentBlog(int blogId, String Content, int UserId) {
        try {
            String sql = "INSERT INTO [Comment] (BlogId, Content, UserId, CreateAt)"
                    + " VALUES (?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, blogId);
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

    // delete Blog
    public boolean deleteBlog(int blogId) {
        try {
            String sql = "DELETE FROM [Blog] WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, blogId);
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
    public int getListUserCommentInBlogTotal(int blogId) {
        try {
            String sql = "SELECT COUNT(*) FROM [Comment] WHERE BlogId = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, blogId);
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
    public List<CommentViewModel> getListUserCommentInBlog(int blogId, int index) {
        try {
            int userId = 0;
            List<CommentViewModel> listComment = new ArrayList<>();
            String sql = "SELECT * FROM [Comment] WHERE BlogId = ? ORDER BY Id DESC OFFSET ? ROW FETCH NEXT 8 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setInt(1, blogId);
            ps.setInt(2, (index - 1) * 8);
            rs = ps.executeQuery();
            while (rs.next()) {
                CommentViewModel commentVM = new CommentViewModel();
                commentVM.setId(rs.getInt("Id"));
                commentVM.setPostId(rs.getInt("BlogId"));
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

}
