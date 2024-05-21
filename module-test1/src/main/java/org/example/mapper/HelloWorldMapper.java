package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.model.Dual;

@Mapper
public interface HelloWorldMapper {

    Dual find1FromDual();
}
