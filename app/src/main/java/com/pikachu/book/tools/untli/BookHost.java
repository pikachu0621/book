/**
 *
 * 根据host 进行不同小说数据的截取
 *
 * 类似 畅读 V0.1
 *
 *
 */
package com.pikachu.book.tools.untli;

import android.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;


public class BookHost {

    //http://www.qk-tv.com/1362_1460.html utf-8   超级慢
    //http://www.xs7.com/shu/58/58266/63686705.html gbk
    //https://www.zbzw.la/book/40965/89405021.html utf-8
    //https://www.630shu.net/shu/43404/61559805.html gbk

    //https://www.lwxs99.com/book/32222/24333515.html gbk
    //http://www.qudushu.com/html/701/701467/123501406.html  utf-8
    //http://www.caiuun.com/279_5689.html utf-8
    //https://www.114zw.org/book/77108548/594491645.html gbk
    //http://www.biqufu.com/book/72/72416/774639.html gbk
    //http://lanmaoco.com/2817_4079.html utf-8
    //https://www.23xsw.cc/book/73/73073/651558480.html gbk
    //https://www.02shu.cc/87_87330/48514885.html gbk
    //http://www.lwxs.io/article/18237/7258268.html gbk
    //https://www.bqguan.com/bqg68515/3575667.html utf-8
    //http://www.yanxiran.com/book_192308/47.html utf-8
    //http://www.kanshula.com/book/gongqiangliu/2463869.shtml utf-8     超级慢
    //http://www.xiangcunxiaoshuo.com/html/644820/108326332.html gbk
    //http://du1du.org/txt-122323/168021718.htm gbk
    //https://www.lingyutxt.com/book_20374/4612873.html utf-8
    //https://www.lwxsw.cc/book/30177/17795090.html gbk
    //http://www.xiaoshuobuluo.com/book_80261/47022047.html gbk
    //https://www.meiyuxs.com/nr/25800/8330970.html  utf-8    x
    //http://www.88xiaoshuo.com/Partlist/3390/1194666.shtml gbk
    //https://www.630book.com/book_121997/60644017.html gbk
    //http://www.tsxsw.net/html/25/25170/526.html gbk
    //https://www.50zw.com/book_1556/3187689.html gbk
    //https://www.shumil.co/tiancaierzixiaozhangma/3442753.html gbk
    //https://www.fzzw.net/du_9749/533590.html  utf-8
    //https://www.siluke.tv/ny15860/8280056.html utf-8
    //https://www.800xs.net/book_7647/4857471.html gbk   x
    //https://www.999xs.com/files/article/html/38/38747/4051235.html  utf-8 -
    //https://www.doulaidu.com/xs/38349/202084715.html gbk
    //https://www.360xs.com/mulu/105/105943-61337879.html gbk
    //http://www.xs7.cc/shu/27/27148/8641285.html gbk
    //http://www.yttke.com/doushi/lengkuzongcaibadaoai/82080143.html utf-8
    //http://www.k6uk.com/novel/24/24894/6653794.html gbk
    //https://www.lingdiankanshu.co/76742/3995505.html utf-8
    //http://www.23wx.io/book/52879/32640268.html gbk
    //https://www.999zww.com/628/628870/291978854.html
    //http://kk-xs.com/388_1712.html utf-8
    //http://www.778buy.com/666_666504/250071712.html gbk
    //https://www.booktxt.net/20_20832/624766187.html gbk
    //https://www.biqule8.com/book_717/16478468.html gbk
    //https://www.dushiyanqing.net/book/220/220143/40799402.html gbk
    //https://www.800xs.cc/book_1116/47667848.html gbk
    //http://www.tanhua3.com/booktxt/75429/30206924.html gbk
    //https://www.cuiweijuxs.com/0_209/50224.html gbk
    //https://www.biqugej.com/book/47/47114/26221722.html utf-8
    //http://www.yingshixiaoshuo.com/houweidong/26105.html utf-8  x
    //https://www.sanjiangge.org/book/8/8748/9246260.html gbk
    //https://www.ks67.com/13_13483/6002715.html gbk
    //https://www.shukeju.com/a/29/29100/6959962.html gbk
    //https://www.soshuwu.com/ZhanShenYiXu/2489194.html utf-8
    //https://ewenxue.net/xs/43405/8475633.htm gbk
    //http://www.paoshuba.cc/Partlist/41975/11533685.shtml gbk
    //https://www.nuoha.com/book/41164/00301.html utf-8  x
    //https://www.bsl800.com/xs/55338/15455130.html gbk
    //http://www.23us23us.com/skxs/36831/15213173.html utf-8
    //https://www.25kanshu.com/html/30/30907/5032525.html gbk
    //http://www.shancunfengkuang.org/xiangcunyanyi/59828.html gbk
    //https://www.bixiabook.com/0_299/83995.html utf-8
    //https://www.x23us.me/html/13/13147/16834845.html utf-8
    //http://www.15cy.org/29/29800/17470133.html gbk
    //http://www.mianhuatang.cc/6/6765/29709247.html   x
    //http://www.remen88.com/123265/1371860.html utf-8
    //http://www.dushiwenxue.com/html/37/37861/10652669.html gbk  超级慢
    //https://www.58book.cc/book/27827/10976212.html utf-8
    //http://www.530p.com/dushi/cunyuqingyuan-175909/18309909.htm gbk
    //https://www.shipinxiaoshuo.com/0/100/7887.html gbk
    //http://www.tihugujiu.com/j/368640/115355753.html  utf-8
    //http://www.mangg.com/id20213/1158541.html  utf-8
    //https://www.biqukan.net/book/87828/44770840.html gbk
    //https://www.upuxs8.com/book/xiangcunxiaoyeyi/15880.html utf-8
    //http://www.jjwxc.net/onebook.php?novelid=1161292&chapterid=16 gbk
    //http://www.qjwen.com/read/234221/55640987.html   x
    //https://www.lread.net/read/64815/25377604.html utf-8
    //http://vipreader.qidian.com/chapter/3663273/315480192 utf-8
    //http://www.dushiwenxue.net/html/38/38952/11833791.html gbk
    //https://www.166xs.cc/xiaoshuo/20/20072/12552031 utf-8
    //http://www.txt86.cc/91520/31560052.html  utf-8
    //https://www.duoben.net/book/15080/7434891.html gbk
    //https://www.89wxw.com/read/3414/925672.html utf-8
    //http://www.liduzww.com/read/91/91065/37195132.html gbk
    //https://www.69shu.com/txt/30165/24738716 gbk
    //https://www.kanunu8.com/files/xunhuan/201103/2017/45781.html gbk
    //https://www.zhuidu.cc/author/LiuJieSanDao.html  utf-8
    //https://www.biquge98.com/biquge_96032/47820304.html gbk

    //20 -   c-5    s-5
    //




    //已收录的 host  没有的跳转网页
    private static class HostCoding {
        public HostCoding(String host, String coding) {
            this.host = host;
            this.coding = coding;
        }

        public String host;//host
        public String coding;//编码
    }
    private final static HostCoding[] hosts ={
            new HostCoding("lanmaoco.com","utf-8"),//c
            new HostCoding("caiuun.com","utf-8"),//c
            new HostCoding("biqufu.com","gbk"),//c
            new HostCoding("qk-tv.com","utf-8"),//c
            new HostCoding("di01.co","utf-8"),//c
            new HostCoding("xs7.com","gbk"),
            new HostCoding("zbzw.la","utf-8"),
            new HostCoding("630shu.net","gbk"),
            new HostCoding("lwxs99.com","gbk"),
            new HostCoding("qudushu.com","utf-8"),
            new HostCoding("114zw.org","gbk"),
            new HostCoding("23xsw.cc","gbk"),
            new HostCoding("02shu.cc","gbk"),
            new HostCoding("lwxs.io","gbk"),
            new HostCoding("bqguan.com","utf-8"),
            new HostCoding("yanxiran.com","utf-8"),
            new HostCoding("kanshula.com","utf-8"),
            new HostCoding("xiangcunxiaoshuo.com","gbk"),
            new HostCoding("du1du.org","gbk"),
            new HostCoding("lingyutxt.com","utf-8"),
            new HostCoding("lwxsw.cc","gbk"),
            new HostCoding("xiaoshuobuluo.com","gbk"),
            new HostCoding("meiyuxs.com","utf-8"),
            new HostCoding("88xiaoshuo.com","gbk"),
            new HostCoding("630book.com","gbk"),
            new HostCoding("tsxsw.net","gbk"),
            new HostCoding("50zw.com","gbk"),
            new HostCoding("shumil.co","gbk"),
            new HostCoding("fzzw.net","utf-8"),
            new HostCoding("siluke.tv","utf-8"),
            new HostCoding("800xs.net","gbk"),
            new HostCoding("999xs.com","utf-8"),
            new HostCoding("doulaidu.com","gbk"),
            new HostCoding("360xs.com","gbk"),
            new HostCoding("xs7.cc","gbk"),
            new HostCoding("yttke.com","utf-8"),
            new HostCoding("k6uk.com","gbk"),
            new HostCoding("lingdiankanshu.co","utf-8"),
            new HostCoding("23wx.io","gbk"),
            new HostCoding("999zww.com","utf-8"),
            new HostCoding("kk-xs.com","utf-8"),
            new HostCoding("778buy.com","gbk"),
            new HostCoding("booktxt.net","gbk"),
            new HostCoding("biqule8.com","gbk"),
            new HostCoding("dushiyanqing.net","gbk"),
            new HostCoding("800xs.cc","gbk"),
            new HostCoding("tanhua3.com","gbk"),
            new HostCoding("cuiweijuxs.com","gbk"),
            new HostCoding("biqugej.com","utf-8"),
            new HostCoding("yingshixiaoshuo.com","utf-8"),
            new HostCoding("sanjiangge.org","gbk"),
            new HostCoding("ks67.com","gbk"),
            new HostCoding("shukeju.com","gbk"),
            new HostCoding("soshuwu.com","utf-8"),
            new HostCoding("ewenxue.net","gbk"),
            new HostCoding("paoshuba.cc","gbk"),
            new HostCoding("nuoha.com","utf-8"),
            new HostCoding("bsl800.com","gbk"),
            new HostCoding("23us23us.com","utf-8"),
            new HostCoding("25kanshu.com","gbk"),
            new HostCoding("shancunfengkuang.org","gbk"),
            new HostCoding("bixiabook.com","utf-8"),
            new HostCoding("x23us.me","utf-8"),
            new HostCoding("15cy.org","gbk"),
            new HostCoding("mianhuatang.cc","gbk"),
            new HostCoding("remen88.com","utf-8"),
            new HostCoding("dushiwenxue.com","gbk"),
            new HostCoding("58book.cc","utf-8"),
            new HostCoding("530p.com","gbk"),
            new HostCoding("shipinxiaoshuo.com","gbk"),
            new HostCoding("tihugujiu.com","utf-8"),
            new HostCoding("mangg.com","utf-8"),
            new HostCoding("biqukan.net","gbk"),
            new HostCoding("upuxs8.com","utf-8"),
            new HostCoding("jjwxc.net","gbk"),
            new HostCoding("qjwen.com","utf-8"),
            new HostCoding("lread.net","utf-8"),
            new HostCoding("vipreader.qidian.com","utf-8"),
            new HostCoding("dushiwenxue.net","gbk"),
            new HostCoding("166xs.cc","utf-8"),
            new HostCoding("txt86.cc","utf-8"),
            new HostCoding("duoben.net","gbk"),
            new HostCoding("89wxw.com","utf-8"),
            new HostCoding("liduzww.com","gbk"),
            new HostCoding("69shu.com","gbk"),
            new HostCoding("kanunu8.com","gbk"),
            new HostCoding("zhuidu.cc","utf-8"),
            new HostCoding("biquge98.com","gbk")
    };


    /**
     * 判断网站是否收录
     * @param hostStr host
     * @return
     */
    public static boolean isHost(String hostStr){
        for (HostCoding hostCoding:hosts)
            if (hostStr.equals(hostCoding.host))
                return true;
        return false;
    }

    /**
     * 根据host返回编码
     * @param host host
     * @return coding
     */
    public static String getHostCoding(String host){
        for (HostCoding hostCoding:hosts)
            if (host.equals(hostCoding.host))
                return hostCoding.coding;
        return null;
    }








    //根据网站进行截取
    public static String hostStr(String host,String str){

       /*
       if (hosts[0].host.equals(host)) // lanmaoco.com
            return lanmaoco(str);
        else if (hosts[1].host.equals(host)) // caiuun.com
            return caiuun(str);
        else if (hosts[2].host.equals(host)) // biqufu.com
            return biqufu(str);
        else if (hosts[3].host.equals(host)) // qk-tv.com
            return qktv(str);
        else if (hosts[4].host.equals(host)) // di01.co
            return di01(str);
*/
        return noHost(str);
    }



    //lanmaoco.com
    private static String lanmaoco(String str) {

        return Tools.cutStr(str,"id=\"chaptercontent\">","</div>");
    }

    //caiuun.com
    private static String caiuun(String str) {
        return qktv(str);
    }

    //biqufu.com
    private static String biqufu(String str) {
        return qktv(str);
    }

    //qk-tv.com
    private static String qktv(String str) {
        return Tools.cutStr(str,"<div id=\"content\">","<div");
    }

    // di01.co
    private static String di01(String str) {
        return qktv(str);
    }













    //公用  没有收录用此方法
    private static String noHost(String str){


        String str1 = Tools.cutStr(str, "id=\"content\">", "<div");
        if (str1==null||str1.equals(""))
            str1 =  Tools.cutStr(str, "content\">", "<div");
        if (str1==null||str1.equals(""))
            str1 =  Tools.cutStr(str, "content\">", "</DIV");

        return str1;
    }






}
