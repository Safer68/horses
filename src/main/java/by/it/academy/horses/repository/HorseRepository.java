package by.it.academy.horses.repository;

import by.it.academy.horses.repository.Horse;
import org.springframework.data.repository.CrudRepository;

public interface HorseRepository extends CrudRepository<Horse, Long> {
}
