package com.virtual.teacher.configs;

import com.virtual.teacher.services.functions.YoutubeCodeExtractorImpl;
import com.virtual.teacher.services.functions.interfaces.YoutubeCodeExtractor;
import com.virtual.teacher.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@PropertySource("application.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

//    private String dbUrl, dbUsername, dbPassword;
//
//    public SecurityConfig(Environment env) {
//        dbUrl = env.getProperty("database.url");
//        dbUsername = env.getProperty("database.username");
//        dbPassword = env.getProperty("database.password");
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.jdbcAuthentication()
//                .dataSource(securityDataSource());
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login").anonymous()
                .antMatchers("/register").anonymous()
                .antMatchers("/addLecture").hasRole("TEACHER")
                .antMatchers("/createCourse").hasRole("TEACHER")
                .antMatchers("/editCourse").hasRole("TEACHER")
                .antMatchers("/editCourse/**").hasRole("TEACHER")
                .antMatchers("/courses/**/videoLecture/**").hasAnyRole("STUDENT", "TEACHER")
                .antMatchers("/adminCourses").hasRole("ADMIN")
                .antMatchers("/adminStudents").hasRole("ADMIN")
                .antMatchers("/adminTeachers").hasRole("ADMIN")
                .antMatchers("/adminAdmins").hasRole("ADMIN")
                .antMatchers("/approve").hasRole("ADMIN")
                .antMatchers("/approveTeacherPromotion/**").hasRole("ADMIN")
                .antMatchers("/declineTeacherPromotion/**").hasRole("ADMIN")
                .antMatchers("/deleteStudent/**").hasRole("ADMIN")
                .antMatchers("/restoreStudent/**").hasRole("ADMIN")
                .antMatchers("/promoteStudent/**").hasRole("ADMIN")
                .antMatchers("/promoteStudentAdmin/**").hasRole("ADMIN")
                .antMatchers("/deleteTeacher/**").hasRole("ADMIN")
                .antMatchers("/restoreTeacher/**").hasRole("ADMIN")
                .antMatchers("/demoteTeacher/**").hasRole("ADMIN")
                .antMatchers("/promoteTeacher/**").hasRole("ADMIN")
                .antMatchers("/createAdmin").hasRole("ADMIN")
                .antMatchers("/delete/**").hasRole("ADMIN")
                .antMatchers("/myCoursesView").hasAnyRole("STUDENT", "TEACHER")
                .antMatchers("/videoLecture").hasAnyRole("STUDENT", "TEACHER")
                .antMatchers("/edit").hasAnyRole("STUDENT", "TEACHER", "ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();

    }

//    @Bean
//    public DataSource securityDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl(dbUrl);
//        dataSource.setUsername(dbUsername);
//        dataSource.setPassword(dbPassword);
//
//        return dataSource;
//    }
//
//    @Bean
//    public UserDetailsManager userDetailsManager() {
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
//        jdbcUserDetailsManager.setDataSource(securityDataSource());
//        return jdbcUserDetailsManager;
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public YoutubeCodeExtractor youtubeCodeExtractor() {
        return new YoutubeCodeExtractorImpl();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

}