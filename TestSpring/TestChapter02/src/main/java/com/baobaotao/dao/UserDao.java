package com.baobaotao.dao;

import com.baobaotao.domain.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by james on 2017/5/24.
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Logger logger = Logger.getLogger(UserDao.class);

    public int getMatchCount(String userName, String password) {

        String sqlStr = " SELECT count(*) FROM t_user "
                + " WHERE user_name =? and password=? ";

        logger.info("getMatchCount sql : " + sqlStr);

        return jdbcTemplate.queryForInt(sqlStr, new Object[] { userName, password });
    }

    public User findUserByUserName(final String userName) {
        String sqlStr = " SELECT user_id,user_name,credits "
                + " FROM t_user WHERE user_name =? ";

        logger.info("findUserByUserName sql : " + sqlStr);

        final User user = new User();
        jdbcTemplate.query(sqlStr, new Object[] { userName },
                rs -> {
                    user.setUserId(rs.getInt("user_id"));
                    user.setUserName(userName);
                    user.setCredits(rs.getInt("credits"));
                });
        return user;
    }

    public void updateLoginInfo(User user) {
        String sqlStr = " UPDATE t_user SET last_visit=?,last_ip=?,credits=? "
                + " WHERE user_id =?";

        logger.info("updateLoginInfo sql : " + sqlStr);

        jdbcTemplate.update(sqlStr, new Object[] { user.getLastVisit(),
                user.getLastIp(),user.getCredits(),user.getUserId()});
    }
}
