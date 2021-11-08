# Warehouse
<p align="center">
<img src="https://user-images.githubusercontent.com/85931447/140827752-3422c8f7-bf70-4581-b5e3-df84e7ac45ac.jpg" alt="Warehouse">
</p>

## Description
This project is a simple realization of warehouse<br/>
The project is N-tier application with:
- DAO layer for interaction with DB
- Service layer with business logic
- Controller level for handling HTTP requests<br/>

CRUD operations released for products<br/>
Integration test added

## Features
- Adding products to warehouse DB
- Getting all products available in stock
- Getting products by ID or SKU number
- Removing products from stock by ID
- Updating products available in stock by ID
- Placing orders for products

## Technologies used
- Java 8
- Maven
- Spring framework (Spring MVC)
- Servlet API
- Hibernate
- MySQL Database
- Apache Tomcat
- TestNG

## How to run locally
1. For the correct work of the program you need to have MySQL and <a href="https://tomcat.apache.org/download-90.cgi">Apache Tomcat 9</a> installed
2. Fork and clone this project.
3. Add DB credentials to `db.properties` file from `resources` directory.
      ```properties
         db.url=jdbc:mysql://localhost/DB_NAME?serverTimezone=UTC
         db.user=DB_USER
         db.password=DB_PASSWORD
      ```
4. Add Tomcat configuration to your project.
    - Fix missing artifact. [EXAMPLE](https://cln.sh/4cj9kj)
    - Use `/` as your Tomcat application context. [EXAMPLE](https://cln.sh/d68iSq)

## REST API
The REST API to the Coordinator app is described below.

### POST (add) products to Database
`http://localhost:8080/products`

#### Sample request body
```javascript
{
  "title": "Product name",
  "weight": 119.1,
  "price": 220.0,
  "sku": "SKU_NUMBER", // should be unique for each product
  "quantity": 150
}
```

#### Sample response
```json
{
  "id": 1,
  "title": "Product name",
  "weight": 119.1,
  "price": 220.0,
  "sku": "SKU_NUMBER",
  "quantity": 150
}
```

### GET all products
`http://localhost:8080/products`

#### Sample response
```json
[
  {
    "id": 1,
    "title": "Product 2",
    "weight": 119.1,
    "price": 220.0,
    "sku": "PRODUCT2",
    "quantity": 150
  },
  {
    "id": 2,
    "title": "Product 3",
    "weight": 434.0,
    "price": 34.0,
    "sku": "PRODUCT2",
    "quantity": 34
  }
]
```

### GET product by ID
`http://localhost:8080/products/id/?id=ID`

#### Sample response
```json
{
  "id": 1,
  "title": "Product name",
  "weight": 119.1,
  "price": 220.0,
  "sku": "SKU_NUMBER",
  "quantity": 150
}
```

### GET product by SKU number
`http://localhost:8080/products/sku/?sku=SKU_NUMBER`

#### Sample response
```json
{
  "id": 1,
  "title": "Product name",
  "weight": 119.1,
  "price": 220.0,
  "sku": "SKU_NUMBER",
  "quantity": 150
}
```

### PUT (update) product by SKU number
`http://localhost:8080/products/ID`

#### Sample request body
```json
{
  "id": 1,
  "title": "Modified product name",
  "weight": 787,
  "price": 234,
  "sku": "SKU_NUMBER",
  "quantity": 49
}
```

#### Sample response
```json
{
  "id": 1,
  "title": "Modified product name",
  "weight": 787,
  "price": 234,
  "sku": "SKU_NUMBER",
  "quantity": 49
}
```

### DELETE (remove) product from Database
`http://localhost:8080/products/ID`

#### Sample response
```text
Status: 200 OK
Empty response
```

### GET (place) product by SKU number
`http://localhost:8080/products/ID`

#### Sample request body
```javascript
{
  "skuAndQuantity": {
    "SKU_NUMBER": 5, // amount of products by SKU_NUMBER
    "SKU_NUMBER_2": 20 // amount of products by SKU_NUMBER2
  }
}
```

#### Sample response
```javascript
{
  "skuAndPrice": {
    "SKU_NUMBER": 170.0, // amount to be paid by SKU_NUMBER
    "SKU_NUMBER_2": 680.0 // amount to be paid by SKU_NUMBER2
  }
}
```
