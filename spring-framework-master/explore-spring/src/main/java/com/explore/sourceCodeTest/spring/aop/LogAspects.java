package com.explore.sourceCodeTest.spring.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 切面类
 *
 * @author lfy
 * @Aspect： 告诉Spring当前类是一个切面类
 */
@Aspect
public class LogAspects {

	//抽取公共的切入点表达式
	//1、本类引用
	//2、其他的切面引用
	@Pointcut("execution(public int com.explore.sourceCodeTest.spring.aop.MathCalculator.*(..))")
	public void pointCut() {
	}

	;

	//@Before在目标方法之前切入；切入点表达式（指定在哪个方法切入）
	@Before("pointCut()")
	public void logStart(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		System.out.println("" + joinPoint.getSignature().getName() + "运行。。。@Before:参数列表是：{" + Arrays.asList(args) + "}");
	}

	@After("com.explore.sourceCodeTest.spring.aop.LogAspects.pointCut()")
	public void logEnd(JoinPoint joinPoint) {
		System.out.println("" + joinPoint.getSignature().getName() + "结束。。。@After");
	}

	//JoinPoint一定要出现在参数表的第一位
	@AfterReturning(value = "pointCut()", returning = "result")
	public void logReturn(JoinPoint joinPoint, Object result) {
		System.out.println("" + joinPoint.getSignature().getName() + "正常返回。。。@AfterReturning:运行结果：{" + result + "}");
	}

	@AfterThrowing(value = "pointCut()", throwing = "exception")
	public void logException(JoinPoint joinPoint, Exception exception) {
		System.out.println("" + joinPoint.getSignature().getName() + "异常。。。异常信息：{" + exception + "}");
	}

	@Around(value = "pointCut()")
	public Object logAround(ProceedingJoinPoint pj) throws Throwable {
		System.out.println("在proceed()之前:输出的增强该组件,原来的参数是：{" + Arrays.asList(pj.getArgs()) + "}");
		Object obj = pj.proceed(new Integer[]{4, 2});
		System.out.println("在proceed()之后:输出的增强该组件,proceed的结果是："+obj);
		return obj;
	}
}
