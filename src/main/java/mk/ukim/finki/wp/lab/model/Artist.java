package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String bio;


    public Artist(String firstName, String lastName,  String bio) {
        this.bio = bio;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
