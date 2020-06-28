package kecheng.haha.sneakers.dto;

import kecheng.haha.sneakers.model.User;
import lombok.Data;

/**
 * by kecheng
 * 2020/06/09
 */
@Data
public class InformationDTO {
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
    private User user;
}
