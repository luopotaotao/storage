package com.l1.controller;

import com.l1.entity.*;
import com.l1.service.*;
import com.l1.util.DateUtil;
import com.l1.util.StringUtil;
import com.l1.util.WrappedJSON;
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
    public Map<String, Object> save(Reversion reversion,@RequestParam(value = "details",required = false) String detailsStr, BindingResult err) {
        reversion.setCreate_time(DateUtil.now());
        List<ReversionDtl> details = null;
        if(detailsStr!=null&&detailsStr.length()>0){
            details = jsonArrayToReversionDetailList(JSONArray.fromObject(detailsStr));
        }

        int count = 0;
        if (reversion.getId() != null) {
            count = reversionService.update(reversion, details);
        } else {
            count = reversionService.save(reversion, details);

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
        ret.setId(item.getInteger("Id"));
        ret.setReversionId(item.getInteger("reversionId"));
        ret.setRentDtlId(item.getString("rentDtlId"));
        ret.setReversionAmount(item.getInteger("reversionAmount"));
        ret.setReversionStat(item.getInteger("reversionStat"));

        ret.setItemRent(item.getBigDecimal("itemRent"));
        ret.setItemRepo(item.getBigDecimal("itemRepo"));
        ret.setItemCompensate(item.getBigDecimal("itemCompensate"));

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
    public Map<String, Object> delete(@RequestParam(value = "ids[]") Integer[] ids) throws Exception {
        int count = 0;
        if (ids != null && ids.length > 0) {
            count = reversionService.delete(ids);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", count>0);
        return result;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Reversion findById(@RequestParam(value = "id") Integer id) throws Exception {
        Reversion reversion = reversionService.findById(id);
        return reversion;
    }

    @RequestMapping(value = "loadAvailableRentBillNos",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<Integer,String>> loadAvailableRentBillNos(){
        return reversionService.loadAvailableRentBillNos();
    }
    @RequestMapping(value = "loadReversionFromRent",method = RequestMethod.GET)
    @ResponseBody
    public Reversion loadReversionFromRent(Integer rentId){
        return reversionService.loadReversionFromRent(rentId);
    }
    @RequestMapping(value = "/finish",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> finish(@RequestParam(value = "ids[]")Integer[] ids){
        Map<String,Object> ret = new HashMap<String, Object>();
        int count = reversionService.finish(ids);
        ret.put("flag",count>0);
        return ret;
    }
}
