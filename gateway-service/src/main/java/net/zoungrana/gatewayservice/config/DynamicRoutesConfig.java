package net.zoungrana.gatewayservice.config;

import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamicRoutesConfig {
    @Bean
    DiscoveryClientRouteDefinitionLocator routes(ReactiveDiscoveryClient discoveryClient, DiscoveryLocatorProperties locatorProperties){
        return new DiscoveryClientRouteDefinitionLocator(discoveryClient,locatorProperties);
    }
}
