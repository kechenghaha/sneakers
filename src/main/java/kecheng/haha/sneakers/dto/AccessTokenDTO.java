package kecheng.haha.sneakers.dto;

import lombok.Data;

/**
 * by kecheng
 * 2020/06/09
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
