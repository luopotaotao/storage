package com.l1.controller;

import com.l1.entity.BillStat;
import com.l1.entity.PageBean;
import com.l1.entity.Purchase;
import com.l1.entity.PurchaseDtl;
import com.l1.entity.Warehouse;
import com.l1.service.BillStatService;
import com.l1.service.PurchaseService;
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
import javax.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: hongxp
 * @Since: 2016年4月12日
 */

@Controller
@RequestMapping("/purchase")
public class PurchaseController {
    @Resource
    private PurchaseService purchaseService;

    @Resource
    private WarehouseService warehouseService;

    @Resource
    private BillStatService billStatService;

    @RequestMapping
    public String showPage() {
        return "purchaseManage";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false) String page,
                                    @RequestParam(value = "rows", required = false) String rows, Purchase s_purchase,
                                    HttpServletResponse response) throws Exception {
        if (page == null) {
            page = "1";
        }

        if (rows == null) {
            rows = "10";
        }

        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("supplierName", StringUtil.formatLike(s_purchase.getSupplierName()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<Purchase> purchaseList = purchaseService.find(map);
        Long total = purchaseService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();

        result.put("rows", purchaseList);
        result.put("total", total);

        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(Purchase purchase, @RequestParam(value = "inserted", required = false) String insertedStr,
                                    @RequestParam(value = "updated", required = false) String updatedStr,
                                    @RequestParam(value = "deleted", required = false) Integer[] deleted, BindingResult err) {
        purchase.setCreate_time(DateUtil.now());
        List<PurchaseDtl> updateList = null;

        if (purchase.getId() != null && purchase.getId() > 0) {
            if (updatedStr != null && updatedStr.length() > 0) {
                updateList = jsonArrayToDetailList(JSONArray.fromObject(updatedStr));
            }
        }

        List<PurchaseDtl> insertList = null;
        if (insertedStr != null && insertedStr.length() > 0) {
            insertList = jsonArrayToDetailList(JSONArray.fromObject(insertedStr));
        }

        int count = 0;
        if (purchase.getId() != null && purchase.getId() > 0) {
            count = purchaseService.updatePurchaseWithDetails(purchase, insertList, updateList, deleted);
        } else {
            count = purchaseService.savePurchaseWithDetails(purchase, insertList);
        }

        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", count > 0);
        return ret;
    }

    private List<PurchaseDtl> jsonArrayToDetailList(JSONArray array) {
        List<PurchaseDtl> dtls = new LinkedList<PurchaseDtl>();
        if (array != null && array.size() > 0) {
            for (int i = 0; i < array.size(); i++) {
                dtls.add(jsonToPurchaseDtl(array.getJSONObject(i)));
            }
        }

        return dtls;
    }

    private PurchaseDtl jsonToPurchaseDtl(JSONObject json) {
        WrappedJSON item = new WrappedJSON(json);
        PurchaseDtl ret = new PurchaseDtl();

        ret.setDtlId(item.getInteger("dtlId"));
        ret.setSkuId(item.getInteger("skuId"));
        ret.setItemName(item.getString("itemName"));
        ret.setItemPrice(item.getBigDecimal("itemPrice"));
        ret.setItemAmount(item.getInteger("itemAmount"));
        ret.setItemTotal(item.getBigDecimal("itemTotal"));

        return ret;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(Purchase purchase) {
        purchase.setUpdate_time(DateUtil.now());
        purchaseService.update(purchase);

        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);

        return ret;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value = "ids[]") Integer[] ids,
                                      HttpServletResponse response) throws Exception {
        if (ids != null && ids.length > 0) {
            purchaseService.delete(ids);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", true);
        return result;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Purchase findById(@RequestParam(value = "id") Integer id, HttpServletResponse response)
            throws Exception {
        Purchase purchase = purchaseService.findById(id);
        return purchase;
    }

    @RequestMapping(value = "/unfinish", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> unfinish(@RequestParam(value = "ids[]") Integer[] ids, @RequestParam(value = "warehouseIds[]") Integer[] warehouseIds) {
        Map<String, Object> ret = new HashMap<String, Object>();
        int count = purchaseService.unfinish(ids, warehouseIds);
        ret.put("flag", count > 0);
        return ret;
    }

    @RequestMapping(value = "/finish", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> finish(@RequestParam(value = "ids[]") Integer[] ids, @RequestParam(value = "warehouseIds[]") Integer[] warehouseIds) {
        Map<String, Object> ret = new HashMap<String, Object>();
        int count = purchaseService.finish(ids, warehouseIds);
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
