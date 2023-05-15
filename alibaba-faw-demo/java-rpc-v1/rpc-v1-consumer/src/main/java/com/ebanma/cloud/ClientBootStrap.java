package com.ebanma.cloud;

import com.ebanma.cloud.rpc.api.UserApi;
import com.ebanma.cloud.rpc.api.dto.UserInfoDTO;
import com.ebanma.cloud.rpc.consumer.proxy.RpcClientProxy;

/**
 * 测试类
 */
public class ClientBootStrap {
    public static void main(String[] args) {
        UserApi userService = (UserApi) RpcClientProxy.createProxy(UserApi.class);
        UserInfoDTO user = userService.getById(2);
        System.out.println(user);
    }
}