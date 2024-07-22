/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

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
public class League {
    private int Id;
    private int Status;
    private String Name;
    private String Description;
    private LocalDateTime StartDate;
    private LocalDateTime EndDate;
    private String Address;
    private String DateRegister;
    private int TeamSize;
    private String CreateAt;
    private String UpdateAt;
    private String DeleteAt;
    private String Image;
    private String Type;
    private int UserId;
    private int TeamMemberSize;
}
