package spittr.data.jdbc;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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
//  @Cacheable(value="spittleCache", unless="#result.message.contains('NoCache')")
//  @Cacheable(value="spittleCache", unless="#result.message.contains('NoCache')", condition="#id >= 10")
  Spittle findOne(long id);

  @CachePut(value="spittleCache", key="#result.id")
  Spittle save(Spittle spittle);
    
  List<Spittle> findBySpitterId(long spitterId);

  @CacheEvict("spittleCache")
  void delete(long id);
    
}
