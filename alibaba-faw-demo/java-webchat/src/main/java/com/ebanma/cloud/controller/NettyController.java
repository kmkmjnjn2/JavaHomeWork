package com.ebanma.cloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author WHY
 * @version $ Id: NettyController, v 0.1 2023/04/21 15:26 kmkmj Exp $
 */
@Controller
public class NettyController {

    @RequestMapping("/chat")
    public ModelAndView index(){
        return new ModelAndView("chat");
    }
}