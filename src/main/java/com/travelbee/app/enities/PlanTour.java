package com.travelbee.app.enities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name = "plantour")
public class PlanTour implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date stardate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date enddate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedate;
    private String description;

    @ManyToOne
    @JoinColumn(name = "accountid", referencedColumnName = "id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "tourid", referencedColumnName = "id")
    private Tour tour;

    @OneToMany(mappedBy = "plantour",fetch = FetchType.EAGER)
    private List<Orders> orders;
}
