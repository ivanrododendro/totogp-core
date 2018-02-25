package com.totogp.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.totogp.application.dto.LoginMessageRP;
import com.totogp.model.User;

@Mapper
public interface LoginMessageMapper {
	LoginMessageMapper INSTANCE = Mappers.getMapper(LoginMessageMapper.class);

	@Mappings({ @Mapping(source = "user.id", target = "userId") })
	LoginMessageRP carToCarDto(User user);
}
