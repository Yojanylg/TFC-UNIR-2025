package com.myweddingplanner.back.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "allergens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"images"})
public class Allergen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    @OneToMany(mappedBy = "allergen", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageAllergen> images = new ArrayList<>();


    public void setImages (List<ImageAllergen> nuevas){
        this.images.clear();
        if (nuevas != null){
            nuevas.forEach(this::addImage);
        }
    }

    public void addImage(ImageAllergen img){
        img.setAllergen(this);
        this.images.add(img);
    }

    public void removeImage(ImageAllergen img){
        this.images.remove(img);
        img.setAllergen(null);
    }

}