package spittr.data.hibernate4;

import org.springframework.cache.annotation.Cacheable;
import spittr.domain.Spittle;

import java.util.List;

/**
 * Repository interface with operations for {@link Spittle} persistence.
 * @author habuma
 */
public interface SpittleRepository {

  long count();

  List<Spittle> findRecent();

  List<Spittle> findRecent(int count);

  @Cacheable("spittleCache")
  Spittle findOne(long id);

  Spittle save(Spittle spittle);

  List<Spittle> findBySpitterId(long spitterId);
  
  void delete(long id);

}
