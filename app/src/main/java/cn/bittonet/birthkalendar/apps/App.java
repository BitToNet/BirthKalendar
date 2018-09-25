package cn.bittonet.birthkalendar.apps;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

public class App extends Application {
    public static  Context context;
    public static  Handler mainHandler;
    private static App     instance;
    private static final boolean        isDebug    = true;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        mainHandler = new Handler();
    }


    public static boolean isDebug() {
        return isDebug;
    }

    // 懒汉式单例模式
    public static App getInstance() {
        if (instance == null) {
            synchronized (App.class) {
                if (instance == null) {
                    instance = new App();
                }
            }
        }
        return instance;
    }
}
