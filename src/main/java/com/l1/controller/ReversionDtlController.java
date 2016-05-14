package com.l1.controller;

import com.l1.entity.BillStat;
import com.l1.entity.PageBean;
import com.l1.entity.ReversionDtl;
import com.l1.entity.Sku;
import com.l1.service.BillStatService;
import com.l1.service.ReversionDtlService;
import com.l1.service.SkuService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: hongxp
 * @Since: 2016年4月12日
 */

@Controller
@RequestMapping("/reversionDtl")
public class ReversionDtlController {
    @Resource
    private ReversionDtlService reversionDtlService;

    @Resource
    private BillStatService billStatService;

    @Resource
    private SkuService skuService;

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
                                    @RequestParam(value = "rows", required = false) String rows, ReversionDtl s_reversionDtl,
                                    HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", s_reversionDtl.getId());
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<ReversionDtl> reversionDtlList = reversionDtlService.find(map);
        Long total = reversionDtlService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();

        result.put("rows", reversionDtlList);
        result.put("total", total);

        return result;
    }

    @RequestMapping(value = "/loadRentDtlsForReversion",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> loadRentDtlsForReversion(@RequestParam  Integer rentId) throws Exception {
        List<ReversionDtl> list = reversionDtlService.loadRentDtlsForReversion(rentId);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", list);
        result.put("total", list.size());
        return result;
    }
    @RequestMapping(value = "/findRentDtlsById",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> findRentDtlsById(Integer reversionId){
        Map<String, Object> result = new HashMap<String, Object>();
        List<ReversionDtl> list = reversionDtlService.findRentDtlsById(reversionId);
        result.put("rows", list);
        result.put("total", list.size());
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(ReversionDtl reversionDtl) {

        reversionDtlService.update(reversionDtl);
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> remove(@RequestParam(value = "ids[]") String[] ids) {
        reversionDtlService.delete(ids);
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public ReversionDtl findById(@RequestParam("id") int id) {
        ReversionDtl reversionDtl = reversionDtlService.findById(id);
        return reversionDtl;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(ReversionDtl reversionDtl, @RequestParam("id") int id) throws Exception {
        reversionDtl.setId(id);

//        int resultTotal = 0; // 操作的记录条数
//        if (reversionDtl.getDtlId() == null) {
//            resultTotal = reversionDtlService.add(reversionDtl);
//        } else {
//            resultTotal = reversionDtlService.update(reversionDtl);
//        }

        Map<String, Object> result = new HashMap<String, Object>();
//        if (resultTotal > 0) {
//            result.put("success", true);
//        } else {
//            result.put("success", false);
//        }

        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value = "ids[]") String[] ids) throws Exception {
        if (ids != null && ids.length > 0) {
            reversionDtlService.delete(ids);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", true);
        return result;
    }

}
