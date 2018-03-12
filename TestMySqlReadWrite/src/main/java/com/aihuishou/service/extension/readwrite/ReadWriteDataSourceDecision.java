package com.aihuishou.service.extension.readwrite;

/**
 * Created by james on 2017/6/29.
 */
public class ReadWriteDataSourceDecision {

    private static final ThreadLocal<DataSourceType> holder = new ThreadLocal<>();

    public enum DataSourceType {
        write, read
    }

    public static void markWrite() {
        holder.set(DataSourceType.write);
    }

    public static void markRead() {
        holder.set(DataSourceType.read);
    }

    public static void reset() {
        holder.set(null);
    }

    public static boolean isChoiceNone() {
        return null == holder.get();
    }

    public static boolean isChoiceWrite() {
        return DataSourceType.write == holder.get();
    }

    public static boolean isChoiceRead() {
        return DataSourceType.read == holder.get();
    }



}
