package com.ebanma.cloud.usertestall.controller;
import com.ebanma.cloud.usertestall.domain.common.ErrorCode;
import com.ebanma.cloud.usertestall.domain.common.PageQuery;
import com.ebanma.cloud.usertestall.domain.common.PageResult;
import com.ebanma.cloud.usertestall.domain.common.Result;
import com.ebanma.cloud.usertestall.domain.dto.UserDTO;
import com.ebanma.cloud.usertestall.domain.dto.UserQueryDTO;
import com.ebanma.cloud.usertestall.domain.vo.UserVO;
import com.ebanma.cloud.usertestall.service.UserService;
import com.ebanma.cloud.usertestall.util.InsertValidationGroup;
import com.ebanma.cloud.usertestall.util.UpdateValidationGroup;
import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author WHY
 * @version $ Id: UserController, v 0.1 2023/03/22 8:28 kmkmj Exp $
 */
@RestController
@RequestMapping("api/user")
@Validated
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    /*
    新增用户
     */
    @CacheEvict(cacheNames = "users-cache",allEntries = true)
    @PostMapping
    public Result save(@Validated(InsertValidationGroup.class) @RequestBody UserDTO userDTO){
        int save = userService.save(userDTO);
        if (save == 1) {
            return Result.success();
        }else{
            return Result.fail(ErrorCode.SYSTEM_ERROR);
        }
    }

    /*
    更新用户
     */
    @PutMapping("/{id}")
    public Result update(@NotNull @PathVariable("id") Long id, @Validated(UpdateValidationGroup.class) @RequestBody UserDTO userDTO) {
        int update = userService.update(id, userDTO);
        if (update == 1) {
            return Result.success();
        }else{
            return Result.fail(ErrorCode.SYSTEM_ERROR);
        }
    }

    /*
    删除用户信息
     */
    @DeleteMapping("/{id}")
    public Result delete(@NotNull @PathVariable("id") Long id) {
        int delete = userService.delete(id);
        if(delete == 1) {
            return Result.success();
        }else{
            return Result.fail(ErrorCode.SYSTEM_ERROR);
        }
    }

    @Cacheable(cacheNames = "users-cache")
    @GetMapping
    public Result<PageResult> query(Integer pageNo, Integer pageSize, UserQueryDTO queryDTO) {
        if (logger.isInfoEnabled()) {
            logger.info("未使用缓存");
        }
        // 构造查询条件
        PageQuery<UserQueryDTO> pageQuery = new PageQuery<>();
        pageQuery.setPageNo(pageNo);
        pageQuery.setPageSize(pageSize);
        pageQuery.setQuery(queryDTO);
        // 查询
        PageResult<List<UserDTO>> pageResult = userService.query(pageQuery);

        //实体转换
        List<UserVO> userVOList = Optional.ofNullable(pageResult.getData())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(userDTO -> {
                    UserVO userVO = new UserVO();
                    BeanUtils.copyProperties(userDTO, userVO);
                    //对特殊字段做处理
                    userVO.setPassword("******");
                    return userVO;
                }).collect(Collectors.toList());
        // 封装返回结果
        PageResult<List<UserVO>> result = new PageResult<>();
        BeanUtils.copyProperties(pageResult, result);
        result.setData(userVOList);
        return Result.success(result);
    }


}