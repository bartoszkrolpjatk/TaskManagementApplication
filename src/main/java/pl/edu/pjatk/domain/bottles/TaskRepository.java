package pl.edu.pjatk.domain.bottles;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TaskRepository extends CrudRepository<Task, Long> {
}
