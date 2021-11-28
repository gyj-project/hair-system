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
 * @description (skill)表实体类
 * @author 高玉津
 * @date 2020-04-20 14:02:45
 */
 
@TableName(value = "skill")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Skill {
    /**
     id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
      理发师id，取自user表id 
    */
    @TableField(value = "barber_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long barberId;
    
    /**
      理发师name，取自user表name 
    */
    @TableField(value = "barber_name")
    private String barberName;
    
    /**
      业务id,取自业务表id 
    */
    @TableField(value = "business_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long businessId;
    
    /**
      业务名称，取自业务表name 
    */
    @TableField(value = "business_name")
    private String businessName;

    /**
     禁用标识
     */
    @TableField(value = "is_closed")
    private Integer isClosed;
    
}