package com.luke.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (UserRole)实体类
 *
 * @author makejava
 * @since 2020-04-21 19:41:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole implements Serializable {
    private static final long serialVersionUID = -91271548996876386L;
    
    private Integer id;
    
    private Integer uid;
    
    private Integer rid;




}