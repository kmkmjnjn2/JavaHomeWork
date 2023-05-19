package com.ebanma.cloud.web;

import com.ebanma.cloud.mvc.annotation.Autowired;
import com.ebanma.cloud.mvc.annotation.Controller;
import com.ebanma.cloud.mvc.annotation.RequestMapping;
import com.ebanma.cloud.service.HeartbeatService;

@Controller
@RequestMapping("/test")
public class HeartbeatController {

    @Autowired
    private HeartbeatService heartbeatService;

    @RequestMapping("/heartbeat")
    public String heartbeat(String msg) {
        return heartbeatService.getMessage(msg);
    }


}
