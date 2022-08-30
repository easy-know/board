package com.paul.board.config.security

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.stereotype.Component
import java.net.URLEncoder
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAuthenticationFailureHandler : SimpleUrlAuthenticationFailureHandler() {
    override fun onAuthenticationFailure(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        exception: AuthenticationException?
    ) {
        val errorMessage =
            when {
                (exception is BadCredentialsException) -> "아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해 주세요."
                (exception is InternalAuthenticationServiceException) -> "내부적으로 발생한 시스템 문제로 인해 요청을 처리할 수 없습니다. 관리자에게 문의하세요."
                (exception is UsernameNotFoundException) -> "계정이 존재하지 않습니다. 회원가입 진행 후 로그인 해주세요."
                (exception is AuthenticationCredentialsNotFoundException) -> "인증 요청이 거부되었습니다. 관리자에게 문의하세요."
                else -> "알 수 없는 이유로 로그인에 실패하였습니다. 관리자에게 문의하세요."
            }

        setDefaultFailureUrl("/sign-in?error=true&exception=" + URLEncoder.encode(errorMessage, "UTF-8"))

        super.onAuthenticationFailure(request, response, exception)
    }
}