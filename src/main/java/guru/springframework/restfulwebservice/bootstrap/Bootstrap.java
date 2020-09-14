package guru.springframework.restfulwebservice.bootstrap;

import guru.springframework.restfulwebservice.domain.Category;
import guru.springframework.restfulwebservice.domain.Customer;
import guru.springframework.restfulwebservice.domain.Vendor;
import guru.springframework.restfulwebservice.repositories.CategoryRepository;
import guru.springframework.restfulwebservice.repositories.CustomerRepository;
import guru.springframework.restfulwebservice.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 9/24/17.
 */
@Component
public class Bootstrap implements CommandLineRunner{

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);


        System.out.println("Data Loaded Category= " + categoryRepository.count() );

        //loading Customers

        Customer brandon = Customer.builder()
                .firstName("Brandon")
                .lastName("Moore")
                .build();

        Customer nevada = Customer.builder()
                .firstName("Nevada")
                .lastName("Surname")
                .build();

        Customer alex = Customer.builder()
                .firstName("Alex")
                .lastName("Burchess")
                .build();

        customerRepository.save(brandon);
        customerRepository.save(nevada);
        customerRepository.save(alex);


        System.out.println("Data Loaded Customers= " + customerRepository.count() );

        //loading Vendors

        Vendor vendor1 = Vendor.builder().name("Vendor1").build();
        Vendor vendor2 = Vendor.builder().name("Vendor2").build();
        Vendor vendor3 = Vendor.builder().name("Vendor3").build();
        Vendor vendor4 = Vendor.builder().name("Vendor4").build();
        Vendor vendor5 = Vendor.builder().name("Vendor5").build();

        vendorRepository.save(vendor1);
        vendorRepository.save(vendor2);
        vendorRepository.save(vendor3);
        vendorRepository.save(vendor4);
        vendorRepository.save(vendor5);
    }
}
