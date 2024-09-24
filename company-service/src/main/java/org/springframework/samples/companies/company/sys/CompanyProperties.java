package org.springframework.samples.companies.company.sys;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "company")
public class CompanyProperties {

    private Cache cache;

    @Data
    public static class Cache {

        private int ttl;

        private int heapSize;
    }
}
