package fastshop.bob.com.latte_core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

public class Configurator {

    private static final HashMap<String, Object> LATTE_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configurator () {
        //配置开始但是未完成
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    //一种比较好的线程安全就是静态内部类+get他
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    final HashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    private static class Holder {
        //放入单例模式
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure() {
        initIcons();
        //配置完成
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOSt.name(), host);
        return this;
    }

    //初始化图标
    private void initIcons() {
        if(ICONS.size() > 0) {
            //初始化第一个值
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            //0被加载从1开始
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        //添加自己的图标
        ICONS.add(descriptor);
        return this;
    }

    //检查配置项,保证配置项的完整性和正确性
    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());

        if (!isReady) {
            throw new RuntimeException("Configuration is not ready, call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }
}
