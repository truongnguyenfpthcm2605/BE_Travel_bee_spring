package com.travelbee.app.enities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Table(name = "tour")
public class Tour extends BaseEntity implements Serializable {

    private String title;
    private String description;
    private Double price;
    private Long views;
    @Lob
    private String images;


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
