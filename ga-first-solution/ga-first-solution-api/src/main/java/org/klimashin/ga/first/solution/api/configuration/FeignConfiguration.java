package org.klimashin.ga.first.solution.api.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "org.klimashin.ga.first.solution.api.resource")
public class FeignConfiguration {
}
