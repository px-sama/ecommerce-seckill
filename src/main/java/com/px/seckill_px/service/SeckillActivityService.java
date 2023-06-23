package com.px.seckill_px.service;

import com.alibaba.fastjson.JSON;
import com.px.seckill_px.db.dao.OrderDao;
import com.px.seckill_px.db.dao.SeckillActivityDao;
import com.px.seckill_px.db.po.Order;
import com.px.seckill_px.db.po.SeckillActivity;
import com.px.seckill_px.mq.RocketMQService;
import com.px.seckill_px.util.RedisService;
import com.px.seckill_px.util.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Service
public class SeckillActivityService {
    @Resource
    private RedisService redisService;

    @Resource
    private SeckillActivityDao seckillActivityDao;

    @Resource
    private RocketMQService rocketMQService;

    @Resource
    OrderDao orderDao;

    private SnowFlake snowFlake = new SnowFlake(1, 1);

    public boolean seckillStockValidator(long activityId) {
        String key = "stock:" + activityId;
        return redisService.stockDeductValidator(key);
    }

    public Order createOrder(long seckillActivityId, long userId) throws Exception{
        SeckillActivity seckillActivity = seckillActivityDao.querySeckillActivityById(seckillActivityId) ;
        Order order = new Order();
        // use snowflase algorithm to generate ID
        order.setOrderNo(String.valueOf(snowFlake.nextId()));
        order.setSeckillActivityId(seckillActivity.getId());
        order.setUserId(userId);
        order.setOrderAmount(seckillActivity.getSeckillPrice().longValue());

        /*
         *2.发送创建订单消息
         */
        rocketMQService.sendMessage("seckill_order", JSON.toJSONString(order));
        return order;
    }

    public void payOrderProcess(String orderNo) {
        log.info("完成支付订单 订单号:" + orderNo);
        Order order = orderDao.queryOrder(orderNo);
        boolean deductStockResult = seckillActivityDao.deductStock(order.getSeckillActivityId());
        if (deductStockResult) {
            order.setPayTime(new Date());
// 订单状态 0、没有可用库存，无效订单 1、已创建等待支付 2、完成支付
            order.setOrderStatus(2);
            orderDao.updateOrder(order);
        }
    }
}
