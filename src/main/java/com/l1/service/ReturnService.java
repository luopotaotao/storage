package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.Reversion;

public interface ReturnService {
	public List<Reversion> find(Map<String, Object> map);

	public List<Reversion> findByIds(String ids);

	public Reversion findById(Integer id);

	public List<String> findNamesByIds(String ids);

	public Long getTotal(Map<String, Object> map);

	public Integer add(Reversion Reversion);

	public Integer update(Reversion Reversion);

	public Integer deleteById(Integer id);

	public int save(Reversion rent);

	public Integer delete(String[] ids);
}
