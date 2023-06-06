package com.hh.computerShop.api.controller;

import com.hh.computerShop.model.enums.ProductType;
import com.hh.computerShop.model.enums.PropertyType;
import com.hh.computerShop.model.enums.property.FormFactor;
import com.hh.computerShop.model.enums.property.SizeType;
import com.hh.computerShop.persist.db.h2.DetailRepository;
import com.hh.computerShop.persist.db.h2.ProductRepository;
import com.hh.computerShop.persist.db.h2.entity.DetailEntity;
import com.hh.computerShop.persist.db.h2.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class ProductControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    DetailRepository detailRepository;

    @Test
    void handleGetProductById_ReturnsValidResponseEntity() throws Exception {
        var requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/products/4");

        this.productRepository.saveAll(getTestData());

        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                        MockMvcResultMatchers.content().json("""
                                                                
                                {
                                    "id": 4,
                                    "serialNumber": "43fr34f34f34f",
                                    "producer": "ComInvest",
                                    "price" : 100000,
                                    "quantity": 200000,
                                    "productType": "HDD",
                                    "details": [
                                        {
                                            "propertyType": "CAPACITY",
                                            "propertyValue": "256"
                                        }
                                    ]
                                }
                                                                
                                """)

                );
    }

    @Test
    void handleGetAllProductsByProductType_ReturnsValidResponseEntity() throws Exception {
        var requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/products").queryParam("productType", "MONITOR");

        this.productRepository.saveAll(getTestData());

        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                        MockMvcResultMatchers.content().json("""
                                [
                                    {
                                        "id": 1,
                                        "serialNumber": "4dge54tcye4t",
                                        "producer": "Apple",
                                        "price" : 60000,
                                        "quantity": 1000,
                                        "productType": "MONITOR",
                                        "details": [
                                            {
                                                "propertyType": "DIAGONAL_SIZE",
                                                "propertyValue": "27"
                                            }
                                        ]
                                    },
                                    {
                                        "id": 6,
                                        "serialNumber": "43fr34f34f34f",
                                        "producer": "ComInvest",
                                        "price" : 100000,
                                        "quantity": 200000,
                                        "productType": "MONITOR",
                                        "details": [
                                            {
                                                "propertyType": "DIAGONAL_SIZE",
                                                "propertyValue": "32"
                                            }
                                        ]
                                    }
                                ]
                                """)

                );
    }

    @Test
    void handleUpdateProduct_ReturnsValidResponseEntity() throws Exception {
        this.productRepository.saveAll(getTestData());

        var requestBuilder = MockMvcRequestBuilders
                .put("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "id": 3,
                            "price" : 15000,
                            "quantity": 1000,
                            "details": [
                                {
                                    "propertyType": "FORM_FACTOR",
                                    "propertyValue": "NETTOP"
                                }
                            ]
                        }
                        """);

        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                        MockMvcResultMatchers.content().json("""
                                {
                                    "id": 3,
                                    "serialNumber": "43fr34f34f34f",
                                    "producer": "ComInvest",
                                    "price" : 15000,
                                    "quantity": 1000,
                                    "productType": "DESKTOP",
                                    "details": [
                                        {
                                            "propertyType": "FORM_FACTOR",
                                            "propertyValue": "NETTOP"
                                        }
                                    ]
                                }
                                """)

                );
    }

    @Test
    void handleAddProduct_ReturnsValidResponseEntity() throws Exception {
        this.productRepository.saveAll(getTestData());

        var requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "serialNumber": "43fr34f34f34f",
                            "producer": "ComInvest",
                            "price" : 100000,
                            "quantity": 200000,
                            "productType": "HDD",
                            "details": [
                                {
                                    "propertyType": "CAPACITY",
                                    "propertyValue": "512"
                                }
                            ]
                        }
                        """);

        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        MockMvcResultMatchers.status().isCreated(),
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                        MockMvcResultMatchers.content().json("""
                                {
                                    "id": 7,
                                    "serialNumber": "43fr34f34f34f",
                                    "producer": "ComInvest",
                                    "price" : 100000,
                                    "quantity": 200000,
                                    "productType": "HDD",
                                    "details": [
                                        {
                                            "propertyType": "CAPACITY",
                                            "propertyValue": "512"
                                        }
                                    ]
                                }
                                """)

                );

    }

    public List<ProductEntity> getTestData() {
        var product1 = new ProductEntity(
                1L,
                "4dge54tcye4t",
                "Apple",
                60000,
                1000,
                ProductType.MONITOR,
                List.of(
                        new DetailEntity(
                                1L,
                                PropertyType.DIAGONAL_SIZE,
                                "27",
                                new ProductEntity(
                                        1L,
                                        null,
                                        null,
                                        null,
                                        null,
                                        null,
                                        null
                                )
                        )
                )
        );

        var product2 = new ProductEntity(
                2L,
                "43fr34f34f34f",
                "ComInvest",
                100000,
                200000,
                ProductType.NOTEBOOK,
                List.of(
                        new DetailEntity(
                                2L,
                                PropertyType.NOTEBOOK_SIZE,
                                SizeType.SIZE_TYPE_13.getSizeType(),
                                new ProductEntity(
                                        2L,
                                        null,
                                        null,
                                        null,
                                        null,
                                        null,
                                        null
                                )
                        )
                )
        );

        var product3 = new ProductEntity(
                3L,
                "43fr34f34f34f",
                "ComInvest",
                100000,
                200000,
                ProductType.DESKTOP,
                List.of(
                        new DetailEntity(
                                3L,
                                PropertyType.FORM_FACTOR,
                                FormFactor.NETTOP.getForm(),
                                new ProductEntity(
                                        3L,
                                        null,
                                        null,
                                        null,
                                        null,
                                        null,
                                        null
                                )
                        )
                )
        );

        var product4 = new ProductEntity(
                4L,
                "43fr34f34f34f",
                "ComInvest",
                100000,
                200000,
                ProductType.HDD,
                List.of(
                        new DetailEntity(
                                4L,
                                PropertyType.CAPACITY,
                                "256",
                                new ProductEntity(
                                        4L,
                                        null,
                                        null,
                                        null,
                                        null,
                                        null,
                                        null
                                )
                        )
                )
        );

        var product5 = new ProductEntity(
                5L,
                "43fr34f34f34f",
                "ComInvest",
                100000,
                200000,
                ProductType.HDD,
                List.of(
                        new DetailEntity(
                                5L,
                                PropertyType.CAPACITY,
                                "512",
                                new ProductEntity(
                                        5L,
                                        null,
                                        null,
                                        null,
                                        null,
                                        null,
                                        null
                                )
                        )
                )
        );

        var product6 = new ProductEntity(
                6L,
                "43fr34f34f34f",
                "ComInvest",
                100000,
                200000,
                ProductType.MONITOR,
                List.of(
                        new DetailEntity(
                                6L,
                                PropertyType.DIAGONAL_SIZE,
                                "32",
                                new ProductEntity(
                                        6L,
                                        null,
                                        null,
                                        null,
                                        null,
                                        null,
                                        null
                                )
                        )
                )
        );

        return List.of(
                product1,
                product2,
                product3,
                product4,
                product5,
                product6
        );
    }
}
