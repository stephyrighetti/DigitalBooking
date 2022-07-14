package com.example.proyectoIntegrador.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name="policies_type")
public class PolicyType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @NotEmpty
    String name;

    @OneToMany(mappedBy = "policyType", fetch = FetchType.LAZY)
    @JsonIgnore
    Set<Policy> policies = new HashSet<>();


    public PolicyType(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
