package com.travelbee.app.enities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "account", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username","email"})
})
public class Account implements Serializable {
    private static final long serialVersionUID = 4819986675557946872L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String fullname;
    @JsonIgnore
    private String password;
    @Lob
    private String image;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedate;
    private String qrcode;
    private String providerID;
    @Temporal(TemporalType.DATE)
    private Date birthday;
    private Boolean isactive ;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(name = "authorities",
            joinColumns = @JoinColumn(name = "accountid"),
            inverseJoinColumns = @JoinColumn(name = "roleid")
    )
    private Set<Role> roles = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<Transport> transports;

    @JsonIgnore
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<Hotel> hotels;

    @JsonIgnore
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<Tour> tours;

    @JsonIgnore
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<Location> locations;

    @JsonIgnore
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<Feedback> feedbacks;

    @JsonIgnore
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<Likes> likes;

    @JsonIgnore
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<Voucher> vouchers;

    @JsonIgnore
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<Orders> orders;

    @JsonIgnore
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<PlanTour> planTours;

    @JsonIgnore
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<ReportTour> reportTours;


}
