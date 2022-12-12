package it.afp.spring.purchase.service;

import it.afp.spring.purchase.domain.UserDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("user-service")
public interface UserServiceFeignService {
    @PostMapping("/auth/verify")
    public UserDetails Verify(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken);
}
