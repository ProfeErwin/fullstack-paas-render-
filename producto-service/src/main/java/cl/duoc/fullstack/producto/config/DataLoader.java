package cl.duoc.fullstack.producto.config;

import cl.duoc.fullstack.producto.model.Producto;
import cl.duoc.fullstack.producto.repository.ProductoRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner cargarDatos(ProductoRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(Producto.builder()
                        .nombre("Notebook Educativo")
                        .categoria("Computación")
                        .stock(20)
                        .precio(350000)
                        .build());

                Faker faker = new Faker();
                for (int i = 0; i < 4; i++) {
                    repository.save(Producto.builder()
                            .nombre(faker.commerce().productName())
                            .categoria(faker.commerce().department())
                            .stock(faker.number().numberBetween(10, 100))
                            .precio(faker.number().numberBetween(5000, 90000))
                            .build());
                }
            }
        };
    }
}
