package cc.tianbin.demo.agent.plugin;

/**
 * 监控组件
 * Created by nibnait on 2022/11/22
 */
public interface IPlugin {

    //名称
    String name();

    //监控点
    InterceptPoint[] buildInterceptPoint();

    //拦截器类
    Class adviceClass();

}
