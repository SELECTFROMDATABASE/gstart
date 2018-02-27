package aspect;

import com.alibaba.dubbo.rpc.RpcContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RPC消费调用
 */
@Aspect
public class RPCLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(RPCLogAspect.class);

    @Around("execution(* *.rpc.*.*(..))")
    public Object doServiceAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object o = joinPoint.proceed();
        String host = RpcContext.getContext().getRemoteHost();
        Object serviceName = joinPoint.getTarget();
        if(RpcContext.getContext().isProviderSide()){
            logger.info("provider side : Host = {}, service = {} ",host,serviceName);
        }
        if(RpcContext.getContext().isConsumerSide()){
            logger.info("consumer side : Host = {}, service = {} ",host,serviceName);

        }
        return o;
    }
}