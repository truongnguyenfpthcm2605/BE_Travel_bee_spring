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
@Table(name = "tour")
public class Tour implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Double price;
    private Long views;
    @Lob
    private String images;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate ;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedate;
    private Boolean isactive;

    @ManyToOne
    @JoinColumn(name = "accountId", referencedColumnName = "id")
    private Account account;

    @JsonIgnore
    @OneToMany(mappedBy = "tour", fetch = FetchType.EAGER)
    private List<Likes> likes;

    @JsonIgnore
    @OneToMany(mappedBy = "tour", fetch = FetchType.EAGER)
    private List<Comment> comments ;

    @JsonIgnore
    @OneToMany(mappedBy = "tour", fetch = FetchType.EAGER)
    private List<PlanTour>  planTours;

    @JsonIgnore
    @OneToMany(mappedBy = "tour", fetch = FetchType.EAGER)
    private List<Tourdetails> tourdetails;

    @JsonIgnore
    @OneToMany(mappedBy = "tour", fetch = FetchType.EAGER)
    private List<ReportTour> reportTours;

}
