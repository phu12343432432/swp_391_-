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
 * @author ADMIN
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Rank {
    private int Id;
    private int TeamId;
    private int TotalScroing;
    private Date CreateAt;
    private Date UpdateAt;
    private Date DeleteAt;
}