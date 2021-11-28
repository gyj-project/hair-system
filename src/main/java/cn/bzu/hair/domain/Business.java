package cn.bzu.hair.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
/**
 * @description (business)表实体类
 * @author 高玉津
 * @date 2020-04-18 15:40:12
 */
 
@TableName(value = "business")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Business {

    /**
     id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
      业务大类 
    */
    @TableField(value = "business_type")
    private String businessType;
    
    /**
      服务名称 
    */
    @TableField(value = "business_name")
    private String businessName;
    
    /**
      服务所需花费价格 
    */
    @TableField(value = "business_price")
    private Double businessPrice;
    
    /**
      服务所需花费时间(小时计) 
    */
    @TableField(value = "business_time")
    private String businessTime;
    
    /**
      删除标识 
    */
    @TableField(value = "is_deleted")
    private Integer isDeleted;
    
}