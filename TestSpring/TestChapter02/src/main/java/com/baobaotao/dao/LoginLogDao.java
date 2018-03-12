package com.baobaotao.dao;

import com.baobaotao.domain.LoginLog;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by james on 2017/5/24.
 */
@Repository
public class LoginLogDao {

    Logger logger = Logger.getLogger(LoginLogDao.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertLoginLog(LoginLog loginLog) {
        String sqlStr = "INSERT INTO t_login_log(user_id,ip,login_datetime) "
                + "VALUES(?,?,?)";
        logger.info("insertLoginLog sql : " + sqlStr);
        Object[] args = { loginLog.getUserId(), loginLog.getIp(),
                loginLog.getLoginDate() };
        jdbcTemplate.update(sqlStr, args);
    }
}
