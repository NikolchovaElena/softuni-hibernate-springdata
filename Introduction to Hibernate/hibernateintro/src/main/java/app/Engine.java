package app;

import app.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Engine implements Runnable {

    private final EntityManager entityManager;
    private BufferedReader reader;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run() {

//        getEmployeesWithSalaryOver50000();
//        try {
//            removeTowns();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 2.	Remove Objects
     */

    private void removeObjects() {
        entityManager.getTransaction().begin();
        List<Town> towns = entityManager
                .createQuery("SELECT t FROM Town t WHERE LENGTH(t.name)>5")
                .getResultList();

        for (Town t : towns) {
            entityManager.detach(t);
            t.setName(t.getName().toLowerCase());
            entityManager.merge(t);
        }

        entityManager.getTransaction().commit();
    }

    /**
     * 3.	Contains Employee
     * Svetlin Nakov	Yes
     * John Doe	        No
     */
    private void containsEmployee() throws IOException {
        String name = reader.readLine();
        entityManager.getTransaction().begin();

        try {
            Employee employee = entityManager
                    .createQuery("SELECT e FROM Employee e WHERE CONCAT(first_name, ' ', last_name) LIKE (:name)", Employee.class)
                    .setParameter("name", name)
                    .getSingleResult();

            System.out.println("Yes");
        } catch (NoResultException nre) {
            System.out.println("No");
        }

        entityManager.getTransaction().commit();
    }


    /**
     * 4.	Employees with Salary Over 50 000
     */

    private void getEmployeesWithSalaryOver50000() {
        entityManager.getTransaction().begin();

        List<Employee> employeesList = entityManager
                .createQuery("SELECT e FROM Employee e WHERE e.salary > (:salaryValue)", Employee.class)
                .setParameter("salaryValue", BigDecimal.valueOf(50000))
                .getResultList();

        for (Employee employee : employeesList) {
            System.out.println(employee.getFirstName());
        }

        entityManager.getTransaction().commit();
    }

    /**
     * 5.	Employees from Department
     */

    private void getEmployeesFromDepartment() {
        entityManager.getTransaction().begin();

        List<Employee> employeesList = entityManager
                .createQuery("SELECT e FROM Employee e WHERE e.department.name = (:departmentName) ORDER BY e.salary ASC , e.id ASC ", Employee.class)
                .setParameter("departmentName", "Research and Development")
                .getResultList();

        for (Employee employee : employeesList) {

            String result = String.format("%s %s from %s - $%.2f",
                    employee.getFirstName(), employee.getLastName(), employee.getDepartment().getName(), employee.getSalary());
            System.out.println(result);
        }

        entityManager.getTransaction().commit();
    }

    /**
     * 6.	Adding a New Address and Updating Employee
     * Ansman-Wolfe
     */

    private void addNewAddressAndUpdateEmployee() throws IOException {
        String targetLastName = reader.readLine();

        entityManager.getTransaction().begin();

        Address newAddress = new Address();
        newAddress.setText("Vitoshka 15");

        Town town = entityManager
                .createQuery("SELECT t FROM Town t WHERE t.name = 'Sofia'", Town.class)
                .getSingleResult();

        newAddress.setTown(town);
        entityManager.persist(newAddress);

        Employee targetEmployee = entityManager
                .createQuery("SELECT e FROM Employee e WHERE e.lastName = (:lastName)", Employee.class)
                .setParameter("lastName", targetLastName)
                .setMaxResults(1)
                .getSingleResult();

        entityManager.detach(targetEmployee.getAddress());
        targetEmployee.setAddress(newAddress);
        entityManager.merge(targetEmployee);

        entityManager.getTransaction().commit();
    }

    /**
     * 7.	Addresses with Employee Count
     */
    private void getAddressesWithEmployeeCount() {
        entityManager.getTransaction().begin();

        List<Address> addressList = entityManager
                .createQuery("SELECT a FROM Address a ORDER BY a.employees.size DESC, a.town.id ASC",Address.class)
                .setMaxResults(10)
                .getResultList();

        for (Address address : addressList) {
            String addressResult = String.format("%s, %s - %d employees",
                    address.getText(),
                    address.getTown().getName(),
                    address.getEmployees().size());
            System.out.println(addressResult);
        }

        entityManager.getTransaction().commit();
    }

    /**
     * 8.	Get Employee with Project
     */
    private void getEmployeesWithProjects() throws IOException {
        int targetEmployeeId = Integer.parseInt(reader.readLine());
        entityManager.getTransaction().begin();

        Employee targetEmployee = entityManager
                .createQuery("SELECT e FROM Employee e Where e.id = :targetId", Employee.class)
                .setParameter("targetId", targetEmployeeId)
                .getSingleResult();

        List<Project> projects = targetEmployee.getProjects()
                .stream()
                .sorted((a, b) -> a.getName().compareTo(b.getName()))
                .collect(Collectors.toList());

        System.out.printf("%s  %s - %s\n",
                targetEmployee.getFirstName(), targetEmployee.getLastName(), targetEmployee.getJobTitle());

        for (Project p : projects) {
            String addressResult = String.format("\t%s",
                    p.getName());
            System.out.println(addressResult);
        }

        entityManager.getTransaction().commit();
    }

    /**
     * 9.	Find Latest 10 Projects
     */
    private void findLatest10Projects() {
        entityManager.getTransaction().begin();

        List<Project> projects = entityManager
                .createQuery("SELECT p FROM Project p ORDER BY p.startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultList();

        projects = projects.stream().sorted((a, b) -> a.getName().compareTo(b.getName())).collect(Collectors.toList());

        for (Project p : projects) {
            System.out.println(String.format("Project name: %s", p.getName()));
            System.out.println(String.format("\tProject Description: %s", p.getDescription()));
            System.out.println(String.format("\tProject Start Date: %s", p.getStartDate()));
            System.out.println(String.format("\tProject End Date: %s", p.getEndDate()));
        }

        entityManager.getTransaction().commit();
    }

    /**
     * 10.	Increase Salaries
     */
    private void increaseSalaries() {
        entityManager.getTransaction().begin();

        List<Employee> employeeList = entityManager
                .createQuery("SELECT e FROM Employee e Where e.department.name IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services') ", Employee.class)
                .getResultList();

        for (Employee e : employeeList) {
            entityManager.detach(e);
            e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12)));
            entityManager.merge(e);
        }

        entityManager.getTransaction().commit();

        for (Employee e : employeeList) {
            System.out.println(String.format("%s %s ($%.2f)", e.getFirstName(), e.getLastName(), e.getSalary()));
        }
    }

    /**
     * 11.	Remove Towns
     */
    private void removeTowns() throws IOException {
        String targetTown = reader.readLine();
        int addressesSize = 0;
        entityManager.getTransaction().begin();

        List<Address> addresses = (List<Address>) entityManager
                .createQuery("SELECT a FROM Address a Where a.town.name = :townName")
                .setParameter("townName", targetTown)
                .getResultList();
        addressesSize = addresses.size();

        for (Address address : addresses) {
            for (Employee e : address.getEmployees()) {
                e.setAddress(null);
            }
            entityManager.flush();
            entityManager.remove(address);
        }
        Town town = addresses.get(0).getTown();
        entityManager.remove(town);
        entityManager.getTransaction().commit();

        System.out.println(String.format("%d address in %s deleted", addressesSize, targetTown));
    }

    /**
     * 12.	Find Employees by First Name
     * random order, not specified in the description
     */
    private void findEmployeeByFirstName() throws IOException {
        String pattern = reader.readLine() + "%";
        entityManager.getTransaction().begin();

        List<Employee> employeeList = entityManager
                .createQuery("SELECT e FROM Employee e Where e.firstName LIKE :givenPattern", Employee.class)
                .setParameter("givenPattern", pattern)
                .getResultList();

        for (Employee e : employeeList) {
            System.out.println(String.format("%s %s - %s - ($%s)", e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary()));
        }

        entityManager.getTransaction().commit();
    }

    /**
     * 13.	Employees Maximum Salaries
     * random order, not specified in the description
     */

    private void getEmployeesMaximumSalary() {
        entityManager.getTransaction().begin();

        List<Employee> employeeList = entityManager
                .createQuery("SELECT e FROM Employee e WHERE e.salary NOT BETWEEN 30000 AND 70000", Employee.class)
                .getResultList();

        Set<Department> depList = employeeList.stream().distinct().map(Employee::getDepartment).collect(Collectors.toSet());

        for (Department d : depList) {
            BigDecimal max = BigDecimal.valueOf(0);

            for (Employee e : employeeList) {
                if (e.getDepartment().getId() == d.getId()) {
                    if (max.compareTo(e.getSalary()) < 1) {
                        max = e.getSalary();
                    }
                }
            }
            System.out.println(String.format("%s - $%.2f", d.getName(), max));
        }

        entityManager.getTransaction().commit();
    }


}
