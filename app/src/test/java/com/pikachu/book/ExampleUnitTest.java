package com.pikachu.book;

import android.util.Log;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        /*assertEquals(4, 2 + 2);*/

        String str = cutStr("112233546644", "", "");

        Log.i("test_t",str);
    }







    /**
     * 根据字符串截取原字符串中某段字符串
     *
     *
     * @param content 源字符串
     * @param indexStr 开始字符串 （唯一） 可空（空为从0开始）
     * @param endStr 结束字符串（唯一）可空（空为截取到最后一个）
     * @return
     */
    public static String cutStr(String content, String indexStr, String endStr) {
        int index ,end ;



        if (indexStr == null || indexStr.equals(""))
            index = 0;
        else {
            index = content.indexOf(indexStr);
            if (index == -1)
                index = 0;
            else
                index += indexStr.length();
        }

        //第一次截取
        String oneStr = content.substring(index);

        if (endStr == null || endStr.equals(""))
            end = oneStr.length();
        else {
            end = oneStr.indexOf(endStr);
            if (end == -1)
                end = oneStr.length();
        }

        //第二次截取
        return oneStr.substring(0, end);
    }





}