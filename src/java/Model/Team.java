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
public class Team {
    private int Id;
    private String Name;
    private String ShortName;    
    private String Description;
    private int TeamSize;
    private String Image;
    private Date CreateAt;
    private Date UpdateAt;
    private Date DeleteAt;
    private boolean IsActive;
    private int UserId;
}
