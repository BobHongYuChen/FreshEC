package app.freshec.bob.com.latte_core.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import app.freshec.bob.com.latte_core.R;
import app.freshec.bob.com.latte_core.delegates.LatteDelegate;
import me.yokeyword.fragmentation.SupportActivity;

public abstract class ProxyActivity extends SupportActivity {

    //用于返回根delegate
    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    //初始化视图
    private void initContainer(@Nullable Bundle savedInstanceState) {

        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null){
            //第一次加载,传入根delegate
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    //程序为单activity架构，对应用的垃圾回收做一些小小的操作
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
