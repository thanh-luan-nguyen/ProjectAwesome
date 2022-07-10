package com.example.application.service;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationService {

    @Autowired
    /* lấy từ mấy file .properties từ i18n */
    private MessageSource messageSource;

    /** 性別のMapを生成する */
    public Map<String, Integer> getGenderMap(Locale locale) {
        Map<String, Integer> genderMap = new LinkedHashMap<>();
        String male = messageSource.getMessage("male", null, locale); /* trong đây chứa text tiếng anh hoặc nhật */
        String female = messageSource.getMessage("female", null, locale); /* tương tự như trên */
        genderMap.put(male, 1);
        genderMap.put(female, 2);
        return genderMap;
    }
}
