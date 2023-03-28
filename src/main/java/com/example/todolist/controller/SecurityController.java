package com.example.todolist.controller;

import com.example.todolist.security.UserPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class SecurityController {

    @GetMapping("/userID")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseBody
    public Integer currentUserID(UserPrincipal principal) {
        return principal.getId();
    }

}
