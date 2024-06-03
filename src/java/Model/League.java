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
public class League {
    private int Id;
    private String Name;
    private String Description;
    private String StartDate;
    private String EndDate;
    private String Address;
    private int TeamSize;
    private String CreateAt;
    private String UpdateAt;
    private String DeleteAt;
    private String Image;   
    private String Type;
    private int UserID;
}
