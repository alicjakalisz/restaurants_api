package com.alicja.restaurants.mapper;

public interface Mapper<D,E> {

    D fromEntityToDto(E entity);

    E fromDtoToEntity(D Dto);


}
