package com.gamesUP.gamesUP.config;

import com.gamesUP.gamesUP.dao.*;
import com.gamesUP.gamesUP.model.*;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Random;

@Configuration
@AllArgsConstructor
public class DataGeneratorConfig {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private ClientRepository clientRepository;
    private AuthorRepository authorRepository;
    private CategoryRepository categoryRepository;
    private PublisherRepository publisherRepository;
    private GameRepository gameRepository;
    private PurchaseRepository purchaseRepository;
    private PurchaseLineRepository purchaseLineRepository;
    private WishlistRepository wishlistRepository;
    private WishlineRepository wishlineRepository;
    private AvisRepository avisRepository;

    @Bean
    CommandLineRunner generateFakeData() {
        return args -> {
            Faker faker = new Faker();
            Random r = new Random();
//            User
            for (int i = 0; i < 10; i++) {
                User user = new User();
                String username = "%s%d".formatted(faker.name().username(), i);
                String email = "user%d@%s".formatted(i, faker.internet().domainName());
                user.setUsername(username);
                user.setEmail(email);
                user.setNom(faker.name().firstName());
                user.setPrenom(faker.name().lastName());
                user.setMotdepasse(passwordEncoder.encode("Password1234"));

                try {
                    userRepository.saveAndFlush(user);
                } catch (Exception e) {
                    System.out.println("Error insert " + e.getMessage());
                }
            }
//            Client
            for (int i = 0; i < 10; i++) {
                Client model = new Client();
                String username = "%s%d".formatted(faker.name().username(), i);
                String email = "client%d@%s".formatted(i, faker.internet().domainName());
                model.setUsername(username);
                model.setEmail(email);
                model.setNom(faker.name().firstName());
                model.setPrenom(faker.name().lastName());
                model.setMotdepasse(passwordEncoder.encode("Password1234"));
                try {
                    clientRepository.saveAndFlush(model);
                } catch (Exception e) {
                    System.out.println("Error insert " + e.getMessage());
                }
            }
//            Author
            for (int i = 0; i < 10; i++) {
                Author model = new Author();
                String username = "%s%d".formatted(faker.name().username(), i);
                String email = "user%d@%s".formatted(i, faker.internet().domainName());
                model.setUsername(username);
                model.setEmail(email);
                model.setNom(faker.name().firstName());
                model.setPrenom(faker.name().lastName());
                model.setMotdepasse(passwordEncoder.encode("Password1234"));
                try {
                    authorRepository.saveAndFlush(model);
                } catch (Exception e) {
                    System.out.println("Error insert " + e.getMessage());
                }
            }
//            Category
            for (int i = 0; i < 10; i++) {
                Category model = new Category();
                model.setType(faker.name().name());
                try {
                    categoryRepository.saveAndFlush(model);
                } catch (Exception e) {
                    System.out.println("Error insert " + e.getMessage());
                }
            }
//            Publisher
            for (int i = 0; i < 10; i++) {
                Publisher model = new Publisher();
                model.setName(faker.company().name());
                try {
                    publisherRepository.saveAndFlush(model);
                } catch (Exception e) {
                    System.out.println("Error insert " + e.getMessage());
                }
            }
//            Game
            List authors = authorRepository.findAll();
            List categories = categoryRepository.findAll();
            List publishers = publisherRepository.findAll();
            for (int i = 0; i < 10; i++) {
                Game model = new Game();
                model.setNom(faker.name().name());
                model.setGenre(faker.artist().name());
                model.setPrix(faker.number().randomDouble(2, 9, 999));
                model.setNumEdition(faker.number().randomDigitNotZero());
                model.setPublisher((Publisher) publishers.get(r.nextInt(1, publishers.size())));
                model.setAuthor((Author) authors.get(r.nextInt(1, authors.size())));
                model.setCategory((Category) categories.get(r.nextInt(1, categories.size())));
                try {
                    gameRepository.saveAndFlush(model);
                } catch (Exception e) {
                    System.out.println("Error insert " + e.getMessage());
                }
            }
//            Purchase
            List clients = clientRepository.findAll();
            for (int i = 0; i < 10; i++) {
                Purchase model = new Purchase();
                model.setArchived(faker.random().nextBoolean());
                model.setPaid(faker.random().nextBoolean());
                model.setDelivered(faker.random().nextBoolean());
                model.setDate(faker.date().birthday(0, 5));
                faker.random().nextInt(-250000, 0);
                model.setClient((Client) clients.get(r.nextInt(1, clients.size())));
                try {
                    purchaseRepository.saveAndFlush(model);
                } catch (Exception e) {
                    System.out.println("Error insert " + e.getMessage());
                }
            }
//            PurchaseLine
            List games = gameRepository.findAll();
            List purchases = purchaseRepository.findAll();
            for (int i = 0; i < 10; i++) {
                PurchaseLine model = new PurchaseLine();
                model.setPurchase((Purchase) purchases.get(r.nextInt(1, purchases.size())));
                model.setGame((Game) games.get(r.nextInt(1, games.size())));
                model.setQuantite(faker.random().nextInt(1, 10));
                model.setPrixTT();
                try {
                    purchaseLineRepository.saveAndFlush(model);
                } catch (Exception e) {
                    System.out.println("Error insert " + e.getMessage());
                }
            }
//            Wishlist
            for (int i = 0; i < 10; i++) {
                Wishlist model = new Wishlist();
                model.setClient((Client) clients.get(r.nextInt(1, clients.size())));
                try {
                    wishlistRepository.saveAndFlush(model);
                } catch (Exception e) {
                    System.out.println("Error insert " + e.getMessage());
                }
            }
//            WishLine
            List wishlist = wishlistRepository.findAll();
            for (int i = 0; i < 10; i++) {
                WishLine model = new WishLine();
                model.setGame((Game) games.get(r.nextInt(1, games.size())));
                model.setWishlist((Wishlist) wishlist.get(r.nextInt(1, wishlist.size())));
                try {
                    wishlineRepository.saveAndFlush(model);
                } catch (Exception e) {
                    System.out.println("Error insert " + e.getMessage());
                }
            }
//            Avis
            List users = userRepository.findAll();
            for (int i = 0; i < 10; i++) {
                Avis model = new Avis();
                model.setGame((Game) games.get(r.nextInt(1, games.size())));
                model.setUser((User) users.get(r.nextInt(1, users.size())));
                model.setNote(faker.random().nextInt(1, 5));
                model.setCommentaire(faker.random().toString());
                try {
                    avisRepository.saveAndFlush(model);
                } catch (Exception e) {
                    System.out.println("Error insert " + e.getMessage());
                }
            }
        };
    }
}
