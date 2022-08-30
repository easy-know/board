package com.paul.board.controller.profile

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("profile")
class ProfileController {
    @GetMapping
    fun viewProfile(): String = "contents/profile"
}