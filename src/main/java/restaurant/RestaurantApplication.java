package restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import restaurant.database.AdressRepository;
import restaurant.model.Adress;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class RestaurantApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx =
                SpringApplication.run(RestaurantApplication.class, args);
        //SpringApplication.run(RestaurantApplication.class, args);
        AdressRepository repository = ctx.getBean(AdressRepository.class);

        List<Adress> adresy = new ArrayList<>();
        adresy.add(new Adress("kielce","mloda", "57"));
        adresy.add(new Adress("Warszawa", "asd", "123"));
        adresy.add(new Adress("Gdansk", "piewsza", "1234"));

        adresy.forEach(repository::save);

        List<Adress> adressList = repository.findAll();
        Optional<Adress> adresOptional = repository.findById(2L);
        if(adresOptional.isPresent())
        System.out.println(adresOptional);
        adressList.forEach(System.out::println);
    }

}

