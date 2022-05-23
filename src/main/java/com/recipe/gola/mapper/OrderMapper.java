package com.recipe.gola.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.recipe.gola.dto.OrderDTO;

@Mapper
public interface OrderMapper {
	
	public List<OrderDTO> list(OrderDTO dto);
	
	public OrderDTO request(OrderDTO dto);

	public int insertOrder(OrderDTO dto);
	
	public int selectOno();
}