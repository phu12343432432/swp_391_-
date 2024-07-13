/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import DAO.BlogDAO;
import Model.Blog;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@MultipartConfig
@WebServlet(name = "BlogManageController", urlPatterns = {"/admin/BlogManageController"})
public class BlogManageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            BlogDAO blogDAO = new BlogDAO();
            List<Blog> listBlog = blogDAO.getAllBlogs();
            if (listBlog != null || listBlog.size() > 0) {
                request.setAttribute("BLOG_LIST", listBlog);
            }
            request.getRequestDispatcher("/views/manage/blog-manage.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            if(session != null && session.getAttribute("USER") != null) {
                 String title = request.getParameter("title");
            String description = request.getParameter("content");
            User user = (User) session.getAttribute("USER");
            Part image = request.getPart("image");
            Blog blog = new Blog();
            blog.setTitle(title);
            blog.setDescription(description);
            blog.setDeleteAt(null);
            blog.setStatus(1); // 1 for active, 0 for inactive   
            BlogDAO blogDAO = new BlogDAO();
            if (blogDAO.createBlog(blog, image, user.getId())) {
                response.sendRedirect("BlogManageController");
            } else {
                request.setAttribute("ERROR", "Failed to create blog");
                request.getRequestDispatcher("/create-blog.jsp").forward(request, response);
            }
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
