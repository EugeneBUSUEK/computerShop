package com.hh.computerShop.support.mapper;

import com.hh.computerShop.model.ProductDto;
import com.hh.computerShop.persist.db.h2.entity.ProductEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(ProductDto productDto, @MappingTarget ProductEntity productEntity);
}
