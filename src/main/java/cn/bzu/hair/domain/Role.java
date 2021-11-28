package cn.bzu.hair.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
/**
 * @description (role)表实体类
 * @author 高玉津
 * @date 2020-04-14 14:49:26
 */
 
@TableName(value = "role")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    /**
     id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
      父级角色 
    */
    @TableField(value = "parent_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;
    
    /**
      角色中文名称 
    */
    @TableField(value = "name")
    private String name;
    
    /**
      角色英文名称 
    */
    @TableField(value = "enname")
    private String enname;
    
    /**
      角色描述 
    */
    @TableField(value = "description")
    private String description;
    
    /**
      删除标识，0未删除，1已删除 
    */
    @TableField(value = "is_deleted")
    private Integer isDeleted;
    
}