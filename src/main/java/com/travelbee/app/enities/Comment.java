package com.travelbee.app.enities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "comment")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate ;
    @Lob
    private String image;
    private Boolean isactive;
    @ManyToOne
    @JoinColumn(name = "accountId", referencedColumnName = "id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "tourid", referencedColumnName = "id")
    private Tour tour;



}
