package com.l1.dao;

import com.l1.entity.Reversion;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReturnMainDao {
    List<Reversion> find(Map<String, Object> map);

    List<Reversion> findByIds(String ids);

    Reversion findById(Integer id);

    List<String> findNamesByIds(String ids);

    Long getTotal(Map<String, Object> map);

    Integer add(Reversion color);

    Integer update(Reversion color);

    Integer delete(String[] ids);

    Integer deleteById(int id);

    int save(Reversion rent);

}
