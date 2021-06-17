#Restaurants API
This api allows retrieving restaurants detailed information. This API is based on Google API.

## How to compile
```
mvn clean compile test
```

#API
To use the API you need to include a Google API Key in the file RestaurantService.

All the tests mock the Google Rest API call≥

### GET restaurants/${id}

### GET restaurants/search?location=${required_location}&cuisine=${optional}&radius=${radius}$rating={$rating}