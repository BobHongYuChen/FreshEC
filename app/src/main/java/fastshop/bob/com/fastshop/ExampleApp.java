package fastshop.bob.com.fastshop;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import fastshop.bob.com.latte.ec.icon.FontEcModule;
import fastshop.bob.com.latte_core.app.Latte;

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.0.1/")
                .configure();
    }
}
