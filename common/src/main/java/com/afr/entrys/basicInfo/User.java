package com.afr.entrys.basicInfo;

import com.afr.entrys.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Yan liang
 * @create 2019/1/24
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class User extends BaseModel {
    private String Code;

    private String Name;
}