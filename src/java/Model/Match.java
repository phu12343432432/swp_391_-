/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ADMIN
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Match {
    private int Id;
    private int HomeTeamId;   
    private int AwayTeamId;
    private int LeaugeId;
    private String Name;
    private String Address;
    private LocalDateTime StartAt;
    private LocalDateTime EndAt;
    private int TeamSize;
    private LocalDateTime UpdateAt; 
    private int ScoreHome;   
    private int ScoreAway;
    private int GroupId;
}
