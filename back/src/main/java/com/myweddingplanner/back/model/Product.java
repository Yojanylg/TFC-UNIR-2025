package com.myweddingplanner.back.model;

import com.myweddingplanner.back.model.enums.ProductType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"images"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    private String description;

    private String link;

    private double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type")
    private ProductType productType;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageProduct> images = new ArrayList<>();


    public void setImagenes (List<ImageProduct> nuevas){
        this.images.clear();
        if (nuevas != null){
            nuevas.forEach(this::addImagen);
        }
    }

    public void addImagen(ImageProduct img){
        img.setProduct(this);
        this.images.add(img);
    }

    public void removeImagen(ImageProduct img){
        this.images.remove(img);
        img.setProduct(null);
    }

}