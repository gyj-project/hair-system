package cn.bzu.hair.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * @description (captcha)表实体类
 * @author 高玉津
 * @date 2020-04-16 13:27:41
 */
 
@TableName(value = "captcha")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Captcha {
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
     手机号
     */
    @TableField(value = "phone")
    private String phone;
    /**
      验证码 
    */
    @TableField(value = "code")
    private String code;
    
    /**
      发送时间 
    */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "date")
    private Date date;
    
    /**
      用途 1注册，2登录，3找回密码 
    */
    @TableField(value = "type")
    private String type;
    
}