package guru.springframework.restfulwebservice.repositories;

import guru.springframework.restfulwebservice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
