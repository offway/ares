package cn.offway.ares.config;

import cn.offway.ares.properties.QiniuProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(QiniuProperties.class)
public class QiniuConfig {

}
