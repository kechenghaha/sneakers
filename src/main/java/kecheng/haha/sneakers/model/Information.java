package kecheng.haha.sneakers.model;

import lombok.Data;

/**
 * by kecheng
 * 2020/06/09
 */
@Data
public class Information {
    private Long id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer browseCount;
    private Integer likeCount;
    private String tag;
}
