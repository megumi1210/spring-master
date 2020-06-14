package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;



@Configuration
@ComponentScan("com.example.*")
@EnableAspectJAutoProxy
@Import({TaskExecutorConfig.class,TaskScheduledConfig.class})
public class SpringConfig {


}
