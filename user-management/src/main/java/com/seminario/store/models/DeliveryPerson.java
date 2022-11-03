package com.seminario.store.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "delivery_person")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeliveryPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "driver_license")
    @JsonProperty("driver_license")
    private String driverLicense;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", unique = true)
    @JsonManagedReference
    private User user;

    @OneToMany(mappedBy = "deliveryPerson", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Vehicle> vehicles = new ArrayList<>();

    private Boolean available;
    private Boolean status;

}
