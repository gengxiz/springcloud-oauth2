springcloud 结合 spring-security 和oauth2的项目<br/>


oauth2 认证端点<br/>
AuthorizationEndpoint<br/>
oauth2 token 端点<br/>
TokenEndpoint<br/>
oauth2 过滤器<br/>
OAuth2AuthenticationProcessingFilter<br/>

客户端获取token也是走DaoAuthenticationProvider
的认证方法,通过任何后在生成token