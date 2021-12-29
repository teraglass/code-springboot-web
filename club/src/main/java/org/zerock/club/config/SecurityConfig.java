package org.zerock.club.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        // bcrypt 해시 함수를 이용해서 패스워드 암호화. 복호화 불가능, 암호화된 값은 길이는 같으나 매번 다름
        // 특정 문자열이 암호화된 결과인지만을 확인할 수 있기 때문에 원본 내용을 볼 수 없으므로 최근에 많이 사용
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/sample/all").permitAll()
                .antMatchers("/sample/member").hasRole("USER");
        http.formLogin(); //인가,인증에 문제시 로그인 화면
        // loginPage(), loginProcessUrl(), defaultSuccessUrl(), failure() 등으로 별도의 디자인 지정
        // 대부분 loginPage()를 이용해서 별도의 로그인 페이지를 이용
        http.csrf().disable(); // csrf 토큰 발행하지 않도록 설정
        http.logout(); // CSRF 토큰을 사용할 때는 POST 방식으로만 로그아웃 처리
        // 로그아웃 관련 설정 : logoutUrl(), logoutSuccessUrl()
        // 스프링 시큐리티는 기본적으로 HttpSession을 이용하는데 invalidateHttpSession()과 deleteCookies()를 이용해서 쿠키나 세션을 무효화 시킬 수 있도록 설정할 수 있음

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

        //사용자 계정은 user1
        auth.inMemoryAuthentication().withUser("user1")
                //1111 패스워드 인코딩 결과
                .password("$2a$10$g7eUIyzZEr6Ya.WVCNNFe.tNDaDRNXVh0/1J5mx1GnU6asE2J7g5S")
                .roles("USER");
        
    }
}
