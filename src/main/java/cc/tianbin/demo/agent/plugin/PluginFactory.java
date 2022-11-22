package cc.tianbin.demo.agent.plugin;

import cc.tianbin.demo.agent.plugin.jvm.JvmPlugin;
import cc.tianbin.demo.agent.plugin.link.LinkPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nibnait on 2022/11/22
 */
public class PluginFactory {

    public static List<IPlugin> pluginGroup = new ArrayList<>();

    static {
        //链路监控
        pluginGroup.add(new LinkPlugin());
        //Jvm监控
        pluginGroup.add(new JvmPlugin());
    }

}
