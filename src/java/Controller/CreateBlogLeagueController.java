/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.BlogLeagueDAO;
import Model.Blog;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.IOException;

@MultipartConfig
public class CreateBlogLeagueController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String leagueId = request.getParameter("leagueId");
        request.setAttribute("leagueId", leagueId);
        request.getRequestDispatcher("views/user/league-owner/add-blog-league.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("USER") != null) {
                User user = (User) session.getAttribute("USER");
                String title = request.getParameter("title");
                String description = request.getParameter("content");
                int leagueId = Integer.parseInt(request.getParameter("leagueId"));
                Part image = request.getPart("image");
                Blog blog = new Blog();
                blog.setTitle(title);
                blog.setDescription(description);
                BlogLeagueDAO blogLeagueDAO = new BlogLeagueDAO();
                if (blogLeagueDAO.createBlogForLeague(blog, image, leagueId, user.getId())) {
                    request.setAttribute("MESSAGE", "Create blog successfully");
                } else {
                    request.setAttribute("ERRORMESSAGE", "Failed to create blog");
                }
                response.sendRedirect("ViewBlogLeagueListController?leagueId=" + leagueId);
            } else {
                response.sendRedirect("auth?action=login");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
