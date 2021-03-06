package com.shopping.recommendationservice.service;

import com.shopping.recommendationservice.model.Product;
import com.shopping.recommendationservice.model.Recommendation;
import com.shopping.recommendationservice.model.User;
import com.shopping.recommendationservice.repository.RecommendationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecommendationServiceTests {

    private final Long RECOMMENDATION_ID = 1L;
    private final Integer RATING = 5;
    private final String PRODUCT_NAME = "testProduct";
    private final String USER_NAME = "testUser";
    private User user;
    private Product product;
    private Recommendation recommendation;
    private List<Recommendation> recommendations;

    @Mock
    private RecommendationRepository repository;

    @InjectMocks
    private RecommendationServiceImpl recommendationService;

    @Before
    public void setUp(){
        user = new User();
        user.setUserName(USER_NAME);
        product = new Product();
        product.setProductName(PRODUCT_NAME);
        recommendation = new Recommendation();
        recommendation.setId(RECOMMENDATION_ID);
        recommendation.setUser(user);
        recommendation.setProduct(product);
        recommendation.setRating(RATING);
        recommendations = new ArrayList<>();
        recommendations.add(recommendation);
    }

    @Test
    public void get_all_recommendation_by_product_name_test(){
        // Data preparation
        when(repository.findAllRatingByProductName(anyString())).thenReturn(recommendations);

        //Method call
        List<Recommendation> foundRecommendations = recommendationService.getAllRecommendationByProductName(PRODUCT_NAME);

        //Verification
        assertEquals(foundRecommendations.get(0).getId(), RECOMMENDATION_ID);
        assertEquals(foundRecommendations.get(0).getProduct().getProductName(), PRODUCT_NAME);
        assertEquals(foundRecommendations.get(0).getUser().getUserName(), USER_NAME);
        Mockito.verify(repository, Mockito.times(1)).findAllRatingByProductName(anyString());
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    public void save_recommendation_test(){
        // Data preparation
        when(repository.save(any(Recommendation.class))).thenReturn(recommendation);

        //Method call
        Recommendation found = recommendationService.saveRecommendation(recommendation);

        //Verification
        assertEquals(found.getId(), RECOMMENDATION_ID);
        assertEquals(found.getProduct().getProductName(), PRODUCT_NAME);
        assertEquals(found.getUser().getUserName(), USER_NAME);
        Mockito.verify(repository, Mockito.times(1)).save(any(Recommendation.class));
        Mockito.verifyNoMoreInteractions(repository);

    }
}
