package com.totogp.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.totogp.application.dto.RiderRP;
import com.totogp.model.Rider;

@Mapper
public interface RiderMapper {
	public RiderMapper INSTANCE = Mappers.getMapper(RiderMapper.class);

	@Mappings({ @Mapping(source = "country.name", target = "country"), @Mapping(source = "number", target = "id") })
	public RiderRP riderToDTO(Rider rider);

}
