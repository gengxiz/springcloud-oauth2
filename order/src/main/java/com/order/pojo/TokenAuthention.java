package com.order.pojo;

import java.util.List;

public class TokenAuthention {
    private String principal;
    private List<String> authorities;




    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}
