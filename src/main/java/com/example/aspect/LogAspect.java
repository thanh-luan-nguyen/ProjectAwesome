package com.example.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect /** AOPクラス指定 */
@Component /** AOPクラス指定 */
@Slf4j
public class LogAspect {
    /**
     * serviceの実行前にlog出力する
     * 対象:[UserService]をclass名に含んでいる
     * */
    /** execution(戻り値 package名.class名.method名(引数)) */
    //@Before("execution(* *..*.*UserService.*(..))") /** 正規表現: trang 201 */
    @Before("execution(* *..*.*UserService.*(..))")
    public void startLog(JoinPoint jp){
        log.info("@Before method開始: " + jp.getSignature());
    }

    /** 実行後 */
    @After("execution(* *..*.*UserService.*(..))")
    public void endLog(JoinPoint jp) {
        log.info("@After method終了: " + jp.getSignature());
    }

    /** controllerの実行前後にlog出力する */
    //@Around("bean(*Controller)")
    //@Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    @Around("@within(org.springframework.stereotype.Controller)")
    public Object startLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // 開始ログ出力
        log.info("@Around method開始: " + proceedingJoinPoint.getSignature());

        try {
            //method実行
            Object result = proceedingJoinPoint.proceed();

            //終了log出力
            log.info("@Around method終了: " + proceedingJoinPoint.getSignature());

            //実行結果を呼び出し元に返却
            return result;
        } catch (Exception e) {
            //errorログ出力
            log.error("@Around method異常終了: " + proceedingJoinPoint.getSignature());

            //errorの再throw
            throw e;
        }
    }
}
