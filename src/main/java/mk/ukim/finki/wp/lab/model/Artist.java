package mk.ukim.finki.wp.lab.model;

import lombok.Data;

@Data
public class Artist {
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;

    public Artist(String firstName, String lastName, Long id, String bio) {
        this.bio = bio;
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
    }
}
