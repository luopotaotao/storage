package com.l1.controller;

import com.l1.entity.*;
import com.l1.service.*;
import com.l1.util.DateUtil;
import com.l1.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/reversion")
public class ReversionController {
    @Resource
    private ReversionService reversionService;

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
        List<Dic> statusDic = dicService.query("reversionStatus");
        model.addAttribute("statusDic",statusDic);
        return "reversionManage";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
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

        List<Reversion> reversionList = reversionService.find(map);
        Long total = reversionService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", reversionList);
        result.put("total", total);

        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(Reversion reversion,
                                    @RequestParam(value = "inserted",required = false) String insertedStr,
                                    @RequestParam(value = "updated",required = false) String updatedStr,
                                    @RequestParam(value = "deleted",required = false) Integer[] deleted, BindingResult err) {
        reversion.setCreate_time(DateUtil.now());
        List<ReversionDtl> inserted = null;
        List<ReversionDtl> updated = null;
        if(reversion.getId()!=null&&reversion.getId()>0){
            if(updatedStr!=null&&updatedStr.length()>0){
                inserted = jsonArrayToReversionDetailList(JSONArray.fromObject(updatedStr));
            }
        }
        if(insertedStr!=null&&insertedStr.length()>0){
            inserted = jsonArrayToReversionDetailList(JSONArray.fromObject(insertedStr));
        }

        int count = 0;
        if (reversion.getId() != null) {
            count = reversionService.updateWithDetails(reversion, inserted,updated,deleted);
        } else {
            count = reversionService.saveReversionWithDetails(reversion, inserted);

        }
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", count > 0);
        return ret;
    }

    private List<ReversionDtl> jsonArrayToReversionDetailList(JSONArray array){
        List<ReversionDtl> ret = null;
        if(array!=null&&array.size()>0){
            ret = new LinkedList<ReversionDtl>();
            for(int i = 0;i<array.size();i++){
                ret.add(jsonToReversionDetail(array.getJSONObject(i)));
            }
        }
        return ret;
    }

    private ReversionDtl jsonToReversionDetail(JSONObject json) {
        WrappedJSON item = new WrappedJSON(json);
        ReversionDtl ret = new ReversionDtl();
        ret.setSkuId(item.getInteger("skuId"));
        ret.setItemName(item.getString("itemName"));
//        ret.setStat(json.getInt("stat"));
        ret.setItemPrice(item.getBigDecimal("itemPrice"));

        ret.setItemAmount(item.getInteger("itemAmount"));

        ret.setItemRepo(item.getBigDecimal("itemRepo"));
        return ret;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(Reversion reversion) {
        reversion.setUpdate_time(DateUtil.now());
        reversionService.update(reversion);

        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value = "ids[]") String[] ids) throws Exception {
        if (ids != null && ids.length > 0) {
            reversionService.delete(ids);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", true);
        return result;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Reversion findById(@RequestParam(value = "id") Integer id) throws Exception {
        Reversion reversion = reversionService.findById(id);
        return reversion;
    }
    @RequestMapping(value = "/finish",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> finish(@RequestParam(value = "ids[]")Integer[] ids){
        Map<String,Object> ret = new HashMap<String, Object>();
        int count = reversionService.finish(ids);
        ret.put("flag",count>0);
        return ret;
    }
    private class WrappedJSON {
        private JSONObject source;

        public WrappedJSON(JSONObject source) {
            this.source = source;
        }

        public String getString(String key) {
            return source.get(key)==null||source.get(key) instanceof JSONNull ? null : source.getString(key);
        }

        public Integer getInteger(String key) {
            return source.get(key)==null|| source.get(key) instanceof JSONNull ? null : source.getInt(key);
        }

        public Double getDouble(String key) {
            return source.get(key)==null||source.get(key) instanceof JSONNull ? null : source.getDouble(key);
        }

        public BigDecimal getBigDecimal(String key) {
            return source.get(key)==null||source.get(key) instanceof JSONNull ? null : BigDecimal.valueOf(source.getDouble(key));
        }
    }
}
