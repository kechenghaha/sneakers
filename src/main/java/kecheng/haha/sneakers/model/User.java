package kecheng.haha.sneakers.model;

import lombok.Data;

/**
 * by kecheng
 * 2020/06/09
 */
@Data
public class User {
    private Integer id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
