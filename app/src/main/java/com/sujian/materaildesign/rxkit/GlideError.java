package com.sujian.materaildesign.rxkit;

import android.graphics.drawable.Drawable;

/**
 * Created by sujian on 2016/8/6.
 * Mail:121116111@qq.com
 */
public class GlideError extends Exception {
    private Drawable errorDrawable;

    public GlideError(Drawable errorDrawable) {
        this.errorDrawable = errorDrawable;
    }

    public Drawable getErrorDrawable() {
        return errorDrawable;
    }
}
