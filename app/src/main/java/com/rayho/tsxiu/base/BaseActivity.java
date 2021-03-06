package com.rayho.tsxiu.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.rayho.tsxiu.R;
import com.rayho.tsxiu.utils.StatusBarUtil;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.Toolbar;
import butterknife.ButterKnife;
import skin.support.widget.SkinCompatSupportable;

public abstract class BaseActivity extends RxAppCompatActivity implements SkinCompatSupportable {

    private Toolbar mToolbar;
    private TextView mTvTitle;//中间的主标题
    private TextView mTvSubTitle;//toolbar右边的标题
    private ImageView mIvRightImage;//toolbar右边的图标
    private View mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRootView = View.inflate(this, R.layout.activity_base, null);
        addContent(getLayoutId());

        setContentView(mRootView);
/*        指定方向是竖屏 禁止所有activity横屏(手机旋转后重新创建生命周期)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);*/

        // 根据夜间模式的设置 设置状态栏的颜色
        if (SPUtils.getInstance(Constant.SP_SETTINGS).getBoolean(getString(R.string.sp_nightmode))) {
            //夜间模式true 黑色
            StatusBarUtil.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark_night), this);
        } else {
            //夜间模式false 白色
            StatusBarUtil.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark), this);
        }

        initToolBar();
        ButterKnife.bind(this);
        afterSetContentView();
    }


    /**
     * 动态监听换肤行为 设置状态栏颜色
     */
    @Override
    public void applySkin() {
        if (SPUtils.getInstance(Constant.SP_SETTINGS).getBoolean(getString(R.string.sp_nightmode))) {
            //夜间模式true 黑色
            StatusBarUtil.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark_night), this);
        } else {
            //夜间模式false 白色
            StatusBarUtil.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark), this);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        //默认设置左上角返回
        showBack();
    }

    /**
     * 把子类Activity的布局加载到Base布局的FrameLayout里面,activity无须在xml引用toolbar布局
     *
     * @param layoutId
     */
    private void addContent(int layoutId) {
        FrameLayout contentView = mRootView.findViewById(R.id.fl_content);
        View content = View.inflate(this, layoutId, null);
        if (content != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT);
            contentView.addView(content, params);
        }
    }

    /**
     * 获取子类布局的id
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 加载完布局的后续操作
     */
    public abstract void afterSetContentView();

    private void initToolBar() {
        mToolbar = findViewById(R.id.toolbar);
        mTvTitle = findViewById(R.id.title);
        mTvSubTitle = findViewById(R.id.subTitle);
        mIvRightImage = findViewById(R.id.rightImage);

       /* if (mToolbar != null) {
            //将Toolbar显示到界面
            setSupportActionBar(mToolbar);
        }*/

        if (mTvTitle != null) {
            mTvTitle.setText(getTitle());
        }
    }


    public Toolbar getToolbar() {
        return mToolbar;
    }

    /**
     * 某些界面可能需要定制toolbar 那么就隐藏默认Base的toolbar
     */
    public void hideBaseToolbar() {
        if (mToolbar.getVisibility() == View.VISIBLE) {
            mToolbar.setVisibility(View.GONE);
        }
    }

    public TextView getToolbarTitle() {
        return mTvTitle;
    }

    public void setToolbarTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setText(title);
        }
    }

    public TextView getToolbarSubTitle() {
        return mTvSubTitle;
    }

    public void setToolbarSubTitle(String title) {
        if (mIvRightImage.getVisibility() == View.VISIBLE || mTvSubTitle.getVisibility() == View.GONE
                && !TextUtils.isEmpty(title)) {
            mIvRightImage.setVisibility(View.GONE);
            mTvSubTitle.setVisibility(View.VISIBLE);
            mTvSubTitle.setText(title);
        }
    }

    /**
     * 返回右上角图标
     *
     * @return
     */
    public ImageView getRightImage() {
        return mIvRightImage;
    }

    /**
     * 设置右上角的图标
     *
     * @param image
     */
    public void setRightImage(@DrawableRes int image) {
        if (mTvSubTitle.getVisibility() == View.VISIBLE || mIvRightImage.getVisibility() == View.GONE
                && image != 0) {
            mTvSubTitle.setVisibility(View.GONE);
            mIvRightImage.setVisibility(View.VISIBLE);
            mIvRightImage.setImageResource(image);
        }
    }

    /**
     * toolbar返回键的设置
     */
    private void showBack() {
        if (mToolbar != null && isShowBacking()) {
            mToolbar.setNavigationIcon(R.mipmap.arrow_back);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    /**
     * 是否设置返回(子类可以重写)
     *
     * @return
     */
    public boolean isShowBacking() {
        return true;
    }

}
