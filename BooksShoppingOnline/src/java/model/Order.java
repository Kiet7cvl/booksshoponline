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
 * @author dongh
 */
@Builder
@Getter
@Setter
@ToString
public class Order {
    int orderID;
    Date date;
    int total_cost;
    String fullName;
    String mobile;
    String address;
    int status_order;
    int UserId;
    int saler_id;
    String note;
    int countProduct;
    String fullNameFirstProduct;
    String status_order_name;
    String fullNameSaler;
}
