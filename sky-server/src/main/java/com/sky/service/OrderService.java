package com.sky.service;


import com.sky.dto.*;
import com.sky.result.PageResult;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;
import org.springframework.stereotype.Service;


public interface OrderService {



    /**
     * 用户下单
     *
     * @param ordersSubmitDTO
     * @return
     */
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);


    /**
     * 用户历史订单分页查询
     * @param page
     * @param pageSize
     * @param status
     * @return
     */
    PageResult pageQuery4User(int page, int pageSize, Integer status);


    /**
     * 查询用户历史订单详情
     * @param id
     * @return
     */
    OrderVO details(Long id);

    /**
     * 取消用户订单
     * @param id
     */
    void userCancelById(Long id);

    /**
     * 用户再来一单
     * @param id
     */
    void repetition(Long id);

    /**
     * 商家接单
     * @param ordersConfirmDTO
     */
    void confirm(OrdersConfirmDTO ordersConfirmDTO);

    /**
     * 商家完成订单
     * @param id
     */
    void complete(Long id);

    /**
     * 商家拒绝订单
     * @param ordersRejectionDTO
     */
    void rejection(OrdersRejectionDTO ordersRejectionDTO);

    /**
     * 商家取消订单
     * @param ordersCancelDTO
     */
    void cancel(OrdersCancelDTO ordersCancelDTO);

    /**
     * 商家派送订单
     * @param id
     */
    void delivery(Long id);



}
