package com.l1.controller;

import com.l1.entity.*;
import com.l1.service.BillStatService;
import com.l1.service.StockAdjustDtlService;
import com.l1.service.StockAdjustService;
import com.l1.service.WarehouseService;
import com.l1.util.DateUtil;
import com.l1.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description:
 * @Author: luopotaotao
 * @Since: 2016年4月12日
 */

@Controller
@RequestMapping("/stockAdjust")
public class StockAdjustController {
    @Resource
    private StockAdjustService stockAdjustService;

    @Resource
    private StockAdjustDtlService stockAdjustDtlService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
    }

    @RequestMapping
    public String showPage() {
        return "stockAdjustManage";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false) String page,
                                    @RequestParam(value = "rows", required = false) String rows, StockAdjust s_stockAdjust)
            throws Exception {
        if (page == null) {
            page = "1";
        }

        if (rows == null) {
            rows = "10";
        }

        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("warehouseName", StringUtil.formatLike(s_stockAdjust.getWarehouseName()));
        map.put("billNo", StringUtil.formatLike(s_stockAdjust.getBillNo()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<StockAdjust> stockAdjustList = stockAdjustService.find(map);
        Long total = stockAdjustService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();

        result.put("rows", stockAdjustList);
        result.put("total", total);

        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(StockAdjust stockAdjust, @RequestParam(value = "inserted", required = false) String insertedStr,
                                    @RequestParam(value = "updated", required = false) String updatedStr,
                                    @RequestParam(value = "deleted", required = false) Integer[] deleted, BindingResult err) {
        stockAdjust.setCreate_time(DateUtil.now());
        List<StockAdjustDtl> updateList = null;
        if (stockAdjust.getId() != null && stockAdjust.getId() > 0) {
            if (updatedStr != null && updatedStr.length() > 0) {
                updateList = jsonArrayToDetailList(JSONArray.fromObject(updatedStr));
            }
        }

        List<StockAdjustDtl> insertList = null;
        if (insertedStr != null && insertedStr.length() > 0) {
            insertList = jsonArrayToDetailList(JSONArray.fromObject(insertedStr));
        }

        int count ;
        if (stockAdjust.getId() != null && stockAdjust.getId() > 0) {
            count = stockAdjustService.updateKctzWithDetails(stockAdjust, insertList, updateList, deleted);
        } else {
            count = stockAdjustService.saveKctzWithDetails(stockAdjust, insertList);
        }

        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", count > 0);
        return ret;
    }

    private List<StockAdjustDtl> jsonArrayToDetailList(JSONArray array) {
        List<StockAdjustDtl> dtls = new LinkedList<StockAdjustDtl>();
        if (array != null && array.size() > 0) {
            for (int i = 0; i < array.size(); i++) {
                dtls.add(jsonToKctzDtl(array.getJSONObject(i)));
            }
        }

        return dtls;
    }

    private StockAdjustDtl jsonToKctzDtl(JSONObject json) {
        WrappedJSON item = new WrappedJSON(json);
        StockAdjustDtl ret = new StockAdjustDtl();

        ret.setDtlId(item.getInteger("dtlId"));
        ret.setSkuId(item.getInteger("skuId"));
        ret.setTzAmount(item.getInteger("tzAmount"));
        ret.setAmount(item.getInteger("amount"));

        return ret;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(StockAdjust stockAdjust) {
        stockAdjust.setUpdate_time(DateUtil.now());
        stockAdjustService.update(stockAdjust);

        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value = "ids[]") Integer[] ids) throws Exception {
        if (ids != null && ids.length > 0) {
            stockAdjustService.delete(ids);

            for (Integer id : ids) {
                stockAdjustDtlService.deleteByKctzId(id);
            }
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", true);
        return result;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public StockAdjust findById(@RequestParam(value = "id") Integer id) throws Exception {
        StockAdjust stockAdjust = stockAdjustService.findById(id);
        return stockAdjust;
    }

    @RequestMapping(value = "/unfinish", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> unfinish(@RequestParam(value = "ids[]") Integer[] ids,@RequestParam(value="warehouseIds[]") Integer[] warehouseIds) {
        Map<String, Object> ret = new HashMap<String, Object>();
        int count = stockAdjustService.unfinish(ids,warehouseIds);
        ret.put("flag", count > 0);
        return ret;
    }

    @RequestMapping(value = "/finish", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> finish(@RequestParam(value = "ids[]") Integer[] ids,@RequestParam(value="warehouseIds[]") Integer[] warehouseIds) {
        Map<String, Object> ret = new HashMap<String, Object>();
        int count = stockAdjustService.finish(ids,warehouseIds);
        ret.put("flag", count > 0);
        return ret;
    }

    private class WrappedJSON {
        private JSONObject source;

        public WrappedJSON(JSONObject source) {
            this.source = source;
        }

        public String getString(String key) {
            return source.get(key) == null || source.get(key) instanceof JSONNull ? null : source.getString(key);
        }

        public Integer getInteger(String key) {
            return source.get(key) == null || source.get(key) instanceof JSONNull ? null : source.getInt(key);
        }

        public Double getDouble(String key) {
            return source.get(key) == null || source.get(key) instanceof JSONNull ? null : source.getDouble(key);
        }

        public BigDecimal getBigDecimal(String key) {
            return source.get(key) == null || source.get(key) instanceof JSONNull ? null : BigDecimal.valueOf(source.getDouble(key));
        }
    }

}
