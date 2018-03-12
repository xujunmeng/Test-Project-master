package com.csf.ada.datashift.jmslistener;

import java.util.Date;
import java.util.Map;

public abstract class AbstractMessageListener {
	protected abstract void executeTask(Date relDate , StringBuilder infoBuilder , Map<String , String> parm);
}
