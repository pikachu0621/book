package com.pikachu.book.home.fragment.tow.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pikachu.book.R;
import com.pikachu.book.cls.sql.F2BooksData;
import com.pikachu.book.tools.untli.Tools;
import com.pikachu.book.tools.view.QMUIRadiusImageView;

import java.util.List;

public class F2BooksRecyclerAdapter extends RecyclerView.Adapter<F2BooksRecyclerAdapter.ViewHolder> {


    private List<F2BooksData> listData;
    private final Context context;
    private final boolean isBooks;
    private final OnClickItemListener onClickItemListener;

    public F2BooksRecyclerAdapter(Context context,boolean isBooks,List<F2BooksData> listData,OnClickItemListener onClickItemListener) {
        this.context = context;
        this.isBooks = isBooks;
        this.listData = listData;
        this.onClickItemListener = onClickItemListener;
    }


    public void refreshData(List<F2BooksData> listData){
        this.listData = listData;
    }


    public interface OnClickItemListener {
        void noClickItem(View v,int position,F2BooksData f2BooksData);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_m_f2_book_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        F2BooksData f2BooksData = listData.get(position);
        holder.uiMBookItemLin.setOnClickListener(v -> onClickItemListener.noClickItem(v,position,f2BooksData));
        Glide.with(context).load(f2BooksData.getKnotImageUrl()).into(holder.uiMBookItemQmui);
        holder.uiMBookItemText1.setText(f2BooksData.getApiTitle());
        if (isBooks){
            holder.uiMBookItemText2.setText(f2BooksData.getKnotName());
        }else {
            holder.uiMBookItemText2.setVisibility(View.GONE);
            holder.uiMBookItemQmui.getLayoutParams().height = Tools.dp2px(context,100);
            holder.uiMBookItemText1.setTextSize(14);
        }

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public LinearLayout uiMBookItemLin;
        public QMUIRadiusImageView uiMBookItemQmui;
        public TextView uiMBookItemText1;
        public TextView uiMBookItemText2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            uiMBookItemLin = itemView.findViewById(R.id.ui_m_book_item_lin);
            uiMBookItemQmui = itemView.findViewById(R.id.ui_m_book_item_qmui);
            uiMBookItemText1 = itemView.findViewById(R.id.ui_m_book_item_text1);
            uiMBookItemText2 = itemView.findViewById(R.id.ui_m_book_item_text2);
        }

    }

}
