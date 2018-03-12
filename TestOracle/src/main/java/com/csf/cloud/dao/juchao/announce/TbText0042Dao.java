package com.csf.cloud.dao.juchao.announce;

import com.csf.cloud.entity.juchao.announce.TbText0042;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by junmeng.xu on 2016/7/29.
 */
public interface TbText0042Dao {

    Set<String> getSidsFromJuchao(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

    List<TbText0042> getEntityBySids(@Param("sids") Collection<String> sids);

}
