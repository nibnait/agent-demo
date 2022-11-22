package cc.tianbin.demo.agent.track;

/**
 * Created by nibnait on 2022/11/22
 */
public class TrackContext {

    private static final ThreadLocal<String> trackLocal = new ThreadLocal<>();

    public static void clear(){
        trackLocal.remove();
    }

    public static String getLinkId(){
        return trackLocal.get();
    }

    public static void setLinkId(String linkId){
        trackLocal.set(linkId);
    }

}
