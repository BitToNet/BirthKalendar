package cn.bittonet.birthkalendar.activity;

import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;
import butterknife.BindView;
import cn.bittonet.birthkalendar.R;
import cn.bittonet.birthkalendar.fragment.EventFragment;
import cn.bittonet.birthkalendar.fragment.KalendarFragment;
import cn.bittonet.birthkalendar.utils.BottomNavigationViewHelper;
import java.util.ArrayList;

public class MainActivity extends BaseActivity {


    @BindView(R.id.fl_content)
    FrameLayout          mFlContent;
//    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;


    private ArrayList<Fragment> fragments = new ArrayList<>();
    private int lastIndex;

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
        fragments.add(new KalendarFragment());
        fragments.add(new EventFragment());
        //todo butterknife导致的空指针问题，字体颜色记得改
        mNavigation = findViewById(R.id.navigation);

        selectFragment(0);
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(mNavigation);
        mNavigation.setItemIconTintList(null);
        mNavigation.setItemTextColor(ColorStateList.valueOf(getResources().getColor(R.color.black)));
        mNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        selectFragment(0);
                        return true;
                    case R.id.navigation_family:
                        selectFragment(1);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    private void selectFragment(int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        Fragment currentFragment = fragments.get(position);
        Fragment lastFragment    = fragments.get(lastIndex);

        lastIndex = position;
        ft.hide(lastFragment);
        if (!currentFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            ft.add(R.id.fl_content, currentFragment);
        }
        ft.show(currentFragment);
        ft.commitAllowingStateLoss();
    }


    @Override
    protected void initData() {
    }
}
