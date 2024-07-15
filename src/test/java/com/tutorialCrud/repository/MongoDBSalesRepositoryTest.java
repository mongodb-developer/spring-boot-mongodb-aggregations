package com.tutorialCrud.repository;

import com.tutorialCrud.model.Customer;
import com.tutorialCrud.model.Item;
import com.tutorialCrud.model.Sales;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
public class MongoDBSalesRepositoryTest {
    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:7.0.0"));

    @Autowired
    private SalesRepository salesRepository;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
        registry.add("spring.data.mongodb.database", () -> "testdb");
    }


    @BeforeEach
    public void setUp() {
        salesRepository.save(createMockSales());
    }

    private Sales createMockSales() {
        Item item1 = new Item("Item1", Arrays.asList("Tag1", "Tag2"), new BigDecimal("19.99"), 2);
        Item item2 = new Item("Item2", Arrays.asList("Tag3", "Tag4"), new BigDecimal("29.99"), 1);
        List<Item> items = Arrays.asList(item1, item2);
        Customer customer = new Customer("Male", 30, "customer@example.com", 5);
        return new Sales(new ObjectId(), new Date(), items, "Store1", customer, true, "Online");
    }


    @Test
    public void testFindAllSales() {
        List<Sales> salesList = salesRepository.findAll();
        assertThat(salesList).isNotEmpty();
        assertThat(salesList.size()).isGreaterThan(0);
        System.out.println("Sales Data:");
        for (Sales sale : salesList) {
            System.out.println(sale);
        }
    }
}
