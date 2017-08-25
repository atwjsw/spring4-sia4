package spittr.data.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spittr.domain.Spitter;

import java.util.List;

/**
 * Created by wenda on 8/20/2017.
 */
public interface SpitterRepository extends JpaRepository<Spitter, Long>, SpitterSweeper{

    Spitter findByUsername(String username);
    List<Spitter> readByUsernameOrFullNameIgnoringCase(String first, String last);
    @Query("select s from Spitter s where s.email like '%gmail.com'")
    List<Spitter> findAllGmailSpitters();
}
