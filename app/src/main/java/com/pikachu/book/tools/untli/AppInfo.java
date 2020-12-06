/**
 * APP 全局配置
 */
package com.pikachu.book.tools.untli;

public class AppInfo {












    /////// 界面跳转 ////




    ////////////    API     ///////


    //用于加载小说列表
    public static final String APP_API_BOOK_TYPE_LIST = "https://api.xiaoshuo1-sm.com/sc/1/channel/channel/?format=json&page={page}&size=20&q={type}&_t=1605886162002&_=1605886162002&callback=jsonp{page}";
    public static final String[] APP_RE_STR = {"{type}"/*0*/,"{page}"/*1*/,"{title}"/*2*/,"{author}"/*3*/,"{novelid}"/*4*/,"{host}"/*5*/,"{token}"/*6*/};
    //用于加载小说分类（Tab）
    public static final String APP_API_BOOK_TAB_LIST = "https://xiaoshuo.sm.cn";
    public static final String[] APP_JS_STR = {" tags: ",", };"};

    //评论
    public static final String APP_API_COMMENTS = "https://xiaoshuo.sm.cn/sc/2/comment/book?format=json&size=10&title={title}&author={author}&novelid={novelid}&page={page}&callback=jsonp{page}";

    //详情
    public static final String APP_API_BOOK_INFO = "https://m.sm.cn/s?q={type}";
    public static final String[] APP_JS_INFO_STR = {"introUrl\":\"","\"","&host=","&","&token="};

    //章节列表
    public static final String APP_API_BOOK_CH = "https://m.sm.cn/api/rest?format=json&callback=jsonp{page}&method=novelintro.menu&q={title}&author={author}&novelid={novelid}&host={host}&token={token}&page={page}";




}
