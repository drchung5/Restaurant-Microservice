# Restaurant Microservice

Listening on port: 8000

### Get all restaurants
curl --location --request GET 'http://localhost:8000/restaurants'

### Get a restaurant
curl --location --request GET 'http://localhost:8000/restaurants/1'

### Get restaurants by cuisine
curl --location --request GET 'http://localhost:8000/restaurants/cuisines/Sushi'

### Add a restaurant
curl --location --request POST 'http://localhost:8000/restaurants' \
--header 'Content-Type: application/json' \
--data-raw '{"name": "Sushi House", "cuisine_id":2}'

### Delete restaurant by id
curl --location --request DELETE 'localhost:8000/restaurants/1'

### Access Swagger UI (in a browser)
http://localhost:8000/swagger-ui.html

### Access Database (h2) Console (in a browser)
http://localhost:8000/h2-console