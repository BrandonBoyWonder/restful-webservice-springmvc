package guru.springframework.restfulwebservice.repositories;

import guru.springframework.restfulwebservice.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor,Long> {
}
