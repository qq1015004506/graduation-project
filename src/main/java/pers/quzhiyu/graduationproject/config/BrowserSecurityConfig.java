package pers.quzhiyu.graduationproject.config;


//@Configuration
//public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()
//                .loginPage("http://localhost:8080/login")
//                .loginProcessingUrl("/authentication/form")
//                .and()
//                .authorizeRequests()
//                .antMatchers("http://localhost:8080/login").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and().csrf().disable();
//    }
//}
public class BrowserSecurityConfig {

}