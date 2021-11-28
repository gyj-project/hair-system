package cn.bzu.hair.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthTokenDto implements Serializable {

    private String access_token;
    private String token_type;
    private String refresh_token;
    private String expires_in;
    private String scope;

}
