package com.ebanma.cloud.usertestall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ebanma.cloud.usertestall.domain.common.PageQuery;
import com.ebanma.cloud.usertestall.domain.common.PageResult;
import com.ebanma.cloud.usertestall.domain.dto.UserDTO;
import com.ebanma.cloud.usertestall.domain.dto.UserQueryDTO;
import com.ebanma.cloud.usertestall.domain.entity.UserDO;
import com.ebanma.cloud.usertestall.mapper.UserMapper;
import com.ebanma.cloud.usertestall.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author WHY
 * @version $ Id: UserServiceImpl, v 0.1 2023/03/21 21:03 kmkmj Exp $
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public int save(UserDTO userDTO) {
        UserDO userDO = new UserDO();
        //TODO 浅拷贝
        BeanUtils.copyProperties(userDTO,userDO);
        return userMapper.insert(userDO);
    }

    @Override
    public int update(Long id, UserDTO userDTO) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO,userDO);
        userDO.setId(id);
        return userMapper.updateById(userDO);
    }

    @Override
    public int delete(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public PageResult<List<UserDTO>> query(PageQuery<UserQueryDTO> pageQuery) {
        Page page = new Page(pageQuery.getPageNo(), pageQuery.getPageSize());
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(pageQuery,userDO);
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>(userDO);

        IPage<UserDO> userPage = userMapper.selectPage(page, queryWrapper);
        PageResult pageResult = new PageResult();
        pageResult.setPageNo((int) userPage.getCurrent());
        pageResult.setPageSize((int) userPage.getSize());
        pageResult.setTotal(userPage.getTotal());
        pageResult.setPageNum(userPage.getPages());

        List<UserDTO>  userDTOList = Optional.ofNullable(userPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(user -> {
                    UserDTO userDTO = new UserDTO();
                    BeanUtils.copyProperties(user, userDTO);
                    return userDTO;
                }).collect(Collectors.toList());
        pageResult.setData(userDTOList);
        return pageResult;
    }
}