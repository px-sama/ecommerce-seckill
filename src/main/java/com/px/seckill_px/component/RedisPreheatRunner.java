package com.px.seckill_px.component;

import com.px.seckill_px.db.dao.SeckillActivityDao;
import com.px.seckill_px.db.po.SeckillActivity;
import com.px.seckill_px.util.RedisService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class RedisPreheatRunner implements ApplicationRunner {

    @Resource
    RedisService redisService;
    @Resource
    SeckillActivityDao seckillActivityDao;
    /**
     * 启动项目时 向 Redis 存入 商品库存 * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // List all activities from db
        List<SeckillActivity> seckillActivities = seckillActivityDao.querySeckillActivitysByStatus(1);
        // Write all activities to redis
        for (SeckillActivity seckillActivity : seckillActivities) {
            redisService.setValue("stock:" + seckillActivity.getId(),
                    (long) seckillActivity.getAvailableStock());
        } }
}
