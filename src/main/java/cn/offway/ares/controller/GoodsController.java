package cn.offway.ares.controller;

import cn.offway.ares.domain.*;
import cn.offway.ares.properties.QiniuProperties;
import cn.offway.ares.service.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiniu.util.Base64;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * 商品管理
 *
 * @author wn
 */
@Controller
public class GoodsController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private QiniuProperties qiniuProperties;
    @Autowired
    private PhGoodsService goodsService;
    @Autowired
    private PhGoodsStockService goodsStockService;
    @Autowired
    private PhGoodsImageService goodsImageService;
    @Autowired
    private PhGoodsPropertyService goodsPropertyService;
    @Autowired
    private PhBrandService brandService;
    @Autowired
    private PhGoodsTypeService goodsTypeService;
    @Autowired
    private PhGoodsCategoryService goodsCategoryService;


    /**
     * 商品
     */
    @RequestMapping("/goods.html")
    public String index(ModelMap map) {
        map.addAttribute("qiniuUrl", qiniuProperties.getUrl());
        return "goods_index";
    }

    @RequestMapping("/goods_add.html")
    public String add(ModelMap map) {
        map.addAttribute("qiniuUrl", qiniuProperties.getUrl());
        return "goods_add";
    }

    @RequestMapping("/goods_edit.html")
    public String edit(ModelMap map, Long id) {
        map.addAttribute("qiniuUrl", qiniuProperties.getUrl());
        map.addAttribute("theId", id);
        return "goods_edit";
    }

    @RequestMapping("/goods_stock_index.html")
    public String stockIndex(ModelMap map, Long id) {
        map.addAttribute("qiniuUrl", qiniuProperties.getUrl());
        map.addAttribute("theId", id);
        return "goods_stock_index";
    }

    @ResponseBody
    @RequestMapping("/goods_stock_list")
    public Map<String, Object> getStockList(HttpServletRequest request) {
        int sEcho = Integer.parseInt(request.getParameter("sEcho"));
        int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
        int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
        String gid = request.getParameter("theId");
        String remark = request.getParameter("remark");
        Sort sort = new Sort("id");
        Page<PhGoodsStock> pages = goodsStockService.findAll(gid, remark, new PageRequest(iDisplayStart == 0 ? 0 : iDisplayStart / iDisplayLength, iDisplayLength < 0 ? 9999999 : iDisplayLength, sort));
        int initEcho = sEcho + 1;
        Map<String, Object> map = new HashMap<>();
        map.put("sEcho", initEcho);
        map.put("iTotalRecords", pages.getTotalElements());//数据总条数
        map.put("iTotalDisplayRecords", pages.getTotalElements());//显示的条数
        map.put("aData", pages.getContent());//数据集合
        return map;
    }

    @ResponseBody
    @RequestMapping("/goods_list")
    public Map<String, Object> getList(HttpServletRequest request) {
        int sEcho = Integer.parseInt(request.getParameter("sEcho"));
        int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
        int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
        String id = request.getParameter("id");
        if ("".equals(id.trim())) {
            id = "0";
        }
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String status = request.getParameter("status");
        String type = request.getParameter("type");
        String category = request.getParameter("category");
        String kind = request.getParameter("kind");
        Sort sort = new Sort("id");
        PageRequest pr = new PageRequest(iDisplayStart == 0 ? 0 : iDisplayStart / iDisplayLength, iDisplayLength < 0 ? 9999999 : iDisplayLength, sort);
        Page<PhGoods> pages = goodsService.findAll(name, Long.valueOf(id), code, status, type, category, kind, pr);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map> arr = new ArrayList<>();
        for (PhGoods goods : pages.getContent()) {
            Map m = objectMapper.convertValue(goods, Map.class);
            arr.add(m);
        }
        int initEcho = sEcho + 1;
        Map<String, Object> map = new HashMap<>();
        map.put("sEcho", initEcho);
        map.put("iTotalRecords", pages.getTotalElements());//数据总条数
        map.put("iTotalDisplayRecords", pages.getTotalElements());//显示的条数
        map.put("aData", arr);//数据集合
        return map;
    }

    @ResponseBody
    @RequestMapping("/goods_find")
    public Map<String, Object> find(Long id) {
        Map<String, Object> map = new HashMap<>();
        PhGoods goods = goodsService.findOne(id);
        if (goods != null) {
            map.put("main", goods);
            map.put("propertyList", goodsPropertyService.findByPid(goods.getId()));
            map.put("imageList", goodsImageService.findByPid(goods.getId()));
            List<PhGoodsStock> stocks = goodsStockService.findByPid(goods.getId());
            map.put("stockList", stocks);
            Map<String, Object> stockMap = new HashMap<>();
            for (PhGoodsStock stock : stocks) {
                StringBuilder sb = new StringBuilder();
                List<PhGoodsProperty> l = goodsPropertyService.findByStockId(stock.getId());
                for (PhGoodsProperty property : l) {
                    sb.append(property.getName());
                    sb.append(property.getValue());
                }
                stockMap.put(sb.toString(), stock);
            }
            map.put("stockMap", stockMap);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("/goods_stock_find")
    public Map<String, Object> findStock(Long id) {
        Map<String, Object> map = new HashMap<>();
        PhGoodsStock goodsStock = goodsStockService.findOne(id);
        if (goodsStock != null) {
            map.put("main", goodsStock);
            map.put("propertyList", goodsPropertyService.findByStockId(goodsStock.getId()));
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("/goods_findOne")
    public PhGoods findOne(Long id) {
        return goodsService.findOne(id);
    }

    @ResponseBody
    @RequestMapping("/goods_del")
    public boolean delete(@RequestParam("ids[]") Long[] ids) {
        for (Long id : ids) {
            goodsService.del(id);
            goodsImageService.delByPid(id);
            goodsPropertyService.delByPid(id);
            goodsStockService.delByPid(id);
        }
        return true;
    }

    @ResponseBody
    @RequestMapping("/goods_stock_del")
    public boolean deleteStock(@RequestParam("ids[]") Long[] ids) {
        for (Long id : ids) {
            goodsStockService.del(id);
            goodsPropertyService.delByStockId(id);
        }
        return true;
    }

    @ResponseBody
    @RequestMapping("/goods_up")
    public boolean goodsUp(Long id) {
        PhGoods goods = goodsService.findOne(id);
        if (goods != null) {
            goods.setStatus("1");
            goodsService.save(goods);
        }
        return true;
    }

    @ResponseBody
    @RequestMapping("/goods_down")
    public boolean goodsDown(Long id) {
        PhGoods goods = goodsService.findOne(id);
        if (goods != null) {
            goods.setStatus("0");
            goodsService.save(goods);
        }
        return true;
    }

    @ResponseBody
    @Transactional
    @RequestMapping("/goods_add")
    public boolean add(PhGoods goods, @RequestParam("stocks") String stocks, @RequestParam("banners") String[] banners, @RequestParam("intros") String[] intros) {
        PhBrand brand = brandService.findOne(goods.getBrandId());
        if (brand != null) {
            goods.setBrandName(brand.getName());
            goods.setBrandLogo(brand.getLogo());
        }
        PhGoodsType goodsType = goodsTypeService.findOne(Long.valueOf(goods.getType()));
        if (goodsType != null) {
            goods.setType(goodsType.getName());
        }
        PhGoodsCategory goodsCategory = goodsCategoryService.findOne(Long.valueOf(goods.getCategory()));
        if (goodsCategory != null) {
            goods.setCategory(goodsCategory.getName());
        }
        if (goods.getId() == null) {//add
            goods.setCreateTime(new Date());
            goods.setStatus("0");//默认未上架
        } else {
            PhGoods tmpObj = goodsService.findOne(goods.getId());
            goods.setCreateTime(tmpObj.getCreateTime());
            goods.setStatus(tmpObj.getStatus());
        }
        PhGoods goodsSaved = goodsService.save(goods);
        if (goods.getId() != null) {
            //try purge first 轮播图 & 商品长图
            goodsImageService.delByPid(goods.getId());
        }
        //banner 轮播图
        long i = 0;
        for (String banner : banners) {
            PhGoodsImage goodsImage = new PhGoodsImage();
            goodsImage.setGoodsId(goodsSaved.getId());
            goodsImage.setGoodsName(goodsSaved.getName());
            goodsImage.setImageUrl(banner);
            goodsImage.setType("0");
            goodsImage.setSort(i);
            goodsImage.setCreateTime(new Date());
            goodsImageService.save(goodsImage);
            i++;
        }
        //商品长图
        i = 0;
        for (String intro : intros) {
            PhGoodsImage goodsImage = new PhGoodsImage();
            goodsImage.setGoodsId(goodsSaved.getId());
            goodsImage.setGoodsName(goodsSaved.getName());
            goodsImage.setImageUrl(intro);
            goodsImage.setType("1");
            goodsImage.setSort(i);
            goodsImage.setCreateTime(new Date());
            goodsImageService.save(goodsImage);
            i++;
        }
        //库存 & 规格
        for (Object o : JSON.parseArray(stocks)) {
            JSONObject obj = (JSONObject) o;
            PhGoodsStock goodsStock;
            if (obj.containsKey("id") && !"".equals(obj.getString("id"))) {//edit
                goodsStock = goodsStockService.findOne(Long.valueOf(obj.getString("id")));
            } else {
                goodsStock = new PhGoodsStock();
            }
            goodsStock.setBrandId(goodsSaved.getBrandId());
            goodsStock.setBrandLogo(goodsSaved.getBrandLogo());
            goodsStock.setBrandName(goodsSaved.getBrandName());
            goodsStock.setGoodsId(goodsSaved.getId());
            goodsStock.setGoodsName(goodsSaved.getName());
            goodsStock.setGoodsImage(goodsSaved.getImage());
            goodsStock.setType(goodsSaved.getType());
            goodsStock.setCategory(goodsSaved.getCategory());
            goodsStock.setSku(obj.getString("sku"));
            goodsStock.setStock(obj.getLong("stock"));
            goodsStock.setRemark(obj.getString("remark"));
            goodsStock.setImage(obj.getString("image"));
            goodsStock.setCreateTime(new Date());
            PhGoodsStock goodsStockSaved = goodsStockService.save(goodsStock);
            if (goodsStock.getId() != null) {
                //try purge first 规格
                goodsPropertyService.delByStockId(goodsStock.getId());
            }
            byte[] jsonStr = Base64.decode(obj.getString("detail"), Base64.DEFAULT);
            JSONArray jsonArray = JSON.parseArray(new String(jsonStr, StandardCharsets.UTF_8));
            if (jsonArray != null) {
                long index = 0L;
                for (Object oo : jsonArray) {
                    JSONObject jsonObject = (JSONObject) oo;
                    PhGoodsProperty goodsProperty = new PhGoodsProperty();
                    goodsProperty.setGoodsId(goodsSaved.getId());
                    goodsProperty.setGoodsStockId(goodsStockSaved.getId());
                    goodsProperty.setName(jsonObject.getString("name"));
                    goodsProperty.setValue(jsonObject.getJSONObject("value").getString("value"));
                    goodsProperty.setSort(index);
                    goodsProperty.setRemark(jsonObject.getJSONObject("value").getString("remark"));
                    goodsProperty.setCreateTime(new Date());
                    goodsPropertyService.save(goodsProperty);
                    index++;
                }
            } else {
                logger.error("stocks json 非法");
            }
        }
        goodsService.save(goodsSaved);
        return true;
    }

    @ResponseBody
    @RequestMapping("/type_and_category_list")
    public List<Object> getTypeAndCategory() {
        List<Object> ret = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        List<PhGoodsType> typeList = goodsTypeService.findAll();
        for (PhGoodsType type : typeList) {
            Map container = objectMapper.convertValue(type, Map.class);
            List<PhGoodsCategory> categoryList = goodsCategoryService.findByPid(type.getId());
            container.put("sub", categoryList);
            ret.add(container);
        }
        return ret;
    }
}
