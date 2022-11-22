package cc.tianbin.demo.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

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
    @Test
    public void testMonitorMemory() {
        while (true) {
            List<Object> list = new LinkedList<>();
            list.add("嗨！JavaAgent");
            list.add("嗨！JavaAgent");
            list.add("嗨！JavaAgent");
        }
    }


    @Test
    public void testMonitorMethodCost() throws InterruptedException {
        ApiTest apiTest = new ApiTest();
        apiTest.echoHi();
    }

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            List<Object> list = new LinkedList<>();
            list.add("嗨！JavaAgent");
            list.add("嗨！JavaAgent");
            list.add("嗨！JavaAgent");
        }
    }

    private void echoHi() throws InterruptedException {
        log.info("hi agent");
        Thread.sleep((long) (Math.random() * 500));
    }

}
