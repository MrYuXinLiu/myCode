package com.example.mybatis_plus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *
 * @Date: 2019/2/22 11:57
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * 这里的主键自增策略分别有:
     *      Auto 数据库自增类型
     *      None 该类型是未设置主键自增
     *      Input 用户输入Id
     *
     *
     *      ----------以下只有当插入对象的ID为空时才会调用------------
     *      Id_Worker 全局唯一id 整数类型的
     *
     *      UUID(4) 全局唯一Id
     *
     *      Id_Worker_str 全局唯一id 字符串类型的
     *
     *
     */
    @TableId(type = IdType.ID_WORKER)
    private Long id ;
    private String name;
    private int age ;
    private String email;


    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE )
    private Date updateTime;


    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;
}
