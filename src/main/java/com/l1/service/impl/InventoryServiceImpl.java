package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.l1.dao.InventoryDao;
import com.l1.entity.Inventory;
import com.l1.service.InventoryService;

@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {
  @Resource
  private InventoryDao inventoryDao;

  @Override
  public List<Inventory> find(Map<String, Object> map) {
    return inventoryDao.find(map);
  }

  @Override
  public List<Inventory> findByIds(String[] ids) {
    return inventoryDao.findByIds(ids);
  }

  @Override
  public Inventory findById(Integer id) {
    return inventoryDao.findById(id);
  }

  @Override
  public Long getTotal(Map<String, Object> map) {
    return inventoryDao.getTotal(map);
  }

  @Override
  public Integer add(Inventory inventory) {
    return inventoryDao.add(inventory);
  }

  @Override
  public Integer update(Inventory inventory) {
    return inventoryDao.update(inventory);
  }

  @Override
  public Integer deleteById(Integer id) {
    return inventoryDao.deleteById(id);
  }

  @Override
  public void save(Inventory inventory) {
    inventoryDao.save(inventory);
  }

  @Override
  public Integer delete(String[] ids) {
    return inventoryDao.delete(ids);
  }

  @Override
  public Integer getInventory(Integer warehouseId, Integer skuId) {
    return inventoryDao.getInventory(warehouseId,skuId);
  }

}
