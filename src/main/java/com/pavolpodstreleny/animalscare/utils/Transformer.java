package com.pavolpodstreleny.animalscare.utils;

import com.pavolpodstreleny.animalscare.exception.TransformerFailedException;

import org.springframework.beans.BeanUtils;
import java.lang.reflect.InvocationTargetException;

public class Transformer {

    public static <DTO, ENTITY> DTO transformToDTO(ENTITY entity, Class<DTO> dto) throws TransformerFailedException {
        try {
            DTO newDto = dto.getConstructor().newInstance();
            BeanUtils.copyProperties(entity, newDto);
            return newDto;
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException
                | IllegalAccessException e) {
            throw new TransformerFailedException("Problem with transformation of entity to DTO");
        }
    }

    public static <DTO, ENTITY> ENTITY transformToEntity(DTO dto, Class<ENTITY> entity)
            throws TransformerFailedException {
        try {
            ENTITY newEntity = entity.getConstructor().newInstance();
            BeanUtils.copyProperties(dto, newEntity);
            return newEntity;
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException
                | IllegalAccessException e) {
            throw new TransformerFailedException("Problem with transformation of DTO to entity");
        }

    }

}