package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    /** admin権限専用画面に遷移 */
    @GetMapping("/admin")
    public String getAdmin() {
        return "admin/admin";
    }
}
