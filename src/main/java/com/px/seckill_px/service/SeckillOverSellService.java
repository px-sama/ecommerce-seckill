package com.px.seckill_px.service;

import com.px.seckill_px.db.dao.SeckillActivityDao;
import com.px.seckill_px.db.po.SeckillActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeckillOverSellService {
    @Autowired
    private SeckillActivityDao seckillActivityDao;

    public String processSeckill(long activityId) {
        SeckillActivity activity = seckillActivityDao.querySeckillActivityById(activityId);
        int availableStock = activity.getAvailableStock();
        String result;

        if (availableStock > 0) {
            result = "Congratulations!, the order has been placed!";
            System.out.println(result);
            availableStock -= 1;
            activity.setAvailableStock(availableStock);
            seckillActivityDao.updateSeckillActivity((activity));
        }else {
             result = "Sorry, you didn't get the item.";
             System.out.println(result);
        }
        return result;

    }

}
