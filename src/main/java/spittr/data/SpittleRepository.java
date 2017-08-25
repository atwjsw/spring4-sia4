package spittr.data;

import java.util.List;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import spittr.Spittle;

public interface SpittleRepository {

  List<Spittle> findRecentSpittles();

  List<Spittle> findSpittles(long max, int count);

  @Cacheable("spittleCache")
  Spittle findOne(long id);

  @CachePut("spittleCache")
  void save(Spittle spittle);

}
