package com.csf.ada.datashift.dao.security;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import com.csf.ada.datashift.common.constants.Constants;
import com.csf.ada.datashift.entity.dict.DictMarket;
import com.csf.platform.dataentry.common.Parameter;
import com.csf.platform.dataentry.common.datasource.DateStoreSets;
import com.csf.platform.dataentry.common.listener.DefaultTransportListener;
import com.csf.platform.dataentry.common.listener.ITransportListener;
import com.csf.platform.dataentry.entity.dict.DictBase;
import com.google.code.morphia.query.Query;

public abstract class BaseDao {
	private JdbcTemplate jdbcTemplate;
	protected DateStoreSets dsSets;
    private ITransportListener listener;
	
	
	
	public abstract void initNewOp(Date from , Date to , Parameter param) throws Exception;
	public abstract void initAllOp() throws Exception;
	
	
	
	
	
	
	
	
	
    public ITransportListener getListener() {
        if (listener == null) {
            listener = new DefaultTransportListener();
        }
        return listener;
    }
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
    public <T extends DictBase> Map<String, T> getSavedMap(Class<T> clazz, int field, String... queryFields) {
        Map<String, T> result = new HashMap<String, T>();

        List<T> list = null;
        if (queryFields == null || queryFields.length == 0) {
            list = findAll(clazz);
        } else {
            list = findAllWithFields(clazz, queryFields);
        }

        for (T t : list) {
            switch (field) {
            case Constants.DICT_BASE_GET_FIELD_CODE:
                result.put(t.getCode(), t);
                break;
            case Constants.DICT_BASE_GET_FIELD_ENAME:
                result.put(t.getEname(), t);
                break;
            case Constants.DICT_BASE_GET_FIELD_ZHSNAME:
                result.put(t.getZhsname(), t);
                break;
            default:
                break;
            }
        }
        return result;
    }
	
    @SuppressWarnings("unchecked")
    public <T> List<T> findWithFieldsByCriteria(Class<T> clazz, Map<String, Object> criteria, String... fields) {
        Query<T> q = dsSets.find(clazz);
        if (ArrayUtils.isNotEmpty(fields)) {
            q = q.retrievedFields(true, fields);
        }
        if (MapUtils.isNotEmpty(criteria)) {
            for (Map.Entry<String, Object> entry : criteria.entrySet()) {
                Object value = entry.getValue();

                if (value instanceof Iterable) {
                    q = q.field(entry.getKey()).in((Iterable<T>) value);
                } else {
                    q = q.field(entry.getKey()).equal(value);
                }
            }
        }
        return q.asList();
    }
    public <T> List<T> findAllWithFields(Class<T> clazz, String... fields) {
        return findWithFieldsByCriteria(clazz, null, fields);
    }
    public <T> List<T> findAll(Class<T> clazz) {
        return findAllWithFields(clazz);
    }
    protected Map<String, DictMarket> getDictMarketMap() {
        Map<String, DictMarket> result = new HashMap<String, DictMarket>();

        List<DictMarket> dictMarketList = findAll(DictMarket.class);
        for (DictMarket dm : dictMarketList) {
            String dmSzh = dm.getZhsname();
            if (Constants.marketMap.containsKey(dmSzh)) {
                if ("深圳证券交易所主板A股".equals(dmSzh)) {
                    result.put("深交所", dm);
                    result.put("深交所主板", dm);
                } else {
                    result.put(Constants.marketMap.get(dmSzh), dm);
                }
            } else {
                result.put(dmSzh, dm);
            }
        }

        return result;
    }
	
	
	
	
	
	
}
