package com.seminario.store.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "store")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_type_id")
    @JsonProperty("store_type")
    private StoreType storeType;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Product> products = new ArrayList<>();

    @Column(name = "user_id")
    @JsonProperty("user_id")
    private Long userId;

    private String name;
    private String description;
    private String phone;
    private String email;
    private String address;
    private Boolean status;


}
