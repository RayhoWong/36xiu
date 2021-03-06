package com.rayho.tsxiu.ui.channelhelper.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.rayho.tsxiu.R;
import com.rayho.tsxiu.ui.channelhelper.activity.ChannelActivity;
import com.rayho.tsxiu.ui.channelhelper.base.EditModeHandler;
import com.rayho.tsxiu.ui.channelhelper.base.IChannelType;
import com.rayho.tsxiu.ui.channelhelper.base.ItemDragListener;
import com.rayho.tsxiu.ui.channelhelper.base.ItemDragVHListener;
import com.rayho.tsxiu.ui.channelhelper.bean.ChannelBean;
import com.rayho.tsxiu.ui.channelhelper.helper.ItemDragHelperCallback;
import com.rayho.tsxiu.ui.channelhelper.other.APPConst;
import com.rayho.tsxiu.ui.channelhelper.view.MyChannelHeaderWidget;
import com.rayho.tsxiu.ui.channelhelper.view.MyChannelWidget;
import com.rayho.tsxiu.ui.channelhelper.view.RecChannelHeaderWidget;
import com.rayho.tsxiu.ui.channelhelper.view.RecChannelWidget;
import com.rayho.tsxiu.utils.ToastUtil;

import java.util.List;

import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by goach on 2016/9/28.
 */

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ChannelViewHolder> implements
        ItemDragListener {
    private List<ChannelBean> mMyChannelItems;
    private List<ChannelBean> mOtherChannelItems;
    private int mMyHeaderCount;
    private int mRecHeaderCount;
    private LayoutInflater mInflater;
    private SparseArray<IChannelType> mTypeMap = new SparseArray();
    private boolean isEditMode;
    private ItemTouchHelper mItemTouchHelper;
    // touch 点击开始时间
    private long startTime;
    // touch 间隔时间  用于分辨是否是 "点击"
    private static final long SPACE_TIME = 100;
    private ChannelItemClickListener channelItemClickListener;

    private ChannelActivity mActivity;

    public ChannelAdapter(ChannelActivity mActivity, RecyclerView recyclerView,
                          List<ChannelBean> myChannelItems,
                          List<ChannelBean> otherChannelItems,
                          int myHeaderCount, int recHeaderCount){
        this.mActivity = mActivity;
        this.mItemTouchHelper = new ItemTouchHelper(new ItemDragHelperCallback(this));
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        this.mMyChannelItems = myChannelItems;
        this.mOtherChannelItems = otherChannelItems;
        this.mMyHeaderCount = myHeaderCount;
        this.mRecHeaderCount = recHeaderCount ;
        mInflater = LayoutInflater.from(mActivity);
        mTypeMap.put(IChannelType.TYPE_MY_CHANNEL_HEADER,new MyChannelHeaderWidget(new EditHandler()));
        mTypeMap.put(IChannelType.TYPE_MY_CHANNEL,new MyChannelWidget(new EditHandler()));
        mTypeMap.put(IChannelType.TYPE_REC_CHANNEL_HEADER,new RecChannelHeaderWidget());
        mTypeMap.put(IChannelType.TYPE_REC_CHANNEL,new RecChannelWidget(new EditHandler()));
    }

    @Override
    public ChannelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mTypeMap.get(viewType).createViewHolder(mInflater,parent);
    }

    @Override
    public void onBindViewHolder(ChannelViewHolder holder, int position) {
        if(getItemViewType(position)==IChannelType.TYPE_MY_CHANNEL){
            int myPosition = position-mMyHeaderCount;
            myPosition = myPosition<0||myPosition>=mMyChannelItems.size()?0:myPosition;
            mTypeMap.get(getItemViewType(position)).bindViewHolder(holder,position,mMyChannelItems.get(myPosition));
            return;
        }
        if(getItemViewType(position)==IChannelType.TYPE_REC_CHANNEL){
            int otherPosition = position-mMyChannelItems.size()-mMyHeaderCount-mRecHeaderCount;
            otherPosition = otherPosition<0||otherPosition>=mOtherChannelItems.size()?0:otherPosition;
            mTypeMap.get(getItemViewType(position)).bindViewHolder(holder,position,mOtherChannelItems.get(otherPosition));
            return;
        }
        mTypeMap.get(getItemViewType(position)).bindViewHolder(holder,position,null);
    }

    @Override
    public int getItemViewType(int position) {
        if(position<mMyHeaderCount)
            return IChannelType.TYPE_MY_CHANNEL_HEADER;
        if(position>=mMyHeaderCount&&position<mMyChannelItems.size()+mMyHeaderCount)
            return IChannelType.TYPE_MY_CHANNEL;
        if(position>=mMyChannelItems.size()+mMyHeaderCount&&position<mMyChannelItems.size()
                +mMyHeaderCount+mRecHeaderCount)
            return IChannelType.TYPE_REC_CHANNEL_HEADER;
        return IChannelType.TYPE_REC_CHANNEL;
    }

    @Override
    public int getItemCount() {
        return mMyChannelItems.size()+mOtherChannelItems.size()+mMyHeaderCount+mRecHeaderCount;
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if(toPosition > 2){
            ChannelBean item = mMyChannelItems.get(fromPosition - mMyHeaderCount);
            mMyChannelItems.remove(fromPosition - mMyHeaderCount);
            mMyChannelItems.add(toPosition - mMyHeaderCount, item);
            notifyItemMoved(fromPosition, toPosition);
        }
    }
    @Override
    public void onItemSwiped(int position) {
    }

    public static class ChannelViewHolder extends RecyclerView.ViewHolder implements ItemDragVHListener {

        public ChannelViewHolder(View itemView) {
            super(itemView);
        }
        @Override
        public void onItemSelected() {
            scaleItem(1.0f , 1.2f , 0.5f);
        }

        @Override
        public void onItemFinished() {
            scaleItem(1.2f , 1.0f , 1.0f);
        }

        public void scaleItem(float start , float end , float alpha) {
            ObjectAnimator anim1 = ObjectAnimator.ofFloat(itemView, "scaleX",
                    start, end);
            ObjectAnimator anim2 = ObjectAnimator.ofFloat(itemView, "scaleY",
                    start, end);
            ObjectAnimator anim3 = ObjectAnimator.ofFloat(itemView, "alpha",
                    alpha);

            AnimatorSet animSet = new AnimatorSet();
            animSet.setDuration(200);
            animSet.setInterpolator(new LinearInterpolator());
            animSet.playTogether(anim1, anim2 ,anim3);
            animSet.start();
        }
    }

    private class EditHandler extends EditModeHandler {
        @Override
        public void startEditMode(RecyclerView mRecyclerView) {
            doStartEditMode(mRecyclerView);
        }
        @Override
        public void cancelEditMode(RecyclerView mRecyclerView) {
            doCancelEditMode(mRecyclerView);
        }
        @Override
        public void clickMyChannel(RecyclerView mRecyclerView, ChannelAdapter.ChannelViewHolder holder) {
            RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
            int position = holder.getAdapterPosition();
            if(isEditMode){
                View targetView = layoutManager.findViewByPosition(mMyChannelItems.size()
                        +mMyHeaderCount+mRecHeaderCount);
                View currentView = mRecyclerView.getLayoutManager().findViewByPosition(position);
                int targetX ;
                int targetY;
                if(mRecyclerView.indexOfChild(targetView)>=0){
                    int spanCount = ((GridLayoutManager)layoutManager).getSpanCount();
                    targetX = targetView.getLeft();
                    targetY = targetView.getTop();
                    if ((mMyChannelItems.size()) % spanCount == 1) {
                        View preTargetView = layoutManager.findViewByPosition(mMyChannelItems.size()
                                + mMyHeaderCount+mRecHeaderCount - 1);
                        targetX = preTargetView.getLeft();
                        targetY = preTargetView.getTop();
                    }
                }else{
                    View preTargetView = layoutManager.findViewByPosition(mMyChannelItems.size()
                            + mMyHeaderCount+ mRecHeaderCount- 1);
                    targetX = preTargetView.getLeft();
                    targetY = preTargetView.getTop()+preTargetView.getHeight()+APPConst.ITEM_SPACE;
                }

                int myPosition = position - mMyHeaderCount;
                ChannelBean item = mMyChannelItems.get(myPosition);
                //如果item是默认的两个频道 不做改动
                if(item.getTabType() == 0 || item.getTabType() == 1){
                    return;
                }else {
                    //做改动 开启动画
                    moveMyToOther(position);
                    startAnimation(mRecyclerView, currentView, targetX, targetY);
                }

            }else{
                if(channelItemClickListener!=null){
                    channelItemClickListener.onChannelItemClick(mMyChannelItems,position-mMyHeaderCount);
                }
            }
        }

        @Override
        public void touchMyChannel(MotionEvent motionEvent, ChannelViewHolder holder) {
            if (!isEditMode) {
                return;
            }
            switch (MotionEventCompat.getActionMasked(motionEvent)) {
                case MotionEvent.ACTION_DOWN:
                    startTime = System.currentTimeMillis();
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (System.currentTimeMillis() - startTime > SPACE_TIME) {
                        mItemTouchHelper.startDrag(holder);
                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    startTime = 0;
                    break;
            }
            //如果编辑模式开启 只要item被触摸 我的频道动态更新
            //将数据赋予 当前频道管理器显示的我的频道(ChannelActivity中的mMyChannelList_2)
            mActivity.mMyChannelList_2 = mMyChannelItems;
        }

        @Override
        public void clickLongMyChannel(RecyclerView mRecyclerView, ChannelViewHolder holder) {
            if(!isEditMode){
                doStartEditMode(mRecyclerView);
                View view = mRecyclerView.getChildAt(0);
                if(view == mRecyclerView.getLayoutManager().findViewByPosition(0)){
                    TextView dragTip = (TextView) view.findViewById(R.id.id_my_header_tip_tv);
                    dragTip.setText("拖拽可以排序");

                    TextView tvBtnEdit = (TextView) view.findViewById(R.id.id_edit_mode);
                    tvBtnEdit.setText("完成");
                    tvBtnEdit.setSelected(true);
                }
                mItemTouchHelper.startDrag(holder);
            }
        }

        @Override
        public void clickRecChannel(RecyclerView mRecyclerView, ChannelViewHolder holder) {
            GridLayoutManager  layoutManager = (GridLayoutManager) mRecyclerView.getLayoutManager();
            int position = holder.getAdapterPosition();
            View targetView = layoutManager.findViewByPosition(mMyChannelItems.size() +mMyHeaderCount-1);
            View currentView = mRecyclerView.getLayoutManager().findViewByPosition(position);
            if(mRecyclerView.indexOfChild(targetView)>=0){
                int targetX = targetView.getLeft();
                int targetY = targetView.getTop();
                int spanCount = layoutManager.getSpanCount();
                View nextTargetView = layoutManager.findViewByPosition(mMyChannelItems.size() +mMyHeaderCount);
                if (mMyChannelItems.size() % spanCount == 0) {
                    targetX = nextTargetView.getLeft();
                    targetY = nextTargetView.getTop();
                }else{
                    targetX += targetView.getWidth() + 2* APPConst.ITEM_SPACE;
                }
                moveOtherToMy(position);
                startAnimation(mRecyclerView, currentView, targetX, targetY);
            }else{
                moveOtherToMy(position);
            }
            //只要点击推荐频道，我的频道动态更新
            // 将数据赋予 当前频道管理器显示的我的频道(ChannelActivity中的mMyChannelList_2)
            mActivity.mMyChannelList_2 = mMyChannelItems;
            /*ToastUtil util = new ToastUtil(mActivity,"当前本地频道list的频道数："+
                    String.valueOf(mActivity.mMyChannelList_2.size()));
            util.show();*/
        }
    }
    private void doStartEditMode(RecyclerView parent) {
        isEditMode = true;
        int visibleChildCount = parent.getChildCount();
        for (int i = 0; i < visibleChildCount; i++) {
            View view = parent.getChildAt(i);
            ImageView imgEdit = (ImageView) view.findViewById(R.id.id_delete_icon);
            if (imgEdit != null) {
                ChannelBean item = mMyChannelItems.get(i - mMyHeaderCount);
                if(item.getTabType() == 2 ){
                    imgEdit.setVisibility(View.VISIBLE);
                }else{
                    imgEdit.setVisibility(View.INVISIBLE);
                }
            }
        }
    }
    private void doCancelEditMode(RecyclerView parent) {
        isEditMode = false;
        int visibleChildCount = parent.getChildCount();
        for (int i = 0; i < visibleChildCount; i++) {
            View view = parent.getChildAt(i);
            ImageView imgEdit = (ImageView) view.findViewById(R.id.id_delete_icon);
            if (imgEdit != null) {
                imgEdit.setVisibility(View.INVISIBLE);
            }
        }
    }
    private void moveMyToOther(int position) {
        int myPosition = position - mMyHeaderCount;
        ChannelBean item = mMyChannelItems.get(myPosition);
        if(item.getTabType() == 0 || item.getTabType() == 1){
            return;
        }
        mMyChannelItems.remove(myPosition);
        mOtherChannelItems.add(0, item);
        notifyItemMoved(position, mMyChannelItems.size() + mMyHeaderCount+mRecHeaderCount);
    }
    private void moveOtherToMy(int position) {
        int recPosition = processItemRemoveAdd(position);
        if (recPosition == -1) {
            return;
        }
        notifyItemMoved(position, mMyChannelItems.size() + mMyHeaderCount-1);
    }
    private int processItemRemoveAdd(int position) {
        int startPosition = position - mMyChannelItems.size() - mRecHeaderCount-mMyHeaderCount;
        if (startPosition > mOtherChannelItems.size() - 1) {
            return -1;
        }
        ChannelBean item = mOtherChannelItems.get(startPosition);
        item.setEditStatus(isEditMode?1:0);
        mOtherChannelItems.remove(startPosition);
        mMyChannelItems.add(item);
        return position;
    }

    private void startAnimation(RecyclerView recyclerView, final View currentView, float targetX, float targetY) {
        final ViewGroup viewGroup = (ViewGroup) recyclerView.getParent();
        final ImageView mirrorView = addMirrorView(viewGroup, recyclerView, currentView);
        Animation animation = getTranslateAnimator(targetX - currentView.getLeft(),
                targetY - currentView.getTop());
        currentView.setVisibility(View.INVISIBLE);
        mirrorView.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                viewGroup.removeView(mirrorView);
                if (currentView.getVisibility() == View.INVISIBLE) {
                    currentView.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private ImageView addMirrorView(ViewGroup parent, RecyclerView recyclerView, View view) {
/**
 * 我们要获取cache首先要通过setDrawingCacheEnable方法开启cache，然后再调用getDrawingCache方法就可以获得view的cache图片了。
 buildDrawingCache方法可以不用调用，因为调用getDrawingCache方法时，若果cache没有建立，系统会自动调用buildDrawingCache方法生成cache。
 若想更新cache, 必须要调用destoryDrawingCache方法把旧的cache销毁，才能建立新的。
 当调用setDrawingCacheEnabled方法设置为false, 系统也会自动把原来的cache销毁。
 */
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(true);
        final ImageView mirrorView = new ImageView(recyclerView.getContext());
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        mirrorView.setImageBitmap(bitmap);
        view.setDrawingCacheEnabled(false);
        int[] locations = new int[2];
        view.getLocationOnScreen(locations);
        int[] parenLocations = new int[2];
        parent.getLocationOnScreen(parenLocations);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(bitmap.getWidth(), bitmap.getHeight());
        params.setMargins(locations[0], locations[1] - parenLocations[1], 0, 0);
        parent.addView(mirrorView, params);
        return mirrorView;
    }
    private TranslateAnimation getTranslateAnimator(float targetX, float targetY) {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.ABSOLUTE, targetX,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.ABSOLUTE, targetY);
        // RecyclerView默认移动动画250ms 这里设置360ms 是为了防止在位移动画结束后 remove(view)过早 导致闪烁
        translateAnimation.setDuration(360);
        translateAnimation.setFillAfter(true);
        return translateAnimation;
    }
    public void setChannelItemClickListener(ChannelItemClickListener channelItemClickListener){
        this.channelItemClickListener = channelItemClickListener ;
    }

    public interface ChannelItemClickListener {
        void onChannelItemClick(List<ChannelBean> list, int position);
    }
}
