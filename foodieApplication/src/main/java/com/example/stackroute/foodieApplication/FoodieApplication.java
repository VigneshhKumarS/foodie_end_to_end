package com.example.stackroute.foodieApplication;

import com.example.stackroute.foodieApplication.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class FoodieApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodieApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean filterRegistrationBean(){
		FilterRegistrationBean frb = new FilterRegistrationBean();
		frb.setFilter(new JwtFilter());
		frb.addUrlPatterns("/restaurant-app/add-restaurant","/restaurant-app/delete-restaurant",
				"/restaurant-app/edit-restaurant","/restaurant-app/get-restaurant-by-owner",
				"/restaurant-app/add-items-to-cart","/user-app/get-user-byID",
				"/user-app/add-favourite-restaurant","/restaurant-app/remove-item-from-cart",
				"/restaurant-app/add-quantity",
				"/restaurant-app/place-order","/feedback/add-feedback","/restaurant-app/remove-restaurant-from-fav-list");
		return frb;
	}

	@Bean
	public FilterRegistrationBean registrationBean(){
		final CorsConfiguration corsConfiguration=new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.addAllowedOrigin("http://localhost:4200");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		final UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**",corsConfiguration);
		FilterRegistrationBean frb=new FilterRegistrationBean(new CorsFilter(source));
		frb.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return frb;
	}

}
