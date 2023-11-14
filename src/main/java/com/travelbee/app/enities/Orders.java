package com.travelbee.app.enities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String qrcode;
    private String voucher;
    private String status;
    private String cccd;
    private String email;
    private String numberphone;
    private Double price;
    private Integer member;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;

    @ManyToOne
    @JoinColumn(name = "accountId", referencedColumnName = "id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "plantourid", referencedColumnName = "id")
    private PlanTour plantour;

    @OneToMany(mappedBy = "orders", fetch = FetchType.EAGER)
    private List<Payment> payments;


}
