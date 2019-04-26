package fastshop.bob.com.latte_core.app;


/**
 * 进行惰性单例模式的初始化,保证线程安全
 * */
public enum ConfigType {

    //配置网络域名
    API_HOSt,
    //全局上下文
    APPLICATION_CONTEXT,
    //控制初始化上下文
    CONFIG_READY,
    ICON

}
