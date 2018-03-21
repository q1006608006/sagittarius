package top.ivan.sagittarius.fetch.parts;

import top.ivan.sagittarius.screen.Seed;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class JDProductPersist extends AbsProductFetchPersist {

    @Override
    public List<Map<String, String>> getKeepItems(Seed item) {
        Map<String,String> result = item.getStorage();
        String mainDes = result.get("mainDes");
        result.put("type",getMainDesInfo(mainDes,"类别"));
        result.put("modelNo",getMainDesInfo(mainDes,"型号"));
        return Collections.singletonList(result);
    }

    private static String getMainDesInfo(String mainDes,String key) {
        if(null == mainDes) {
            return null;
        }
        if(!mainDes.contains(" "+key+" ")) {
            return null;
        }
        return mainDes.replaceAll("[\\s\\S]* " + key + " (\\S+) [\\s\\S]*","$1");
    }


    public static void main(String[] args) {
        String t1 = "主体 品牌 美宝莲 产地 中国 保质期 1095 类别 唇膏/口红 颜色 红色 妆效 其它";
        String t2 = "主体参数 品牌 李宁（LI-NING） 类别 情侣沙滩裤 型号 031 适用人群 情侣";
        String t3 = "主体 类别 蛋糕 包装 袋装 类型 西式糕点 是否含糖 是 口味 其他 品牌 盼盼 配料 小麦粉、白砂糖、鸡蛋等 净含量 100g 保质期 360天 储存方法 常温 生产许可证号 SC12434110105073";
        String t4 = "主体 型号 对外宣传型号 一加5T 入网型号 工业代号或者入网型号 ONEPLUS A5010 上市年份 2017年 上市月份 11月\n" +
                "基本信息 机身颜色 星辰黑 机身长度（mm） 156.1 机身宽度（mm） 75 机身厚度（mm） 7.25 机身重量（g） 162 输入方式 触控 运营商标志或内容 定制机往往会有运营商的元素在手机的某些位置，该属性会介绍这些元素出现的位置。 无 机身材质分类 金属边框；金属后盖 机身材质工艺 铝合金金属\n" +
                "操作系统 操作系统 Android 操作系统版本 H2OS 3.7\n" +
                "主芯片 CPU品牌 骁龙（Snapdragon) CPU频率 最高主频2.45GHz CPU核数 八核 CPU型号 骁龙835（MSM8998）\n" +
                "网络支持 双卡机类型 双卡双待单通 最大支持SIM卡数量 2个 SIM卡类型 sim卡的规格，大卡、小卡或者nano卡。如果副卡有不同可在下方副卡规格中填写或显示 Nano SIM 4G网络 单卡手机或者主卡的4G网络在这里填写，副卡的网络在副sim卡4G网络中填写。 4G：移动（TD-LTE)；4G：联通(FDD-LTE)；4G：电信(FDD-LTE)；4G：联通(TD-LTE) 3G/2G网络 3G：移动(TD-SCDMA)；3G：联通(WCDMA)；3G：电信(CDMA2000)；2G：移动（GSM）+联通(GSM)；2G：电信(CDMA)；2G：移动联通(GSM)+电信(CDMA) 网络频率（2G/3G） 2G：GSM 850/900/1800/1900；2G：CDMA 800；3G：TD-SCDMA 1900/2000；3G：WCDMA 850/900/1900/2100；3G：CDMA2000\n" +
                "存储 ROM 机身的存储空间 64GB ROM类型 UFS RAM 机型的运行内存，决定机身的运行速度。 6GB RAM类型 LPDDR 4X 存储卡 不支持\n" +
                "屏幕 主屏幕尺寸（英寸） 6.01英寸 分辨率 2160*1080分辨率 屏幕材质类型 AMOLED\n" +
                "前置摄像头 前置摄像头 1600万像素 前摄光圈大小 f/2.0\n" +
                "后置摄像头 摄像头数量 指的是后置摄像头的数量，不是所有摄像头的数量 2个 后置摄像头 1600万像素；2000万像素 摄像头光圈大小 f/1.7\n" +
                "电池信息 电池容量（mAh） 3300mAh 电池类型 锂电池 电池是否可拆卸 不可拆卸电池手机更加节省内部空间，密封性更好，请勿在没有专业人士的帮助下自行拆卸。 否 充电器 5V/4A 闪充 支持\n" +
                "数据接口 数据传输接口 WIFI；NFC；蓝牙；WiFi热点；OTG接口 NFC/NFC模式 支持（点对点模式）；支持（读卡器模式）；支持（卡模式） 耳机接口类型 3.5mm 充电接口类型 Type-C\n" +
                "手机特性 指纹识别 支持 GPS 支持 陀螺仪 支持\n" +
                "辅助功能 常用功能 录音；便签；SOS功能；重力感应";

        System.out.println(getMainDesInfo(t4, "型号"));
    }
}
