package cn.offway.ares.service;


import java.util.List;

import cn.offway.ares.domain.PhAddressBrand;

/**
 * 品牌地址管理Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-11-27 18:02:40 Exp $
 */
public interface PhAddressBrandService{

    PhAddressBrand save(PhAddressBrand phAddressBrand);
	
    PhAddressBrand findOne(Long id);

    void delete(Long id);

    List<PhAddressBrand> save(List<PhAddressBrand> entities);
}
