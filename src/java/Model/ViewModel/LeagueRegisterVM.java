/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

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
public class LeagueRegisterVM {

    private int TeamId;
    private String TeamName;
    private String ShortName;
    private String RegisterAt;
    private String Image;
    private int Point;
    private int Wins;
    private int Loses;
    private int Ties;
    private int TotalGoals;
    private int TotalCards;
    private int totalCardPoint;
}
