package com.seminario.store.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ps_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Address> addresses = new ArrayList<>();

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, optional = true)
    @JsonBackReference
    private DeliveryPerson deliveryPerson;

    private String email;
    private String username;
    private String password;

    public void addAddress(Address address) {
        if (!addresses.contains(address)) {
            this.addresses.add(address);
        }
        address.setUser(this);
    }

}
