package br.com.muralis.gabriel.henrique.rinhabackend.outbound.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"br.com.muralis.gabriel.henrique.rinhabackend.outbound.postgres"})
public class PostgresConfiguration {
}
