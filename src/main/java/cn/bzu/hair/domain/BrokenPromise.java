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
 * @description (broken_promise)表实体类
 * @author 高玉津
 * @date 2020-04-27 14:09:59
 */
 
@TableName(value = "broken_promise")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrokenPromise {

    /**
     id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
      顾客id（取自user表id） 
    */
    @TableField(value = "customer_id")
    private Long customerId;
    
    /**
      失信次数 默认为0 
    */
    @TableField(value = "broken_number")
    private Integer brokenNumber;

    /**
     顾客姓名
     */
    @TableField(exist = false)
    private String customerName;
    
}