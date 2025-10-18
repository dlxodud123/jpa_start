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
            Member member = new Member();
            member.setUsername("hello");
            member.setHomeAddress(new Address("city", "street", "10000")    );
            member.setWorkPeriod(new Period());

            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}