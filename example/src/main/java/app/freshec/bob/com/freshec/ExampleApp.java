package app.freshec.bob.com.freshec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import app.freshec.bob.com.latte_core.app.Latte;
import app.freshec.bob.com.latte_ec.icon.FontEcModule;

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
