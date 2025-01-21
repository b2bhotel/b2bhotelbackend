package com.b2b.hotel.in.configuration;

import com.b2b.hotel.in.interceptor.AgentIdSetInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private final AgentIdSetInterceptor agentIdSetInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(agentIdSetInterceptor)
                .addPathPatterns("/hotels/login");
    }

}

