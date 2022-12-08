package com.example.aviatales2.dto.converters;

import com.example.aviatales2.dto.SignUpDto;
import jakarta.servlet.http.HttpServletRequest;

public class HttpFormsConverter {
    public static SignUpDto from(HttpServletRequest request) {
        return SignUpDto.builder()
                .name(request.getParameter("username"))
                .email(request.getParameter("e-mail"))
                .password(request.getParameter("password"))
                .build();
    }
}
