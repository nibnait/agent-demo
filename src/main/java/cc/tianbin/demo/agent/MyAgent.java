package cc.tianbin.demo.agent;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by nibnait on 2022/11/22
 */
@Slf4j
public class MyAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        log.info("this is my agent：" + agentArgs);

        Executors.newScheduledThreadPool(1)
                .scheduleAtFixedRate(new Runnable() {
                    public void run() {
                        JvmStack.printMemoryInfo();
                        JvmStack.printGCInfo();
                        log.info("===================================================================================================");
                    }
                }, 0, 5000, TimeUnit.MILLISECONDS);
    }

    //JVM 首先尝试在代理类上调用以下方法
    public static void premain1(String agentArgs, Instrumentation inst) {
        log.info("this is my agent：" + agentArgs);

        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, javaModule) -> {
            return builder
                    .method(ElementMatchers.any()) // 拦截任意方法
                    .intercept(MethodDelegation.to(MethodCostTime.class)); // 委托
        };

        AgentBuilder.Listener listener = new AgentBuilder.Listener() {
            @Override
            public void onDiscovery(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b, DynamicType dynamicType) {

            }

            @Override
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

            @Override
            public void onError(String s, ClassLoader classLoader, JavaModule javaModule, boolean b, Throwable throwable) {

            }

            @Override
            public void onComplete(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

        };

        new AgentBuilder
                .Default()
                .type(ElementMatchers.nameStartsWith("cc.tianbin.demo.test")) // 指定需要拦截的类
                .transform(transformer)
                .with(listener)
                .installOn(inst);
    }

    //JVM 首先尝试在代理类上调用以下方法
    public static void premainByMonitorTransformer(String agentArgs, Instrumentation inst) {
        log.info("this is my agent：{}", agentArgs);
        MyMonitorTransformer monitor = new MyMonitorTransformer();
        inst.addTransformer(monitor);
    }

    //如果代理类没有实现上面的方法，那么 JVM 将尝试调用该方法
    public static void premain(String agentArgs) {
    }

}
