package com.l1.dao;

import com.l1.entity.StockInDtl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by luopotaotao on 2016/5/13.
 */
@Repository
public interface StockInDtlDao {
    List<StockInDtl> loadStockInDtlFromRentDtl(Integer id);

    int batchSave(List<StockInDtl> details);

    List<StockInDtl> findRentDtlsById(Integer id);

    int deleteByStockInIds(Integer[] ids);
}
