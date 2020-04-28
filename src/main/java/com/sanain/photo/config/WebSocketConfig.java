package com.sanain.photo.config;

import com.sanain.photo.controller.ChatController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Author sanain
 */
@Configuration
public class WebSocketConfig {

    /**
     * 初始化Bean，它会自动注册使用了 @ServerEndpoint 注解声明的 WebSocket endpoint
     * @return
     */
    @Bean
    public ServerEndpointExporter getServerEndpoint(){
        ServerEndpointExporter exporter = new ServerEndpointExporter();
        exporter.setAnnotatedEndpointClasses(ChatController.class);
        return exporter;
    }
}
