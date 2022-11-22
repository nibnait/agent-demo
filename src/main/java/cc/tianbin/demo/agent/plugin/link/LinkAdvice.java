package cc.tianbin.demo.agent.plugin.link;

import cc.tianbin.demo.agent.track.Span;
import cc.tianbin.demo.agent.track.TrackContext;
import cc.tianbin.demo.agent.track.TrackManager;
import io.github.nibnait.common.utils.DataUtils;
import net.bytebuddy.asm.Advice;

import java.util.UUID;

/**
 * Created by nibnait on 2022/11/22
 */
public class LinkAdvice {

    @Advice.OnMethodEnter()
    public static void enter(@Advice.Origin("#t") String className, @Advice.Origin("#m") String methodName) {
        Span currentSpan = TrackManager.getCurrentSpan();
        if (null == currentSpan) {
            String linkId = UUID.randomUUID().toString();
            TrackContext.setLinkId(linkId);
        }
        TrackManager.createEntrySpan();
    }

    @Advice.OnMethodExit()
    public static void exit(@Advice.Origin("#t") String className, @Advice.Origin("#m") String methodName) {
        Span exitSpan = TrackManager.getExitSpan();
        if (null == exitSpan) return;
        System.out.println(DataUtils.format("链路追踪(MQ)：{} {}.{} 耗时：{}ms",
                exitSpan.getLinkId(), className, methodName,
                (System.currentTimeMillis() - exitSpan.getEnterTime())
        ));
    }

}
