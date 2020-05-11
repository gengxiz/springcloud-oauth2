package com.order.filter;

import com.order.pojo.TokenAuthention;
import com.order.util.JsonUtil;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("json-token");
        if(token != null){
            TokenAuthention authention = JsonUtil.parseObject(token, TokenAuthention.class);
            String[] authors = new String[authention.getAuthorities().size()];

            authention.getAuthorities().toArray(authors);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authention.getPrincipal(),null,
                    AuthorityUtils.createAuthorityList(authors));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);
    }
}
