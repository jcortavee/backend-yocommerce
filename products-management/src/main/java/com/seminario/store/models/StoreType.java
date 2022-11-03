package com.seminario.store.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "store_type")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoreType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "storeType", fetch = FetchType.LAZY, orphanRemoval = true)
    @Transient
    private List<Store> stores = new ArrayList<>();

    private String type;
    private String description;

}
