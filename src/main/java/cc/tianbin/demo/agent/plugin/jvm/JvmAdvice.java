package cc.tianbin.demo.agent.plugin.jvm;

import net.bytebuddy.asm.Advice;

/**
 * Created by nibnait on 2022/11/22
 */
public class JvmAdvice {

    @Advice.OnMethodExit()
    public static void exit() {
        JvmStack.printMemoryInfo();
        System.out.println();
        JvmStack.printGCInfo();
        System.out.println("-------------------------------------------------------------------------------------------------");
    }

}
