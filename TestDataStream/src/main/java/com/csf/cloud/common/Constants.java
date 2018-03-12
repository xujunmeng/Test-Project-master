package com.csf.cloud.common;

import com.aug3.storage.passage.util.ConfigManager;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by junmeng.xu on 2016/7/29.
 */
public class Constants {

    public static final String DICT_MKT_A = "A";

    public static Set<String> ANNOUNCE_TYPE_SET = new HashSet<String>();
    static {
        ANNOUNCE_TYPE_SET.add("01030101");  //年度报告
        ANNOUNCE_TYPE_SET.add("1030101");   //年度报告
        ANNOUNCE_TYPE_SET.add("01030301");  //半年度报告
        ANNOUNCE_TYPE_SET.add("1030301");   //半年度报告
        ANNOUNCE_TYPE_SET.add("01030501");  //一季度报告
        ANNOUNCE_TYPE_SET.add("1030501");   //一季度报告
        ANNOUNCE_TYPE_SET.add("01030701");  //三季度报告
        ANNOUNCE_TYPE_SET.add("1030701");   //三季度报告
        ANNOUNCE_TYPE_SET.add("01020301");  //招股说明书全文
        ANNOUNCE_TYPE_SET.add("1020301");   //招股说明书全文
    }

    public static final int STATUS_UNPUBLISHED_INT = 1;

    public static final String SRC_JUCHAO = "1";

    public static final String SYSTEM_USER = "000000"; // record created/updated by system

    // 新三板
    public static final List<String> THREE_BOARD_MARKET = Lists.newArrayList("1008");

    public static final String PASSAGE_SERVER_HOST_S3_CN = ConfigManager.getConfigProperties().getProperty("passage.server", "122.144.134.96");

    public static final int PASSAGE_SERVER_port_S3_CN = 8888;

    public static final int STATUS_VERIFIED = 2;

    public static final Set<String> ENV_MGT = Sets.newHashSet("mgt", "dp");

    public static final String ENV = ConfigManager.getConfigProperties().getProperty("deploy.env");

    public static final String TYPE_DELTA = "delta";
    public static final String TYPE_HISTORY = "history";
    public static final String TYPE_UPDATE = "update";
}
