package com.openthinks.demo.sprj.web.config;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter.XFrameOptionsMode;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.openthinks.demo.sprj.core.AuthRole;
import com.openthinks.demo.sprj.core.model.SystemRole;
import com.openthinks.demo.sprj.core.model.SystemUser;
import com.openthinks.demo.sprj.core.service.SystemUserService;

@EnableWebSecurity
@Configurable
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private SystemUserService systemUserService;

  private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected UserDetailsService userDetailsService() {
    return new LogserUserDetailsService();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
  }

  @Bean(name = "authenticationManager")
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    LOGGER.info("Security config configuring.....");
    // @formatter:off
		http.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsMode.SAMEORIGIN));
		http.authorizeRequests()
				.antMatchers("/public/**","/css/**", "/img/**", "/lib/**", "/js/**", "/fonts/**", "../fonts/**", "/favicon.ico")
				.permitAll().antMatchers("/admin/**").hasRole(AuthRole.ROLE_ADMIN.shortName()).anyRequest()
				.authenticated().and().formLogin()
				// .loginPage("/login")
				.usernameParameter("userName").passwordParameter("userPass").permitAll().and().logout()
				.logoutUrl("/logout").logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")).permitAll()
				.invalidateHttpSession(true).and().rememberMe().key("sprj-rem-me-key").rememberMeParameter("remember-me")
				.rememberMeCookieName("sprj-rem-me-ck").tokenValiditySeconds(86400);
		;
		// @formatter:on
  }

  class LogserUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      LOGGER.trace("in userdetailsservice impl Checking validity of user " + username);
      SystemUser systemUser = systemUserService.getSystemUser(username);
      if (systemUser == null) {
        throw new UsernameNotFoundException("Not found user name:" + username);
      }
      LOGGER.trace("Found user: " + systemUser + " : " + systemUser.getUserPass());
      List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
      List<SystemRole> roles = systemUserService.listSystemRolesByUser(systemUser.getId());
      roles.forEach(role -> {
        grantedAuthorityList.add(new SimpleGrantedAuthority(role.getRoleName()));
      });
      UserDetails userDetails =
          new User(systemUser.getUserName(), systemUser.getUserPass(), grantedAuthorityList);
      return userDetails;
    }

  }
}
