package org.example.miniprojpetadoptionshelter.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.miniprojpetadoptionshelter.entity.base.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shelters")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shelter extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    private String address;

    private Double latitude;

    private Double longitude;

    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelter_img_file_id",
            foreignKey = @ForeignKey(name = "fk_shelter_img_file"))
    private FileInfo shelterImgFile;


    public void changeBasicInfo(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public void changeLocation(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }


    @Builder
    public Shelter(String name, String address, Double latitude, Double longitude, String phone, FileInfo shelterImgFile) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone = phone;
        this.shelterImgFile = shelterImgFile;
    }
}
