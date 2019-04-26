package fastshop.bob.com.latte_core.app;

import android.content.Context;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.WeakHashMap;

public final class Latte {

    //对象引用转入配置项之中
    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static HashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplication() {
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }


}
