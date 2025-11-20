package org.example.miniprojpetadoptionshelter.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "animals_files")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnimalFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_animal_files_animal"))
    private Animal animal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_animal_files_file_info"))
    private FileInfo fileInfo;

    @Column(name = "display_order")
    private Integer displayOrder = 0;



    @Builder
    public AnimalFile(Animal animal, FileInfo fileInfo, Integer displayOrder) {
        this.animal = animal;
        this.fileInfo = fileInfo;
        this.displayOrder = displayOrder != null ? displayOrder : 0;
    }
}