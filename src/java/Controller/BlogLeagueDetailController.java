/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.BlogDAO;
import DAO.LeagueDAO;
import DAO.TeamDAO;
import Model.Blog;
import Model.League;
import Model.Team;
import Model.User;
import Model.ViewModel.CommentViewModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@MultipartConfig
public class BlogLeagueDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        String url = "views/common/index.jsp";
        switch (action) {
            case "view":
                viewBlogLeagueDetail(request, response);
                break;
            case "deleteComment":
                deleteComment(request, response);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        String url = "views/common/index.jsp";
        if (session != null && session.getAttribute("USER") != null) {
            User user = (User) session.getAttribute("USER");
            switch (action) {
                case "comment":
                    CommentInBlogLeague(request, response);
                    break;
            }
        } else {
            // trang login
            url = "views/common/sign-in.jsp";
            request.getRequestDispatcher(url).forward(request, response);

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void viewBlogLeagueDetail(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            User userLogin = (User) session.getAttribute("USER");
            String indexS = request.getParameter("index");
            String leagueIds = request.getParameter("leagueId");
            if (indexS == null) {
                indexS = "1";
            }
            int index = Integer.parseInt(indexS);
            String blogIds = request.getParameter("blogId");
            int blogId = Integer.parseInt(blogIds);
            BlogDAO blogDAO = new BlogDAO();
            Blog blog = blogDAO.getBlogById(blogId);
            int total = blogDAO.getListUserCommentInBlogTotal(blogId);
            List<CommentViewModel> listComment = blogDAO.getListUserCommentInBlog(blogId, index);
            int lastPage = total / 12;
            if (total % 12 != 0) {
                lastPage++;
            }
            request.setAttribute("BLOG", blog);
            request.setAttribute("leagueId", blog.getLeagueId());
            request.setAttribute("endP", lastPage);
            request.setAttribute("selectedPage", index);
            request.setAttribute("COMMENTS", listComment);
            request.getRequestDispatcher("views/common/blog-league-detail.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void CommentInBlogLeague(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("USER") != null) {
                User userLogin = (User) session.getAttribute("USER");
                String content = request.getParameter("feedback");
                String blogIds = request.getParameter("blogId");
                int blogId = Integer.parseInt(blogIds);

                TeamDAO teamDAO = new TeamDAO();

                Team userTeam = teamDAO.getTeamByUserId(userLogin.getId());
                if (userTeam != null) {
                    BlogDAO blogDAO = new BlogDAO();
                    boolean result = blogDAO.commentBlog(blogId, content, userLogin.getId());
                    if (result) {
                        response.sendRedirect("blog-league?action=view&blogId=" + blogId);
                        return;
                    }
                    request.setAttribute("Failed", "Your comment post failed");
                    response.sendRedirect("blog-league?action=view&blogId=" + blogId);
                }
            } else {
                response.sendRedirect("auth?action=login");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteComment(HttpServletRequest request, HttpServletResponse response) {
        try {
            int leagueId = 0;
            String blogIds = request.getParameter("blogId");
            HttpSession session = request.getSession();
            if (session != null && session.getAttribute("USER") != null) {
                User userLogin = (User) session.getAttribute("USER");
                String commentIdS = request.getParameter("id");
                String leagueIdS = request.getParameter("leagueId");
                leagueId = Integer.parseInt(leagueIdS);

                int commentId = Integer.parseInt(commentIdS);
                LeagueDAO leagueDAO = new LeagueDAO();
                BlogDAO blogDAO = new BlogDAO();
                boolean result = blogDAO.deleteCommentBlog(commentId, userLogin.getId());
                if (result) {
                    request.setAttribute("MESSAGE", "Xóa comment thành công");
                } else {
                    // get leagueOwner of this blog.
                    League league = leagueDAO.getLeagueById(leagueId);
                    int ownerLeague = league.getUserId();
                    if (userLogin.getRoleId() == 2 || ownerLeague == userLogin.getId()) {
                        result = blogDAO.deleteCommentBlogOwnerRole(commentId);
                    }
                }
                if (!result) {
                    request.setAttribute("ERROR", "Xóa comment không thành công");
                }
            }
            request.setAttribute("blogId", blogIds);
            request.setAttribute("leagueId", leagueId);
            String url = "blog-league?action=view";

            request.getRequestDispatcher(url).forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
