package cn.com.ttxg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.ttxg.mapper.InStorehouseMapper;
import cn.com.ttxg.pojo.InStorehouseCustom;
import cn.com.ttxg.pojo.InStorehouseExample;

@Service
public class InStorehouseServiceImpl implements InStorehouseService {

	@Autowired
	private InStorehouseMapper inStorehouseMapper;
	
	

	@Override
	public PageInfo<InStorehouseCustom> getInStorehouseCustomsPage(Integer pn,String condition,int searchType) {
		
		InStorehouseExample example = new InStorehouseExample();
		//条件不为空的时候在example中插入条件
		if(!StringUtils.isEmpty(condition)){
			if(searchType==1){
				example.createCriteria().andGoodsNameLike("%"+condition+"%");
			}else if(searchType==2){
				example.createCriteria().andGoodsidEqualTo(condition);
			}else if(searchType==3){
				example.createCriteria().andBrandNameLike("%"+condition+"%");
			}
			
		}
		
		// 从pn页开始查，每页显示5个数据，start后面紧跟的查询就是分页查询
		PageHelper.startPage(pn, 5);
		List<InStorehouseCustom> inStorehouseCustoms = inStorehouseMapper.selectWithGoodsAndBrandByExample(example);
		PageInfo<InStorehouseCustom> page = new PageInfo<InStorehouseCustom>(inStorehouseCustoms, 5);
		
		return page;
	}
	
	
	

}
