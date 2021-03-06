package restaurant.data;

import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import restaurant.database.*;
import restaurant.exception.ClassIsNotEntityException;
import restaurant.exception.OrderEmptyFieldException;
import restaurant.misc.Builder;
import restaurant.misc.Password;
import restaurant.misc.Storage;
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

    public void generateFinishedOrders() {
        ClientRepository clientRepository = context.getBean(ClientRepository.class);
        ProductRepository productRepository = context.getBean(ProductRepository.class);
        OrderRepository orderRepository = context.getBean(OrderRepository.class);
        List<Client> clients = clientRepository.findAll();
        for (Client client : clients) {
            for (int i = 0; i < 5; i++) {

                if (random.nextBoolean()) {
                    Builder.getBuilder().newOrder(Order.Type.DELIVERY);
                    Builder.getBuilder().addDeliveryAddress(client.getAddress());
                } else {
                    Builder.getBuilder().newOrder(Order.Type.RESTAURANT);
                }

                for (int j = 0; j < (random.nextInt(5) + 1); j++) {
                    Builder.getBuilder().addProduct(productRepository.getOne((long) (random.nextInt(dummyProduct.length) + 1)));
                }

                Builder.getBuilder().completeOnlinePayment();
                try {
                    Order order = Builder.getBuilder().build();
                    order.setStatus(Order.Status.ZREALIZOWANE);
                    order.setClientList(Arrays.asList(client));
                    client.addOrder(order);
                    orderRepository.save(order);
                } catch (OrderEmptyFieldException e) {
                    throw new IllegalStateException();
                }

            }
        }
    }

    public void loadStorage() {
        IngredientRepository ingredientRepository = context.getBean(IngredientRepository.class);
        List<String> ingredients = ingredientRepository.getAllIngredients();
        Map<String, Integer> ingredientList = new HashMap<>();
        ingredients.forEach((x) -> ingredientList.put(x, random.nextInt(101) + 10));
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

    public static Product getProduct(int id) {
        return dummyProduct[id];
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
                random.nextInt(899999999) + 100000000L, "TadeuszKucharski", Password.hash("pass")),
            new Client("Genowefa", "Czarnecka", dummyAddress[random.nextInt(dummyAddress.length)],
                    random.nextInt(899999999) + 100000000L, "GenowefaCzarnecka", Password.hash("pass")),
            new Client("Wisława", "Woźniak", dummyAddress[random.nextInt(dummyAddress.length)],
                    random.nextInt(899999999) + 100000000L, "WislawaWozniak", Password.hash("pass")),
            new Client("Witold", "Dudek", dummyAddress[random.nextInt(dummyAddress.length)],
                    random.nextInt(899999999) + 100000000L, "WitoldDudek", Password.hash("pass")),
            new Client("Dawid", "Chmielewski", dummyAddress[random.nextInt(dummyAddress.length)],
                    random.nextInt(899999999) + 100000000L, "DawidChmielewski", Password.hash("pass"))
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
            new Ingredient("Water", 50),
            new Ingredient("BBQ sauce", 150),
            new Ingredient("Onion", 50),
            new Ingredient("Peppers", 150),
            new Ingredient("Mushrooms", 75),
            new Ingredient("Garlic", 75),
            new Ingredient("Olives", 50),
            new Ingredient("Bacon", 250),
            new Ingredient("Pineapple", 150),
            new Ingredient("Salami", 200),
            new Ingredient("Chicken", 300),
            new Ingredient("Mozzarella", 250),
            new Ingredient("Croutons", 50),
            new Ingredient("Lettuce", 50),
            new Ingredient("Rucola", 100),
            new Ingredient("Cucumber", 100),
            new Ingredient("Red Onion", 100),
            new Ingredient("Oregano", 100),
            new Ingredient("Corn", 150),
            new Ingredient("Sour Cream", 75),
            new Ingredient("Spinach", 150)
    };

    @Dummy(dependency = {Ingredient.class})
    private static Product[] dummyProduct = {
            new Product("Margherita Pizza", 2200, Arrays.asList(
                    getIngredient("Dough"), getIngredient("Tomato sauce"),
                    getIngredient("Mozzarella"), getIngredient("Oregano")
            )),
            new Product("Salami Pizza", 2700, Arrays.asList(
                    getIngredient("Tomato sauce"), getIngredient("Mozzarella"),
                    getIngredient("Salami")
            )),
            new Product("Dracula", 2900, Arrays.asList(
                    getIngredient("Tomato sauce"), getIngredient("Onion"),
                    getIngredient("Ham"), getIngredient("Mushrooms"),
                    getIngredient("Oregano"), getIngredient("Bacon")
            )),
            new Product("Mexican Pizza", 2700, Arrays.asList(
                    getIngredient("Dough"), getIngredient("Tomato sauce"),
                    getIngredient("Mozzarella"), getIngredient("Oregano"),
                    getIngredient("Peppers"), getIngredient("Beef"),
                    getIngredient("Corn")
            )),
            new Product("Italian Pizza", 2650, Arrays.asList(
                    getIngredient("Dough"), getIngredient("Tomato sauce"),
                    getIngredient("Mozzarella"), getIngredient("Oregano"),
                    getIngredient("Bacon"), getIngredient("Onion"),
                    getIngredient("Olives")
            )),
            new Product("Spanish Pizza", 2600, Arrays.asList(
                    getIngredient("Dough"), getIngredient("Tomato sauce"),
                    getIngredient("Mozzarella"), getIngredient("Oregano"),
                    getIngredient("Mushrooms"), getIngredient("Salami"),
                    getIngredient("Tomato")
            )),
            new Product("Carbonara Pizza", 2500, Arrays.asList(
                    getIngredient("Dough"), getIngredient("Sour Cream"),
                    getIngredient("Mozzarella"), getIngredient("Onion"),
                    getIngredient("Bacon")
            )),
            new Product("Meat Pizza", 2850, Arrays.asList(
                    getIngredient("Dough"), getIngredient("Tomato sauce"),
                    getIngredient("Mozzarella"), getIngredient("Oregano"),
                    getIngredient("Ham"), getIngredient("Chicken"),
                    getIngredient("Beef")
            )),
            new Product("Arabic Pizza", 2600, Arrays.asList(
                    getIngredient("Dough"), getIngredient("Tomato sauce"),
                    getIngredient("Mozzarella"), getIngredient("Oregano"),
                    getIngredient("Tomato"), getIngredient("Spinach"),
                    getIngredient("Chicken")
            )),
            new Product("Vegetarian Pizza", 3400, Arrays.asList(
                    getIngredient("Dough"), getIngredient("Tomato sauce"),
                    getIngredient("Mozzarella"), getIngredient("Mushrooms"),
                    getIngredient("Oregano"), getIngredient("Peppers"),
                    getIngredient("Corn")
            )),
            new Product("Burger", 1800, Arrays.asList(
                    getIngredient("Bun"), getIngredient("Beef"),
                    getIngredient("Tomato"), getIngredient("Mayonese"),
                    getIngredient("Cheese"), getIngredient("Ham")
            )),
            new Product("Burger hot cheese bacon", 2500, Arrays.asList(
                    getIngredient("Bun"), getIngredient("Lettuce"),
                    getIngredient("Rucola"), getIngredient("Cucumber"),
                    getIngredient("Bacon"), getIngredient("Red Onion"),
                    getIngredient("BBQ sauce"), getIngredient("Cheese")
            )),
            new Product("Hawaiian Burger", 1900, Arrays.asList(
                    getIngredient("Bun"), getIngredient("Beef"),
                    getIngredient("Lettuce"), getIngredient("Cucumber"),
                    getIngredient("Pineapple"), getIngredient("Bacon"),
                    getIngredient("Red Onion"), getIngredient("BBQ sauce"),
                    getIngredient("Cheese"), getIngredient("Mayonese")
            )),
            new Product("Hot-dog", 1200, Arrays.asList(
                    getIngredient("Hot-dog bun"), getIngredient("Sausage"),
                    getIngredient("Mustard"), getIngredient("Tomato"),
                    getIngredient("Lettuce")
            )),
            new Product("Orange juice(fresh) 1l", 250, Arrays.asList(
                    getIngredient("Water"), getIngredient("Orange")
            ), false),
            new Product("Pepsi 0.7l", 350, Arrays.asList(), false),
            new Product("Ice tea green 0.5l", 450, Arrays.asList(), false),
            new Product("Ice tea lemon 0.5l", 450, Arrays.asList(), false),
            new Product("Pepsi 1l", 550, Arrays.asList(), false),
            new Product("Mirinda 1l", 550, Arrays.asList(), false),
            new Product("Apple juice 1l", 350, Arrays.asList(), false)
    };

    @Dummy(dependency = {Address.class})
    private static Employee[] dummyEmployee = {
            new Employee("Angelika", "Sobczak", dummyAddress[random.nextInt(dummyAddress.length)],
                    random.nextInt(899999999) + 100000000L, Employee.Type.WAITER),
            new Employee("Michał", "Młodawski", dummyAddress[random.nextInt(dummyAddress.length)],
                    random.nextInt(899999999) + 100000000L, Employee.Type.COOK),
            new Employee("Gerwazy", "Grabowski", dummyAddress[random.nextInt(dummyAddress.length)],
                    random.nextInt(899999999) + 100000000L, Employee.Type.SUPPLIER),
    };

    public void addOrder() {
        OrderRepository orderRepository = context.getBean(OrderRepository.class);
        EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);
        AddressRepository addressRepository = context.getBean(AddressRepository.class);
        ReservationRepository reservationRepository = context.getBean(ReservationRepository.class);
        ClientRepository clientRepository = context.getBean(ClientRepository.class);

        Client client = clientRepository.getOne(1L);
        Reservation reservation = new Reservation(2, 3, client);
        PurchaseProof proof = new PurchaseProof();

        Order order = new Order();

        order.setEmployee(employeeRepository.getOne(1L));
        order.setDeliveryAddress(addressRepository.getOne(1L));
        order.setTable(reservation);
        proof.setOrder(order);
        proof.setType(PurchaseProof.PurchaseType.BILL);
        order.setPurchaseProof(proof);
        order.setStatus(Order.Status.UTWORZONE);
        order.setType(Order.Type.RESTAURANT);
        order.setClientList(Arrays.asList(client));
        List<Product> productList = new LinkedList<>();
        productList.add(DataManager.getProduct(3));
        productList.add(DataManager.getProduct(5));
        order.setProductList(productList);

        orderRepository.save(order);
    }
}