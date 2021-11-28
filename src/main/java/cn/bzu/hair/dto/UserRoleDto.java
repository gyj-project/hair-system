package cn.bzu.hair.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;
import java.util.Date;

@Data
public class UserRoleDto {
    private Long userId;
    private Long roleId;
    private String userName;
    private String roleName;
    private String userPassword;
    private String gender;
    private String phone;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date birthday;
    private int isDeleted;
}
