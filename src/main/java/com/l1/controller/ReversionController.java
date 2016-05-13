package com.l1.controller;

import com.l1.entity.*;
import com.l1.service.BillStatService;
import com.l1.service.DicService;
import com.l1.service.ReturnService;
import com.l1.service.WarehouseService;
import com.l1.util.DateUtil;
import com.l1.util.StringUtil;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: luopotaotao
 * @Since: 2016年5月13日
 */

@Controller
@RequestMapping("/reversion")
public class ReversionController {
    @Resource
    private ReturnService returnService;

    @Resource
    private WarehouseService warehouseService;

    @Resource
    private BillStatService billStatService;

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
        List<Dic> statusDic = dicService.query("returnStatus");
        model.addAttribute("statusDic",statusDic);
        return "returnManage";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false) String page,
                                    @RequestParam(value = "rows", required = false) String rows, Reversion s_reversion)
            throws Exception {
        if (page == null) {
            page = "1";
        }

        if (rows == null) {
            rows = "10";
        }

        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("customerName", StringUtil.formatLike(s_reversion.getCustomerName()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<Reversion> reversionList = returnService.find(map);
        Long total = returnService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", reversionList);
        result.put("total", total);

        return result;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(Reversion reversion, BindingResult err) throws IOException {
        Integer warehouseId = reversion.getWarehouseId();
        if (warehouseId != null && warehouseId > 0) {
            Warehouse warehouse = warehouseService.findById(warehouseId);
            if (warehouse != null) {
                reversion.setWarehouseName(warehouse.getName());
            }
        }

        if (reversion.getStat() > 0) {
            BillStat billStat = billStatService.findById(reversion.getStat());
            if (billStat != null) {
                reversion.setStatName(billStat.getName());
            }
        }

        reversion.setCreate_time(DateUtil.now());
        int count = returnService.save(reversion);

        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", count>0);
        return ret;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(Reversion reversion) {
        reversion.setUpdate_time(DateUtil.now());
        int count = returnService.update(reversion);

        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", count>0);
        return ret;
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value = "ids[]") String[] ids, HttpServletResponse response) throws Exception {
        if (ids != null && ids.length > 0) {
            returnService.delete(ids);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", true);
        return result;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Reversion findById(@RequestParam(value = "id") Integer id) throws Exception {
        Reversion reversion = returnService.findById(id);
        return reversion;
    }

}
