# First Kotlin application

## Overview

Implementation happens using Kotlin & Spring Boot

There are three endpoints:

1. endpoint that allows for customers to submit their email address along with the list of toppings that they would be
   interested in

   POST http://localhost:8080/toppings

Request example

 ```json
{
  "email": "test@test.com",
  "toppings": [
    "Pepperoni",
    "Garlic"
  ]
}
```

2. endpoint that allows for the front end team to grab the list of toppings currently submitted and the number of unique
   customers that have requested that topping

   GET http://localhost:8080/toppings/statistics

Response example

 ```json
[
  {
    "name": "Pepperoni",
    "numberOfRequests": 3
  },
  {
    "name": "Garlic",
    "numberOfRequests": 3
  }
]
```

3. endpoint for listing your personal topping choice

   GET http://localhost:8080/toppings/choice?email=test@test.com

Response example

```json
[
  "Pepperoni",
  "Garlic"
]
```
