package architect.example1.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AnyAspect1 {
	@Before("execution(* architect.example1.service.AnyService1.anyMethod())")
	public void beforeMethod(JoinPoint joinPoint) {
		System.out.println("This is the before method.");
	}
	
	@After("execution(* architect.example1.service.AnyService1.anyMethod())")
	public void afterMethod(JoinPoint joinPoint) {
		System.out.println("This is the after method.");
	}
	
	@Around("execution(* architect.example1.service.AnyService1.anyMethod())")
	public void aroundMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("This is the around method.");
		proceedingJoinPoint.proceed();
	}
}
