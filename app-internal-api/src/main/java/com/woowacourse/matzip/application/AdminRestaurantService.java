package com.woowacourse.matzip.application;

import com.woowacourse.matzip.application.response.RestaurantResponse;
import com.woowacourse.matzip.domain.campus.Campus;
import com.woowacourse.matzip.domain.category.Category;
import com.woowacourse.matzip.domain.restaurant.Restaurant;
import com.woowacourse.matzip.exception.CampusNotFoundException;
import com.woowacourse.matzip.exception.CategoryNotFoundException;
import com.woowacourse.matzip.repository.CampusRepository;
import com.woowacourse.matzip.repository.CategoryRepository;
import com.woowacourse.matzip.repository.RestaurantRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AdminRestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final CategoryRepository categoryReposiroty;
    private final CampusRepository campusRepository;

    public AdminRestaurantService(final RestaurantRepository restaurantRepository,
                                  final CategoryRepository categoryReposiroty,
                                  final CampusRepository campusRepository) {
        this.restaurantRepository = restaurantRepository;
        this.categoryReposiroty = categoryReposiroty;
        this.campusRepository = campusRepository;
    }

    public List<RestaurantResponse> findAll() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream()
                .map(this::toRestaurantResponse)
                .collect(Collectors.toList());
    }

    private RestaurantResponse toRestaurantResponse(final Restaurant restaurant) {
        Category category = categoryReposiroty.findById(restaurant.getId())
                .orElseThrow(CategoryNotFoundException::new);
        Campus campus = campusRepository.findById(restaurant.getCampusId())
                .orElseThrow(CampusNotFoundException::new);
        return RestaurantResponse.of(restaurant, category, campus);
    }
}
