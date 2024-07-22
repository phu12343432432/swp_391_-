/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.DBContext;
import Model.Card;
import Model.Goal;
import Model.Match;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Datnt
 */
public class MatchDAO extends DBContext {

    private Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public MatchDAO() {
        try {
            con = new DBContext().getConnection();
            System.out.println("Connect success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean InsertMatchCard(Card card) {
        try {
            String sql = "INSERT  dbo.[Card] ([Type], [Team_MemberId], [MatchId], Card_Time, TeamId) VALUES (?, ?, ?, ?, ?)";

            ps = con.prepareStatement(sql);
            ps.setString(1, card.getType());
            ps.setInt(2, card.getTeamMemberId());
            ps.setInt(3, card.getMatchId());
            ps.setInt(4, card.getCard_Time());
            ps.setInt(5, card.getTeamId());

            int afftecRow = ps.executeUpdate();
            return afftecRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean InsertMatchGoal(Goal goal) {
        try {
            String sql = "INSERT  dbo.[Goal] ([Team_MemberId], [MatchId], Goal_Time, TeamId) VALUES (?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, goal.getTeamMemberId());
            ps.setInt(2, goal.getMatchId());
            ps.setInt(3, goal.getGoal_Time());
            ps.setInt(4, goal.getTeamId());
            int afftecRow = ps.executeUpdate();
            return afftecRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Map<String, String>> getMatchDetailByMatchId(int matchId) {
        try {
            List<Map<String, String>> teamEvents = new ArrayList<>();
            String sql = "SELECT t.Id as TeamId, tm.Name AS PlayerName, t.Name AS TeamName, "
                    + "'Goal' AS ActionType, g.Goal_Time AS ActionTime, NULL AS CardType "
                    + "FROM goal g "
                    + "INNER JOIN Team_Member tm ON g.Team_MemberId = tm.Id "
                    + "INNER JOIN team t ON g.TeamId = t.Id "
                    + "WHERE g.MatchId = ? "
                    + "UNION ALL "
                    + "SELECT t.Id AS TeamId, tm.Name AS PlayerName, t.Name AS TeamName, "
                    + "'Card' AS ActionType, c.Card_Time AS ActionTime, c.Type AS CardType "
                    + "FROM card c "
                    + "INNER JOIN Team_Member tm ON c.Team_MemberId = tm.Id "
                    + "INNER JOIN team t ON c.TeamId = t.Id "
                    + "WHERE c.MatchId = ? "
                    + "ORDER BY ActionTime";
            ps = con.prepareStatement(sql);
            ps.setInt(1, matchId);
            ps.setInt(2, matchId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, String> event = new HashMap<>();
                event.put("TeamId", rs.getString("TeamId"));
                event.put("PlayerName", rs.getString("PlayerName"));
                event.put("TeamName", rs.getString("TeamName"));
                event.put("ActionType", rs.getString("ActionType"));
                event.put("ActionTime", rs.getString("ActionTime"));
                event.put("CardType", rs.getString("CardType"));

                teamEvents.add(event);
            }
            return teamEvents;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateMatchTime(int matchId, LocalDateTime startDate, LocalDateTime endDate) {
        try {
            String sql = "UPDATE Match SET StartAt = ?, EndAt = ? WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setTimestamp(1, java.sql.Timestamp.valueOf(startDate));
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(endDate));
            ps.setInt(3, matchId);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hasMatchTimeConflict(int matchId, LocalDateTime startDate, LocalDateTime endDate) {
        try {
            int matchTime = endDate.getHour() - startDate.getHour();
            if(matchTime > 2) {
                return false;
            }
            String sql = "SELECT COUNT(*) FROM Match WHERE Id != ? AND (? BETWEEN StartAt AND EndAt AND ? BETWEEN StartAt AND EndAt)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, matchId);
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(startDate));
            ps.setTimestamp(3, java.sql.Timestamp.valueOf(endDate));
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Match getLastMatchInLeague(int leagueId) {
        try {
            String sql = "SELECT * FROM [Match] WHERE LeagueId = ? ORDER BY Id DESC ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, leagueId);
            rs = ps.executeQuery();
            if(rs.next()) {
                Match match = new Match();
                match.setEndAt(rs.getTimestamp("EndAt").toLocalDateTime());
                match.setGroupId(rs.getInt("GroupId"));
                match.setAwayTeamId(rs.getInt("AwayTeamId"));    
                match.setHomeTeamId(rs.getInt("HomeTeamId"));
                match.setScoreHome(rs.getInt("ScoreHome"));       
                match.setScoreAway(rs.getInt("ScoreAway"));
                match.setLeaugeId(rs.getInt("LeagueId"));
                return match;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        MatchDAO matchDAO = new MatchDAO();
        System.out.println(matchDAO.getLastMatchInLeague(7));
    }
};
