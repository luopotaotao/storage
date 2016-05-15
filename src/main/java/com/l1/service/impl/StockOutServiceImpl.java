package com.l1.service.impl;

import com.l1.dao.StockOutDao;
import com.l1.dao.StockOutDtlDao;
import com.l1.entity.StockOut;
import com.l1.entity.StockOutDtl;
import com.l1.service.StockOutService;
import com.l1.service.SeqService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("stockOutService")
public class StockOutServiceImpl implements StockOutService {
    @Resource
    private StockOutDao stockOutDao;

    @Resource
    private StockOutDtlDao stockOutDtlDao;

    @Resource
    private SeqService seqService;

    @Override
    public List<StockOut> find(Map<String, Object> map) {
        return stockOutDao.find(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return stockOutDao.getTotal(map);
    }

    @Override
    public Integer add(StockOut stockOut) {
        return stockOutDao.add(stockOut);
    }

    @Override
    public Integer update(StockOut stockOut) {
        return stockOutDao.update(stockOut);
    }


    @Override
    public Integer deleteById(Integer id) {
        return stockOutDao.deleteById(id);
    }

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRES_NEW)
    @Override
    public Integer delete(Integer[] ids) {
        stockOutDtlDao.deleteByStockOutIds(ids);
        return stockOutDao.delete(ids);
    }


    @Override
    public List<StockOut> findByIds(String ids) {
        return stockOutDao.findByIds(ids);
    }

    @Override
    public List<String> findNamesByIds(String ids) {
        return stockOutDao.findNamesByIds(ids);
    }

    @Override
    public StockOut findById(Integer id) {
        return stockOutDao.findById(id);
    }

    @Override
    public int save(StockOut stockOut) {
        int ret = stockOutDao.save(stockOut);
        return ret > 0 ? stockOut.getId() : -1;
    }

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRES_NEW)
    @Override
    public int saveStockOutWithDetails(StockOut stockOut, List<StockOutDtl> details) {
        String billNo = seqService.next("CK");
        stockOut.setBillNo(billNo);
        int id = this.save(stockOut);
        if (details != null && details.size() > 0) {
            for (StockOutDtl item : details) {
                item.setStockOutId(id);
            }
            stockOutDtlDao.batchSave(details);
        }
        return id;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public int updateWithDetails(StockOut stockOut, List<StockOutDtl> inserted,List<StockOutDtl> updated,Integer[] ids) {
        int id = this.update(stockOut);
        if (inserted != null && inserted.size() > 0) {
            for (StockOutDtl item : inserted) {
                item.setStockOutId(stockOut.getId());
            }
            stockOutDtlDao.batchSave(inserted);
        }
        if (updated != null && updated.size() > 0) {
            for (StockOutDtl item : updated) {
                item.setStockOutId(stockOut.getId());
                stockOutDtlDao.update(item);
            }
        }
        if(ids!=null&&ids.length>0){
            stockOutDtlDao.delete(ids);
        }
        return id;
    }

    @Override
    public int finish(Integer[] ids) {
        //TODO 将出租明细数量添加回仓库
        return stockOutDao.finish(ids);
    }

    @Override
    public List<Map<Integer, String>> findListFinishedForCombo() {
        return stockOutDao.findListFinishedForCombo();
    }

}
