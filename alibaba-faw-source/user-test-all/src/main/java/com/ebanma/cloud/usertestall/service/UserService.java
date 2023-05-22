package com.ebanma.cloud.usertestall.service;

import com.ebanma.cloud.usertestall.domain.common.PageQuery;
import com.ebanma.cloud.usertestall.domain.common.PageResult;
import com.ebanma.cloud.usertestall.domain.dto.UserDTO;
import com.ebanma.cloud.usertestall.domain.dto.UserQueryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kmkmj
 * @date 2023/03/21
 */
@Service
public interface UserService {

    /**
     * @author: WangHaiyang
     * @date: 2023/3/21 21:01
     * @param userDTO
     * @return int
     * @description:新增
     */
    int save(UserDTO userDTO);

    /**
     * @author: WangHaiyang
     * @date: 2023/3/21 21:01
     * @param id
     * @param userDTO
     * @return int
     * @description:更新
     */
    int update(Long id, UserDTO userDTO);
    /**
     * @author: WangHaiyang
     * @date: 2023/3/21 21:02
     * @param id
     * @return int
     * @description:删除
     */
    int delete(Long id);

    PageResult<List<UserDTO>> query(PageQuery<UserQueryDTO> pageQuery);
}
