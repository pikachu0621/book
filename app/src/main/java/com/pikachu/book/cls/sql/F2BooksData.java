package com.pikachu.book.cls.sql;

import com.pikachu.book.tools.untli.AppInfo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;




@Entity
public class F2BooksData implements Serializable {

    @Transient
    private static final long serialVersionUID = 48L;
    @Id(autoincrement = true)
    private Long id;
    //创建数据库储存
    private String knotName;//章节名
    private String knotImageUrl;//图片url
    private String knotConnectUrl;//书内容url
    private int size; //用于保存当前章节在  这本书的API第几个
    private int apiPage; //用于保存当前章节在  这本书的API第页 == {page}
    private int apiOrder;//必要参数 == {order} //0倒序 1正序
    private String apiTitle;//书名  == {title}
    private String apiAuthor;//作者 == {author}
    private String apiId;//书id == {novelid}
    private String apiHost;//必要参数 == {host}
    private String apiToken;//必要参数 == {token}
    private int bookBrightness = -1;//亮度
    private int bookFontSize = 16;//字体大小
    private int bootTheme = -1;//主题 0 1 2 3  -1随机

    public F2BooksData(String knotName, String knotImageUrl, String knotConnectUrl, int size, int apiPage, int apiOrder, String apiTitle, String apiAuthor, String apiId, String apiHost, String apiToken, int bookBrightness, int bookFontSize, int bootTheme) {
        this.knotName = knotName;
        this.knotImageUrl = knotImageUrl;
        this.knotConnectUrl = knotConnectUrl;
        this.size = size;
        this.apiPage = apiPage;
        this.apiOrder = apiOrder;
        this.apiTitle = apiTitle;
        this.apiAuthor = apiAuthor;
        this.apiId = apiId;
        this.apiHost = apiHost;
        this.apiToken = apiToken;
        this.bookBrightness = bookBrightness;
        this.bookFontSize = bookFontSize;
        this.bootTheme = bootTheme;
    }

    public F2BooksData(){}

    @Generated(hash = 1432634918)
    public F2BooksData(Long id, String knotName, String knotImageUrl, String knotConnectUrl, int size, int apiPage, int apiOrder, String apiTitle, String apiAuthor, String apiId, String apiHost, String apiToken, int bookBrightness, int bookFontSize,
            int bootTheme) {
        this.id = id;
        this.knotName = knotName;
        this.knotImageUrl = knotImageUrl;
        this.knotConnectUrl = knotConnectUrl;
        this.size = size;
        this.apiPage = apiPage;
        this.apiOrder = apiOrder;
        this.apiTitle = apiTitle;
        this.apiAuthor = apiAuthor;
        this.apiId = apiId;
        this.apiHost = apiHost;
        this.apiToken = apiToken;
        this.bookBrightness = bookBrightness;
        this.bookFontSize = bookFontSize;
        this.bootTheme = bootTheme;
    }





    public int getApiOrder() {
        return apiOrder;
    }

    public void setApiOrder(int apiOrder) {
        this.apiOrder = apiOrder;
    }

    public int getBookBrightness() {
        return bookBrightness;
    }

    public void setBookBrightness(int bookBrightness) {
        this.bookBrightness = bookBrightness;
    }

    public int getBookFontSize() {
        return bookFontSize;
    }

    public void setBookFontSize(int bookFontSize) {
        this.bookFontSize = bookFontSize;
    }

    public int getBootTheme() {
        return bootTheme;
    }

    public void setBootTheme(int bootTheme) {
        this.bootTheme = bootTheme;
    }






    public String getKnotImageUrl() {
        return knotImageUrl;
    }

    public void setKnotImageUrl(String knotImageUrl) {
        this.knotImageUrl = knotImageUrl;
    }

    public String getKnotConnectUrl() {
        return knotConnectUrl;
    }

    public void setKnotConnectUrl(String knotConnectUrl) {
        this.knotConnectUrl = knotConnectUrl;
    }

    public String getApiTitle() {
        return apiTitle;
    }

    public void setApiTitle(String apiTitle) {
        this.apiTitle = apiTitle;
    }

    public String getKnotName() {
        return knotName;
    }

    public void setKnotName(String knotName) {
        this.knotName = knotName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getApiPage() {
        return apiPage;
    }

    public void setApiPage(int apiPage) {
        this.apiPage = apiPage;
    }

    public String getApiAuthor() {
        return apiAuthor;
    }

    public void setApiAuthor(String apiAuthor) {
        this.apiAuthor = apiAuthor;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getApiHost() {
        return apiHost;
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

















    // 返回 章节列表url
    public String getApiUrl(int page){

        return AppInfo.APP_API_BOOK_CH.replace(AppInfo.APP_RE_STR[1], "" + page)
                .replace(AppInfo.APP_RE_STR[2], apiTitle)
                .replace(AppInfo.APP_RE_STR[3], apiAuthor)
                .replace(AppInfo.APP_RE_STR[4], apiId)
                .replace(AppInfo.APP_RE_STR[5], apiHost)
                .replace(AppInfo.APP_RE_STR[6], apiToken)
                .replace(AppInfo.APP_RE_STR[7], ""+apiOrder);
    }

    // 返回 章节列表url
    public String getApiUrl(){
        return AppInfo.APP_API_BOOK_CH.replace(AppInfo.APP_RE_STR[1], "" + apiPage)
                .replace(AppInfo.APP_RE_STR[2], apiTitle)
                .replace(AppInfo.APP_RE_STR[3], apiAuthor)
                .replace(AppInfo.APP_RE_STR[4], apiId)
                .replace(AppInfo.APP_RE_STR[5], apiHost)
                .replace(AppInfo.APP_RE_STR[6], apiToken)
                .replace(AppInfo.APP_RE_STR[7], ""+apiOrder);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}