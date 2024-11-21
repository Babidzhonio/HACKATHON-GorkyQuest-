package com.example.GorkyQuest.quests.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quests")
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "task",nullable = false)
    private String task;

    @Column(name = "difficulty",nullable = false)
    private String difficulty;

    @Column(name = "point",nullable = false)
    private String point;

    @Column(name = "reward",nullable = false)
    private String reward;

    @ManyToOne
    @JoinColumn(name="district_name",nullable = false)
    private District district;
}

