package com.afr.facerecognition.domain.detectface.attributes;

import com.afr.facerecognition.domain.detectface.enums.EthnicityEnum;
import com.afr.utils.stringhelper.StringHelper;

/**
 * @author Yan liang
 * @create 2019/4/5
 * @since 1.0.0
 */
public class Ethnicity {

    private String value;

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        String ethnicityEnumstr = EthnicityEnum.getEthnicityValue(value);
        this.value = StringHelper.IsNullOrEmpty(ethnicityEnumstr) ? value : ethnicityEnumstr;
    }
}