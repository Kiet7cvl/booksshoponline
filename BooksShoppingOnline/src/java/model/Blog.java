/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ADMIN
 */
@Builder
@Getter
@Setter
@ToString
public class Blog {
    private int blog_id;
    private String title;
    private int author_id;
    private Date updated_date;
    private String content;
    private String thumbnail;
    private String brief_infor;
    private int categoryBlog_id;
    private boolean status;
}
