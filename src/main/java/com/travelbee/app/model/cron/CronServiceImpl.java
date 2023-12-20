package com.travelbee.app.model.cron;

import com.travelbee.app.enities.Voucher;
import com.travelbee.app.service.impl.VoucherServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CronServiceImpl implements CronService{

    private final VoucherServiceImpl voucherService;
    @Override
    @Async
    @Scheduled(cron = "0 0 0 12 * ?")
    public void VoucherOutOfQuantity() {
        List<Voucher> list = voucherService.findAll();
        list.forEach(e -> {
            if(e.getCondition() >= e.getQuanity()){
                e.setIsactive(false);
                voucherService.update(e);
            }
        });
    }
}
