package com.px.seckill_px.web;

import com.px.seckill_px.service.SeckillActivityService;
import com.px.seckill_px.service.SeckillOverSellService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class SecKillOverSellController {
    @Resource
    private SeckillOverSellService seckillOverSellService;

    @Resource
    private SeckillActivityService seckillActivityService;

//    @ResponseBody
//    @RequestMapping("/seckill/{seckillActivityId}")
//    public String seckill(@PathVariable long seckillActivityId) {
//        return seckillOverSellService.processSeckill((seckillActivityId));
//    }
    @ResponseBody
    @RequestMapping("/seckill/{seckillActivityId}")
    public String seckillCommodity(@PathVariable long seckillActivityId) {
        boolean stockValidateResult = seckillActivityService.seckillStockValidator(seckillActivityId);
        return stockValidateResult ? "Congratulations! Your order has been placed" : "Sorry, the good is sold out. Please Try again next time!";
        // Use Lua script to process seckill request

    }
}
