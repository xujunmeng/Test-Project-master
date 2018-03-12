package com.csf.ada.datashift.jmslistener;

import java.util.Date;
import java.util.Map;

import com.aug3.sys.util.DateUtil;
import com.google.common.base.Strings;

public abstract class CommonMessageListener extends AbstractMessageListener {
	
	private static final String PARAM_FROM = "from";
	private static final String PARAM_TO = "to";
	/**
	 * 解析出导入历史数据的时间段
	 * @param param
	 * @return
	 */
	protected Date[] extractImportRangeDate(Map<String , String> param){
		String from = param.get(PARAM_FROM);
		String to = param.get(PARAM_TO);
		if(!Strings.isNullOrEmpty(from) && !Strings.isNullOrEmpty(to)){
			Date start = DateUtil.convertLocal2UTC(from);
			Date end = DateUtil.convertLocal2UTC(to);
			return new Date[]{start , end};
		}
		return null;
	}
	
	protected static final String PARAM_TYPE = "type";
}
