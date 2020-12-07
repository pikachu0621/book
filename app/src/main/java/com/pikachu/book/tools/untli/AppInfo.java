/**
 * APP 全局配置
 */
package com.pikachu.book.tools.untli;

public class AppInfo {












    /////// 界面跳转 ////




    ////////////    API     ///////


    //用于加载小说列表
    public static final String APP_API_BOOK_TYPE_LIST = "https://api.xiaoshuo1-sm.com/sc/1/channel/channel/?format=json&page={page}&size=20&q={type}&_t=1605886162002&_=1605886162002&callback=jsonp{page}";
    public static final String[] APP_RE_STR = {"{type}"/*分类名 0*/,"{page}"/*页码 1*/,"{title}"/*书名 2*/,"{author}"/*作者名 3*/,"{novelid}"/*书名+作者名id 4*/,"{host}"/*host 5*/,"{token}"/*token 6*/,"{order}"/*顺序0到序，1正序 7*/};
    //用于加载小说分类（Tab）
    public static final String APP_API_BOOK_TAB_LIST = "https://xiaoshuo.sm.cn";
    public static final String[] APP_JS_STR = {" tags: ",", };"};

    //评论
    public static final String APP_API_COMMENTS = "https://xiaoshuo.sm.cn/sc/2/comment/book?format=json&size=10&title={title}&author={author}&novelid={novelid}&page={page}&callback=jsonp{page}";

    //详情
    public static final String APP_API_BOOK_INFO = "https://m.sm.cn/s?q={type}";
    public static final String[] APP_JS_INFO_STR = {"introUrl\":\""/*0*/,"\""/*1*/,"&host="/*2*/,"&"/*3*/,"&token="/*4*/,"&title="/*5*/};

    //章节列表
    public static final String APP_API_BOOK_CH = "https://quark.sm.cn/api/rest?format=json&order={order}&callback=jsonp{page}&method=novelintro.menu&q={title}&author={author}&novelid={novelid}&host={host}&token={token}&page={page}";
    //https://quark.sm.cn/api/rest?
    //https://m.sm.cn/api/rest?



}
