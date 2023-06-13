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
 * @author dongh
 */
@Builder
@Getter
@Setter
@ToString
public class Cart {
        private int cart_id;
        private int product_id;
        private String product_name;
        private int product_price;
        private int quantity;
        private int total_cost;
        private int user_id;              

}
