package com.paul.board.controller.sign

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class SignController {
    @GetMapping("sign-in")
    fun viewSignIn(): String = "contents/sign-in"

    @GetMapping("sign-up")
    fun viewSignUp(): String = "contents/sign-up"
}