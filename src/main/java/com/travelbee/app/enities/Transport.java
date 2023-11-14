package com.travelbee.app.enities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transport")
public class Transport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String phone;
    private String address;
    @Lob
    private String images;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedate;
    private String description;
    private Boolean isactive;

    @ManyToOne
    @JoinColumn(name = "accountid", referencedColumnName = "id")
    private Account account;

    @JsonIgnore
    @OneToMany(mappedBy = "transport", fetch = FetchType.EAGER)
    private List<Tourdetails> tourdetails;



}
