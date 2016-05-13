package com.l1.service;

import com.l1.entity.Reversion;
import com.l1.entity.ReversionDtl;

import java.util.List;
import java.util.Map;

/**
 * Created by luopotaotao on 2016/5/13.
 */
public interface ReversionService {
    List<Reversion> find(Map<String, Object> map);

    Reversion findById(Integer id);

    Long getTotal(Map<String, Object> map);

    int saveReversionWithDetails(Reversion reversion, List<ReversionDtl> inserted);

    void delete(String[] ids);

    void update(Reversion reversion);

    int updateWithDetails(Reversion reversion, List<ReversionDtl> inserted, List<ReversionDtl> updated, Integer[] deleted);

    int finish(Integer[] ids);

    List<Map<Integer,String>> loadAvailableRentBillNos();

    Reversion loadReversionFromRent(Integer rentId);
}
