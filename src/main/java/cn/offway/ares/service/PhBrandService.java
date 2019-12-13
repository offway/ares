package cn.offway.ares.service;

import cn.offway.ares.domain.PhBrand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 品牌库Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhBrandService {

    PhBrand save(PhBrand phBrand);

    List<PhBrand> save(List<PhBrand> phBrands);

    PhBrand findOne(Long id);

    List<PhBrand> findAll();

    Page<PhBrand> findByPage(Long brandId, String brandName, List<Long> brandIds, Pageable page);

    List<PhBrand> findByIds(List<Long> ids);

    List<PhBrand> findByShowImgId(Long showImgId);

    void updateChildren(Long id, String logo, String name);

    List<PhBrand> findAll(String prefix);
}
