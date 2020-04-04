package com.sanain.photo.config;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

/**
 * @Author slearing
 */
public class CorsFilter extends Filter {
    @Override
    public void doFilter(HttpExchange httpExchange, Chain chain) throws IOException {

    }

    @Override
    public String description() {
        return null;
    }
}
