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
    public int Id;
    public String Name;
    public String ShortName;    
    public String Description;
    public int TeamSize;
    public byte[] Image;
    public Date CreateAt;
    public Date UpdateAt;
    public Date DeleteAt;
    public boolean IsActive;
    public int UserId;
}
