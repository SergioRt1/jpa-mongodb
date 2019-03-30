package eci.cosw;

import eci.cosw.data.CustomerRepository;
import eci.cosw.data.TodoRepository;
import eci.cosw.data.model.Customer;
import eci.cosw.data.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TodoRepository todoRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {

        customerRepository.deleteAll();

        customerRepository.save(new Customer("Alice", "Smith"));
        customerRepository.save(new Customer("Bob", "Marley"));
        customerRepository.save(new Customer("Jimmy", "Page"));
        customerRepository.save(new Customer("Freddy", "Mercury"));
        customerRepository.save(new Customer("Michael", "Jackson"));

        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : customerRepository.findAll()) {
            System.out.println(customer);
        }
        System.out.println("-------------------------------");

        todoRepository.deleteAll();

        todoRepository.save(new Todo("travel to Galapagos",10,"Jan 10 - 1860","charles@natural.com","pending"));
        todoRepository.save(new Todo("Pass IETI",10,"Jan 10 - 2019","sergioRt@natural.com","done"));
        todoRepository.save(new Todo("travel to tokyo",2,"Jan 20 - 1900","charles@natural.com","pending"));

        System.out.println("Todos found with findByResponsible():");
        System.out.println("-------------------------------");
        for (Todo todo : todoRepository.findByResponsible("charles@natural.com")) {
            System.out.println(todo);
        }
        System.out.println("-------------------------------\n");

    }

}