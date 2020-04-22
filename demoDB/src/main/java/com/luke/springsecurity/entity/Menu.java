package com.luke.springsecurity.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * (Menu)实体类
 *
 * @author makejava
 * @since 2020-04-22 20:28:26
 */
@Data
public class Menu implements Serializable {
    private static final long serialVersionUID = 592434213599106027L;
    
    private Integer id;
    
    private String name;
    
    private String path;

    private List<Role> roles;

}