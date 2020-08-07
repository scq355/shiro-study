package com.wowjoy.boot.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author scq
 * @date 2020-08-07 12:42:00
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    private Integer id;
    private String name;
    //权限集合
    private List<Perms> perms;
}
