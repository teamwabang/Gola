package com.recipe.gola.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.gola.dto.RecipeLinkDTO;
import com.recipe.gola.mapper.RecipeLinkMapper;

import lombok.Data;

@Service
@Data
public class RecipeLinkService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private final RecipeLinkMapper recipelinkMapper;
	
	public RecipeLinkDTO detailLink(RecipeLinkDTO linkdto) {
		return recipelinkMapper.detailLink(linkdto);
	}
}
