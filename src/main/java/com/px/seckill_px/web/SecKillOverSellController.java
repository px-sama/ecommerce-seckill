package com.px.seckill_px.web;

import com.px.seckill_px.service.SeckillOverSellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecKillOverSellController {
    @Autowired
    private SeckillOverSellService seckillOverSellService;

    @ResponseBody
    @RequestMapping("/seckill/{seckillActivityId}")
    public String seckill(@PathVariable long seckillActivityId) {
        return seckillOverSellService.processSeckill((seckillActivityId));
    }

}
