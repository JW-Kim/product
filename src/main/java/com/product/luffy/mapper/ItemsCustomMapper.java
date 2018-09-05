package com.product.luffy.mapper;

import com.product.luffy.po.ItemsCustom;
import com.product.luffy.po.ItemsCustomQueryVo;

import java.util.List;

/**
 * Author: markliu
 * Time  : 16-8-23 下午1:52
 */
public interface ItemsCustomMapper {

	List<ItemsCustom> getAllItemsLikeName(ItemsCustomQueryVo itemsCustomQueryVo) throws Exception;
}
