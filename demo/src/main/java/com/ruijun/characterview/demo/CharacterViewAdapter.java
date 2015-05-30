package com.ruijun.characterview.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ruijun.android.characterview.CharacterView;

import java.util.ArrayList;

/**
 * Created by ruijun on 2015/5/30.
 */
public class CharacterViewAdapter extends RecyclerView.Adapter<CharacterViewAdapter.CharacterViewHolder>{
    private static final String TAG = CharacterViewHolder.class.getSimpleName();
    private ArrayList<String> mNameLists = new ArrayList<String>();
    private Context mContext;

    public CharacterViewAdapter(Context context, ArrayList<String> lists) {
        mContext = context;
        mNameLists = lists;
    }

    class CharacterViewHolder extends RecyclerView.ViewHolder {
        TextView mFileNameTextView;
        CharacterView mCharaterView;

        public CharacterViewHolder(final View itemView) {
            super(itemView);
            mCharaterView = (CharacterView) itemView.findViewById(R.id.rlv_name_view);
            mFileNameTextView = (TextView) itemView.findViewById(R.id.tv_name_holder);
        }

    }

    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_view_item, null);
        CharacterViewHolder viewHolder = new CharacterViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder viewHolder, int i) {
        viewHolder.mCharaterView.setTitleText(mNameLists.get(i));

        if(i%2 == 0){
            viewHolder.mCharaterView.setBackgroundColor(mContext.getResources().getColor(R.color.teal_500));
        }else{
            viewHolder.mCharaterView.setBackgroundColor(mContext.getResources().getColor(R.color.blue_500));
        }
        viewHolder.mFileNameTextView.setText(mNameLists.get(i));
    }

    @Override
    public int getItemCount() {
        return mNameLists.size();
    }
}
