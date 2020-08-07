package com.wowjoy.boot.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author scq
 * @date 2020-08-07 12:42:00
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Perms implements Serializable {
    private Integer id;
    private String name;
    private String url;
}
