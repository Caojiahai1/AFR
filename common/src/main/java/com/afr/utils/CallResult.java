package com.afr.utils;

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
public class CallResult {
    private boolean success;

    private String message;

    private Object object;
}