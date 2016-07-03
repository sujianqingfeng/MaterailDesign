package com.sujian.materaildesign.uitls;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * [@深入注解，在Java中设计和使用自己的注解](http://blog.csdn.net/yue7603835/article/details/7234786)
 * Fuction: Activity、Fragment初始化的用到的注解<p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ActivityFragmentInject {


    /**
     * 菜单id
     *
     * @return
     */
    int menuId() default -1;


    /**
     * 是否存在NavigationView
     *
     * @return
     */
    boolean hasNavigationView() default false;

    /**
     * toolbar的标题id
     *
     * @return
     */
    int toolbarTitle() default -1;


    /**
     * toolbar菜单默认选中项
     *
     * @return
     */
    int menuDefaultCheckedItem() default -1;

}
