package com.l1.service.impl;

import com.l1.dao.ReversionDao;
import com.l1.dao.ReversionDtlDao;
import com.l1.entity.Reversion;
import com.l1.entity.ReversionDtl;
import com.l1.service.ReversionService;
import com.l1.service.SeqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by luopotaotao on 2016/5/13.
 */

@Repository("reversionService")
public class ReversionServiceImpl implements ReversionService{
    @Autowired
    private ReversionDao reversionDao;
    @Autowired
    private ReversionDtlDao reversionDtlDao;
    @Autowired
    private SeqService seqService;

    @Override
    public List<Reversion> find(Map<String, Object> map) {
        return reversionDao.find(map);
    }

    @Override
    public Reversion findById(Integer id) {
        return null;
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return null;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRES_NEW)
    @Override
    public int save(Reversion reversion, List<ReversionDtl> details) {
        reversion.setBillNo(seqService.next("GH"));
        int count = reversionDao.save(reversion);
        int id = reversion.getId();
        for(ReversionDtl item:details){
            item.setReversionId(id);
        }
        reversionDtlDao.batchSave(details);
        return count;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRES_NEW)
    @Override
    public int delete(Integer[] ids) {
        reversionDtlDao.deleteByReversionIds(ids);
        return reversionDao.remove(ids);
    }

    @Override
    public void update(Reversion reversion) {

    }

    @Override
    public int update(Reversion reversion, List<ReversionDtl> details) {
        return 0;
    }

    @Override
    public int finish(Integer[] ids) {
        return 0;
    }

    @Override
    public List<Map<Integer, String>> loadAvailableRentBillNos() {
        return reversionDao.loadAvailableRentBillNos();
    }

    @Override
    public Reversion loadReversionFromRent(Integer rentId) {
        return reversionDao.loadReversionFromRent(rentId);
    }
}
