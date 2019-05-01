package app;

import app.domain.entities.Diagnose;
import app.domain.entities.Medication;
import app.domain.entities.Patient;
import app.domain.entities.Visitation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("hospital");
        EntityManager entityManager = factory.createEntityManager();


        Patient beki = new Patient("Beki", "G");
        Patient natti = new Patient("Natti", "Natasha");

        beki.getVisitations().add(new Visitation(LocalDate.now(), beki));
        natti.getVisitations().add(new Visitation(LocalDate.now(),natti));

        beki.getDiagnoses().add(new Diagnose("depression"));
        beki.getDiagnoses().add(new Diagnose("cold"));
        natti.getDiagnoses().add(new Diagnose("cold"));

        beki.getMedications().add(new Medication("Rakiya"));
        beki.getMedications().add(new Medication("Paracetamol"));

        natti.getMedications().add(new Medication("Aspirin"));
        natti.getMedications().add(new Medication("Paracetamol"));

        entityManager.getTransaction().begin();

        entityManager.persist(beki);
        entityManager.persist(natti);

        natti.getVisitations().forEach(v -> entityManager.persist(v));
        beki.getVisitations().forEach(v -> entityManager.persist(v));

        natti.getMedications().forEach(m -> entityManager.persist(m));
        beki.getMedications().forEach(m -> entityManager.persist(m));

        natti.getDiagnoses().forEach(d -> entityManager.persist(d));
        beki.getDiagnoses().forEach(d -> entityManager.persist(d));

        entityManager.getTransaction().commit();

        beki.getVisitations().forEach(v -> System.out.println(v.getDate()));

    }
}
