package app.domain.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Visitation {
    private Integer id;
    private LocalDate date;
    private String comment;
    private Patient patient;

    public Visitation() {
    }

    public Visitation(LocalDate date, Patient patient) {
        this.setDate(date);
        this.setPatient(patient);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "date_of_visit")
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Column(name = "comment", columnDefinition = "Text")
    public String getComment() {
        return comment;
    }

    public void setComment(String comments) {
        this.comment = comment;
    }

    @ManyToOne()
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
