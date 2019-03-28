package com.afr.entrys;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * @author Yan liang
 * @create 2019/1/24
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class BaseModel {
    private long RowId;

    private String Creater;

    private Date CreateDate;
}