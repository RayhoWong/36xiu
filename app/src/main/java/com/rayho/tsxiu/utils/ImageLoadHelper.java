package com.rayho.tsxiu.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

/**
 * Created by Rayho on 2019/2/24
 * 通过注解@BindingAdapter自定义属性加载图片 只适用于DataBinding
 **/
public class ImageLoadHelper {
    /**
     * 通过注解@BindingAdapter自定义imageUrl属性
     * imageview在xml中可以使用该属性传值url给该方法加载图片
     *
     * 加载圆形图片
     * @param view
     * @param url
     */
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String url) {
        GlideUtils.loadCircleImage(
                view.getContext(), url, view);
    }

    /**
     * 普通加载非圆角
     * @param view
     * @param url
     */
    @BindingAdapter({"imageUrl_2"})
    public static void loadImage_2(ImageView view, String url) {
        GlideUtils.loadImage(
                view.getContext(), url, view);
    }
}
