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
 * @description (orderr)表实体类
 * @author 高玉津
 * @date 2020-05-22 23:33:50
 */
 
@TableName(value = "orderr")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orderr {
    /**
     id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "goods_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long goodsId;
    
    @TableField(value = "customer_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long customerId;
    
    @TableField(value = "goods_name")
    private String goodsName;
    
    @TableField(value = "customer_name")
    private String customerName;

    @TableField(value = "is_pay")
    private Integer isPay;
    
}