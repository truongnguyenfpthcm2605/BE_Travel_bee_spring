package com.travelbee.app.enities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "voucher")
public class Voucher implements Serializable {
    @Id
    private String id;
    private String title;
    @Lob
    private String image;
    private Double discount;
    private Double condition;
    private Integer quanity;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date enddate;
    private Boolean isactive;

    @ManyToOne
    @JoinColumn(name = "accountid", referencedColumnName = "id")
    private Account account;


}
