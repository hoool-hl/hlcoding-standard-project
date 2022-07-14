package com.hlcoding.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAspect {
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * execution函数用于匹配方法执行的连接点，语法为：
     * execution(方法修饰符(可选)  返回类型  方法名  参数  异常模式(可选))
     * 参数部分允许使用通配符：
     * *  匹配任意字符，但只能匹配一个元素
     * .. 匹配任意字符，可以匹配任意多个元素，表示类时，必须和*联合使用
     * +  必须跟在类名后面，如Horseman+，表示类本身和继承或扩展指定类的所有类
     */
    @Pointcut("execution(public * com.hlcoding.controller.*.*(..))")
    private void webLog() {}

    /**
     * 前置通知：在目标方法被调用之前调用通知功能
     */
    @Before("webLog()")
    public void doBefore(JoinPoint jp) {
        System.out.println("=====================doBeforeStart======================");
        // 接收到请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录请求内容
        log.info("URL : {}", request.getRequestURL());
        log.info("HTTP方法 : {}", request.getMethod());
        log.info("IP地址 : {}", request.getRemoteAddr());
        log.info("类的方法 : {}.{}", jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName());
        log.info("方法参数 : {}", Arrays.toString(jp.getArgs()));
        System.out.println("=====================doBeforeEnd======================");
    }
}
