package com.pharmIT.inventoryManager.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private String name;

    private String description;

    private Integer quantity;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    @JsonProperty("category_id")
    private Category category;
}
