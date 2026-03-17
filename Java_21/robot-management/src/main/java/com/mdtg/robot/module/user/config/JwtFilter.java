package com.mdtg.robot.module.user.config;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.util.AntPathMatcher;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author WangYunwei [2026-03-12]
 */
@Slf4j
public class JwtFilter extends AccessControlFilter {

    private static final Set<String> ALLOW_LIST = Set.of(
            "/doc.html"
    );
    private static final List<String> PATTERN_ALLOW_LIST = Arrays.asList(
            "/webjars/js/**",
            "/webjars/css/**",
            "/v3/api-docs/**"
    );
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();



    /**
     * 是否允许访问
     * isAccessAllowed()判断是否携带了有效的JwtToken
     * <p>
     * 1. 返回true: shiro就直接允许访问url
     * 2. 返回false: shiro才会根据onAccessDenied的方法的返回值决定是否允许访问url
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        String requestURI = request.getRequestURI();
//        // 1.如果在精确白名单中，直接放行
//        if (ALLOW_LIST.contains(requestURI)) {
//            return true;
//        }
//        // 2.如果匹配模式白名单，放行
//        for (String pattern : PATTERN_ALLOW_LIST) {
//            if (ANT_PATH_MATCHER.match(pattern, requestURI)) {
//                return true;
//            }
//        }
        // 3.验证Token
        String token = request.getHeader(JwtUtil.HEADER);
        if (token == null) {
            return false;
        }
        JwtToken jwtToken = new JwtToken(token);
        try {
            //进行登录处理,委托realm进行登录认证,调用Realm进行的认证
            getSubject(servletRequest, servletResponse).login(jwtToken);
        } catch (Exception e) {
            log.error("Subject login error:", e);
            return false;
        }
        return true;
    }

    /**
     * 访问被拒绝时
     * onAccessDenied()是没有携带JwtToken的时候进行账号密码登录,登录成功允许访问,登录失败拒绝访问
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        log.info("onAccessDenied方法被调用");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        log.info("== {}",requestURI);

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().print("123123");
        response.flushBuffer();
        return false;
    }
}
