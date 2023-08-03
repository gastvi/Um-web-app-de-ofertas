package br.com.mvc.mudi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests((authorize) -> authorize.requestMatchers("/Home/**").permitAll()
				.anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/usuario/pedido", true).permitAll())
				.logout(logout -> logout.logoutUrl("/logout")).logout().logoutSuccessUrl("/Home");
		return http.build();
	}

//  @Override
//	public void config(AuthenticationManagerBuilder auth) throws Exception {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//		UserDetails user = User.builder().username("a").password(encoder.encode("a")).roles("ADM").build();
//
//		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(encoder).withUser(user);
//
//	}
	
//	@Bean
//	UserDetailsManager users2(DataSource dataSource) {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//		UserDetails admin = User.builder()
//			.username("c")
//			.password(encoder.encode("c"))
//			.roles("USER")
//			.build();
//		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//		users.createUser(admin);
//		return users;
//	}

	@Bean
	public UserDetailsService users() {

		UserDetails user = User.withDefaultPasswordEncoder().username("a").password("a").roles("ADM").build();
		UserDetails user2 = User.withDefaultPasswordEncoder().username("b").password("b").roles("USER").build();
		UserDetails user3 = User.withDefaultPasswordEncoder().username("c").password("c").roles("USER").build();
		UserDetails user4 = User.withDefaultPasswordEncoder().username("gabriel").password("ga").roles("ADM").build();


		return new InMemoryUserDetailsManager(user, user2, user3, user4);
	}

}
