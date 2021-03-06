package com.rayho.tsxiu.ui.channelhelper.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rayho.tsxiu.R;
import com.rayho.tsxiu.ui.channelhelper.adapter.ChannelAdapter;
import com.rayho.tsxiu.ui.channelhelper.base.IChannelType;
import com.rayho.tsxiu.ui.channelhelper.bean.ChannelBean;


/**
 * Created by goach on 2016/9/28.
 */

public class RecChannelHeaderWidget implements IChannelType {
    @Override
    public ChannelAdapter.ChannelViewHolder createViewHolder(LayoutInflater mInflater, ViewGroup parent) {
        return new MyChannelHeaderViewHolder(mInflater.inflate(R.layout.activity_channel_rec_header,parent,false));
    }

    @Override
    public void bindViewHolder(ChannelAdapter.ChannelViewHolder holder, int position, ChannelBean data) {

    }
    public class MyChannelHeaderViewHolder extends ChannelAdapter.ChannelViewHolder{

        public MyChannelHeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
