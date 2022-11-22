package cc.tianbin.demo.agent.track;

import io.github.nibnait.common.utils.date.DateUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by nibnait on 2022/11/22
 */
@Data
@NoArgsConstructor
public class Span {

    private String linkId;  //链路ID
    private Long enterTime; //方法进入时间

    public Span(String linkId){
        this.linkId = linkId;
        this.enterTime = DateUtils.currentTimeMillis();
    }
}
