package com.yff.consultantspringbootlangchain4j.tools;


import com.yff.consultantspringbootlangchain4j.pojo.Reservation;
import com.yff.consultantspringbootlangchain4j.service.ReservationService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author yff
 * @date 2026-02-01 15:42:29
 */
@Component
public class ReservationTool {


    @Autowired
    private ReservationService reservationService;

    //工具方法，添加预约信息
    @Tool("预约志愿填报服务")
    public void addReservation(@P("考生姓名") String name,
                               @P("考生性别") String gender,
                               @P("考生手机号") String phone,
                               @P("预约沟通时间，格式为: yyyy-MM-dd'T'HH:mm") String communicationTime,
                               @P("考生所在省份") String province,
                               @P("考生预估分数") Integer estimatedScore) {

        Reservation reservation = new Reservation(null, name, gender, phone, LocalDateTime.parse(communicationTime), province, estimatedScore);
        reservationService.addReservation(reservation);
    }


    //查询预约信息
    @Tool("根据考试手机号查询预约信息")
    public Reservation findByPhoneReservation(@P("考生手机号") String phone) {
        return reservationService.findByPhoneReservation(phone);
    }


}
