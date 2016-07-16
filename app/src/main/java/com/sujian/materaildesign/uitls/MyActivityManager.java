package com.sujian.materaildesign.uitls;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import com.orhanobut.logger.Logger;

import java.util.Stack;

/**
 * @author Sujian  121116111@QQ.COM
 * @ClassName: MyActivityManager
 * @Description: TODO(Activity的管理类)
 * @date 2016年3月23日 下午9:03:31
 */
public class MyActivityManager {

    private static Stack<Activity> activityStack;
    private static MyActivityManager instance;

    private MyActivityManager() {
    }

    /**
     * @param @return
     * @return MyActivityManager
     * @throws
     * @Title: getMyActivityManager
     * @Description: TODO(单例设计模式得到对象 防止多次创建对象)
     */
    public static MyActivityManager getMyActivityManager() {
        if (instance == null) {
            synchronized (MyActivityManager.class) {
                if (instance == null) {
                    instance = new MyActivityManager();
                }
            }
        }
        return instance;
    }


    /**
     * @param @return
     * @return Activity
     * @throws
     * @Title: currentActivity
     * @Description: TODO(得到第一个activity 也就是最后放进来的)
     */
    public Activity currentActivity() {
        if (activityStack == null || activityStack.size() == 0) {
            return null;
        }
        Activity activity = activityStack.lastElement();

        Logger.e("get current activity:" + activity.getClass().getSimpleName());
        return activity;
    }


    /**
     * @param @param activity
     * @return void
     * @throws
     * @Title: addActivity
     * @Description: TODO(添加一个activity)
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        Logger.e("push stack activity:" + activity.getClass().getSimpleName());
        activityStack.add(activity);
    }

    /**
     * @param @param activity
     * @return void
     * @throws
     * @Title: finishActivity
     * @Description: TODO(销毁一个指定activity)
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            Logger.e("remove current activity:" + activity.getClass().getSimpleName());
            activityStack.remove(activity);
            activity = null;
        }
    }

    /**
     * @param
     * @return void
     * @throws
     * @Title: finishActivity
     * @Description: TODO(销毁当前的activity)
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * @param @param cls
     * @return void
     * @throws
     * @Title: finishActivity
     * @Description: TODO(销毁指定类名的activity)
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * @param
     * @return void
     * @throws
     * @Title: finishAllActivity
     * @Description: TODO(销毁所有)
     */
    public static void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * @param @param context
     * @return void
     * @throws
     * @Title: AppExit
     * @Description: TODO(退出程序 杀死进程)
     */
    public static void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
        }
    }

}
