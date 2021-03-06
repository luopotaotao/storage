package com.l1.controller;

import com.l1.entity.Dic;
import com.l1.entity.PageBean;
import com.l1.entity.StockIn;
import com.l1.entity.StockInDtl;
import com.l1.service.BillStatService;
import com.l1.service.DicService;
import com.l1.service.StockInService;
import com.l1.service.WarehouseService;
import com.l1.util.DateUtil;
import com.l1.util.StringUtil;
import com.l1.util.WrappedJSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description:
 * @Author: luopotaotao
 * @Since: 2016年4月12日
 */

@Controller
@RequestMapping("/stockIn")
public class StockInController {
    @Resource
    private StockInService stockInService;

    @Resource
    private DicService dicService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
    }

    @RequestMapping
    public String showPage(Model model) {
        List<Dic> statusDic = dicService.query("stockInStatus");
        model.addAttribute("statusDic", statusDic);
        return "stockInManage";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
                                    @RequestParam(value = "rows", required = false) Integer rows,
                                    @RequestParam(value = "billStat[]", required = false) Integer[] billStat)
            throws Exception {
        if (page == null) {
            page = 1;
        }
        if (rows == null) {
            rows = 10;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", (page - 1) * rows);
        map.put("size", rows);
        map.put("billStat", billStat);
        List<StockIn> stockInList = stockInService.find(map);
        Long total = stockInService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", stockInList);
        result.put("total", total);

        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(StockIn stockIn, @RequestParam(value = "details", required = false) String detailsStr, BindingResult err) {
        stockIn.setCreate_time(DateUtil.now());
        List<StockInDtl> details = null;
        if (detailsStr != null && detailsStr.length() > 0) {
            details = jsonArrayToStockInDetailList(JSONArray.fromObject(detailsStr));
        }

        int count = 0;
        if (stockIn.getId() != null) {
            count = stockInService.update(stockIn, details);
        } else {
            count = stockInService.save(stockIn, details);

        }
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", count > 0);
        return ret;
    }

    private List<StockInDtl> jsonArrayToStockInDetailList(JSONArray array) {
        List<StockInDtl> ret = null;
        if (array != null && array.size() > 0) {
            ret = new LinkedList<StockInDtl>();
            for (int i = 0; i < array.size(); i++) {
                ret.add(jsonToStockInDetail(array.getJSONObject(i)));
            }
        }
        return ret;
    }

    private StockInDtl jsonToStockInDetail(JSONObject json) {
        WrappedJSON item = new WrappedJSON(json);
        StockInDtl ret = new StockInDtl();
        ret.setId(item.getInteger("id"));
        ret.setStockInId(item.getInteger("stockInId"));
        ret.setSkuId(item.getInteger("skuId"));
        ret.setStockOutDtlId(item.getString("stockOutDtlId"));
        ret.setStockInAmount(item.getInteger("stockInAmount"));
        ret.setStockOutAmount(item.getInteger("stockOutAmount"));

        return ret;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value = "ids[]") Integer[] ids) throws Exception {
        int count = 0;
        if (ids != null && ids.length > 0) {
            count = stockInService.delete(ids);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", count > 0);
        return result;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public StockIn findById(@RequestParam(value = "id") Integer id) throws Exception {
        StockIn stockIn = stockInService.findById(id);
        return stockIn;
    }

    @RequestMapping(value = "loadAvailableStockOutBillNos", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<Integer, String>> loadAvailableStockOutBillNos() {
        return stockInService.loadAvailableStockOutBillNos();
    }

    @RequestMapping(value = "loadStockInFromStockOut", method = RequestMethod.GET)
    @ResponseBody
    public StockIn loadStockInFromStockOut(Integer stockOutId) {
        return stockInService.loadStockInFromStockOut(stockOutId);
    }

    @RequestMapping(value = "/finish", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> finish(@RequestParam(value = "ids[]") Integer[] ids) {
        Map<String, Object> ret = new HashMap<String, Object>();
        int count = stockInService.finish(ids);
        ret.put("flag", count > 0);
        return ret;
    }
}
