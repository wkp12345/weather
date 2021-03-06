package com.example.weather;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * 创建数据库
 */
public class dbhelper extends SQLiteOpenHelper {
    public static final String CREATE_DIARY = "create table city ("+    //关注城市
            "adcode text primary key ,province text,city text"+
            ",weather text,temperature text,humidity text,reporttime text)";

    public static final String CREATE_DIARY1 = "create table hcity ("+   //缓存结果
            "adcode text primary key ,province text,city text"+
            ",weather text,temperature text,humidity text,reporttime text)";

    public dbhelper(@Nullable Context context) {
        super(context, "city.db", null, 1);
    }

    public dbhelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DIARY);
        db.execSQL(CREATE_DIARY1);
        db.execSQL(CREATE_CITY);
        db.execSQL(INSERT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public static final String CREATE_CITY = "CREATE TABLE LocationList (" +  //所有城市
            "FID int  ," +
            "ParentID int ," +
            "LocationName nvarchar(20) " +
            ")";
    public static final String INSERT= "insert into LocationList (FID,ParentID,LocationName)\n" +  //插入所有城市
            "select 1,0,'北京' union all\n" +
            "select 2,0,'天津' union all\n" +
            "select 3,0,'上海' union all\n" +
            "select 4,0,'广东' union all\n" +
            "select 5,0,'广西' union all\n" +
            "select 6,0,'河北' union all\n" +
            "select 7,0,'河南' union all\n" +
            "select 8,0,'湖北' union all\n" +
            "select 9,0,'湖南' union all\n" +
            "select 10,0,'福建' union all\n" +
            "select 11,0,'山东' union all\n" +
            "select 12,0,'安徽' union all\n" +
            "select 13,0,'浙江' union all\n" +
            "select 14,0,'四川' union all\n" +
            "select 15,0,'重庆' union all\n" +
            "select 16,0,'贵州' union all\n" +
            "select 17,0,'云南' union all\n" +
            "select 18,0,'江苏' union all\n" +
            "select 19,0,'山西' union all\n" +
            "select 20,0,'辽宁' union all\n" +
            "select 21,0,'吉林' union all\n" +
            "select 22,0,'内蒙古' union all\n" +
            "select 23,0,'黑龙江' union all\n" +
            "select 24,0,'西藏' union all\n" +
            "select 25,0,'陕西' union all\n" +
            "select 26,0,'甘肃' union all\n" +
            "select 27,0,'青海' union all\n" +
            "select 28,0,'宁夏' union all\n" +
            "select 29,0,'新疆' union all\n" +
            "select 30,0,'江西' union all\n" +
            "select 31,0,'海南' union all\n" +
            "select 32,0,'台湾' union all\n" +
            "select 33,0,'香港' union all\n" +
            "select 34,0,'澳门' union all\n" +
            "select 35,1,'北京' union all\n" +
            "select 36,2,'天津' union all\n" +
            "select 37,3,'上海' union all\n" +
            "select 38,4,'潮州' union all\n" +
            "select 39,4,'东莞' union all\n" +
            "select 40,4,'佛山' union all\n" +
            "select 41,4,'广州' union all\n" +
            "select 42,4,'河源' union all\n" +
            "select 43,4,'惠州' union all\n" +
            "select 44,4,'江门' union all\n" +
            "select 45,4,'揭阳' union all\n" +
            "select 46,4,'茂名' union all\n" +
            "select 47,4,'梅州' union all\n" +
            "select 48,4,'清远' union all\n" +
            "select 49,4,'汕头' union all\n" +
            "select 50,4,'汕尾' union all\n" +
            "select 51,4,'韶关' union all\n" +
            "select 52,4,'深圳' union all\n" +
            "select 53,4,'阳江' union all\n" +
            "select 54,4,'云浮' union all\n" +
            "select 55,4,'湛江' union all\n" +
            "select 56,4,'肇庆' union all\n" +
            "select 57,4,'中山' union all\n" +
            "select 58,4,'珠海' union all\n" +
            "select 59,5,'百色' union all\n" +
            "select 60,5,'北海' union all\n" +
            "select 61,5,'崇左' union all\n" +
            "select 62,5,'防城港' union all\n" +
            "select 63,5,'桂林' union all\n" +
            "select 64,5,'贵港' union all\n" +
            "select 65,5,'河池' union all\n" +
            "select 66,5,'贺州' union all\n" +
            "select 67,5,'来宾' union all\n" +
            "select 68,5,'柳州' union all\n" +
            "select 69,5,'南宁' union all\n" +
            "select 70,5,'钦州' union all\n" +
            "select 71,5,'梧州' union all\n" +
            "select 72,5,'玉林' union all\n" +
            "select 73,6,'保定' union all\n" +
            "select 74,6,'沧州' union all\n" +
            "select 75,6,'承德' union all\n" +
            "select 76,6,'邯郸' union all\n" +
            "select 77,6,'衡水' union all\n" +
            "select 78,6,'廊坊' union all\n" +
            "select 79,6,'秦皇岛' union all\n" +
            "select 80,6,'石家庄' union all\n" +
            "select 81,6,'唐山' union all\n" +
            "select 82,6,'邢台' union all\n" +
            "select 83,6,'张家口' union all\n" +
            "select 84,7,'安阳' union all\n" +
            "select 85,7,'鹤壁' union all\n" +
            "select 86,7,'济源' union all\n" +
            "select 87,7,'焦作' union all\n" +
            "select 88,7,'开封' union all\n" +
            "select 89,7,'洛阳' union all\n" +
            "select 90,7,'南阳' union all\n" +
            "select 91,7,'平顶山' union all\n" +
            "select 92,7,'三门峡' union all\n" +
            "select 93,7,'商丘' union all\n" +
            "select 94,7,'新乡' union all\n" +
            "select 95,7,'信阳' union all\n" +
            "select 96,7,'许昌' union all\n" +
            "select 97,7,'郑州' union all\n" +
            "select 98,7,'周口' union all\n" +
            "select 99,7,'驻马店' union all\n" +
            "select 100,7,'漯河' union all\n" +
            "select 101,7,'濮阳' union all\n" +
            "select 102,8,'鄂州' union all\n" +
            "select 103,8,'恩施' union all\n" +
            "select 104,8,'黄冈' union all\n" +
            "select 105,8,'黄石' union all\n" +
            "select 106,8,'荆门' union all\n" +
            "select 107,8,'荆州' union all\n" +
            "select 108,8,'潜江' union all\n" +
            "select 109,8,'神农架林区' union all\n" +
            "select 110,8,'十堰' union all\n" +
            "select 111,8,'随州' union all\n" +
            "select 112,8,'天门' union all\n" +
            "select 113,8,'武汉' union all\n" +
            "select 114,8,'仙桃' union all\n" +
            "select 115,8,'咸宁' union all\n" +
            "select 116,8,'襄樊' union all\n" +
            "select 117,8,'孝感' union all\n" +
            "select 118,8,'宜昌' union all\n" +
            "select 119,9,'常德' union all\n" +
            "select 120,9,'长沙' union all\n" +
            "select 121,9,'郴州' union all\n" +
            "select 122,9,'衡阳' union all\n" +
            "select 123,9,'怀化' union all\n" +
            "select 124,9,'娄底' union all\n" +
            "select 125,9,'邵阳' union all\n" +
            "select 126,9,'湘潭' union all\n" +
            "select 127,9,'湘西土家族苗族自治州' union all\n" +
            "select 128,9,'益阳' union all\n" +
            "select 129,9,'永州' union all\n" +
            "select 130,9,'岳阳' union all\n" +
            "select 131,9,'张家界' union all\n" +
            "select 132,9,'株洲' union all\n" +
            "select 133,10,'福州' union all\n" +
            "select 134,10,'龙岩' union all\n" +
            "select 135,10,'南平' union all\n" +
            "select 136,10,'宁德' union all\n" +
            "select 137,10,'莆田' union all\n" +
            "select 138,10,'泉州' union all\n" +
            "select 139,10,'三明' union all\n" +
            "select 140,10,'厦门' union all\n" +
            "select 141,10,'漳州' union all\n" +
            "select 142,11,'滨州' union all\n" +
            "select 143,11,'德州' union all\n" +
            "select 144,11,'东营' union all\n" +
            "select 145,11,'菏泽' union all\n" +
            "select 146,11,'济南' union all\n" +
            "select 147,11,'济宁' union all\n" +
            "select 148,11,'莱芜' union all\n" +
            "select 149,11,'聊城' union all\n" +
            "select 150,11,'临沂' union all\n" +
            "select 151,11,'青岛' union all\n" +
            "select 152,11,'日照' union all\n" +
            "select 153,11,'泰安' union all\n" +
            "select 154,11,'威海' union all\n" +
            "select 155,11,'潍坊' union all\n" +
            "select 156,11,'烟台' union all\n" +
            "select 157,11,'枣庄' union all\n" +
            "select 158,11,'淄博' union all\n" +
            "select 159,12,'亳州' union all\n" +
            "select 160,12,'安庆' union all\n" +
            "select 161,12,'蚌埠' union all\n" +
            "select 162,12,'巢湖' union all\n" +
            "select 163,12,'池州' union all\n" +
            "select 164,12,'滁州' union all\n" +
            "select 165,12,'阜阳' union all\n" +
            "select 166,12,'合肥' union all\n" +
            "select 167,12,'淮北' union all\n" +
            "select 168,12,'淮南' union all\n" +
            "select 169,12,'黄山' union all\n" +
            "select 170,12,'六安' union all\n" +
            "select 171,12,'马鞍山' union all\n" +
            "select 172,12,'宿州' union all\n" +
            "select 173,12,'铜陵' union all\n" +
            "select 174,12,'芜湖' union all\n" +
            "select 175,12,'宣城' union all\n" +
            "select 176,13,'杭州' union all\n" +
            "select 177,13,'湖州' union all\n" +
            "select 178,13,'嘉兴' union all\n" +
            "select 179,13,'金华' union all\n" +
            "select 180,13,'丽水' union all\n" +
            "select 181,13,'宁波' union all\n" +
            "select 182,13,'绍兴' union all\n" +
            "select 183,13,'台州' union all\n" +
            "select 184,13,'温州' union all\n" +
            "select 185,13,'舟山' union all\n" +
            "select 186,13,'衢州' union all\n" +
            "select 187,14,'阿坝' union all\n" +
            "select 188,14,'巴中' union all\n" +
            "select 189,14,'成都' union all\n" +
            "select 190,14,'自贡' union all\n" +
            "select 191,14,'泸州' union all\n" +
            "select 192,14,'达州' union all\n" +
            "select 193,14,'德阳' union all\n" +
            "select 194,14,'甘孜州' union all\n" +
            "select 195,14,'广安' union all\n" +
            "select 196,14,'广元' union all\n" +
            "select 197,14,'乐山' union all\n" +
            "select 198,14,'凉山' union all\n" +
            "select 199,14,'眉山' union all\n" +
            "select 200,14,'绵阳' union all\n" +
            "select 201,14,'南充' union all\n" +
            "select 202,14,'内江' union all\n" +
            "select 203,14,'攀枝花' union all\n" +
            "select 204,14,'遂宁' union all\n" +
            "select 205,14,'雅安' union all\n" +
            "select 206,14,'宜宾' union all\n" +
            "select 207,14,'资阳' union all\n" +
            "select 208,15,'重庆' union all\n" +
            "select 209,16,'黔西南布依族苗族自治州' union all\n" +
            "select 210,16,'安顺' union all\n" +
            "select 211,16,'毕节' union all\n" +
            "select 212,16,'贵阳' union all\n" +
            "select 213,16,'六盘水' union all\n" +
            "select 214,16,'黔东南苗族侗族自治州' union all\n" +
            "select 215,16,'黔南布依族苗族自治州' union all\n" +
            "select 216,16,'铜仁' union all\n" +
            "select 217,16,'遵义' union all\n" +
            "select 218,17,'昭通' union all\n" +
            "select 219,17,'丽江' union all\n" +
            "select 220,17,'临沧' union all\n" +
            "select 221,17,'怒江' union all\n" +
            "select 222,17,'曲靖' union all\n" +
            "select 223,17,'思茅' union all\n" +
            "select 224,17,'文山' union all\n" +
            "select 225,17,'西双版纳' union all\n" +
            "select 226,17,'玉溪' union all\n" +
            "select 227,17,'保山' union all\n" +
            "select 228,17,'楚雄' union all\n" +
            "select 229,17,'大理' union all\n" +
            "select 230,17,'德宏' union all\n" +
            "select 231,17,'迪庆' union all\n" +
            "select 232,17,'红河' union all\n" +
            "select 233,17,'昆明' union all\n" +
            "select 234,18,'无锡' union all\n" +
            "select 235,18,'徐州' union all\n" +
            "select 236,18,'盐城' union all\n" +
            "select 237,18,'扬州' union all\n" +
            "select 238,18,'镇江' union all\n" +
            "select 239,18,'常州' union all\n" +
            "select 240,18,'淮安' union all\n" +
            "select 241,18,'连云港' union all\n" +
            "select 242,18,'南京' union all\n" +
            "select 243,18,'南通' union all\n" +
            "select 244,18,'苏州' union all\n" +
            "select 245,18,'宿迁' union all\n" +
            "select 246,18,'泰州' union all\n" +
            "select 247,19,'阳泉' union all\n" +
            "select 248,19,'运城' union all\n" +
            "select 249,19,'长治' union all\n" +
            "select 250,19,'大同' union all\n" +
            "select 251,19,'晋城' union all\n" +
            "select 252,19,'晋中' union all\n" +
            "select 253,19,'临汾' union all\n" +
            "select 254,19,'吕梁' union all\n" +
            "select 255,19,'朔州' union all\n" +
            "select 256,19,'太原' union all\n" +
            "select 257,19,'忻州' union all\n" +
            "select 258,20,'鞍山' union all\n" +
            "select 259,20,'本溪' union all\n" +
            "select 260,20,'朝阳' union all\n" +
            "select 261,20,'大连' union all\n" +
            "select 262,20,'丹东' union all\n" +
            "select 263,20,'抚顺' union all\n" +
            "select 264,20,'阜新' union all\n" +
            "select 265,20,'葫芦岛' union all\n" +
            "select 266,20,'锦州' union all\n" +
            "select 267,20,'辽阳' union all\n" +
            "select 268,20,'盘锦' union all\n" +
            "select 269,20,'沈阳' union all\n" +
            "select 270,20,'铁岭' union all\n" +
            "select 271,20,'营口' union all\n" +
            "select 272,21,'白山' union all\n" +
            "select 273,21,'长春' union all\n" +
            "select 274,21,'吉林' union all\n" +
            "select 275,21,'辽源' union all\n" +
            "select 276,21,'四平' union all\n" +
            "select 277,21,'松原' union all\n" +
            "select 278,21,'通化' union all\n" +
            "select 279,21,'延边' union all\n" +
            "select 280,21,'白城' union all\n" +
            "select 281,22,'阿拉善盟' union all\n" +
            "select 282,22,'巴彦淖尔市' union all\n" +
            "select 283,22,'包头' union all\n" +
            "select 284,22,'赤峰' union all\n" +
            "select 285,22,'鄂尔多斯' union all\n" +
            "select 286,22,'呼和浩特' union all\n" +
            "select 287,22,'呼伦贝尔' union all\n" +
            "select 288,22,'通辽' union all\n" +
            "select 289,22,'乌海' union all\n" +
            "select 290,22,'乌兰察布市' union all\n" +
            "select 291,22,'锡林郭勒盟' union all\n" +
            "select 292,22,'兴安盟' union all\n" +
            "select 293,23,'大庆' union all\n" +
            "select 294,23,'大兴安岭' union all\n" +
            "select 295,23,'哈尔滨' union all\n" +
            "select 296,23,'鹤岗' union all\n" +
            "select 297,23,'黑河' union all\n" +
            "select 298,23,'鸡西' union all\n" +
            "select 299,23,'佳木斯' union all\n" +
            "select 300,23,'牡丹江' union all\n" +
            "select 301,23,'七台河' union all\n" +
            "select 302,23,'齐齐哈尔' union all\n" +
            "select 303,23,'双鸭山' union all\n" +
            "select 304,23,'绥化' union all\n" +
            "select 305,23,'伊春' union all\n" +
            "select 306,24,'阿里' union all\n" +
            "select 307,24,'昌都' union all\n" +
            "select 308,24,'拉萨' union all\n" +
            "select 309,24,'林芝' union all\n" +
            "select 310,24,'那曲' union all\n" +
            "select 311,24,'日喀则' union all\n" +
            "select 312,24,'山南' union all\n" +
            "select 313,25,'安康' union all\n" +
            "select 314,25,'宝鸡' union all\n" +
            "select 315,25,'汉中' union all\n" +
            "select 316,25,'商洛' union all\n" +
            "select 317,25,'铜川' union all\n" +
            "select 318,25,'渭南' union all\n" +
            "select 319,25,'西安' union all\n" +
            "select 320,25,'咸阳' union all\n" +
            "select 321,25,'延安' union all\n" +
            "select 322,25,'榆林' union all\n" +
            "select 323,26,'白银' union all\n" +
            "select 324,26,'定西' union all\n" +
            "select 325,26,'甘南' union all\n" +
            "select 326,26,'嘉峪关' union all\n" +
            "select 327,26,'金昌' union all\n" +
            "select 328,26,'张掖' union all\n" +
            "select 329,26,'酒泉' union all\n" +
            "select 330,26,'兰州' union all\n" +
            "select 331,26,'临夏' union all\n" +
            "select 332,26,'陇南' union all\n" +
            "select 333,26,'平凉' union all\n" +
            "select 334,26,'庆阳' union all\n" +
            "select 335,26,'天水' union all\n" +
            "select 336,26,'武威' union all\n" +
            "select 337,27,'果洛' union all\n" +
            "select 338,27,'海北' union all\n" +
            "select 339,27,'西宁' union all\n" +
            "select 340,27,'玉树' union all\n" +
            "select 341,27,'海东' union all\n" +
            "select 342,27,'海南' union all\n" +
            "select 343,27,'海西' union all\n" +
            "select 344,27,'黄南' union all\n" +
            "select 345,28,'吴忠' union all\n" +
            "select 346,28,'银川' union all\n" +
            "select 347,28,'固原' union all\n" +
            "select 348,28,'石嘴山' union all\n" +
            "select 349,29,'阿克苏' union all\n" +
            "select 350,29,'阿拉尔' union all\n" +
            "select 351,29,'巴音郭楞' union all\n" +
            "select 352,29,'博尔塔拉' union all\n" +
            "select 353,29,'昌吉' union all\n" +
            "select 354,29,'哈密' union all\n" +
            "select 355,29,'伊犁' union all\n" +
            "select 356,29,'和田' union all\n" +
            "select 357,29,'喀什' union all\n" +
            "select 358,29,'克拉玛依' union all\n" +
            "select 359,29,'石河子' union all\n" +
            "select 360,29,'图木舒克' union all\n" +
            "select 361,29,'吐鲁番' union all\n" +
            "select 362,29,'乌鲁木齐' union all\n" +
            "select 363,29,'五家渠' union all\n" +
            "select 364,29,'克孜勒苏' union all\n" +
            "select 365,30,'抚州' union all\n" +
            "select 366,30,'赣州' union all\n" +
            "select 367,30,'吉安' union all\n" +
            "select 368,30,'景德镇' union all\n" +
            "select 369,30,'九江' union all\n" +
            "select 370,30,'南昌' union all\n" +
            "select 371,30,'萍乡' union all\n" +
            "select 372,30,'上饶' union all\n" +
            "select 373,30,'新余' union all\n" +
            "select 374,30,'宜春' union all\n" +
            "select 375,30,'鹰潭' union all\n" +
            "select 376,31,'白沙' union all\n" +
            "select 377,31,'保亭' union all\n" +
            "select 378,31,'昌江' union all\n" +
            "select 379,31,'澄迈县' union all\n" +
            "select 380,31,'定安县' union all\n" +
            "select 381,31,'东方' union all\n" +
            "select 382,31,'海口' union all\n" +
            "select 383,31,'乐东黎族自治县' union all\n" +
            "select 384,31,'临高县' union all\n" +
            "select 385,31,'陵水黎族自治县' union all\n" +
            "select 386,31,'琼海' union all\n" +
            "select 387,31,'琼中黎族苗族自治县' union all\n" +
            "select 388,31,'五指山' union all\n" +
            "select 389,31,'儋州' union all\n" +
            "select 390,31,'三亚' union all\n" +
            "select 391,31,'屯昌县' union all\n" +
            "select 392,31,'万宁' union all\n" +
            "select 393,31,'文昌' union all\n" +
            "select 394,32,'台中' union all\n" +
            "select 395,32,'台东' union all\n" +
            "select 396,32,'台南' union all\n" +
            "select 397,32,'花莲' union all\n" +
            "select 398,32,'基隆' union all\n" +
            "select 399,32,'嘉义' union all\n" +
            "select 400,32,'台北' union all\n" +
            "select 401,32,'高雄' union all\n" +
            "select 402,33,'香港' union all\n" +
            "select 403,34,'澳门'";
}