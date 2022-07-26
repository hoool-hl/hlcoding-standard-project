package com.hlcoding.framework.interceptor;
import com.hlcoding.framework.annos.SkipUserAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


/**
 * 用户登录状态拦截器
 *
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor{

//    @Autowired
//    StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            SkipUserAuth methodAnnotation = handlerMethod.getMethodAnnotation(SkipUserAuth.class);
            if (methodAnnotation != null){
                System.out.println("不用权限校验");
            }
        }
        System.out.println("拦截器");
        return true;
    }

    private void extracted( ) {
        //  登录状态验证通过
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
