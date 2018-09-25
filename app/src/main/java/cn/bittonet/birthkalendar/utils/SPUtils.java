package cn.bittonet.birthkalendar.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

public class SPUtils {

    private static SharedPreferences sharedPreferences;

    public SPUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context
     * @param key
     * @param object
     * @param name
     */
    public static void put(Context context, String key, Object object, String name) {

        if (object == null) {
            return;
        }

        if (!(object instanceof Serializable)) {
            throw new IllegalArgumentException("The object should implements Serializable!");
        }

        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();

        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else { //保存序列化过的对象
            //1.write obj to bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(object);

                //2.convert obj to string via Base64
                byte[] bytes = Base64.encode(baos.toByteArray(), Base64.DEFAULT);

                //3.save string
                editor.putString(key, new String(bytes));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        editor.apply();
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对应的方法获取值
     *
     * @param context
     * @param key
     * @param defaultObject
     * @param name
     * @return
     */
    public static Object get(Context context, String key, Object defaultObject, String name) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);

        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        } else {// 读取序列化过的对象
            //1.get string
            String string = sp.getString(key, null);

            if (TextUtils.isEmpty(string)) {
                return null;
            }

            //2.decode string
            byte[] bytes = Base64.decode(string, Base64.DEFAULT);

            //3.read bytes to Object
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            try {
                ObjectInputStream ois = new ObjectInputStream(bais);
                return ois.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     * @param key
     * @param name
     */
    public static void remove(Context context, String key, String name) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * 清除所有数据
     *
     * @param context
     * @param name
     */
    public static void clear(Context context, String name) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context
     * @param key
     * @param name
     * @return
     */
    public static boolean contains(Context context, String key, String name) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @param context
     * @param name
     * @return
     */
    public static Map<String, ?> getAll(Context context, String name) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getAll();
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     */
    public static void cleanList(Context context) {
        if(sharedPreferences==null){
            sharedPreferences = context.getSharedPreferences("sp",
                                                             Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }


}
