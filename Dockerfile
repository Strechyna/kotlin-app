FROM amazoncorretto:17.0.6
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test
EXPOSE 8080
CMD ["java","-jar","./build/libs/pizza-toppings-0.0.1-SNAPSHOT.jar"]

