
/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.services;

import com.crio.qeats.dto.Restaurant;
import com.crio.qeats.exchanges.GetRestaurantsRequest;
import com.crio.qeats.exchanges.GetRestaurantsResponse;
import com.crio.qeats.repositoryservices.RestaurantRepositoryService;
import java.time.LocalTime;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
@Log4j2
public class RestaurantServiceImpl implements RestaurantService {

  private static final Logger log = LogManager.getLogger(RestaurantServiceImpl.class);
  private final Double peakHoursServingRadiusInKms = 3.0;
  private final Double normalHoursServingRadiusInKms = 5.0;
  
  @Autowired
  private RestaurantRepositoryService restaurantRepositoryService;

  
  @Override
  public GetRestaurantsResponse findAllRestaurantsCloseBy(
      GetRestaurantsRequest getRestaurantsRequest, LocalTime currentTime) {
        List<Restaurant> restaurants;
        int hour = currentTime.getHour();
        int minutes= currentTime.getMinute();
        //Double latitude=getRestaurantsRequest.getLatitude();
        //Double longitude=getRestaurantsRequest.getLongitude();
        if((hour>=8 && hour <=9) || (hour==10 && minutes==0) ||(hour==13)|| (hour==14 && minutes==0||
        (hour >=19 && hour <=20 || (hour==21 && minutes==0)))){
          restaurants= restaurantRepositoryService.findAllRestaurantsCloseBy(getRestaurantsRequest.getLatitude(), getRestaurantsRequest.getLongitude(), currentTime, peakHoursServingRadiusInKms);
        }
        else{
          restaurants= restaurantRepositoryService.findAllRestaurantsCloseBy(getRestaurantsRequest.getLatitude(), getRestaurantsRequest.getLongitude(), currentTime, normalHoursServingRadiusInKms);
        }
        GetRestaurantsResponse response=  new GetRestaurantsResponse(restaurants);
        log.info(response);
        return response;
  }

}

