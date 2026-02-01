package com.yff.consultantspringbootlangchain4j.mapper;

import com.yff.consultantspringbootlangchain4j.pojo.Reservation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author yff
* @description 针对表【reservation】的数据库操作Mapper
* @createDate 2026-02-01 15:11:18
* @Entity com.yff.consultantspringbootlangchain4j.pojo.Reservation
*/
@Mapper
public interface ReservationMapper {

    //1.添加预约信息
    @Insert("INSERT INTO reservation (name, gender, phone, communication_time, province, estimated_score) " +
            "VALUES (#{name}, #{gender}, #{phone}, #{communicationTime}, #{province}, #{estimatedScore})")
    void insert(Reservation reservation);

    //2.根据手机号查询预约信息
    @Select("SELECT * FROM reservation WHERE phone = #{phone}")
    Reservation findByPhoneReservation(String phone);
}




