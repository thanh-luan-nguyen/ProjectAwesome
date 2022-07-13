package com.example.repository;

import com.example.domain.user.model.MUser;
import org.apache.ibatis.annotations.Mapper;

/** MyBatisでrepositoryを作成するために、Javaのinterfaceに@Mapperをつける */
@Mapper
public interface UserMapper {
    /** user登録 */
    public int insertOne(MUser user);
}

