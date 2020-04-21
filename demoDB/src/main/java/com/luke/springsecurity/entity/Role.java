package com.luke.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Role)实体类
 *
 * @author makejava
 * @since 2020-04-21 19:39:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    private static final long serialVersionUID = -10717875048144045L;
    
    private Integer id;
    
    private String name;
    
    private String nameZh;



}