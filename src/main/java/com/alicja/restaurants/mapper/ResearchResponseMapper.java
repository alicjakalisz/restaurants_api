package com.alicja.restaurants.mapper;

import com.alicja.restaurants.dto.ResearchResponseDto;
import com.alicja.restaurants.model.ResearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ResearchResponseMapper implements Mapper<ResearchResponseDto, ResearchResponse>{

    Logger logger = LoggerFactory.getLogger(ResearchResponseMapper.class);
    @Override
    public ResearchResponseDto fromEntityToDto(ResearchResponse entity) {
        ResearchResponseDto researchResponseDto = new ResearchResponseDto(entity.getId(),
                entity.getName(), entity.getAddress(), entity.getRating(),entity.getPrice_leve(),entity.getPhoto());
        logger.info("Converting entity [{}] into Dto [{}]",entity,researchResponseDto);
        return researchResponseDto;
    }

    @Override
    public ResearchResponse fromDtoToEntity(ResearchResponseDto dto) {
        ResearchResponse researchResponse = new ResearchResponse(dto.getId(),
                dto.getName(), dto.getAddress(), dto.getRating(), dto.getPrice_leve(), dto.getPhoto());
        logger.info("Converting Dto [{}] into Entity [{}]", dto,researchResponse);
        return researchResponse;
    }
}
