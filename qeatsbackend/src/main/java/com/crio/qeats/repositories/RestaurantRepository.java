/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.repositories;

import com.crio.qeats.models.RestaurantEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RestaurantRepository extends MongoRepository<RestaurantEntity, String> {

    @Query("{'name': {$regex: '^?0$', $options: 'i'}}")
    Optional<List<RestaurantEntity>> findRestaurantsByNameExact(String name);

    @Query("{attributes : ?0}")
  	Optional<RestaurantEntity> findRestaurantsByAttributesExact(String attributes);

	@Query("{restaurantId: { $in: ?0 } })")
  	List<RestaurantEntity> findRestaurantsByid(List<String> restaurantId);
}

