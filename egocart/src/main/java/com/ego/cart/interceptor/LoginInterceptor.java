package com.ego.cart.interceptor;

import com.ego.commons.pojo.EgoResult;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.HttpClientUtil;
import com.ego.commons.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 *
 * @Author: yk
 * @Date: 2019/6/15 21:52
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final static String cookieName = "TT_TOKEN";

//    @Value("${passport.url}") 搞不了
    private final static String passportUrl = "http://localhost:8084/user/token/";

    private final static String loginUrl = "http://localhost:8084/user/showLogin";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtils.getCookieValue(request, cookieName);
//        System.out.println(token);
        if(token != null && !token.equals("")) {
            String result = HttpClientUtil.doPost(passportUrl + token);
            EgoResult egoResult = JsonUtils.jsonToPojo(result, EgoResult.class);
            if(egoResult != null && egoResult.getStatus() == 200)
                return true;
        }
        response.sendRedirect(loginUrl + "?isCart=1");
        // 如果出现请求头中没有Referer
//        response.sendRedirect("http://localhost:8084/user/showLogin?interurl="+request.getRequestURL());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
