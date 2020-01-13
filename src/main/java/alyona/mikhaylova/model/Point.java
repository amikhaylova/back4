package alyona.mikhaylova.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "POINT_TABLE")
@Getter
@Setter

public class Point {

    public boolean getHit(){
        return this.hit;
    }

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "X")
    private double x;

    @Column(name = "Y")
    private double y;

    @Column(name = "R")
    private double r;

    @Column(name = "HIT")
    private boolean hit;

    @ManyToOne
    @JoinColumn(name="USER_ID", referencedColumnName="LOGIN")
    private User user;
}



