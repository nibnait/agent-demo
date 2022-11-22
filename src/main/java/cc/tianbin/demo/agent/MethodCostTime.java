package cc.tianbin.demo.agent;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * Created by nibnait on 2022/11/22
 */
@Slf4j
public class MethodCostTime {

    @RuntimeType
    public static Object intercept(@Origin Method method, @SuperCall Callable<?> callable) throws Exception {
        long start = System.currentTimeMillis();
        try {
            // 原有函数执行
            return callable.call();
        } finally {
            log.info("{}.{}() 方法耗时: {}ms", method.getDeclaringClass().getSimpleName(), method.getName(), (System.currentTimeMillis() - start));
        }
    }

}
