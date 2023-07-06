package com.example.miniProject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class LogoutController {
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) {
        // Xóa session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Chuyển hướng người dùng về trang chủ hoặc trang đăng nhập
        return new ModelAndView("redirect:http://127.0.0.1:5500/homepage.html");
    }
}

