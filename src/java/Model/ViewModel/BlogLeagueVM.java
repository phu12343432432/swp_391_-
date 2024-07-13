/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.ViewModel;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BlogLeagueVM {
     public int Id;
    public String Title;
    public String Description;
    public String Image;
    public String CreateAt;
    public String UpdateAt;
    public String DeleteAt;
    public int Status;
    public int LeagueId;
    public String LeagueName;
}

