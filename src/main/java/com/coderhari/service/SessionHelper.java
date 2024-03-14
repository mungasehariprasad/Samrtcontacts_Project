package com.coderhari.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Component
@Service
public class SessionHelper {

    public void removeMessageFromSession() {
        try {

            HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
                    .getSession();
            session.removeAttribute("message");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
