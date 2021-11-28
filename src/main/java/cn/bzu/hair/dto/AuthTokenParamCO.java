package cn.bzu.hair.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthTokenParamCO implements Serializable {

    private String client_id;
    private String grant_type;
    private String client_secret;
    private String username;
    private String password;

}
