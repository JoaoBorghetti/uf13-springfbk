package com.example.gateway.filters;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;
@Component
public class LogFilter implements GlobalFilter {
	final Logger logger = LoggerFactory.getLogger(LogFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("Global filter > requesto to : " + exchange.getRequest().getPath());
		if(exchange.getRequest().getPath().value().contains("/api/purchase/me")) {
			if(!exchange.getRequest().getHeaders().get("Authorization").isEmpty()) {
				logger.info("user has bearer, modifying path");
				String user = getUserId(exchange.getRequest().getHeaders().get("Authorization").get(0));
				
				ServerWebExchange newExch = exchange.mutate()
														.request(r -> r.path("/purchase/api/purchases/"+user)
																.headers(h -> h.remove("Authorization")))
														.build();
				logger.info("modified path from " +exchange.getRequest().getPath()+ " to "+ newExch.getRequest().getPath());
				newExch.getResponse().setComplete();
				return chain.filter(newExch);
			}
		}
		return chain.filter(exchange);
	}
	
	private String getUserId(String token) {
		return "user1";
	}
}
