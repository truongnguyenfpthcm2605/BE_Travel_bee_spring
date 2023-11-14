package com.travelbee.app.enities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "payment")
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate ;
    private String name;
    private String typepayment;
    private String stk;
    private Double money;

    @ManyToOne
    @JoinColumn(name = "ordersid", referencedColumnName = "id")
    private Orders orders;


}
