package gamestore;

import gamestore.controllers.GameController;
import gamestore.controllers.UserController;
import gamestore.domain.entities.User;
import gamestore.repository.GameRepository;
import gamestore.repository.UserRepository;
import gamestore.service.GameServiceImpl;
import gamestore.service.UserServiceImpl;
import gamestore.service.api.GameService;
import gamestore.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

@Controller
public class ConsoleRunner implements CommandLineRunner {
    private Scanner scanner;
    private UserRepository userRepository;
    private GameRepository gameRepository;
    private HashMap<String, Object> cache;
    private HashMap<String, Class<?>> mappings;


    @Autowired
    public ConsoleRunner(UserRepository userRepository, GameRepository gameRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.createMappings();
        this.cacheRepositories();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            String[] input = scanner.nextLine().split("\\|");
            String methodName = input[0].split("(?=\\p{Upper})")[0].toLowerCase();
            String controllerName = input[0].split("(?=\\p{Upper})")[1];
            String pathName = "gamestore.controllers." + controllerName + "Controller";
            Object controller = this.resolveParameters(pathName);
            Method[] methods = controller.getClass().getDeclaredMethods();
            Method mtd = Arrays.stream(methods)
                    .filter(m -> m.getName().equals(methodName))
                    .findFirst().get();
            Class<?>[] params = mtd.getParameterTypes();
            Object[] methodArguments = new Object[mtd.getParameterCount()];

            for (int i = 0; i < params.length; i++) {
                if (params[i] == String.class) {
                    methodArguments[i] = input[i + 1];
                } else if (params[i] == BigDecimal.class) {
                    methodArguments[i] = new BigDecimal(input[i + 1]);
                }
            }
            mtd.setAccessible(true);
            System.out.println(mtd.invoke(controller, methodArguments));
        }
    }

    private Object resolveParameters(String pathName) throws IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        if (this.cache.containsKey(pathName)) {
            return this.cache.get(pathName);
        }
        Class<?> clazz = mappings.get(pathName);
        //  Class<?> clazz = Class.forName(pathName);
        Constructor constructor = clazz.getDeclaredConstructors()[0];
        Parameter[] params = constructor.getParameters();
        Object[] arguments = new Object[constructor.getParameterCount()];
        int i = 0;
        for (Parameter param : params) {
            arguments[i] = resolveParameters(param.getParameterizedType().getTypeName());
            i++;
        }
        Object instance = constructor.newInstance(arguments);
        this.cache.put(instance.getClass().getName(), instance);
        return instance;
    }

    private void createMappings() {
        this.mappings = new HashMap<>();
        this.mappings.put(UserService.class.getName(), UserServiceImpl.class);
        this.mappings.put(GameService.class.getName(), GameServiceImpl.class);
        this.mappings.put(UserController.class.getName(), UserController.class);
        this.mappings.put(GameController.class.getName(), GameController.class);
        this.mappings.put(AutenticationContext.class.getName(), AutenticationContext.class);

    }

    private void cacheRepositories() {
        this.cache = new HashMap<>();
        cache.put(UserRepository.class.getName(), this.userRepository);
        cache.put(GameRepository.class.getName(), this.gameRepository);
    }
}

