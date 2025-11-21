package org.example.miniprojpetadoptionshelter.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.miniprojpetadoptionshelter.entity.base.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "animals",
        indexes = {
                @Index(name = "idx_animals_shelter", columnList = "shelter_id, status"),
                @Index(name = "idx_animals_species", columnList = "species, breed")
        })
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Animal extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(length = 30)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelter_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_animals_shelter"))
    private Shelter shelter;

    @Column(nullable = false, length = 30)
    private String species;

    @Column(length = 60)
    private String breed;

    @Column(nullable = false, length = 10)
    private String sex;

    @Column(name = "age_years")
    private Double ageYears;

    @Column(name = "weight_kg")
    private Double weightKg;

    @Column(length = 100)
    private String temperament;

    @Column(nullable = false, length = 20)
    private String status = "AVAILABLE";


    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnimalFile> files = new ArrayList<>();


    // === 편의 메서드 ===
    public void addFile(AnimalFile file) {
        files.add(file);
        file.setAnimal(this);
    }

    public void changeBasicInfo(String name, String species, String breed, String sex) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.sex = sex;
    }

    public void updateStatus(String status) {
        this.status = status;
    }


    @Builder
    public Animal(String name, Shelter shelter, String species, String breed, String sex,
                  Double ageYears, Double weightKg, String temperament, String status) {

        this.name = name != null ? name : "TBD";
        this.shelter = shelter;
        this.species = species;
        this.breed = breed;
        this.sex = sex;
        this.ageYears = ageYears;
        this.weightKg = weightKg;
        this.temperament = temperament;
        this.status = status != null ? status : "AVAILABLE";
    }
}
