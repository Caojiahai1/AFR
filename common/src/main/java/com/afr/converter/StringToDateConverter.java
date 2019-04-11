package com.afr.converter;

import com.afr.utils.DateUtil;
import com.afr.utils.MyLogger;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Yan liang
 * @create 2019/1/23
 * @since 1.0.0
 */
public class StringToDateConverter implements Converter<String, Date>{
    @Override
    public Date convert(String s) {
        SimpleDateFormat sf = DateUtil.getDateFormat();
        try {
            Date d = sf.parse(s);
            return d;
        } catch (ParseException e) {
            MyLogger.logger.error(e.getMessage());
        }
        return null;
    }
}