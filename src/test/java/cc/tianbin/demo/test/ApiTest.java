package cc.tianbin.demo.test;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by nibnait on 2022/11/22
 */
@Slf4j
public class ApiTest {

    /**
     * mvn package
     *
     * VM options:
     * -javaagent:/Users/nibnait/github/agent-demo/target/agent-demo-1.0-SNAPSHOT.jar=testargs
     */
    public static void main(String[] args) throws InterruptedException {
        ApiTest apiTest = new ApiTest();
        apiTest.echoHi();
    }

    private void echoHi() throws InterruptedException {
        log.info("hi agent");
        Thread.sleep((long) (Math.random() * 500));
    }

}
