package study.spring.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAdvice {
	
	private static final Logger log=LoggerFactory.getLogger(study.spring.aop.LogAdvice.class);
	
	@AfterThrowing(pointcut = "execution(* study.spring.service.*.*(..))", throwing="exception")
	public void logException(Exception exception) {
		log.info("야 에러다");
		log.info("exception "+exception);
	}
	@Around("execution(* study.spring.service.*.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) {
		long start = System.currentTimeMillis();
		log.info("Target: " +pjp.getTarget());
		log.info("Param: "+Arrays.toString(pjp.getArgs()));
		Object result = null;
		try {
			result=pjp.proceed();
			
		}catch(Throwable e) {e.printStackTrace();}
		long end=System.currentTimeMillis();
		log.info("Time: "+(end-start));
		return result;
	}
}
