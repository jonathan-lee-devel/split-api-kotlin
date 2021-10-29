package io.jonathanlee.splitapi.interceptor.auth

import org.springframework.http.HttpStatus
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
        if (SecurityContextHolder.getContext().authentication.principal == null || request.userPrincipal == null) {
            HttpStatus.UNAUTHORIZED.value().also { response.status = it }
            return false
        }

        return try {
            val userPrincipal = request.userPrincipal as JwtAuthenticationToken
            val credentials = userPrincipal.credentials as Jwt
            request.setAttribute(usernameAttribute, credentials.getClaimAsString(usernameClaim))
            true
        } catch (ex: Exception) {
            HttpStatus.UNAUTHORIZED.value().also { response.status = it }
            when (ex) {
                is NullPointerException -> false
                is ClassCastException -> false
                else -> throw ex
            }
        }
    }

    companion object {
        const val usernameClaim = "preferred_username"
        const val usernameAttribute = "username"
    }

}
