package com.pikachu.book.cls;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonTabCls {

    @SerializedName("男生")
    private List<Bean> boy;
    @SerializedName("女生")
    private List<Bean> girl;
    @SerializedName("耽美")
    private List<Bean> good;
    @SerializedName("热门排行榜")
    private List<Bean> ranking;


    public List<Bean> getBoy() {
        return boy;
    }

    public void setBoy(List<Bean> boy) {
        this.boy = boy;
    }

    public List<Bean> getGirl() {
        return girl;
    }

    public void setGirl(List<Bean> girl) {
        this.girl = girl;
    }

    public List<Bean> getGood() {
        return good;
    }

    public void setGood(List<Bean> good) {
        this.good = good;
    }

    public List<Bean> getRanking() {
        return ranking;
    }

    public void setRanking(List<Bean> ranking) {
        this.ranking = ranking;
    }


    public static class Bean {
        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        /**
         * tag : 玄幻
         * query : 玄幻
         */
        private String tag;
        private String query;
    }


}
