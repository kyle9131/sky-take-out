package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface SetmealDishMapper {

        /**
         * 根据菜品差对应的套餐id
         *
         * @param dishIds
         * @return
         */
        //select setmeal_id from setmeal_dish where dish_id in(1,2,3,4)
        List<Long> getSetmealIdsByDishIds(List<Long> dishIds);


        /**
         * 批量插入
         * @param setmealDishes
         */
        void insertBatch(List<SetmealDish> setmealDishes);

        /**
         * 批量删除套餐内的菜品
         * @param id
         */
        @Delete("delete  from setmeal_dish where  setmeal_id = #{setmealId}")
        void deleteBySetmealId(Long id);

        /**
         * 批量删除套餐id
         * @param id
         * @return
         */
        @Select("select * from setmeal_dish where setmeal_id = #{setmealId}")
        List<SetmealDish> getBySetmealId(Long id);
}
