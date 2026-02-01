package com.yff.consultantspringbootlangchain4j.service.impl;


import com.yff.consultantspringbootlangchain4j.mapper.ReservationMapper;
import com.yff.consultantspringbootlangchain4j.pojo.Reservation;
import com.yff.consultantspringbootlangchain4j.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yff
 * @date 2026-02-01 15:18:10
 */
@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationMapper reservationMapper;

    @Override
    public void addReservation(Reservation reservation) {

        reservationMapper.insert(reservation);

    }

    @Override
    public Reservation findByPhoneReservation(String phone) {
        return reservationMapper.findByPhoneReservation(phone);
    }
}
