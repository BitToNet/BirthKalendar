package cn.bittonet.birthkalendar.activity;

import android.os.Bundle;
import butterknife.ButterKnife;
import cn.bittonet.birthkalendar.R;

public class MainActivity extends BaseActivity {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

//    @Override
//    protected void initImmersionBar() {
//        super.initImmersionBar(mToolbar, true);
//        initToolBar(mToolbar, mTvTitle, true, strTitle);
//    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
