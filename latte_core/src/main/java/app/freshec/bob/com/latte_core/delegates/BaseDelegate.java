package app.freshec.bob.com.latte_core.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

public abstract class BaseDelegate extends SwipeBackFragment {

    @SuppressWarnings("SpellCheckingInspection")
    private Unbinder mUnbinder = null;
    //子类传入其布局
    public abstract Object setLayout();

    public abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = null;
        //如果我们的setLayout函数传入了一个int值
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer)setLayout(), container, false);
        }else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        }

        if (rootView != null) {
            //绑定视图
            mUnbinder = ButterKnife.bind(this, rootView);
            //使用视图
            onBindView(savedInstanceState, rootView);
        }

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //在进行destroyView的时候需要处理Unbinder的情况
        if (mUnbinder != null) {
            //解除绑定
            mUnbinder.unbind();
        }
    }
}
