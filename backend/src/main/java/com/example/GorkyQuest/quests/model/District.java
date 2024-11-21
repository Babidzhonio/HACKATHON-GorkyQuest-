package com.example.GorkyQuest.quests.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "districts")
public class District {
    @Id
    @Column(name="name",  nullable = false)
    private String name;

    @Column(name="description",nullable = false)
    private String description;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quest> quest = new ArrayList<>();

}
