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
@Table(name = "location")
public class Location implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Double latitude;
    private Double longitude;
    @Lob
    private String images;
    private String description;
    private String address;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedate;
    private Boolean isactive;

    @ManyToOne
    @JoinColumn(name = "accountid", referencedColumnName = "id")
    private Account account;

    @JsonIgnore
    @OneToMany(mappedBy = "location", fetch = FetchType.EAGER)
    private List<Tourdetails> tourdetails;

}
