package com.afr.converter;

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
    private String datePattern;

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    @Override
    public Date convert(String s) {
        SimpleDateFormat sf = new SimpleDateFormat(this.datePattern);
        try {
            Date d = sf.parse(s);
            return d;
        } catch (ParseException e) {
            MyLogger.logger.error(e.getMessage());
        }
        return null;
    }
}