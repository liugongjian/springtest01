package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;


@Aspect
public class LogAspects {

    @Pointcut("execution(public int aop.MathCalculator.*(..))")
    public void pointCut(){}


    @Before("pointCut()")
    public void logStart2(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println(joinPoint.getSignature().getName()+"Before2。。。参数列表是：{"+ Arrays.asList(args)+"}");
    }

    //@Before("public int MathCalculator.*(..)")
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println(joinPoint.getSignature().getName()+"Before。。。参数列表是：{"+ Arrays.asList(args)+"}");
    }

    //after
    @After("pointCut()")
    public void logEnd(){
        System.out.println("After除法结束");
    }

    //res代表接收方法执行的返回值
    @AfterReturning(value="pointCut()",returning = "res")
    public void logReturn(Object res){
        System.out.println("After Returning除法正常返回。。。运行结果：{"+res+"}");
    }
    //except代表接收方法执行的返回值
    //JoinPoint参数必须放在切面函数参数表的第一位
    @AfterThrowing(value="pointCut()",throwing = "except")
    public void logException(JoinPoint joinPoint,Exception except){
        System.out.println(joinPoint.getSignature().getName()+"After throwing除法异常。。。异常是：{" + except + "}");
    }



}
