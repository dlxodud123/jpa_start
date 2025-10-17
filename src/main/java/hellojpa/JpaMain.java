package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;

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
            Member member1 = new Member();
            member1.setUsername("hello1");
            em.persist(member1);

            em.flush();
            em.clear();

            Member reference = em.getReference(Member.class, member1.getId());
            System.out.println("reference = " + reference.getClass());

            reference.getUsername();
            // 초기화 유무
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(reference));

            // 프록시 클래스 확인
            System.out.println("proxy class = " + reference.getClass());

            // 강제 초기화
            Hibernate.initialize(reference);


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}