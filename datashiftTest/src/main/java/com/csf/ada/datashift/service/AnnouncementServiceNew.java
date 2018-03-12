package com.csf.ada.datashift.service;

import java.util.Date;
import java.util.Set;

import org.apache.log4j.Logger;

import com.csf.ada.datashift.dao.news.AnnouncementTransportDao;
import com.csf.ada.datashift.entity.news.Announcement;
import com.csf.ada.datashift.utils.DataShiftUtil;
import com.csf.platform.dataentry.common.Parameter;
import com.csf.platform.dataentry.common.listener.ITransportListener;
import com.google.common.collect.Sets;
import com.sun.tools.javac.util.List;

public class AnnouncementServiceNew {
	private static final Logger log = Logger.getLogger(AnnouncementServiceNew.class);
	private static final int ANNOUNCE_BATCH_SIZE = 500;
	
	private AnnouncementTransportDao announceDao;
	
	public void deltaShift(Date from , Date to , StringBuilder infoBuilder , int opTyp){
		long start = System.currentTimeMillis();
		Parameter p = new Parameter();
		p.setStartRowNum(1);
		p.setMaxRows(ANNOUNCE_BATCH_SIZE);
		p.setFrom(from);
		p.setTo(to);
		p.setOpTyp(opTyp);
		int saved = this.transport(p);
	}
	
	private int transport(Parameter p){
		int saved = 0;
		try {
			announceDao.initNewOp(p.getFrom() , p.getTo() , p);
		} catch (Exception e) {
			log.error("error in init step" , e);
			return saved;
		}
		ITransportListener listener = announceDao.getListener();
		long total = announceDao.countAll(p);
		int pages = DataShiftUtil.getTotalPages(total , ANNOUNCE_BATCH_SIZE);
		log.info("Find records : " + total + "pages : " + pages);
		
		int innerErrCount = 0;
		for(int i = 0 ; i < pages ; i++){
			try {
				log.info("Start to import announcement for page : " + (i +　1) + "total pages : " + pages);
				java.util.List<String> willImportIds = announceDao.willImportAnnounce(p , i * ANNOUNCE_BATCH_SIZE , (i + 1) * ANNOUNCE_BATCH_SIZE);
				listener.doBeforeAll(p, announceDao);
				Set<String> idsSet = announceDao.removeExitsSid(p.getFrom(), p.getTo() , Sets.newHashSet(willImportIds));
				log.info("size : " + willImportIds.size() + ",exists : " + (willImportIds.size() - idsSet.size()));
				List<Announcement> result = announceDao.findFromJuchao(idsSet , p);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
	}
	
}
