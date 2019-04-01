package com.afr.utils.stringhelper;

import java.util.List;

/**
 * @author Yan liang
 * @create 2019/3/29
 * @since 1.0.0
 */
public class StringHelper {

    public static String listToString(List list, String separator) {
        StringBuffer sb = new StringBuffer();
        if (list == null || list.size() == 0) {
            return "";
        }
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).toString());
            if (i != list.size() - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    public static String ToString(Object o) {
        return o == null ? "" : o.toString().isEmpty() ? "" : o.toString();
    }

    public static boolean IsNullOrEmpty(String str) {
        return str == null || str.isEmpty() ? true : false;
    }
}