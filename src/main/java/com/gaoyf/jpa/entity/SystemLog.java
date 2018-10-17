package com.gaoyf.jpa.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 高宇飞 on 2018/10/15 13:54:07
 */
@Data
@Entity
@Table(name = "system_log")
public class SystemLog {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", length = 32, nullable = false, unique = true)
    private String id;
    //用户id
    private String userId;
    //用户姓名
    private String realName;
    //请求时间
    private Date operaDate;
    //请求IP
    private String operaIp;
    //操作模块名称
    private String moduleName;
    //操作方法名称
    private String operaName;
    //请求路径
    private String operaUrl;
    //请求参数
    private String operaParams;

    public SystemLog(String userId, String realName, Date operaDate, String operaIp, String moduleName,
                     String operaName, String operaUrl, String operaParams) {
        this.userId = userId;
        this.realName = realName;
        this.operaDate = operaDate;
        this.operaIp = operaIp;
        this.moduleName = moduleName;
        this.operaName = operaName;
        this.operaUrl = operaUrl;
        this.operaParams = operaParams;
    }
}
