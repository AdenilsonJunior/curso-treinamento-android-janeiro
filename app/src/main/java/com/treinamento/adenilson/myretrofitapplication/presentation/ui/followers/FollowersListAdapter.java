package com.treinamento.adenilson.myretrofitapplication.presentation.ui.followers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.treinamento.adenilson.myretrofitapplication.R;
import com.treinamento.adenilson.myretrofitapplication.domain.entity.Follower;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by adenilson on 14/01/17.
 */

public class FollowersListAdapter extends BaseAdapter {

    private List<Follower> followers;
    private ViewHolder mHolder;

    public FollowersListAdapter(List<Follower> followers) {
        this.followers = followers;
    }

    @Override
    public int getCount() {
        return followers.size();
    }

    @Override
    public Follower getItem(int i) {
        return followers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return followers.get(i).id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Follower follower = followers.get(i);

        if (view != null) {
            mHolder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_followers,
                    viewGroup, false);
            mHolder = new ViewHolder(view);
            view.setTag(mHolder);
        }

        Glide.with(viewGroup.getContext()).load(follower.avatarUrl).into(mHolder.mImageViewProfile);
        mHolder.mTextViewId.setText(String.valueOf(follower.id));
        mHolder.mTextViewLogin.setText(follower.login);


        return view;
    }

    public class ViewHolder {

        @BindView(R.id.image_view_profile)
        ImageView mImageViewProfile;
        @BindView(R.id.text_view_login)
        TextView mTextViewLogin;
        @BindView(R.id.text_view_id)
        TextView mTextViewId;

        public ViewHolder(View v) {
            ButterKnife.bind(this, v);
        }

    }
}
