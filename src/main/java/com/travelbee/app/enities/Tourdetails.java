package com.travelbee.app.enities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tourdetails")
public class Tourdetails  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToOne
    @JoinColumn(name = "locationid", referencedColumnName = "id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "hotelid", referencedColumnName = "id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "transportid", referencedColumnName = "id")
    private Transport transport;

    @ManyToOne
    @JoinColumn(name = "tourid", referencedColumnName = "id")
    private Tour tour;


}
