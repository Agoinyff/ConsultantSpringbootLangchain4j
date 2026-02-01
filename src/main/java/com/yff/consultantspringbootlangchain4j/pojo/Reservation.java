package com.yff.consultantspringbootlangchain4j.pojo;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName reservation
 */
@Data
@AllArgsConstructor

public class Reservation {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 考生姓名
     */
    private String name;

    /**
     * 考生性别
     */
    private String gender;

    /**
     * 考生手机号
     */
    private String phone;

    /**
     * 沟通时间
     */
    private LocalDateTime communicationTime;

    /**
     * 考生所处的省份
     */
    private String province;

    /**
     * 考生预估分数
     */
    private Integer estimatedScore;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Reservation other = (Reservation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getCommunicationTime() == null ? other.getCommunicationTime() == null : this.getCommunicationTime().equals(other.getCommunicationTime()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getEstimatedScore() == null ? other.getEstimatedScore() == null : this.getEstimatedScore().equals(other.getEstimatedScore()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getCommunicationTime() == null) ? 0 : getCommunicationTime().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getEstimatedScore() == null) ? 0 : getEstimatedScore().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", gender=").append(gender);
        sb.append(", phone=").append(phone);
        sb.append(", communicationTime=").append(communicationTime);
        sb.append(", province=").append(province);
        sb.append(", estimatedScore=").append(estimatedScore);
        sb.append("]");
        return sb.toString();
    }
}