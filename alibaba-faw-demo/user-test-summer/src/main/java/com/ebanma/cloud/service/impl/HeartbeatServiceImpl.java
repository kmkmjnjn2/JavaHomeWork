package com.ebanma.cloud.service.impl;

import com.ebanma.cloud.mvc.annotation.Service;
import com.ebanma.cloud.service.HeartbeatService;

@Service("heartbeatService")
public class HeartbeatServiceImpl implements HeartbeatService {
    @Override
    public String getMessage(String msg) {
        System.out.println("HeartbeatServiceImpl中的入参："+msg);
        return msg;
    }
}
