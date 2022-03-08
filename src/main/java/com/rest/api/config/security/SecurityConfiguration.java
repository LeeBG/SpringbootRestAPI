package com.rest.api.config.security;

import com.rest.api.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// annotation으로 리소스 접근 권한 설정

/** 컨트롤러에 어노테이션으로 권한을 설정할 수 있다.
 * // @preAuthorize
 * - 표현식 사용 가능
 * ex) @PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_ADMIN')")
 *
 * // @Secured
 * - 표현식 사용 불가능
 * ex) @Secured({"ROLE_USER","ROLE_ADMIN"})
 */
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .httpBasic().disable()// rest api이므로 기본설정 사용안함. 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 된다.
                .csrf().disable() //rest api이므로 csrf보안이 필요 없으므로 disable
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                    .authorizeRequests()
//                        .antMatchers("/*/signin", "/*/signin/**","/*/signup","/*/signup/**","/social/**").permitAll() // 가입 및 인증(로그인) 주소는 누구나 접근 가능
//                        .antMatchers(HttpMethod.GET,"/exception/**","helloworld/**","/actuator/health","/v1/board/**","/favicon.ico").permitAll() // helloworld/ 이후 시작 Get요청은 모두 접근가능
//                        .antMatchers("/*/users").hasRole("Admin")
//                        .anyRequest().hasRole("USER")
                .and()
                    .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                    .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                    .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
                    //jwt token필ㅇ터를 id/password 인증 필터 전에 넣는다.
    }

    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**",
                "/swagger-ui.html", "/webjars/**", "/swagger/**");
    }
}
