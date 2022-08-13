package com.qxy.NoError.utils;

import java.util.List;

/**
 * 封装的字符串操作工具类
 * @author 徐鑫
 */
public class StrUtil {

    public static String getStrFromStrList(List<String> strings, int size) {
        return formatStr(strings, "", size);
    }
    public static String getStrFromStrList(List<String> strings) {
        return formatStr(strings, "", 0);
    }

    /**
     * 从list中格式化字符串
     * @param strings 需要格式化的list集合
     * @param mark 每个字符串的分割符
     * @param count 保留 list 多少位
     * @return 格式化后的字符串
     */
    public static String formatStr(List<String> strings, String mark, int count) {
        if (strings == null || strings.isEmpty()) {
            return null;
        }
        int size = Math.min(count, strings.size());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size - 1; i++) {
            stringBuilder.append(strings.get(i)).append(mark);
        }
        stringBuilder.append(strings.get(size - 1));
        return stringBuilder.toString();
    }

    /**
     * 格式化数字
     * 例如 11111 格式化为 1.1万，默认保留一位小数
     * @param needToFormat 需要格式化的数字
     * @param pointIndex 小数点后有多少位为，4表示 1.1111，也就是 1.1万
     * @param unit 单位
     * @return 格式化后的字符串
     */
    public static String formatFromInt(Long needToFormat, int pointIndex, String unit) {
        String value = String.valueOf(needToFormat);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < value.length() - pointIndex; i++) {
            stringBuilder.append(value.charAt(i));
        }
        return stringBuilder.append(".")
                .append(value.charAt(value.length() - pointIndex + 1))
                .append(unit)
                .toString();
    }

    /**
     * 格式化排行数据
     * @param rank 排名
     * @param minRank 需要加前缀的最小排名
     * @param prefix 需要添加的前缀
     * @return 排行字符串
     */
    public static String formatRank(int rank, int minRank , String prefix) {
        StringBuilder stringBuilder = new StringBuilder();
        if (rank <= minRank) {
            stringBuilder.append(prefix);
        }
        return stringBuilder.append(rank).toString();
    }

}
