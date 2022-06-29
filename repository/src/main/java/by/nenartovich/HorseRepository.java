package by.nenartovich;

import by.nenartovich.entity.Horse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorseRepository extends CrudRepository<Horse, Long> {

}
