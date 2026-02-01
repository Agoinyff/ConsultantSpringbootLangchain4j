package com.yff.consultantspringbootlangchain4j.service;


import com.yff.consultantspringbootlangchain4j.pojo.Reservation;
import org.springframework.stereotype.Service;

/**
 * @author yff
 * @date 2026-02-01 15:16:14
 */

public interface ReservationService {
    //添加预约信息
    void addReservation(Reservation reservation);

    //根据手机号查询预约信息
    Reservation findByPhoneReservation(String phone);

}
