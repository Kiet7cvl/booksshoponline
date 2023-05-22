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
 * @author ADMIN
 */
    @Builder
    @Getter
    @Setter
    @ToString

    public class Slider {

        private int id;
        private String slider_title;
        private String slider_image;
        private String backlink;
        private String note;
        private boolean status;
        private int updated_by;

    }
