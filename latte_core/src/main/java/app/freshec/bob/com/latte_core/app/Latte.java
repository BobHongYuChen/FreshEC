package app.freshec.bob.com.latte_core.app;

import android.content.Context;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * 该类并不希望任何人继承与它
 * 对外工具类
 * */
public final class Latte {

    public static Configurator init(Context context) {

        //传入APPLICATION_CONTEXT
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<String, Object> getConfigurations() {

        return Configurator.getInstance().getLatteConfigs();
    }

    //传入Context
    public static Context getApplication() {

        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }


}
