package com.yeseul.bookmark.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtils(private val userDetailsService: UserDetailsServiceImpl) {

    val expireTime: Long = 1000L * 60 * 3
    val secretKey: String = "eyJhbGciOiJIUzI1NiJ9eyJzdWIiOiJKb2UifQ1KP0SsvENi7Uz1oQc07aXTL7kpQG5jBNIybqr60AlD4"
    val signatureAlgorithm: SignatureAlgorithm = SignatureAlgorithm.HS256

    // 토큰생성
    fun createToken(username: String): String {
        val claims: Claims = Jwts.claims()
        claims["username"] = username

        return Jwts.builder()
            .setClaims(claims)
            .setExpiration(Date(System.currentTimeMillis() + expireTime))
            .signWith(signatureAlgorithm, secretKey)
            .compact()
    }

    // 토큰검증
    fun validation(token: String): Boolean {
        val claims: Claims = getAllClaims(token)
        val exp: Date = claims.expiration
        return exp.after(Date())
    }

    fun parseUsername(token: String): String {
        val claims: Claims = getAllClaims(token)
        return claims["username"] as String
    }

    fun getAuthentication(username: String): Authentication {
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
    }

    private fun getAllClaims(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .body
    }
}
