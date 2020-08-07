package com.wowjoy.boot.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author scq
 * @date 2020-08-07 11:02:00
 */
@Data
@Accessors(chain = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String salt;

    private List<Role> roles;
}
