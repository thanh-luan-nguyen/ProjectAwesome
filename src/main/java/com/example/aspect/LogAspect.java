package com.example.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {
    /**
     * serviceの実行前にlog出力する
     * 対象:[UserService]をclass名に含んでいる
     * */
    @Before("execution(* *..*.*UserService.*(..))")
    public void startLog(JoinPoint joinPoint){
        log.info("method開始: " + joinPoint.getSignature());
    }

    
}
