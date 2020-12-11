package com.pikachu.book.cls.sql;

import com.pikachu.book.tools.untli.AppInfo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;


@Entity
public class F2BooksData implements Serializable {

    @Transient
    private static final long serialVersionUID = 48L; //  序列化
    @Id(autoincrement = true)
    private Long id;    //创建数据库储存 id
    private String knotName;//章节名
    private String knotImageUrl;//图片url
    private String knotConnectUrl;//书内容url
    private int apiSize; //用于保存当前章节在  这本书的API第几个
    private int apiPage; //用于保存当前章节在  这本书的API第页 == {page}
    private int apiOrder;//必要参数 == {order} //0倒序 1正序
    private String apiTitle;//书名  == {title}
    private String apiAuthor;//作者 == {author}
    private String apiId;//书id == {novelid}
    private String apiReaduv;//阅读量
    private String apiDescription;//内容
    private String apiMarkScore;//评分
    private String apiMonthuv;//月热度

    private String apiHost;//必要参数 == {host}
    private String apiToken;//必要参数 == {token}

    private int bookBrightness = -1;//亮度
    private int bookFontSize = 16;//字体大小
    private int bootTheme = -1;//主题 0 1 2 3  -1随机
    private int bookType = 0;  // 1浏览记录  2书架   有2没1（加入书架会删除浏览记录）

    public F2BooksData(){}

    @Generated(hash = 1638268033)
    public F2BooksData(Long id, String knotName, String knotImageUrl, String knotConnectUrl, int apiSize, int apiPage, int apiOrder, String apiTitle, String apiAuthor, String apiId, String apiReaduv, String apiDescription, String apiMarkScore, String apiMonthuv, String apiHost,
            String apiToken, int bookBrightness, int bookFontSize, int bootTheme, int bookType) {
        this.id = id;
        this.knotName = knotName;
        this.knotImageUrl = knotImageUrl;
        this.knotConnectUrl = knotConnectUrl;
        this.apiSize = apiSize;
        this.apiPage = apiPage;
        this.apiOrder = apiOrder;
        this.apiTitle = apiTitle;
        this.apiAuthor = apiAuthor;
        this.apiId = apiId;
        this.apiReaduv = apiReaduv;
        this.apiDescription = apiDescription;
        this.apiMarkScore = apiMarkScore;
        this.apiMonthuv = apiMonthuv;
        this.apiHost = apiHost;
        this.apiToken = apiToken;
        this.bookBrightness = bookBrightness;
        this.bookFontSize = bookFontSize;
        this.bootTheme = bootTheme;
        this.bookType = bookType;
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

    public int getApiSize() {
        return apiSize;
    }

    public void setApiSize(int apiSize) {
        this.apiSize = apiSize;
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBookType() {
        return bookType;
    }

    public void setBookType(int bookType) {
        this.bookType = bookType;
    }


    public String getApiDescription() {
        return apiDescription;
    }

    public void setApiDescription(String apiDescription) {
        this.apiDescription = apiDescription;
    }

    public String getApiMarkScore() {
        return apiMarkScore;
    }

    public void setApiMarkScore(String apiMarkScore) {
        this.apiMarkScore = apiMarkScore;
    }

    public String getApiMonthuv() {
        return apiMonthuv;
    }

    public void setApiMonthuv(String apiMonthuv) {
        this.apiMonthuv = apiMonthuv;
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

    public String getApiReaduv() {
        return this.apiReaduv;
    }

    public void setApiReaduv(String apiReaduv) {
        this.apiReaduv = apiReaduv;
    }




}