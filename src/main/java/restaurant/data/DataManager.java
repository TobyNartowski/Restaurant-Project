package restaurant.data;

import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import restaurant.database.IngredientRepository;
import restaurant.exception.ClassIsNotEntityException;
import restaurant.model.*;

import javax.persistence.Entity;
import java.lang.reflect.Field;
import java.util.*;

@Component
public class DataManager {

    private ApplicationContext context;
    private static Random random = new Random();

    @Autowired
    public DataManager(ApplicationContext context) {
        this.context = context;
    }

    public void loadStorage() {
        IngredientRepository ingredientRepository = context.getBean(IngredientRepository.class);
        List<String> ingredients = ingredientRepository.getAllIngredients();
        Map<String, Integer> ingredientList = new HashMap<>();
        ingredients.forEach((x) -> ingredientList.put(x, 100));
        Storage.getInstance().setIngredientList(ingredientList);
    }

    public void addDummyData(Class<?> entityClass) throws ClassIsNotEntityException {
        if (!entityClass.isAnnotationPresent(Entity.class)) {
            throw new ClassIsNotEntityException();
        }

        Field[] fields = getClass().getDeclaredFields();
        for (Field dummyField : fields) {
            if (dummyField.isAnnotationPresent(Dummy.class) && dummyField.getType().getComponentType().equals(entityClass)) {
               for (Class<?> dependencyClass : dummyField.getAnnotation(Dummy.class).dependency()) {
                    addDummyData(dependencyClass);
                }

                Reflections reflections = new Reflections("restaurant.database");
                for (Class<? extends JpaRepository> repositoryClass : reflections.getSubTypesOf(JpaRepository.class)) {
                    if (repositoryClass.getSimpleName().equals(entityClass.getSimpleName() + "Repository")) {
                        try {
                            for (Object object : (Object[]) dummyField.get(this)) {
                                context.getBean(repositoryClass).save(object);
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }

             }
        }
    }

    private static Ingredient getIngredient(String name) {
        for (Ingredient ingredient : dummyIngredient) {
            if (ingredient.getName().toLowerCase().equals(name.toLowerCase())) {
                return ingredient;
            }
        }
        return null;
    }

    @Dummy
    private static Address[] dummyAddress = {
            new Address("Darłowo", "Pocztowa", "20"),
            new Address("Warszawa", "Miła", "49"),
            new Address("Szczecin", "Słowińców", "36"),
            new Address("Kraków", "Armii Krajowej", "18"),
            new Address("Warszawa", "Żegańska", "15"),
            new Address("Zielona Góra", "Generała Langiewicza Mariana", "77"),
            new Address("Bydgoszcz", "Dzięciołowa", "118"),
            new Address("Gdańsk", "Milskiego Bernarda", "13"),
            new Address("Poznań", "Braillea Ludwika", "41")
    };

    @Dummy(dependency = {Address.class})
    private static Client[] dummyClient = {
            new Client("Tadeusz", "Kucharski", dummyAddress[random.nextInt(dummyAddress.length)],
                random.nextInt(899999999) + 100000000L, "TadeuszKucharski", "pass"),
            new Client("Genowefa", "Czarnecka", dummyAddress[random.nextInt(dummyAddress.length)],
                    random.nextInt(899999999) + 100000000L, "GenowefaCzarnecka", "pass"),
            new Client("Wisława", "Woźniak", dummyAddress[random.nextInt(dummyAddress.length)],
                    random.nextInt(899999999) + 100000000L, "WislawaWozniak", "pass"),
            new Client("Witold", "Dudek", dummyAddress[random.nextInt(dummyAddress.length)],
                    random.nextInt(899999999) + 100000000L, "WitoldDudek", "pass"),
            new Client("Dawid", "Chmielewski", dummyAddress[random.nextInt(dummyAddress.length)],
                    random.nextInt(899999999) + 100000000L, "DawidChmielewski", "pass"),
    };

    @Dummy
    private static Ingredient[] dummyIngredient = {
            new Ingredient("Dough", 300),
            new Ingredient("Tomato sauce", 100),
            new Ingredient("Cheese", 200),
            new Ingredient("Ham", 300),
            new Ingredient("Beef", 500),
            new Ingredient("Bun", 300),
            new Ingredient("Mayonese", 100),
            new Ingredient("Mustard", 100),
            new Ingredient("Tomato", 60),
            new Ingredient("Hot-dog bun", 300),
            new Ingredient("Sausage", 300),
            new Ingredient("Orange", 100),
            new Ingredient("Water", 50)
    };

    @Dummy(dependency = {Ingredient.class})
    private static Product[] dummyProduct = {
            new Product("Pizza", 1100, Arrays.asList(
                    getIngredient("Dough"), getIngredient("Tomato sauce"),
                    getIngredient("Cheese"), getIngredient("Ham")
            )),
            new Product("Burger", 1300, Arrays.asList(
                    getIngredient("Bun"), getIngredient("Beef"),
                    getIngredient("Tomato"), getIngredient("Mayonese")
            )),
            new Product("Hot-dog", 1000, Arrays.asList(
                    getIngredient("Hot-dog bun"), getIngredient("Sausage"),
                    getIngredient("Mustard")
            )),
            new Product("Orange juice", 250, Arrays.asList(
                    getIngredient("Water"), getIngredient("Orange")
            )),
    };

    @Dummy
    private static Employee[] dummyEmployee = {
            new Employee("Angelika", "Sobczak", dummyAddress[random.nextInt(dummyAddress.length)],
                    random.nextInt(899999999) + 100000000L, Employee.Type.WAITER),
            new Employee("Marian", "Wysocki", dummyAddress[random.nextInt(dummyAddress.length)],
                    random.nextInt(899999999) + 100000000L, Employee.Type.COOK),
            new Employee("Gerwazy", "Grabowski", dummyAddress[random.nextInt(dummyAddress.length)],
                    random.nextInt(899999999) + 100000000L, Employee.Type.SUPPLIER),
    };
}