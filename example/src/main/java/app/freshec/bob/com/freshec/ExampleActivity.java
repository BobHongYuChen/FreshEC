package app.freshec.bob.com.freshec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import app.freshec.bob.com.latte_core.activity.ProxyActivity;
import app.freshec.bob.com.latte_core.app.Latte;
import app.freshec.bob.com.latte_core.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {


    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
