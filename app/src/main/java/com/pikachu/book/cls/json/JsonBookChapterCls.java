package com.pikachu.book.cls.json;

import java.util.List;


public class JsonBookChapterCls {


    /**
     * error : 0
     * msg :
     * data : {"pages":49,"chapters":[{"id":"1349","name":"第一千三百五十章 巅峰之斗","url":"http://www.caiuun.com/64_1358.html"},{"id":"1348","name":"第一千三百四十九章 血狱九印，狱镇九天","url":"http://www.caiuun.com/64_1357.html"},{"id":"1347","name":"第一千三百四十八章 周元的反击","url":"http://www.caiuun.com/64_1356.html"},{"id":"1346","name":"第一千三百四十七章 法域最顶尖的战斗","url":"http://www.caiuun.com/64_1355.html"},{"id":"1345","name":"第一千三百四十六章 九爪紫金圣龙","url":"http://www.caiuun.com/64_1354.html"},{"id":"1344","name":"第一千三百四十五章 法域之撞","url":"http://www.caiuun.com/64_1353.html"},{"id":"1343","name":"第一千三百四十四章 再度归来的周元","url":"http://www.caiuun.com/64_1352.html"},{"id":"1342","name":"第一千三百四十三章 结界破碎","url":"http://www.caiuun.com/64_1351.html"},{"id":"1341","name":"第一千三百四十二章 苏醒","url":"http://www.caiuun.com/64_1350.html"},{"id":"1340","name":"第一千三百四十一章 赤雀化凰","url":"http://www.caiuun.com/64_1349.html"},{"id":"1339","name":"第一千三百四十章 圣龙之气，三分归元","url":"http://www.caiuun.com/64_1348.html"},{"id":"1338","name":"第一千三百三十九章 拯救周元的行动","url":"http://www.caiuun.com/64_1347.html"},{"id":"1337","name":"第一千三百三十八章 血镜","url":"http://www.caiuun.com/64_1346.html"},{"id":"1336","name":"第一千三百三十七章 一步","url":"http://www.caiuun.com/64_1345.html"},{"id":"1335","name":"第一千三百三十六章 溃逃","url":"http://www.caiuun.com/64_1344.html"},{"id":"1334","name":"第一千三百三十五章 太轩之力","url":"http://www.caiuun.com/64_1343.html"},{"id":"1333","name":"第一千三百三十四章 开吃","url":"http://www.caiuun.com/64_1342.html"},{"id":"1332","name":"第一千三百三十三章 太轩之谋","url":"http://www.caiuun.com/64_1341.html"},{"id":"1331","name":"第一千三百三十二章 防线","url":"http://www.caiuun.com/64_1340.html"},{"id":"1330","name":"第一千三百三十一章 龙首汇聚","url":"http://www.caiuun.com/64_1339.html"},{"id":"1329","name":"第一千三百三十章 九寸八","url":"http://www.caiuun.com/64_1338.html"},{"id":"1328","name":"第一千三百二十九章 海底的祖龙魂髓","url":"http://www.caiuun.com/64_1337.html"},{"id":"1327","name":"第一千三百二十八章 核心锚点","url":"http://www.caiuun.com/64_1336.html"},{"id":"1326","name":"第一千两百二十七章 诸圣赞","url":"http://www.caiuun.com/64_1335.html"},{"id":"1325","name":"第一千三百二十六章 赤梭显神威","url":"http://www.caiuun.com/64_1334.html"},{"id":"1324","name":"第一千三百二十五章 大日斩圣梭","url":"http://www.caiuun.com/64_1333.html"},{"id":"1323","name":"第一千三百二十四章 破局的手段","url":"http://www.caiuun.com/64_1332.html"},{"id":"1322","name":"第一千三百二十三章 圣烛法域","url":"http://www.caiuun.com/64_1331.html"},{"id":"1321","name":"第一千三百二十二章 苦战渊泉","url":"http://www.caiuun.com/64_1330.html"},{"id":"1320","name":"第一千三百二十一章 再碰渊泉","url":"http://www.caiuun.com/64_1329.html"}],"total":"1445"}
     */

    private Integer error;
    private String msg;

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    private DataBean data;

    public static class DataBean {
        public Integer getPages() {
            return pages;
        }

        public void setPages(Integer pages) {
            this.pages = pages;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<ChaptersBean> getChapters() {
            return chapters;
        }

        public void setChapters(List<ChaptersBean> chapters) {
            this.chapters = chapters;
        }

        /**
         * pages : 49
         * chapters : [{"id":"1349","name":"第一千三百五十章 巅峰之斗","url":"http://www.caiuun.com/64_1358.html"},{"id":"1348","name":"第一千三百四十九章 血狱九印，狱镇九天","url":"http://www.caiuun.com/64_1357.html"},{"id":"1347","name":"第一千三百四十八章 周元的反击","url":"http://www.caiuun.com/64_1356.html"},{"id":"1346","name":"第一千三百四十七章 法域最顶尖的战斗","url":"http://www.caiuun.com/64_1355.html"},{"id":"1345","name":"第一千三百四十六章 九爪紫金圣龙","url":"http://www.caiuun.com/64_1354.html"},{"id":"1344","name":"第一千三百四十五章 法域之撞","url":"http://www.caiuun.com/64_1353.html"},{"id":"1343","name":"第一千三百四十四章 再度归来的周元","url":"http://www.caiuun.com/64_1352.html"},{"id":"1342","name":"第一千三百四十三章 结界破碎","url":"http://www.caiuun.com/64_1351.html"},{"id":"1341","name":"第一千三百四十二章 苏醒","url":"http://www.caiuun.com/64_1350.html"},{"id":"1340","name":"第一千三百四十一章 赤雀化凰","url":"http://www.caiuun.com/64_1349.html"},{"id":"1339","name":"第一千三百四十章 圣龙之气，三分归元","url":"http://www.caiuun.com/64_1348.html"},{"id":"1338","name":"第一千三百三十九章 拯救周元的行动","url":"http://www.caiuun.com/64_1347.html"},{"id":"1337","name":"第一千三百三十八章 血镜","url":"http://www.caiuun.com/64_1346.html"},{"id":"1336","name":"第一千三百三十七章 一步","url":"http://www.caiuun.com/64_1345.html"},{"id":"1335","name":"第一千三百三十六章 溃逃","url":"http://www.caiuun.com/64_1344.html"},{"id":"1334","name":"第一千三百三十五章 太轩之力","url":"http://www.caiuun.com/64_1343.html"},{"id":"1333","name":"第一千三百三十四章 开吃","url":"http://www.caiuun.com/64_1342.html"},{"id":"1332","name":"第一千三百三十三章 太轩之谋","url":"http://www.caiuun.com/64_1341.html"},{"id":"1331","name":"第一千三百三十二章 防线","url":"http://www.caiuun.com/64_1340.html"},{"id":"1330","name":"第一千三百三十一章 龙首汇聚","url":"http://www.caiuun.com/64_1339.html"},{"id":"1329","name":"第一千三百三十章 九寸八","url":"http://www.caiuun.com/64_1338.html"},{"id":"1328","name":"第一千三百二十九章 海底的祖龙魂髓","url":"http://www.caiuun.com/64_1337.html"},{"id":"1327","name":"第一千三百二十八章 核心锚点","url":"http://www.caiuun.com/64_1336.html"},{"id":"1326","name":"第一千两百二十七章 诸圣赞","url":"http://www.caiuun.com/64_1335.html"},{"id":"1325","name":"第一千三百二十六章 赤梭显神威","url":"http://www.caiuun.com/64_1334.html"},{"id":"1324","name":"第一千三百二十五章 大日斩圣梭","url":"http://www.caiuun.com/64_1333.html"},{"id":"1323","name":"第一千三百二十四章 破局的手段","url":"http://www.caiuun.com/64_1332.html"},{"id":"1322","name":"第一千三百二十三章 圣烛法域","url":"http://www.caiuun.com/64_1331.html"},{"id":"1321","name":"第一千三百二十二章 苦战渊泉","url":"http://www.caiuun.com/64_1330.html"},{"id":"1320","name":"第一千三百二十一章 再碰渊泉","url":"http://www.caiuun.com/64_1329.html"}]
         * total : 1445
         */

        private Integer pages;
        private String total;
        private List<ChaptersBean> chapters;

        public static class ChaptersBean {
            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            /**
             * id : 1349
             * name : 第一千三百五十章 巅峰之斗
             * url : http://www.caiuun.com/64_1358.html
             */
            private String id;
            private String name;
            private String url;
        }
    }
}
