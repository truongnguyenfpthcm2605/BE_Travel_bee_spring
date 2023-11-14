package com.travelbee.app.enities;

import com.travelbee.app.util.Roles;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import java.io.Serializable;

@Entity
@Data
@Table(name = "role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @NaturalId
    private Roles name;


}
