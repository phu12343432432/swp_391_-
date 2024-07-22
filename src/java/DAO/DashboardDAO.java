/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;


public class DashboardDAO extends DBContext {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public DashboardDAO() {
        try {
            con = new DBContext().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
        public Map<YearMonth, Integer> getMonthlyFinishedLeague() {
        Map<YearMonth, Integer> monthlyConfirmedPosts = new HashMap<>();
        try {
            String sql = "SELECT MONTH(CreateAt) AS Month, YEAR(CreateAt) AS Year, COUNT(*) AS TotalPosts " + "FROM League " + "WHERE Status = 4 " + "GROUP BY MONTH(CreateAt), YEAR(CreateAt)";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int month = rs.getInt("Month");
                int year = rs.getInt("Year");
                int totalPosts = rs.getInt("TotalPosts");
                YearMonth yearMonth = YearMonth.of(year, month);
                monthlyConfirmedPosts.put(yearMonth, totalPosts);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return monthlyConfirmedPosts;
    }

    public Map<YearMonth, Integer> getMonthlyLeague() {
        Map<YearMonth, Integer> monthlySavedPosts = new HashMap<>();
        try {
            String sql = "SELECT MONTH(CreateAt) AS Month, YEAR(CreateAt) AS Year, COUNT(*) AS TotalLeague " + "FROM League GROUP BY MONTH(CreateAt), YEAR(CreateAt)";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int month = rs.getInt("Month");
                int year = rs.getInt("Year");
                int totalPosts = rs.getInt("TotalLeague");
                YearMonth yearMonth = YearMonth.of(year, month);
                monthlySavedPosts.put(yearMonth, totalPosts);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return monthlySavedPosts;
    }
}
