package com.ebanma.cloud.dao;

import com.ebanma.cloud.domain.User;

import java.util.List;

/**
 * @author kmkmj
 * @date 2023/03/21
 */
public interface IUserDao {

    User findByCondition(User user);

    List<User> findAll();
}
