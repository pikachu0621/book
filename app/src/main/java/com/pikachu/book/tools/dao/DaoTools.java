package com.pikachu.book.tools.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.pikachu.book.cls.json.JsonBookItemCls;
import com.pikachu.book.cls.sql.F2BooksData;
import com.pikachu.book.tools.untli.AppInfo;

import org.greenrobot.greendao.annotation.NotNull;

import java.util.List;

public class DaoTools {
    private DaoMaster.DevOpenHelper mHelper;//获取Helper对象
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private Context context;
    private F2BooksData f2BooksData;
    @SuppressLint("StaticFieldLeak")
    private static DaoTools daoTools;
    private final F2BooksDataDao f2BooksDataDao;

    /**
     * 获取单例
     */
    public static DaoTools getInstance(Context context) {
        if (daoTools == null) {
            synchronized (DaoTools.class) {
                if (daoTools == null) {
                    daoTools = new DaoTools(context);
                }
            }
        }
        return daoTools;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public DaoTools(Context context) {
        this.context = context;
        mHelper = new DaoMaster.DevOpenHelper(context, AppInfo.APP_SQL_NAME, null);
        mDaoMaster = new DaoMaster(getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
        f2BooksDataDao = mDaoSession.getF2BooksDataDao();
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (mHelper == null)
            mHelper = new DaoMaster.DevOpenHelper(context, AppInfo.APP_SQL_NAME, null);
        return mHelper.getReadableDatabase();
    }

    /**
     * 获取可写数据库
     *
     * @return
     */
    private SQLiteDatabase getWritableDatabase() {
        if (mHelper == null)
            mHelper = new DaoMaster.DevOpenHelper(context, AppInfo.APP_SQL_NAME, null);
        return mHelper.getWritableDatabase();
    }


    /**
     * 会自动判定是插入还是替换
     *
     * @param f2BooksData
     */
    public void insertOrReplace(F2BooksData f2BooksData) {
        f2BooksDataDao.insertOrReplace(f2BooksData);
    }

    /**
     * 插入一条记录，表里面要没有与之相同的记录
     *
     * @param f2BooksData
     */
    public long insert(F2BooksData f2BooksData) {
        return f2BooksDataDao.insert(f2BooksData);
    }


    /**
     * 更新数据
     *
     * @param f2BooksData
     */
    public void update(F2BooksData f2BooksData) {
        F2BooksData f2BooksData1 = f2BooksDataDao.queryBuilder().where(F2BooksDataDao.Properties.Id.eq(f2BooksData.getId())).build().unique();//拿到之前的记录
        if (f2BooksData1 != null) {
            f2BooksDataDao.update(f2BooksData);
        }
    }


    /**
     * 查询历史
     */
    public List<F2BooksData> inquireBookHistory() {
        /*if (f2BooksData != null && f2BooksData.size() > 0) {
            return f2BooksData;
        }
        return null;*/
        return (List<F2BooksData>) f2BooksDataDao.queryBuilder().where(F2BooksDataDao.Properties.BookType.eq(1)).build().list();
    }


    /**
     * 查询书架
     */
    public List<F2BooksData> inquireBookFrame() {
        return (List<F2BooksData>) f2BooksDataDao.queryBuilder().where(F2BooksDataDao.Properties.BookType.eq(2)).build().list();
    }


    /**
     * 按书名查询    模糊查询
     */
    public List<F2BooksData> inquireBookName(String bookName) {
        return (List<F2BooksData>) f2BooksDataDao.queryBuilder().where(F2BooksDataDao.Properties.ApiTitle.like(bookName)).build().list();
    }

    /**
     * 按书名查询    精准查询
     */
    public F2BooksData inquireBook(String bookName) {
        List<F2BooksData> list =  (List<F2BooksData>) f2BooksDataDao.queryBuilder().where(F2BooksDataDao.Properties.ApiTitle.eq(bookName)).build().list();
        F2BooksData f2BooksData = null;
        if (list!=null && list.size()>0){
            f2BooksData  = list.get(0);
        }
        return f2BooksData;
    }

    /**
     * 按id查询    精准查询
     */
    public F2BooksData inquireBookID(long id) {
        List<F2BooksData> list =  (List<F2BooksData>) f2BooksDataDao.queryBuilder().where(F2BooksDataDao.Properties.Id.eq(id)).build().list();
        F2BooksData f2BooksData = null;
        if (list!=null && list.size()>0){
          f2BooksData  = list.get(0);
        }
        return f2BooksData;
    }


    /**
     * 查询所有数据
     */
    public List<F2BooksData> inquireAll() {
        return f2BooksDataDao.queryBuilder().list();
    }


    /**
     * 按id 删除
     */
    public void delete(F2BooksData f2BooksData) {
        f2BooksDataDao.queryBuilder().where(F2BooksDataDao.Properties.Id.eq(f2BooksData.getId())).buildDelete().executeDeleteWithoutDetachingEntities();
    }


    /**
     * 根据书名 添加一本书到书架
     *
     * @param f2BooksData
     */
    public void addBookFrame(F2BooksData f2BooksData) {

        F2BooksData f2BooksData1 = inquireBook(f2BooksData.getApiTitle());
        f2BooksData.setBookType(2);
        if (f2BooksData1 != null) {
            f2BooksData.setId(f2BooksData1.getId());
        }
        insertOrReplace(f2BooksData);

    }


    /**
     * 根据书名 添加一本书到历史
     *
     * @param f2BooksData
     */
    public void addBookHistory(F2BooksData f2BooksData) {
        F2BooksData f2BooksData1 = inquireBook(f2BooksData.getApiTitle());

        if (f2BooksData1 != null) {
            if (f2BooksData1.getBookType() != 2) {
                f2BooksData.setBookType(1);
                f2BooksData.setId(f2BooksData1.getId());
                insertOrReplace(f2BooksData);
            }
        } else {
            f2BooksData.setBookType(1);
            insertOrReplace(f2BooksData);
        }
    }









    //对象转换


    //F2BooksData -> ListBean
    public static JsonBookItemCls.ListBean f2BooksDataToListBean(@NotNull F2BooksData f2BooksData, JsonBookItemCls.ListBean listBean){
        if (listBean == null)
            listBean = new JsonBookItemCls.ListBean();
        listBean.setToken(f2BooksData.getApiToken());
        listBean.setTitle(f2BooksData.getApiTitle());
        listBean.setId(f2BooksData.getApiId());
        listBean.setHost(f2BooksData.getApiHost());
        listBean.setIcon(f2BooksData.getKnotImageUrl());
        listBean.setReaduv(f2BooksData.getApiReaduv());
        listBean.setDescription(f2BooksData.getApiDescription());
        listBean.setAuthor(f2BooksData.getApiAuthor());
        listBean.setMark_score(f2BooksData.getApiMarkScore());
        listBean.setMonthuv(f2BooksData.getApiMonthuv());
        return listBean;
    }

    //ListBean - > F2BooksData
    public static F2BooksData  listBeanToF2BooksData(@NotNull JsonBookItemCls.ListBean listBean,F2BooksData  f2BooksData){
        if (f2BooksData == null)
            f2BooksData = new F2BooksData();

        f2BooksData.setApiToken(listBean.getToken());
        f2BooksData.setApiTitle(listBean.getTitle());
        f2BooksData.setApiId(listBean.getId());
        f2BooksData.setApiHost(listBean.getHost());
        f2BooksData.setKnotImageUrl(listBean.getIcon());
        f2BooksData.setApiReaduv(listBean.getReaduv());
        f2BooksData.setApiDescription(listBean.getDescription());
        f2BooksData.setApiAuthor(listBean.getAuthor());
        f2BooksData.setApiMarkScore(listBean.getMark_score());
        f2BooksData.setApiMonthuv(listBean.getMonthuv());
        return f2BooksData;
    }




    //F2BooksData -> ListBean
    public static JsonBookItemCls.ListBean  f2BooksDataToListBean( F2BooksData f2BooksData){
        return f2BooksDataToListBean(f2BooksData,null);
    }

    //ListBean - > F2BooksData
    public static F2BooksData  listBeanToF2BooksData(JsonBookItemCls.ListBean listBean){
        return listBeanToF2BooksData(listBean,null);
    }


    //对象复制
    public static F2BooksData f2BooksDataToF2BooksData(F2BooksData f1,F2BooksData f2){
        return null;
    }



}
