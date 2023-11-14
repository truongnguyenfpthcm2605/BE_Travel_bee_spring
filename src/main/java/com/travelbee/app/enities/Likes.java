package com.travelbee.app.enities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "likes")
public class Likes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate = new Date();
    private Boolean isactive;

    @ManyToOne
    @JoinColumn(name = "accountid", referencedColumnName = "id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "tourid", referencedColumnName = "id")
    private Tour tour;

}
