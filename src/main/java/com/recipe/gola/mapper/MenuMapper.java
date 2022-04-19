package com.recipe.gola.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.recipe.gola.dto.MenuDTO;

@Mapper
public interface MenuMapper {

	public List<MenuDTO> menulist();

	public MenuDTO detail(int no);
}
