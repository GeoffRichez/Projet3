package fr.groupe1.goevent.configuration;

import javax.sql.DataSource; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import fr.groupe1.goevent.GoeventApplication;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;

	@Value("${spring.queries.users-query}")
	private String usersQuery;

	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) // authentification
			throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http	.authorizeRequests()
				.antMatchers("/","/index","/login","/createAccount","/allFuturEvents","/event/detailsOfAnEvent/{id}","/resetPassword","/resetPassword2").permitAll()
				.antMatchers("/admin/**").hasAuthority(GoeventApplication.ADMIN)
				.antMatchers("/participant/**").hasAuthority(GoeventApplication.PARTICIPANT)
				.antMatchers("/dashboardOrganisateur/**").hasAuthority(GoeventApplication.ORGANIZER)
				.antMatchers("/prestataire/**").hasAuthority(GoeventApplication.PROVIDER)

				.anyRequest().authenticated().and().csrf().disable().formLogin() // l'accès de fait via un formulaire

				.loginPage("/login").failureUrl("/login?error=true") // fixer la page login

				.defaultSuccessUrl("/index") // page d'accueil après login avec succès
				.usernameParameter("email") // paramètres d'authentifications login et password
				.passwordParameter("password")
				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login").and().exceptionHandling()
				.accessDeniedPage("/403");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**","/assets/**","/contactform/**","/css/**","/img/**","/js/**","/lib/**", "/uploads/**");
	}
}
