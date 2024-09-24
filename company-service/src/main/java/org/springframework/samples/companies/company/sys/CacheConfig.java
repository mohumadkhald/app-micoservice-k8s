package org.springframework.samples.companies.company.sys;


import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Cache could be disable in unit test.
 * @author Maciej Szarlinski
 */
@Configuration
@EnableCaching
@Profile("production")
class CacheConfig {
}
