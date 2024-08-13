package org.una.programmingIII.WikiPets.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cat_breed_id")
    private CatBreed catBreed;

    @ManyToOne
    @JoinColumn(name = "dog_breed_id")
    private DogBreed dogBreed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private Integer rating;

    @Column(length = 1000)
    private String comment;

    @Version
    @Column(name = "REVIEW _VERSION")
    private Long version;

//    public Review(ReviewDto reviewDto) {
//        this.id = reviewDto.getId();
//        update(reviewDto);
//    }
//
//    public void update(ReviewDto reviewDto) {
//        this.comment = reviewDto.getComment();
//        this.rating = reviewDto.getRating();
//        this.catBreed= new CatBreed(reviewDto.getCatBreedDto());
//        this.dogBreed= new DogBreed(reviewDto.getDogBreedDto());
//        this.user= new User(reviewDto.getUserDto());
//
//    }
}
