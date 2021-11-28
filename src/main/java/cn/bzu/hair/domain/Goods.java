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
 * @description (goods)表实体类
 * @author 高玉津
 * @date 2020-05-22 19:25:36
 */
 
@TableName(value = "goods")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Goods {
    /**
     id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    
    @TableField(value = "goods_name")
    private String goodsName;
    
    @TableField(value = "goods_price")
    private Double goodsPrice;
    
    @TableField(value = "goods_num")
    private Integer goodsNum;

    @TableField(value = "is_deleted")
    private Integer isDeleted;
    
}