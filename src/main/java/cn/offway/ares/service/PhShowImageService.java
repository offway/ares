package cn.offway.ares.service;

import java.util.List;

import cn.offway.ares.domain.PhShowImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 返图Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhShowImageService{

	PhShowImage save(PhShowImage phShowImage);
	
	PhShowImage findOne(Long id);

	PhShowImage findByOrderNo(String orderNo);

	Page<PhShowImage> findByPage(String realName, String position, String orderNo, String unionid, String status,
			Long brandId, String isOffway, List<Long> brandIds, Pageable page);
}
