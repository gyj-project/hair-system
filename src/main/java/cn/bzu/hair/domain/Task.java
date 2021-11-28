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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Date;

/**
 * @description (task)表实体类
 * @author 高玉津
 * @date 2020-04-25 16:50:10
 */
 
@TableName(value = "task")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    /**
     id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
      顾客id 取自user表id 
    */
    @TableField(value = "customer_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long customerId;
    
    /**
      理发师id 取自user表id 
    */
    @TableField(value = "barber_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long barberId;
    
    /**
      业务id 
    */
    @TableField(value = "business_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long businessId;
    
    /**
      预计花费时间 取自business表business_time 
    */
    @TableField(value = "task_spend_time")
    private String taskSpendTime;
    
    /**
      服务开始时间（预约时间） 
    */
    @TableField(value = "task_start")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date taskStart;
    
    /**
      服务结束时间 (预约时间+花费时间） 
    */
    @TableField(value = "task_end")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date taskEnd;
    
    /**
      是否已提醒  0未， 1已
    */
    @TableField(value = "is_remind")
    private Integer isRemind;
    
    /**
      删除标识 0未删除，1已删除 
    */
    @TableField(value = "is_deleted")
    private Integer isDeleted;
    
    /**
      创建时间 
    */
    @TableField(value = "created_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createdTime;
    
}