package io.jonathanlee.splitapi.interceptor.auth

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthorizationInterceptor : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val principal = SecurityContextHolder.getContext().authentication.principal
        val userPrincipal = request.userPrincipal as JwtAuthenticationToken
        val credentials = userPrincipal.credentials as Jwt
        request.setAttribute(usernameAttribute, credentials.getClaimAsString(usernameClaim))
        if (principal != null && principal !is String) {
            val jwt = principal as Jwt
        }

        return true
    }

    companion object {
        const val rolesAttribute = "roles"
        const val usernameClaim = "preferred_username"
        const val usernameAttribute = "username"
    }

}
