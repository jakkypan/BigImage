package com.bigimage.client;

import android.graphics.Bitmap;
import android.os.Binder;

/**
 * @author hongchao.pan
 * created at 2022/4/23 11:51 下午
 */
public class ImageBinder extends Binder {
    private Bitmap bitmap;

    public ImageBinder(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    Bitmap getBitmap() {
        return bitmap;
    }
}
