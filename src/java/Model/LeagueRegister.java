/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author ADMIN
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LeagueRegister {
    private int TeamId;
    private String TeamName;
    private String RegisterAt;
    private String Image;
    private int Point;
}
