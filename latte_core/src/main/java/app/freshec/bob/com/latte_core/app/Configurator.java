package app.freshec.bob.com.latte_core.app;


import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * 配置文件的存储以及获取
 * */
public class Configurator {

    private static final HashMap<String, Object> LATTE_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configurator() {
        //配置开始但是配置并没有完成
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    //线程安全的懒汉模式
    public static Configurator getInstance() {

        return Holder.INSTANCE;
    }

    final HashMap<String, Object> getLatteConfigs() {

        return LATTE_CONFIGS;
    }

    //静态内部类单例模式的初始化
    private static class Holder {

        private static final Configurator INSTANCE = new Configurator();
    }

    //配置彻底结束需要使用到的方法
    public final void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    //初始化API HOST
    public final Configurator withApiHost(String host) {

        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    //iconify封装
    private void initIcons() {
        if (ICONS.size() > 0) {
            //初始化第一个ICON
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            //从1开始即逻辑上的2开始
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {

        ICONS.add(descriptor);
        return this;
    }

    //配置项完成情况检查
    private void checkConfiguration() {

        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {

            //配置并没有完成，抛出一个运行异常
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    //没有检测过
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key) {

        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }
}
