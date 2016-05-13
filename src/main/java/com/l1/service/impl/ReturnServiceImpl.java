package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.l1.entity.Reversion;
import org.springframework.stereotype.Service;

import com.l1.dao.ReturnMainDao;
import com.l1.service.ReturnService;

@Service("returnMainService")
public class ReturnServiceImpl implements ReturnService {
  @Resource
  private ReturnMainDao returnMainDao;

  @Override
  public List<Reversion> find(Map<String, Object> map) {
    return returnMainDao.find(map);
  }

  @Override
  public Long getTotal(Map<String, Object> map) {
    return returnMainDao.getTotal(map);
  }

  @Override
  public Integer add(Reversion reversion) {
    return returnMainDao.add(reversion);
  }

  @Override
  public Integer update(Reversion reversion) {
    return returnMainDao.update(reversion);
  }

  @Override
  public Integer deleteById(Integer id) {
    return returnMainDao.deleteById(id);
  }

  @Override
  public Integer delete(String[] ids) {
    return returnMainDao.delete(ids);
  }

  @Override
  public List<Reversion> findByIds(String ids) {
    return returnMainDao.findByIds(ids);
  }

  @Override
  public List<String> findNamesByIds(String ids) {
    return returnMainDao.findNamesByIds(ids);
  }

  @Override
  public Reversion findById(Integer id) {
    return returnMainDao.findById(id);
  }

  @Override
  public int save(Reversion reversion) {
		return returnMainDao.save(reversion);
  }

}
