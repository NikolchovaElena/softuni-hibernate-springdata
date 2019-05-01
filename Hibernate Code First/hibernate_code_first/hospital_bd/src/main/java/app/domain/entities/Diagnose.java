package app.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Diagnose {
    private Integer id;
    private String name;
    private String comment;
    private Set<Patient> patients;

    public Diagnose() {
    }

    public Diagnose(String name) {
        this.setName(name);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "comment", columnDefinition = "Text")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @ManyToMany(mappedBy = "diagnoses", targetEntity = Patient.class)
    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
