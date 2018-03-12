package com.csf.ada.datashift.jmslistener;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.aug3.sys.util.DateUtil;
import com.csf.ada.datashift.common.constants.Constants;
import com.csf.ada.datashift.service.FundService;
import com.csf.app.base.BaseConstants;
import com.google.common.base.Strings;

public class FundCommonMessageListener extends CommonMessageListener {
	private static final String FUND_DB_NAME = "fund";
	private static final String TABLE_TYPE = "table";
	private static final String FROM_DATE = "date";
	private FundService fundService;
	
	@Override
	protected void executeTask(Date relDate , StringBuilder infoBuilder , Map<String , String> param){
		String type = param.get(PARAM_TYPE);
		String table = FUND_DB_NAME + BaseConstants.DOT + param.get(TABLE_TYPE);
		
		//增量导入 
		if(Constants.TRANSPORT_FUND_DELTA.equals(type)){
			Date[] dt = this.extractImportRangeDate(param);
			if(null == dt){
				dt = new Date[2];
				String from = param.get(FROM_DATE);
				if(!Strings.isNullOrEmpty(from)){
					try {
						dt[1] = DateUtil.parseDate(from);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if(dt[1] == null){
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.HOUR_OF_DAY, 8);
					dt[1] = cal.getTime();
				}
				dt[0] = DateUtil.getDaysBefore(dt[1], -2);
			}
			//先按导入历史的方式导入最近两天数据，再更新改过的数据
			int countHist = fundService.deltaShift(table , dt[0] , dt[1] , Constants.TRANSPORT_FUND_HISTORY);
			infoBuilder.append("import ").append(table).append(", size:").append(countHist);
			
			Long count = fundService.updateByModifyTime(table);
			infoBuilder.append(", size:").append(count);
			
			
		}
	}
}	
