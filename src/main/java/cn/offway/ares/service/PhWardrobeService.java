package cn.offway.ares.service;

import cn.offway.ares.domain.PhWardrobe;

/**
 * 衣柜Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhWardrobeService{

	PhWardrobe save(PhWardrobe phWardrobe);
	
	PhWardrobe findOne(Long id);
}
