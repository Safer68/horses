package by.it.academy.horses.accessingdatajpa;

import org.springframework.data.repository.CrudRepository;

public interface HorseRepository extends CrudRepository<Horse, Long> {
}
