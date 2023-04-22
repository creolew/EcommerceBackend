package com.example.Ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;


    @Column(length = 125, nullable = false, unique = true)
    private String name;

    @Column(length = 50, nullable = false, unique = true)
    private String alias;

    @Column(length = 125, nullable = false)
    private String image;

    private boolean enabled;

    @OneToOne
    @JoinColumn(name = "parent_id")
    private  Category parent;

    @OneToMany(mappedBy = "parent")
    private Set<Category> children = new HashSet<>();

    public Category(Integer id) {
        this.id = id;
    }

    public Category(String name) {
        this.name = name;
        this.alias = name;
        this.image = "default.png";
    }

    public Category(String name, Category parent){
        this(name);
        this.parent = parent;
    }
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true )
    private Set<Product> products = new HashSet<>();
}
