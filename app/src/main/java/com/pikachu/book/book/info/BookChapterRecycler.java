package com.pikachu.book.book.info;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pikachu.book.R;
import com.pikachu.book.cls.json.JsonBookChapterCls;
import com.pikachu.book.cls.json.JsonBookCommentsCls;
import com.pikachu.book.tools.view.QMUIRadiusImageView;

import java.util.ArrayList;
import java.util.List;

public class BookChapterRecycler extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private final boolean isChapter;
    private final Context context;
    private OnClickItemCHListener onClickItemCHListener;
    private OnClickItemCListener onClickItemCListener;
    private List<JsonBookCommentsCls.DataBean.ListBean> list;
    private List<JsonBookChapterCls.DataBean.ChaptersBean> list2;


    public interface OnClickItemCHListener {
        void onClick(View v, int position, JsonBookChapterCls.DataBean.ChaptersBean listBean);
    }

    public interface OnClickItemCListener {
        void onClick(View v, int position, JsonBookCommentsCls.DataBean.ListBean listBean);
    }


    //章节
    public BookChapterRecycler(Context context, JsonBookChapterCls jsonBookChapterCls, OnClickItemCHListener onClickItemCHListener) {
        this.context = context;
        list2 = jsonBookChapterCls.getData().getChapters();
        this.isChapter = true;
        this.onClickItemCHListener = onClickItemCHListener;
    }


    //评论
    public BookChapterRecycler(Context context, JsonBookCommentsCls jsonBookCommentsCls, OnClickItemCListener onClickItemCListener) {
        this.context = context;
        list = jsonBookCommentsCls.getData().getList();
        this.isChapter = false;
        this.onClickItemCListener = onClickItemCListener;
    }


    public void addList(List<JsonBookCommentsCls.DataBean.ListBean> list) {
        if (this.list == null)
            this.list = new ArrayList<>();
        this.list.addAll(list);
    }

    public void addList2(List<JsonBookChapterCls.DataBean.ChaptersBean> list) {
        if (this.list2 == null)
            this.list2 = new ArrayList<>();

        this.list2.addAll(list);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (isChapter) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_book_chapter_item, parent, false));
        }
        return new ViewHolder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_book_comments_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        if (isChapter) {
            ViewHolder holder2 = (ViewHolder) holder;
            JsonBookChapterCls.DataBean.ChaptersBean chaptersBean = list2.get(position);
            holder2.uiBookChLin1.setOnClickListener(v -> onClickItemCHListener.onClick(v, position, chaptersBean));
            holder2.uiBookChText1.setText(chaptersBean.getName());
        } else {
            ViewHolder2 holder1 = (ViewHolder2) holder;
            JsonBookCommentsCls.DataBean.ListBean listBean = list.get(position);
            holder1.uiBookCLin1.setOnClickListener(v -> onClickItemCListener.onClick(v, position, listBean));
            Glide.with(context).load(listBean.getAvatar()).into(holder1.uiBookCQmui1);//用户头像
            holder1.uiBookCText1.setText(listBean.getSm_name());//用户名
            holder1.uiBookCText2.setText(listBean.getPub_time());//评论时间
            holder1.uiBookCText3.setText(Html.fromHtml(listBean.getText()));//内容
            holder1.uiBookCText4.setText(listBean.getReplynum());//评论数
            holder1.uiBookCText5.setText(listBean.getZan());//赞
            if (listBean.getPics().size() > 0) {
                holder1.uiBookCQmui2.setVisibility(View.VISIBLE);
                Glide.with(context).load(listBean.getPics().get(0)).into(holder1.uiBookCQmui2);//评论的图片
            } else {
                holder1.uiBookCQmui2.setVisibility(View.GONE);
            }

        }

    }


    @Override
    public int getItemCount() {
        if (isChapter)
            return list2.size();
        return list.size();
    }


    //章节
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout uiBookChLin1;
        public TextView uiBookChText1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            uiBookChLin1 = itemView.findViewById(R.id.ui_book_ch_lin1);
            uiBookChText1 = itemView.findViewById(R.id.ui_book_ch_text1);
        }
    }


    //评论
    public static class ViewHolder2 extends RecyclerView.ViewHolder {

        public LinearLayout uiBookCLin1;
        public QMUIRadiusImageView uiBookCQmui1, uiBookCQmui2;
        public TextView uiBookCText1;
        public TextView uiBookCText2;
        public TextView uiBookCText3;
        public TextView uiBookCText4;
        public TextView uiBookCText5;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            uiBookCLin1 = itemView.findViewById(R.id.ui_book_c_lin1);
            uiBookCQmui1 = itemView.findViewById(R.id.ui_book_c_qmui1);
            uiBookCQmui2 = itemView.findViewById(R.id.ui_book_c_qmui2);
            uiBookCText1 = itemView.findViewById(R.id.ui_book_c_text1);
            uiBookCText2 = itemView.findViewById(R.id.ui_book_c_text2);
            uiBookCText3 = itemView.findViewById(R.id.ui_book_c_text3);
            uiBookCText4 = itemView.findViewById(R.id.ui_book_c_text4);
            uiBookCText5 = itemView.findViewById(R.id.ui_book_c_text5);
        }
    }

    public List<JsonBookChapterCls.DataBean.ChaptersBean> getList2() {
        return list2;
    }
}
