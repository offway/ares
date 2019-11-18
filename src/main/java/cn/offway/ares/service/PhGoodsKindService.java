package cn.offway.ares.service;


import cn.offway.ares.domain.PhGoodsKind;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品种类Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-11-18 14:49:58 Exp $
 */
public interface PhGoodsKindService {

    PhGoodsKind save(PhGoodsKind phGoodsKind);

    PhGoodsKind findOne(Long id);

    void delete(Long id);

    List<PhGoodsKind> save(List<PhGoodsKind> entities);

    List<PhGoodsKind> findByPid(Long pid);

    Page<PhGoodsKind> findByPid(Long pid, Pageable pageable);

    void delByPid(Long pid);

    void delByPPid(Long ppid);

    void resort(Long sort, Long theId);
}
