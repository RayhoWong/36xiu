package com.rayho.tsxiu.module_photo.viewmodel;


import com.blankj.rxbus.RxBus;
import com.rayho.tsxiu.base.BaseDataBindingApater;
import com.rayho.tsxiu.base.Constant;
import com.rayho.tsxiu.module_photo.adapter.PhotoAdapter;
import com.rayho.tsxiu.module_photo.bean.PhotoBean;
import com.rayho.tsxiu.module_photo.retrofit.PhotosLoader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.Observable;

/**
 * PhotoTabFragment界面的VM
 */
public class PhotoTabViewModel {

    private PhotoAdapter mAdapter;

    public PhotoTabViewModel(PhotoAdapter adapter) {
        mAdapter = adapter;
    }


    /**
     * 获取图片
     *
     * @return 返回一个PhotoBean类型的Observable
     */
    public Observable<PhotoBean> getPhotosObservable() {
        return PhotosLoader.getInstance().getPhotos();
    }


    /**
     * 设置图片列表
     *
     * @param list        图片数据
     * @param refreshType 获取数据的方式（下拉/上拉）
     */
    public void setPhotos(List<PhotoBean.FeedListBean> list, int refreshType) {
        List<BaseDataBindingApater.Items> mItems = new ArrayList<>();

        if (list != null && list.size() >= 0) {
            //使用迭代器对list进行删除/添加操作
            // 避免用foreach循环出现ConcurrentModificationException导致程序崩溃
            Iterator<PhotoBean.FeedListBean> iterator = list.iterator();
            while (iterator.hasNext()) {
                PhotoBean.FeedListBean bean = iterator.next();
                if (bean.images == null || bean.images.size() == 0) {
                    iterator.remove();
                }
            }
            for (PhotoBean.FeedListBean bean : list) {
                mItems.add(new PhotosViewModel(bean));
            }
            if (refreshType == Constant.REFRESH_DATA) {
                //下拉刷新
                //提醒用户更新数据的数量
                RxBus.getDefault().postSticky(mItems.size(), "updateNums");
                mAdapter.setItems(mItems);
            } else if (refreshType == Constant.LOAD_MORE_DATA) {
                //加载更多
                mAdapter.addItems(mItems);
            }
        }
    }
}
