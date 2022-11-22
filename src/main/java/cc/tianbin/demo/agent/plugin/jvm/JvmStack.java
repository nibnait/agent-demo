package cc.tianbin.demo.agent.plugin.jvm;

import io.github.nibnait.common.utils.DataUtils;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nibnait on 2022/11/22
 */
public class JvmStack {

    private static final long MB = 1048576L;

    public static void printMemoryInfo() {
        MemoryMXBean memory = ManagementFactory.getMemoryMXBean();

        // 堆内存
        MemoryUsage headMemory = memory.getHeapMemoryUsage();
        System.out.println(DataUtils.format("headMemory    init: {}\t max: {}\t used: {}\t committed: {}\t use rate: {}",
                headMemory.getInit() / MB + "MB",
                headMemory.getMax() / MB + "MB", headMemory.getUsed() / MB + "MB",
                headMemory.getCommitted() / MB + "MB",
                headMemory.getUsed() * 100 / headMemory.getCommitted() + "%"

        ));

        // 非堆内存
        MemoryUsage nonheadMemory = memory.getNonHeapMemoryUsage();
        System.out.println(DataUtils.format("nonheadMemory init: {}\t max: {}\t used: {}\t committed: {}\t use rate: {}",
                nonheadMemory.getInit() / MB + "MB",
                nonheadMemory.getMax() / MB + "MB", nonheadMemory.getUsed() / MB + "MB",
                nonheadMemory.getCommitted() / MB + "MB",
                nonheadMemory.getUsed() * 100 / nonheadMemory.getCommitted() + "%"
        ));

    }

    public static void printGCInfo() {
        List<GarbageCollectorMXBean> garbages = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean garbage : garbages) {
            System.out.println(DataUtils.format("name: {}\t count:{}\t took:{}\t pool name:{}",
                    garbage.getName(),
                    garbage.getCollectionCount(),
                    garbage.getCollectionTime(),
                    Arrays.deepToString(garbage.getMemoryPoolNames())
            ));
        }
    }

}
