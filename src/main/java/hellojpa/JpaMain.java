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

            Movie movie = new Movie();
            movie.setDirector("aaa");
            movie.setActor("bbb");
            movie.setName("바람과 함께 사라지다");
            movie.setPrice(10000);

            em.persist(movie);

            Album album = new Album();
            album.setArtist("태풍");
            album.setName("뛰어");
            album.setPrice(20000);

            em.persist(album);

            Book book = new Book();
            book.setAuthor("베르나르");
            book.setIsbn("띠용");
            book.setName("노인과 바다");
            book.setPrice(30000);

            em.persist(book);

            em.flush();
            em.clear();

            Movie findMovie = em.find(Movie.class, movie.getId());
            System.out.println("find movie = " + findMovie);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}