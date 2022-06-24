package com.backgammon.v1.base;

import java.util.List;
import java.util.Set;

public interface BaseMapper<E, D> {

  D mapToDto(E entity);

  E mapToEntity(D dto);

  Set<D> mapToDtoSet(Set<E> entitySet);

  Set<E> mapToEntitySet(Set<D> dtoSet);

  List<D> mapToDtoList(List<E> entityList);

  List<E> mapToEntityList(List<D> dtoList);

}
