package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;

import java.util.ArrayList;
import java.util.List;

public interface DishService {
    /**
     * 新增菜品以及口味
     * @param dishDTO
     */
    void saveWithFlavor(DishDTO dishDTO);


    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);


    /**
     * 菜品批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 对菜品进行起售禁售
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);
}

