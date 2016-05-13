package com.l1.service.impl;

import com.l1.entity.Reversion;
import com.l1.entity.ReversionDtl;
import com.l1.service.ReversionService;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by luopotaotao on 2016/5/13.
 */

@Repository("reversionService")
public class ReversionServiceImpl implements ReversionService{
    @Override
    public List<Reversion> find(Map<String, Object> map) {
        return null;
    }

    @Override
    public Reversion findById(Integer id) {
        return null;
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return null;
    }

    @Override
    public int saveReversionWithDetails(Reversion reversion, List<ReversionDtl> inserted) {
        return 0;
    }

    @Override
    public void delete(String[] ids) {

    }

    @Override
    public void update(Reversion reversion) {

    }

    @Override
    public int updateWithDetails(Reversion reversion, List<ReversionDtl> inserted, List<ReversionDtl> updated, Integer[] deleted) {
        return 0;
    }

    @Override
    public int finish(Integer[] ids) {
        return 0;
    }
}
