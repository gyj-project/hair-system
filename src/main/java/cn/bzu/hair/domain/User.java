package cn.bzu.hair.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @description (user)表实体类
 * @author 高玉津
 * @date 2020-04-14 21:07:55
 */
 
@TableName(value = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
      用户名 
    */
    @TableField(value = "user_name")
    private String userName;
    
    /**
      用户密码 
    */
    @TableField(value = "user_password")
    private String userPassword;
    
    /**
      用户性别  “1” 男，“2” 女 
    */
    @TableField(value = "gender")
    private String gender;
    
    /**
      用户手机 
    */
    @TableField(value = "phone")
    private String phone;
    
    /**
      用户邮箱 
    */
    @TableField(value = "email")
    private String email;
    
    /**
      用户生日 
    */
    @TableField(value = "birthday")
    private Date birthday;
    
    /**
      删除标识，0未删除，1已删除 
    */
    @TableField(value = "is_deleted")
    private Integer isDeleted;
    
}