# Restaurant Microservice

Listening on port: 8000

### Get all restaurants
curl --location --request GET 'http://localhost:8000/restaurants'

### Get a restaurant
curl --location --request GET 'http://localhost:8000/restaurants/1'

### Add a restaurant
curl --location --request POST 'http://localhost:8000/restaurants' \
--header 'Content-Type: application/json' \
--data-raw '{"name": "Sushi House", "cuisine_id":2}'

### Get best restaurant by cuisine
curl --location --request GET 'localhost:8000/restaurants/best?cuisine_id=1'