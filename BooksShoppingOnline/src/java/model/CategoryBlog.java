/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ASUS
 */
@Builder
@Getter
@Setter
@ToString
public class CategoryBlog {
    private int categoryBlog_id;
    private String categoryBlog_name;
}

