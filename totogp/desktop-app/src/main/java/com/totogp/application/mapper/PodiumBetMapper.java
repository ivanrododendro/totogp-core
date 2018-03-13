package com.totogp.application.mapper;

import org.mapstruct.factory.Mappers;

import com.totogp.application.dto.PodiumBetRP;
import com.totogp.model.PodiumBet;

//@Mapper
public interface PodiumBetMapper {
	PodiumBetMapper INSTANCE = Mappers.getMapper(PodiumBetMapper.class);

	PodiumBetRP betToDTO(PodiumBet bet);
}
