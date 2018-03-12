package com.junmeng.dao;

import com.junmeng.entity.CsfUser;

import java.util.List;

/**
 * Created by junmeng.xu on 2016/11/11.
 */
public interface ICsfUserDao {

    //增加记录  返回的必须是Integer，而不是String
    Integer insertCsfUser(CsfUser user);

    //删除记录
    Integer delCsfUserByUid(Integer uid);

    //修改记录
    Integer updateCsfUser(CsfUser user);

    //查看记录
    List<CsfUser> findCsfUser();

}
