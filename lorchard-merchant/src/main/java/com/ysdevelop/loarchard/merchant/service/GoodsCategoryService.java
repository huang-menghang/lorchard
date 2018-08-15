package com.ysdevelop.loarchard.merchant.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.ysdevelop.loarchard.merchant.entity.GoodsCategory;

public interface GoodsCategoryService {
   PageInfo<GoodsCategory> list(Map<String, String> queryMap);
}
