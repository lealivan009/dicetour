package dicetour.config;


import dicetour.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter{

    @Autowired
    private CuentaService cuentaService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(cuentaService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                //PAGINAS A LAS QUE SOLO PUEDE ACCEDER EL USUARIO QUE TIENE ROL DE ADMINISTRADOR
                .antMatchers("/colectivo/crudHorarios/**",
                        "/colectivo/crudHorarios/**",
                        "/persona/crudPersonas/**",
                        "/rol/listaRoles/**",
                        "/referencia/listaReferencias/**",
                        "/tipoColectivo/listaTiposColectivos/**")
                .hasAuthority("ROLE_ADMIN")

                //PAGINAS A LAS QUE SOLO PUEDE ACCEDER EL USUARIO QUE TIENE ROL DE CARGA
                .antMatchers("/colectivo/crudHorarios/**",
                        "/colectivo/crudHorarios/**",
                        "/referencia/listaReferencias/**",
                        "/tipoColectivo/listaTiposColectivos/**")
                .hasAuthority("ROLE_CARGA")

                //TODOS PUEDEN ACCEDER A ESTAS PAGINAS
                .antMatchers("/persona/signup/**",
                        "/persona/guardarPersona/**",
                        "/cuenta/**",
                        "/",
                        "/colectivo/**",
                        "/js/**",
                        "/css/**",
                        "/img/**").permitAll()
                .anyRequest().authenticated()
                .and()

                //LOGIN
                .formLogin()
                .loginPage("/cuenta/signin")
                .permitAll()
                .and()

                //LOGOUT
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/cuenta/logout"))  //url para cerrar sesion
                .logoutSuccessUrl("/cuenta/signin") //pagina a la q te redirige luego de cerrar sesion
                .permitAll();
    }
}
