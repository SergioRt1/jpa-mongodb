package eci.cosw;

import com.mongodb.BasicDBObject;
import eci.cosw.config.AppConfiguration;
import eci.cosw.data.CustomerRepository;
import eci.cosw.data.TodoRepository;
import eci.cosw.data.UserRepository;
import eci.cosw.data.model.Customer;
import eci.cosw.data.model.Todo;
import eci.cosw.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        MongoOperations mongoOperation = (MongoOperations) applicationContext.getBean("mongoTemplate");

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

        userRepository.deleteAll();

        userRepository.save(new User("Sergio Rodriguez", "sergioRt@example.com"));
        userRepository.save(new User("Charles Nat", "charles@example.com"));
        userRepository.save(new User("Michael Jackson", "MichaelJackson@example.com"));
        userRepository.save(new User("Jimmy Torn", "JimmTorn@example.com"));
        userRepository.save(new User("Elvis Presley", "ElvisPresley@example.com"));
        userRepository.save(new User("Bob Marley", "BobMarley@example.com"));
        userRepository.save(new User("Jimmy Dash", "JimmyDash@example.com"));
        userRepository.save(new User("David Kaden", "Davidkd@example.com"));
        userRepository.save(new User("Dominic Jhonson", "Dominicjh@example.com"));
        userRepository.save(new User("Freddy Mercury", "FreddyMercury@example.com"));

        todoRepository.deleteAll();

        todoRepository.save(new Todo("travel to Galapagos",10,"Jan 10 - 1860","charles@natural.com","pending"));
        todoRepository.save(new Todo("Pass IETI",10,"Jan 10 - 2019","sergioRt@example.com","done"));
        todoRepository.save(new Todo("travel to tokyo",2,"Jan 20 - 1900","charles@natural.com","pending"));
        todoRepository.save(new Todo("Description",1,"Jan 10 - 1898","MichaelJackson@example.com","done"));
        todoRepository.save(new Todo("Pass IETI",2,"Jan 10 - 1860","MichaelJackson@example.com","Pending"));
        todoRepository.save(new Todo("Description",2,"Jan 10 - 1860","JimmTorn@example.com","Pending"));
        todoRepository.save(new Todo("Description",4,"Jan 10 - 1850","sergioRt@example.com","Pending"));
        todoRepository.save(new Todo("Pass IETI",3,"Jan 10 - 1860","Davidkd@example.com","done"));
        todoRepository.save(new Todo("Description",3,"Jan 10 - 1860","FreddyMercury@example.com","Pending"));
        todoRepository.save(new Todo("Description",6,"Jan 10 - 1890","charles@example.com","Pending"));
        todoRepository.save(new Todo("Description",4,"Jan 10 - 1860","ElvisPresley@example.com","Pending"));
        todoRepository.save(new Todo("Pass IETI",4,"Jan 10 - 2021","JimmTorn@example.com","Pending"));
        todoRepository.save(new Todo("Description",5,"Jan 10 - 1860","JimmTorn@example.com","done"));
        todoRepository.save(new Todo("Description",8,"Jan 10 - 1860","sergioRt@example.com","Pending"));
        todoRepository.save(new Todo("Description",5,"Jan 10 - 1340","charles@example.com","Pending"));
        todoRepository.save(new Todo("Pass IETI",6,"Jan 10 - 1870","sergioRt@example.com","done"));
        todoRepository.save(new Todo("Pass IETI",6,"Jan 10 - 1810","BobMarley@example.com","Pending"));
        todoRepository.save(new Todo("Description",9,"Jan 10 - 1890","JimmTorn@example.com","Pending"));
        todoRepository.save(new Todo("Description",9,"Jan 10 - 1860","charles@example.com","Pending"));
        todoRepository.save(new Todo("Description",7,"Jan 10 - 1880","JimmTorn@example.com","done"));
        todoRepository.save(new Todo("Pass IETI",7,"Jan 10 - 1866","Davidkd@example.com","Pending"));
        todoRepository.save(new Todo("Pass IETI",2,"Jan 10 - 1863","Dominicjh@example.com","done"));
        todoRepository.save(new Todo("Description larger description that must be show in search",7,"Jan 10 - 1860","sergioRt@example.com","done"));
        todoRepository.save(new Todo("Pass IETI",1,"Jan 10 - 1860","JimmyDash@example.com","done"));
        todoRepository.save(new Todo("Pass IETI...........................................",10,"Jan 10 - 1889","JimmyDash@example.com","Pending"));

        System.out.println("Todos found with findByResponsible():");
        System.out.println("-------------------------------");
        for (Todo todo : todoRepository.findByResponsible("charles@natural.com")) {
            System.out.println(todo);
        }
        System.out.println("-------------------------------\nTodos that the dueDate has expire:");

        Query query = new Query();
        query.addCriteria(Criteria.where("dueDate").lt(new SimpleDateFormat("MMM dd - yyyy").format(new Date())));
        List<Todo> todos = mongoOperation.find(query,Todo.class);
        for(Todo todo : todos){
            System.out.println(todo);
        }

        System.out.println("-------------------------------\nTodos that are assigned to given user and have priority greater equal to:");
        query = new Query();
        query.addCriteria(Criteria.where("responsible").is("sergioRt@example.com").and("priority").gte(5));
        todos = mongoOperation.find(query,Todo.class);
        for(Todo todo : todos){
            System.out.println(todo);
        }

        System.out.println("-------------------------------\nList users that have assigned more than 2 Todos:");
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.group("responsible").count().as("count"),
                Aggregation.match(Criteria.where("count").gt(2)),
                Aggregation.project("_id"));
        List<BasicDBObject> mappedResult = mongoOperation.aggregate(agg, "todo", BasicDBObject.class).getMappedResults();
        for (Object doc : mappedResult) {
            org.bson.Document docc = (org.bson.Document) doc;
            System.out.println(docc.get("_id"));
        }
        System.out.println("-------------------------------\nTodo list that contains the description with a length greater than 30 characters:");
        query = new Query();
        query.addCriteria(new Criteria().andOperator(Criteria.where("description").regex(".{31,}")));
        todos = mongoOperation.find(query,Todo.class);
        for(Todo todo : todos){
            System.out.println(todo);
        }


    }

}