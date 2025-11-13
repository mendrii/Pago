package com.example.Pago;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import net.datafaker.Faker;

import com.example.Pago.model.Order;
import com.example.Pago.model.OrderItem;
import com.example.Pago.model.Payment;
import com.example.Pago.repository.OrderRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private OrderRepository orderRepository;

    // number of sample entries to create (configurable)
    @org.springframework.beans.factory.annotation.Value("${datafaker.count:10}")
    private int count;

    // Lista de videojuegos populares
    private static final String[] VIDEOGAMES = {
        "The Legend of Zelda: Breath of the Wild",
        "Elden Ring",
        "Cyberpunk 2077",
        "Hogwarts Legacy",
        "Baldur's Gate 3",
        "Final Fantasy VII Remake",
        "God of War Ragnarök",
        "Spider-Man 2",
        "Call of Duty: Modern Warfare III",
        "Starfield",
        "Fortnite Battle Royale",
        "Counter-Strike 2",
        "The Witcher 3: Wild Hunt",
        "Red Dead Redemption 2",
        "Grand Theft Auto VI",
        "Minecraft",
        "Roblox",
        "Valorant",
        "League of Legends",
        "Dota 2",
        "Monster Hunter: World",
        "Dark Souls III",
        "Hollow Knight",
        "Stardew Valley",
        "Among Us"
    };

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        for (int i = 0; i < count; i++) {
            Order order = new Order();
            order.setCustomerName(faker.name().fullName());

            List<OrderItem> items = new ArrayList<>();
            int itemsCount = faker.number().numberBetween(1, 4);
            BigDecimal total = BigDecimal.ZERO;

            for (int j = 0; j < itemsCount; j++) {
                OrderItem item = new OrderItem();
                // Usar un nombre de videojuego aleatorio
                item.setProductName(VIDEOGAMES[faker.number().numberBetween(0, VIDEOGAMES.length)]);
                int qty = faker.number().numberBetween(1, 5);
                item.setQuantity(qty);
                double priceDouble = faker.number().randomDouble(2, 1999, 7999) / 100.0; // Precio en rango 19.99-79.99
                BigDecimal price = BigDecimal.valueOf(priceDouble);
                item.setPrice(price);
                items.add(item);

                total = total.add(price.multiply(BigDecimal.valueOf(qty)));
            }

            order.setItems(items);
            order.setTotal(total);

            Payment payment = new Payment();
            payment.setMethod(faker.options().option("Credit Card", "PayPal", "Transfer"));
            payment.setPaid(faker.bool().bool());
            if (payment.isPaid()) {
                payment.setPaymentDate(faker.date().future(1, java.util.concurrent.TimeUnit.DAYS).toInstant()
                        .atZone(java.time.ZoneId.systemDefault()).toLocalDateTime());
            }
            order.setPayment(payment);

            orderRepository.save(order);
        }
    }
}

