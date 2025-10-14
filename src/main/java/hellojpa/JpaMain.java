package hellojpa;

import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        // entityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // entityManager
        EntityManager em = emf.createEntityManager();
        // transaction
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{

            Member findMember = em.find(Member.class, 150L);
            findMember.setName("AAAAAA333");

            System.out.println("===========================");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}