package com.ebanma.cloud.usertestall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ebanma.cloud.usertestall.domain.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author WHY
 * @version $ Id: UserMapper, v 0.1 2023/03/21 20:42 kmkmj Exp $
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
}