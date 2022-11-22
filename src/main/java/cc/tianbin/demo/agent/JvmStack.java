package cc.tianbin.demo.agent;

import lombok.extern.slf4j.Slf4j;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nibnait on 2022/11/22
 */
@Slf4j
public class JvmStack {

    private static final long MB = 1048576L;

    static void printMemoryInfo() {
        MemoryMXBean memory = ManagementFactory.getMemoryMXBean();

        // 堆内存
        MemoryUsage headMemory = memory.getHeapMemoryUsage();
        log.info("headMemory    init: {}\t max: {}\t used: {}\t committed: {}\t use rate: {}",
                headMemory.getInit() / MB + "MB",
                headMemory.getMax() / MB + "MB", headMemory.getUsed() / MB + "MB",
                headMemory.getCommitted() / MB + "MB",
                headMemory.getUsed() * 100 / headMemory.getCommitted() + "%"

        );

        // 非堆内存
        MemoryUsage nonheadMemory = memory.getNonHeapMemoryUsage();
        log.info("nonheadMemory init: {}\t max: {}\t used: {}\t committed: {}\t use rate: {}",
                nonheadMemory.getInit() / MB + "MB",
                nonheadMemory.getMax() / MB + "MB", nonheadMemory.getUsed() / MB + "MB",
                nonheadMemory.getCommitted() / MB + "MB",
                nonheadMemory.getUsed() * 100 / nonheadMemory.getCommitted() + "%"
        );

    }

    static void printGCInfo() {
        List<GarbageCollectorMXBean> garbages = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean garbage : garbages) {
            log.info("name: {}\t count:{}\t took:{}\t pool name:{}",
                    garbage.getName(),
                    garbage.getCollectionCount(),
                    garbage.getCollectionTime(),
                    Arrays.deepToString(garbage.getMemoryPoolNames()));
        }
    }

}
