package com.recipe.gola.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.gola.dto.OrderDTO;
import com.recipe.gola.mapper.OrderMapper;

import lombok.Data;

@Service
@Data
public class OrderService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private final OrderMapper orderMapper;
	
	public List<OrderDTO> list(OrderDTO dto){
		return orderMapper.list(dto);
	}

	public int insertOrder(OrderDTO dto) {
		return orderMapper.insertOrder(dto);
	}
	
	public OrderDTO request(OrderDTO dto) {
		return orderMapper.request(dto);
	}
	
    //게시글번호 생성
    public int selectOno() throws Exception{
    	return orderMapper.selectOno();
    }
	
	
}
