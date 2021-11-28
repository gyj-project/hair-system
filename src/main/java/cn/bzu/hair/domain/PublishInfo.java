package cn.bzu.hair.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @description (publish_info)表实体类
 * @author 高玉津
 * @date 2020-04-29 00:18:44
 */
 
@TableName(value = "publish_info")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublishInfo  {

    /**
     id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
      店内公告标题 
    */
    @TableField(value = "title")
    private String title;
    
    /**
      店内公告信息 
    */
    @TableField(value = "info")
    private String info;
    
    /**
      公告发布时间 
    */
    @TableField(value = "publish_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date publishTime;
    
    /**
      店内公告图片 
    */
    @TableField(value = "picture")
    private String picture;
    
    /**
      删除标识 0未删除，1已删除 
    */
    @TableField(value = "is_deleted")
    private Integer isDeleted;
    
}