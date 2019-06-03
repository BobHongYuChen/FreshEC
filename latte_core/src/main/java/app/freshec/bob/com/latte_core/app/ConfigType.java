package app.freshec.bob.com.latte_core.app;

/**
 * 安全惰性单例初始化,也即线程安全的懒汉模式
 * */
public enum ConfigType {

    //配置网络请求域名的存储
    API_HOST,
    //全局上下文
    APPLICATION_CONTEXT,
    //控制初始化和配置的完成与否
    CONFIG_READY,
    ICON
}
