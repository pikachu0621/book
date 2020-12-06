package com.pikachu.book.home.fragment.one.fragment.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.pikachu.book.R;
import com.pikachu.book.cls.json.JsonBookItemCls;
import com.pikachu.book.tools.view.ScoreView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    private final OnClickItemListener onClickItemListener;
    private List<JsonBookItemCls.ListBean> list;
    private final Context context;
    private final boolean isBoy;


    public interface OnClickItemListener{
        void onClick(View v, int position, JsonBookItemCls.ListBean listBean);
    }

    public RecyclerAdapter(Context context, JsonBookItemCls jsonBookItemCls,boolean isBoy,OnClickItemListener onClickItemListener) {
        this.context = context;
        list = jsonBookItemCls.getList();
        this.isBoy = isBoy;
        this.onClickItemListener = onClickItemListener;
    }


    public void addList(List<JsonBookItemCls.ListBean> list) {
        if (this.list == null)
            this.list = new ArrayList<>();
        this.list.addAll(list);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_m_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        JsonBookItemCls.ListBean listBean = list.get(position);
        holder.uiMIText1.setText(listBean.getTitle());
        holder.uiMIText2.setText("作者："+listBean.getAuthor());
        holder.uiMIText3.setText(listBean.getDescription());
        holder.uiMIScore.setMaxPoints(5);
        holder.uiMIScore.setPoints(Float.parseFloat(listBean.getMark_score()));
        holder.uiMIScore.setNotColor(context.getResources().getColor(isBoy ? R.color.purple_700_2 : R.color.purge_800_2));
        holder.uiMIScore.setTheColor(context.getResources().getColor(isBoy ? R.color.purple_700 : R.color.purge_800));


        RequestOptions options = new RequestOptions()
                .placeholder( R.drawable.ic_book_load)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter();

        Glide.with(context)
                .load(listBean.getIcon())
                .apply(options)
                .thumbnail(Glide.with(context)
                        .load( R.drawable.ic_book_load))
                .into(holder.uiMIImage1);



        holder.lin1.setOnClickListener(v -> {
            onClickItemListener.onClick(v,position,listBean);
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout lin1;
        public ImageView uiMIImage1;
        public TextView uiMIText1;
        public ScoreView uiMIScore;
        public TextView uiMIText2;
        public TextView uiMIText3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lin1 = itemView.findViewById(R.id.ui_m_i_lin1);
            uiMIImage1 = itemView.findViewById(R.id.ui_m_i_image1);
            uiMIText1 = itemView.findViewById(R.id.ui_m_i_text1);
            uiMIScore = itemView.findViewById(R.id.ui_m_i_score);
            uiMIText2 = itemView.findViewById(R.id.ui_m_i_text2);
            uiMIText3 = itemView.findViewById(R.id.ui_m_i_text3);
        }
    }


}
