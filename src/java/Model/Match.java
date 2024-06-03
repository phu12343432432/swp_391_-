/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Datnt
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    private int Id;
    private int HomeTeamId;   
    private int AwayTeamId;
    private int LeaugeId;
    private String Name;
    private String Address;
    private Date StartAt;
    private Date EndAt;
    private int TeamSize;
    private Date UpdateAt;
}
