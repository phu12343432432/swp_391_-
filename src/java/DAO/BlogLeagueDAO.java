package DAO;

import DAL.DBContext;
import Model.Blog;
import Model.ViewModel.BlogLeagueVM;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class BlogLeagueDAO extends DBContext {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public BlogLeagueDAO() {
        try {
            con = new DBContext().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<BlogLeagueVM> getListBlogByLeagueId(int leagueId) {
        List<BlogLeagueVM> blogs = new ArrayList<>();
        try {
            String sql = "SELECT l.Id as LeagueId, b.Id, b.Title, b.Description, b.Image, b.CreateAt, b.Status, b.UserId, l.Name as LeagueName "
                    + "FROM Blog b "
                    + "JOIN League l ON b.LeagueId = l.Id "
                    + "WHERE b.LeagueId = ? AND b.Status = 1 ORDER BY b.CreateAt DESC";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            while (rs.next()) {
                BlogLeagueVM blog = new BlogLeagueVM();
                blog.setId(rs.getInt("Id"));
                blog.setTitle(rs.getString("Title"));
                blog.setDescription(rs.getString("Description"));
                byte[] imgData = rs.getBytes("Image");
                String base64Image = Base64.getEncoder().encodeToString(imgData);
                blog.setImage(base64Image);
                blog.setCreateAt(rs.getString("CreateAt"));
                blog.setStatus(rs.getInt("Status"));    
                blog.setLeagueId(rs.getInt("LeagueId"));

                blog.setLeagueId(leagueId);
                blog.setLeagueName(rs.getString("LeagueName"));
                blogs.add(blog);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogs;
    }

    public boolean createBlogForLeague(Blog blog, Part image, int leagueId, int userId) {
        try {
            String sql = "INSERT INTO Blog (Title, Description, Image, CreateAt, Status, UserId, LeagueId) VALUES (?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, blog.getTitle());
            ps.setString(2, blog.getDescription());
            InputStream fileContent = image.getInputStream();
            ps.setBinaryStream(3, fileContent, (int) image.getSize());
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String currentDate = dateFormat.format(date);
            ps.setString(4, currentDate);
            ps.setInt(5, 1); // Set status to active
            ps.setInt(6, userId);
            ps.setInt(7, leagueId);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
