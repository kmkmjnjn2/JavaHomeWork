package com.ebanma.cloud.config;

/**
 * @author WHY
 * @version $ Id: NettyConfig, v 0.1 2023/04/21 15:10 kmkmj Exp $
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "netty")
@Data
public class NettyConfig {
    private int port;//netty监听的端口
    private String path;//websocket访问路径
}