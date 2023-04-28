package com.task.pizzatoppings.exceptions

import org.springframework.http.HttpStatus

class ToppingsNotFoundException : CustomException("Not found any toppings", HttpStatus.NOT_FOUND)
