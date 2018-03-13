package com.totogp.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.totogp.application.dto.RankingRP;
import com.totogp.model.Enrollment;

@Mapper
public interface RankingMapper {
	RankingMapper INSTANCE = Mappers.getMapper(RankingMapper.class);

	@Mappings({ @Mapping(source = "user.firstname", target = "userLabel") })
	RankingRP riderToDTO(Enrollment enrollment);
}
