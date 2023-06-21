package com.px.seckill_px.service;

import com.px.seckill_px.util.RedisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SeckillActivityService {
    @Resource
    private RedisService redisService;

    public boolean seckillStockValidator(long activityId) {
        String key = "stock:" + activityId;
        return redisService.stockDeductValidator(key);
    }
}
