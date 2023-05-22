package com.ebanma.cloud.usertestall.controller;

import com.alibaba.fastjson.JSON;
import com.ebanma.cloud.demo.util.ResourceHelper;
import com.ebanma.cloud.usertestall.domain.common.ErrorCode;
import com.ebanma.cloud.usertestall.domain.common.PageQuery;
import com.ebanma.cloud.usertestall.domain.common.PageResult;
import com.ebanma.cloud.usertestall.domain.common.Result;
import com.ebanma.cloud.usertestall.domain.dto.UserDTO;
import com.ebanma.cloud.usertestall.domain.dto.UserQueryDTO;
import com.ebanma.cloud.usertestall.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;



    @Test
    public void should_invoke_service_to_save_user_success(){
        //模拟依赖方法:userService.save
        //given
        Mockito.when(userService.save(any(UserDTO.class))).thenReturn(1);
        //when
        Assert.assertEquals("返回值不一致",Result.success().getData(),userController.save(mock(UserDTO.class)).getData());
        //then
        verify(userService).save(any(UserDTO.class));
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void should_invoke_service_to_save_user_fail(){
        //模拟依赖方法:userService.save
        Mockito.when(userService.save(any(UserDTO.class))).thenReturn(0);
        Assert.assertEquals("返回值不一致",Result.fail(ErrorCode.SYSTEM_ERROR).getData(),userController.save(mock(UserDTO.class)).getData());
        verify(userService).save(any(UserDTO.class));
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void should_invoke_service_to_update_user_success(){
        //given
        when(userService.update(anyLong(), any(UserDTO.class))).thenReturn(1);
        //when
        Assert.assertEquals("返回值不一致",Result.success().getData(),userController.update(1L,mock(UserDTO.class)).getData());
        //then
        verify(userService).update(anyLong(),any(UserDTO.class));
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void should_invoke_service_to_update_user_fail(){
        //given
        when(userService.update(anyLong(), any(UserDTO.class))).thenReturn(0);
        //when
        Assert.assertEquals("返回值不一致",Result.fail(ErrorCode.SYSTEM_ERROR).getData(),userController.update(1L,mock(UserDTO.class)).getData());
        //then
        verify(userService).update(anyLong(),any(UserDTO.class));
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void should_invoke_service_to_delete_user_given_success(){
        //given
        when(userService.delete(anyLong())).thenReturn(1);
        //when
        Assert.assertEquals("返回值不一致",Result.success().getData(),userController.delete(1L).getData());
        //then
        verify(userService).delete(anyLong());
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void should_invoke_service_to_delete_user_given_fail(){
        //given
        when(userService.delete(anyLong())).thenReturn(0);
        //when
        Assert.assertEquals("返回值不一致",Result.fail(ErrorCode.SYSTEM_ERROR).getData(),userController.delete(1L).getData());
        //then
        verify(userService).delete(anyLong());
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void should_invoke_service_to_query_user_given_validation_pass(){
        //given
        Integer pageNo = 1;
        Integer pageSize = 10;
        PageResult<List<UserDTO>> pageResult = new PageResult<>();
        List<UserDTO> userList = new ArrayList<>();
        userList.add(mock(UserDTO.class));
        pageResult.setData(userList);

        Mockito.when(userService.query(any(PageQuery.class))).thenReturn(pageResult);
        //when
        userController.query(pageNo,pageSize,any(UserQueryDTO.class));
        //then
        verify(userService).query(any(PageQuery.class));
        verifyNoMoreInteractions(userService);
    }



}