package fr.cegos.filmapi.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
/**
 * @EnableMethodSecurity permet d'avoir la configuration des autorisations
 * sur les méthodes des controller directements
 *
 * ex :
 *     @RolesAllowed("admin")
 *     @PostMapping
 *     public Film postFilm(@Valid @RequestBody Film film)
 *
 *     @PreAuthorize("isAuthenticated()")
 *     @PostMapping
 *     public void postAvis(@PathVariable Long filmId, @Valid  @RequestBody Avis avis)
 *
  */


@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {
    private JwtFilter jwtFilter;

    /**
     * J'ajoute à mon contexte Spring un @Bean de type SecurityFilterChain
     * Spring Security va inspecter le Spring et si il trouve un bean de ce type
     * il va l'utiliser pour sa configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 1 - suppression de la vérification csrf pour les API stateless (pas de sessions)
        http.csrf(AbstractHttpConfigurer::disable);
        /**
         * Pas de configuration d'autorisation ici car on passe
         * par des annotation sur les méthodes (method-level security)
         */
       http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * Rappel : Spring Security cherche dans le contexte Spring un @Bean
     * de type qui implémente UserDetailService
     * On définit un bean pour la gestion des utilisateurs en mémoire
     * => C'est le cas de la classe InMemoryUserDetailsManager qui est fourne par Spring Security
      */
    /* @Bean : on enlève pour ne pas créer de conflit avec mon service : ServicePersonaliseAuthentification
    public InMemoryUserDetailsManager userDetailsService() {
        // 1 - On défini une liste d'Utilisateurs Spring Security (UserDetails)
        List<UserDetails> userDetailsList = new ArrayList<>();

        // utilisateur : membre (mdp : membre123), qui a le role "user"
        userDetailsList.add(
                User.withUsername("membre").
                // IMPORTANT : Spring impose d'encoder les mots de passe de nos utilisateurs avec l'enodeur défini dans notre contexte Spring
                password(passwordEncoder().encode("membre123"))
                .roles("user").build());

        // utilisateur : admin (mdp : admin123), qui a les roles "user" et "admin"
        userDetailsList.add(
                User.withUsername("admin").
                // IMPORTANT : Spring impose d'encoder les mots de passe de nos utilisateurs avec l'enodeur défini dans notre contexte Spring
                password(passwordEncoder().encode("admin123"))
                .roles("admin", "user").build());

        // 2 - on retourne cette liste qui sera utilisée par Spring Security pour effectuer l'authentification
        return new InMemoryUserDetailsManager(userDetailsList);
    }*/
}