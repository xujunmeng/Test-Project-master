package com.csf.ada.datashift.dao.fund;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.aug3.storage.mongoclient.MongoAdaptor;
import com.aug3.storage.mongoclient.MongoUtils;
import com.csf.ada.datashift.common.constants.Constants;
import com.csf.ada.datashift.dao.security.BaseDao;
import com.csf.ada.datashift.entity.dict.DictMarket;
import com.csf.ada.datashift.entity.dict.DictSecurityType;
import com.csf.app.base.MongoCollections;
import com.csf.platform.dataentry.common.Parameter;
import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public abstract class AbstractFundDao<T> extends BaseDao {
	private static final Logger log = Logger.getLogger(AbstractFundDao.class);
    protected Map<String, DictMarket> dictMarketMap;
    protected Map<String, DictSecurityType> dictSecurityTypeMap;
    protected Map<String, String> baseOrgMap;
	// 需要从巨潮导入的字段
	protected static final Map<String, String> COLL_SQL_DELTA_FIELDS = new HashMap<String, String>() {
		{
			// f029v_0056
			put(MongoCollections.COLL_BASE_FUND,
					"f029v_0056,ob_fundcode_0056,ob_fundname_0056,f002v_0056,f001v_0056,f004v_0056,f010v_0056,f011v_0056,F012V_0056,F013V_0056, F017V_0056,f006d_0056,f021n_0056,f022n_0056,f025v_0056,f027v_0056,ob_seccode_0007,f003v_0007,f006v_0007,f001v_0007,f003v_0056,f026v_0056,f007n_0056,f014v_0056,f015v_0056,f023v_0056,ob_memo_0056");
			put(MongoCollections.COLL_BASE_FUND_RAISE,
					"ob_fundcode_0061,f007d_0061,f008d_0061,f018n_0061,f019n_0061,f021d_0061,f022d_0061,f012v_0061,f013v_0061,f024v_0061,f025v_0061,f027v_0061,f028v_0061,f023n_0061,f026n_0061,f029n_0061,f031n_0061,f014n_0061,f033n_0061,f011n_0061,f034v_0061,ob_object_id");
			put(MongoCollections.COLL_BASE_FUND_SHARE,
					"ob_fundcode_0058,ob_startdate_0058,ob_enddate_0058,f001n_0058,f002n_0058,f003n_0058,f004n_0058,f006n_0058,ob_object_id");
			put(MongoCollections.COLL_BASE_FUND_MANAGER,
					"ob_fundcode_0074,ob_humanname_0074,ob_humanid_0074,f001d_0074,f002d_0074,f004c_0074,f005c_0074,f006v_0074,f013v_0074,f014v_0074,ob_object_id");
			put(MongoCollections.COLL_FUND_NET_VALUE,
					"ob_fundcode_0235,ob_enddate_0235,f002n_0235,f006n_0235,ob_object_id");
			put(MongoCollections.COLL_FUND_EARNING_DAILY,
					"ob_fundcode_0236,ob_enddate_0236,f001n_0236,f002n_0236,ob_object_id");
			put(MongoCollections.COLL_FUND_DIVIDEND,
					"ob_fundcode_0233,f012c_0233,f013v_0233,f002v_0233,f003v_0233,f005n_0233,f014n_0233,f006d_0233,f007d_0233,f008d_0233,f009d_0233,f010d_0233,f011d_0233,ob_object_id,f004v_0233,ob_memo_0233");
			put(MongoCollections.COLL_FUND_BOND_PORTFOLIO,
					"ob_fundcode_0219,ob_startdate_0219,ob_enddate_0219,f001n_0219,f002n_0219,f003n_0219,f004n_0219,f005n_0219,f006n_0219,f007n_0219,f008n_0219,f009n_0219,f010n_0219,f011n_0219,f012n_0219,f013n_0219,f014n_0219,f015n_0219,f016n_0219,f017n_0219,f018n_0219,ob_object_id");
			put(MongoCollections.COLL_FUND_TOP_FIVE_BOND,
					"ob_fundcode_0220,ob_startdate_0220,ob_enddate_0220,f002v_0220,f003v_0220,f006n_0220,f004n_0220,f005n_0220,ob_object_id");
			put(MongoCollections.COLL_FUND_ASSET_COMBINATION,
					"ob_fundcode_0593,ob_startdate_0593,ob_enddate_0593,f001n_0593,f002n_0593,f003n_0593,f004n_0593,f005n_0593,f006n_0593,f007n_0593,f008n_0593,f009n_0593,f010n_0593,f011n_0593,f012n_0593,f013n_0593,f014n_0593,f015n_0593,f016n_0593,f017n_0593,f018n_0593,f019n_0593,f020n_0593,f021n_0593,f022n_0593,f023n_0593,f024n_0593,f025n_0593,f026n_0593,f027n_0593,f028n_0593,f029n_0593,f030n_0593,f031n_0593,f032n_0593,f033n_0593,f034n_0593,f035n_0593,ob_object_id");
			put(MongoCollections.COLL_FUND_TOP_TEN_STOCK,
					"ob_fundcode_0222,ob_startdate_0222,ob_enddate_0222,f001c_0222,f003v_0222,f004v_0222,f005n_0222,f006n_0222,f007n_0222,ob_object_id");
			put(MongoCollections.COLL_FUND_STOCK_DETAIL,
					"ob_fundcode_0225,ob_startdate_0225,ob_enddate_0225,f001c_0225,f003v_0225,f004v_0225,f005n_0225,f006n_0225,f007n_0225,ob_object_id");
			put(MongoCollections.COLL_BASE_ETF_COMPONENT,
					"ob_fundcode_0610,f001d_0610,f002v_0610,f003v_0610,f004n_0610,f005v_0610,f006n_0610,f007n_0610,ob_object_id,ob_samegroup_id");
			put(MongoCollections.COLL_BASE_EFT_PURCHASE_REDEMPTION,
					"ob_fundcode_0615,f001d_0615,f002n_0615,f003n_0615,f004n_0615,f005n_0615,f006n_0615,f007c_0615,f008n_0615,f009n_0615,f010n_0615,f011v_0615,f012v_0615,f013v_0615,f014n_0615,f015n_0615,f016n_0615,ob_object_id,ob_samegroup_id");
		}
	};
	/**
	 * 基金基本信息 （tb_fund_0056） 开放式基金募集情况 （tb_fund_0061） 开放式基金份额变动情况 （tb_fund_0058）
	 * 基金经理任职情况（tb_fund_0074） 基金净值表 （tb_fund_0235） 货币基金收益日报 （tb_fund_0236）
	 * 基金分红、分拆和合并方案 （tb_fund_0233） 基金持有债券品种组合（tb_fund_0219） 基金投资前五名债券
	 * （tb_fund_0220） 期末基金资产组合情况2009版（tb_fund_0593） 基金投资前十名股票 （tb_fund_0222）
	 * 基金持仓股票明细 （tb_fund_0225） ETF成份股信息（tb_fund_0610） ETF申购赎回信息（tb_fund_0615）
	 */
	protected static final Map<String, String> TABLE_MAPPING = new HashMap<String, String>() {
		{
			put(MongoCollections.COLL_BASE_FUND, "tb_fund_0056");
			put(MongoCollections.COLL_BASE_FUND_RAISE, "tb_fund_0061");
			put(MongoCollections.COLL_BASE_FUND_SHARE, "tb_fund_0058");
			put(MongoCollections.COLL_BASE_FUND_MANAGER, "tb_fund_0074");
			put(MongoCollections.COLL_FUND_NET_VALUE, "tb_fund_0235");
			put(MongoCollections.COLL_FUND_EARNING_DAILY, "tb_fund_0236");
			put(MongoCollections.COLL_FUND_DIVIDEND, "tb_fund_0233");
			put(MongoCollections.COLL_FUND_BOND_PORTFOLIO, "tb_fund_0219");
			put(MongoCollections.COLL_FUND_TOP_FIVE_BOND, "tb_fund_0220");
			put(MongoCollections.COLL_FUND_ASSET_COMBINATION, "tb_fund_0593");
			put(MongoCollections.COLL_FUND_TOP_TEN_STOCK, "tb_fund_0222");
			put(MongoCollections.COLL_FUND_STOCK_DETAIL, "tb_fund_0225");
			put(MongoCollections.COLL_BASE_ETF_COMPONENT, "tb_fund_0610");
			put(MongoCollections.COLL_BASE_EFT_PURCHASE_REDEMPTION,
					"tb_fund_0615");
		}
	};

	/**
	 * load the count of the table in a period
	 */
	public long countAll(String table, Date from, Date to) {
		if (!TABLE_MAPPING.containsKey(table))
			return 0;
		String tb = TABLE_MAPPING.get(table);
		String modTime = "ob_modtime" + tb.substring(tb.lastIndexOf("_"));
		String recTime = "ob_rectime" + tb.substring(tb.lastIndexOf("_"));
		String sql = "select count(1) from juchao." + tb + " where (" + modTime
				+ " >= ? and " + modTime + " < ?) " + " or (" + modTime
				+ " is null and " + recTime + " between ? and ?)";
		return getJdbcTemplate().queryForObject(sql,
				new Object[] { from, to, from, to }, Long.class);
	}

	/**
	 * import data from juchao
	 */
	public int importDataFromJuchao(String table, Parameter p,
			String transportType) {
		List<T> list = this.findFromJuchao(table, p);
		if (Constants.TRANSPORT_FUND_HISTORY.equals(transportType)) {
			list = this.removeExists(table, list);
			return this.saveToDB(table, list);
		} else if (Constants.TRANSPORT_FUND_DELTA.equals(transportType)) {
			// sid 存在时 不移除, 更新部分字段
			if (NOT_RECOVER_COLL.contains(table)) {
				return this.saveAndUpdateToDB(table, list);
			} else {
				if (SHOULD_MERGE_COLL.containsKey(table)) {
					this.removeExistsAndFetch(table, list);
				}
				this.removeExistsSidFromDB(table, list);
				return this.saveToDB(table, list);
			}
		}
		return 0;
	}

	/**
	 * sid 存在时先移除
	 */
	protected abstract void removeExistsSidFromDB(String table, List<T> list);

	//
	protected List<T> removeExistsAndFetch(String table, List<T> list) {
		return list;
	}

	// 需要合并记录的表, only for mongo
	protected static final Map<String, Set<String>> SHOULD_MERGE_COLL = new HashMap<String, Set<String>>() {
		{
			// move this fields to items
			put(MongoCollections.COLL_FUND_TOP_FIVE_BOND, Sets.newHashSet(
					"tick", "bond", "abbr", "amou", "mv", "ratio", "sid"));
			put(MongoCollections.COLL_FUND_TOP_TEN_STOCK, Sets.newHashSet(
					"tick", "stock", "abbr", "amou", "mv", "ratio", "sid",
					"type"));
		}
	};

	/**
	 * 保存到数据库 for mongo
	 */
	protected int saveAndUpdateToDB(String table, List<T> list) {
		// override at mongo
		return 0;
	}

	// 不覆盖,只更新部分字段
	private static final Set<String> NOT_RECOVER_COLL = Sets
			.newHashSet(MongoCollections.COLL_BASE_FUND);

	/**
	 * 保存到数据库
	 */
	protected abstract int saveToDB(String table, List<T> list);

	/**
	 * 按照不同的表从巨潮导入数据
	 */
	protected List<T> findFromJuchao(final String table, Parameter p) {

		final List<T> result = new ArrayList<T>();
		if (!COLL_SQL_DELTA_FIELDS.containsKey(table))
			return result;
		String tb = TABLE_MAPPING.get(table);
		String modTime = "ob_modtime" + tb.substring(tb.lastIndexOf("_"));
		String recTime = "ob_rectime" + tb.substring(tb.lastIndexOf("_"));
		String sql = "select t3.* from (select rownum as rn, "
				+ COLL_SQL_DELTA_FIELDS.get(table) + " from juchao." + tb
				+ " where (" + modTime + " >= ? and " + modTime + " < ?) "
				+ " or (" + modTime + " is null and " + recTime
				+ " between ? and ?)) t3 where rn > ? and rn <= ?";
		getJdbcTemplate().query(
				sql,
				new Object[] { p.getFrom(), p.getTo(), p.getFrom(), p.getTo(),
						p.getStartRowNum(), p.getMaxRows() },
				new RowCallbackHandler() {
					public void processRow(ResultSet resultSet) {
						try {
							T obj = transformData(resultSet, table);
							if (obj != null)
								result.add(obj);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				});
		return result;
	}

	/**
	 * 转换数据
	 */
	protected abstract T transformData(ResultSet resultSet, String table)
			throws SQLException;

	/**
	 * sid 存在时不更新
	 */
	protected abstract List<T> removeExists(String table, List<T> list);

	public Date latestModTime(String table) {
		String tb = TABLE_MAPPING.get(table);
		String modTime = "ob_modtime" + tb.substring(tb.lastIndexOf("_"));
		Timestamp ts = this.getJdbcTemplate().queryForObject(
				"select max(" + modTime + ") dt from " + tb, Timestamp.class);
		return new Date(ts.getTime());
	}
	
	
	
	public void ids(String table , Date from , Date to , final Set<Long> ids){
		
		String tb = TABLE_MAPPING.get(table);
		String modTime = "ob_modtime" + tb.substring(tb.lastIndexOf("_"));
		String sql = "select ob_object_id from" + tb + " where " + modTime + " between ? and ? ";
		this.getJdbcTemplate().query(sql, new RowCallbackHandler() {
			
			public void processRow(ResultSet rs) throws SQLException {
				ids.add(rs.getLong("ob_object_id"));
			}
		} , from , to);
	}
	
	
    public int importDataFromJuchao(String table, List<Long> ids){
        List<T> list = this.findFromJuchao(table, ids);
        this.removeExistsSidFromDB(table, list);
        return this.saveToDB(table, list);
    }
	
	
    private List<T> findFromJuchao(final String table, Collection<Long> ids) {

        final List<T> result = new ArrayList<T>();
        if (!COLL_SQL_DELTA_FIELDS.containsKey(table)) return result;
        String tb = TABLE_MAPPING.get(table);
        String sql;
        // base_fund 特殊处理 code
        if (table.equals(MongoCollections.COLL_BASE_FUND)) {
            this.init();
            sql = "select " + COLL_SQL_DELTA_FIELDS.get(table) +
                    " from JUCHAO." + tb + " t1 left join " +
                    " (select * from tb_public_0007 where rowid in (select min(rowid) from (select * from juchao.tb_public_0007 where F013V_0007='基金代码' order by ob_rectime_0007 desc) group by ob_secid_0007 having count(*) > 0 )) " +
                    " t2 on t1.ob_fundcode_0056 = t2.ob_secid_0007 " +
                    " where t1.ob_object_id in ("+Joiner.on(',').join(ids)+")" +
                    " and t2.F013V_0007='基金代码'";
        }else {
            sql = "select " + COLL_SQL_DELTA_FIELDS.get(table) +
                    " from juchao." + tb + " where ob_object_id in ("+ Joiner.on(',').join(ids) +")";
        }
        getJdbcTemplate().query(sql,
                new RowCallbackHandler() {
                    public void processRow(ResultSet resultSet){
                        try {
                            T obj = transformData(resultSet, table);
                            if (obj !=null) result.add(obj);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
        return result;
    }
	
    /**
     * init
     */
    protected void init(){
        dictMarketMap = getDictMarketMap();
        log.debug("dict_market finished.");

        dictSecurityTypeMap = getSavedMap(DictSecurityType.class, Constants.DICT_BASE_GET_FIELD_ZHSNAME);
        log.debug("dict_security_type finished.");

        List<DBObject> orgList = MongoAdaptor.getDBCollection(MongoCollections.COLL_BASE_ORG)
                .find(new BasicDBObject(), new BasicDBObject("_id", 1).append("name.szh", 1)).toArray();
        baseOrgMap = new HashMap<String, String>();
        for (DBObject org: orgList){
            baseOrgMap.put(MongoUtils.getStringByFieldNameChain(org, "name.szh"), MongoUtils.getStringByFieldNameChain(org, "_id"));
        }
        log.debug("base_org finished.");
    }
	

}
