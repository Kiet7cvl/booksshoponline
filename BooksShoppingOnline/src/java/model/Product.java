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
public class Product {

    private int id;
    private String name;
    private int original_price;
    private int sale_price;
    private String brief_infor;
    private String desciption;
    private boolean status;
    private int quantity;
    private String image;
    private Date update_date;
    private int category_id;
    private double rated_star;
    private String author;
}

