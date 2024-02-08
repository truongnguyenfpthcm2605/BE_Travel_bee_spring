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
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hotel")
public class Hotel extends BaseEntity implements Serializable  {

    private String title;
    private String phone;
    private String address;
    private String description;
    @Lob
    private String images;


    @ManyToOne
    @JoinColumn(name = "accountid", referencedColumnName = "id")
    private Account account;

    @JsonIgnore
    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER)
    private List<Tourdetails> tourdetails;
    

}
