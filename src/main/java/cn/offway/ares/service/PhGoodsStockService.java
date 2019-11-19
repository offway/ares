package cn.offway.ares.service;

import cn.offway.ares.domain.PhGoodsStock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品库存Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhGoodsStockService {

    PhGoodsStock save(PhGoodsStock phGoodsStock);

    PhGoodsStock findOne(Long id);

    int countByGoodsIdAndColorAndSize(Long goodsId, String color, String size);

    boolean updateStock(String orderNo) throws Exception;

    String findImage(String color, Long goodsId);

    void updateImage(Long goodsId, String color, String image);

    void deleteByGoodsIds(List<Long> goodsIds);

    Page<PhGoodsStock> findByPage(String sku, Long brandId, String brandName, Long goodsId, String goodsName,
                                  String isOffway, String color, String size, List<Long> brandIds, Pageable page);

    int deleteByIds(List<Long> ids);

    Page<PhGoodsStock> findAll(String name, String remark, Pageable pageable);

    List<PhGoodsStock> findByPid(Long pid);

    void delByPid(Long id);

    void del(Long id);
}
