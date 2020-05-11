package com.gateway.filter;

import com.gateway.util.JsonUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AuthFilter extends ZuulFilter {

    public AuthFilter() {
        System.out.println();
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof OAuth2Authentication)){
            return null;
        }
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
        // 拿到授权信息
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> authoritieList = new ArrayList<>(authorities.size());
        for (GrantedAuthority authority : authorities) {
            authoritieList.add(authority.getAuthority());
        }

        OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();
        Map<String, String> requestParameters = oAuth2Request.getRequestParameters();
        Map<String, Object> jsonToken = new HashMap<>(requestParameters);
        if(authentication != null){
            jsonToken.put("principal",authentication.getPrincipal());
            jsonToken.put("authorities",authoritieList);
        }

        ctx.addZuulRequestHeader("json-token",JsonUtil.toJsonString(jsonToken));
        return null;
    }
}
