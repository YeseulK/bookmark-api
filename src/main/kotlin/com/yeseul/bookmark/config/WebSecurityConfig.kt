package com.yeseul.bookmark.config

import com.yeseul.bookmark.security.JwtFilter
import com.yeseul.bookmark.security.UserDetailsServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class WebSecurityConfig(
    private val userDetailsService: UserDetailsServiceImpl,
    private val jwtFilter: JwtFilter
): WebSecurityConfigurerAdapter() {

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/static/css/**, /static/js/**, *.ico")
        // swagger
        web.ignoring().antMatchers(
            "/v2/api-docs", "/configuration/ui", "/swagger-resources",
            "/configuration/security", "/swagger-ui.html", "/webjars/**","/swagger/**"
        )
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()

        http.authorizeRequests()
            .antMatchers("/", "/v1/users/signup", "/v1/users/login", "/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs").permitAll()
            .anyRequest().authenticated()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling().authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(passwordEncoder())
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }
}