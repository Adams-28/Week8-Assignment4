# Week 8 Assignment 4: Handling Request Parameters and Path Variables

## API Endpoints

| HTTP Method | Endpoint | Description |
|---|---|---|
| GET | `/api/products/{id}/category` | Retrieves the category of a specific product |
| GET | `/api/products/search` | Searches for products by category and maximum price |
| GET | `/api/customers/{customerId}/orders/{orderId}` | Retrieves a specific order belonging to a customer |
| PATCH | `/api/products/{id}` | Partially updates an existing product |

## Endpoint Parameters (Endpoint testing screenshots will be uploaded to the LMS as a Word document)

**Endpoint:** `GET /api/products/{id}/category`

Retrieves the category of a product using its ID

### Parameters

| Parameter | Type | Required | Description |
|---|---|---|---|
| `id` | Long | Yes | Product ID (path variable) |
| `uppercase` | boolean | No | Optional query parameter; defaults to `false` |

### Postman Request

**Method:** GET

**URL:**

```text
http://localhost:8080/api/products/1/category
```

### Combining Path Variables and Query Parameters

Added the optional "uppercase" parameter which changes the category to all uppercase to demonstration combining path variables and request parameters.

**Method:** GET

**URL:**

```text
http://localhost:8080/api/products/1/category?uppercase=true
```

## 2. Search Products by Category and Maximum Price

**Endpoint:** `GET /api/products/search`

Searches for products using query parameters. The `category` parameter is required, while `maxPrice` is optional.

### Parameters

| Parameter | Type | Required | Description |
|---|---|---|---|
| `category` | String | Yes | Category used to filter products |
| `maxPrice` | double | No | Maximum product price; defaults to `10` |

### Postman Request

**Method:** GET

**URL:**

```text
http://localhost:8080/api/products/search?category=Chips&maxPrice=4
```

If no max is specififed then the default maximum price of `10` is used.

## 3. Retrieve a Customer's Order

**Endpoint:** `GET /api/customers/{customerId}/orders/{orderId}`

Retrieves a specific order belonging to a specific customer.

### Parameters

| Parameter | Type | Required | Description |
|---|---|---|---|
| `customerId` | Long | Yes | Customer ID (path variable) |
| `orderId` | Long | Yes | Order ID (path variable) |

### Postman Request

**Method:** GET

**URL:**

```text
http://localhost:8080/api/customers/1/orders/1
```


## 4. Partially Update a Product

**Endpoint:** `PATCH /api/products/{id}`

Updates selected fields of an existing product without replacing the entire product.

This endpoint accepts a JSON request body using `@RequestBody`.

### Parameters

| Parameter | Type | Required | Description |
|---|---|---|---|
| `id` | Long | Yes | Product ID (path variable) |
| `name` | String | No | Updated product name (JSON body) |
| `category` | String | No | Updated product category (JSON body) |
| `price` | Double | No | Updated product price (JSON body) |

### Postman Request

**Method:** PATCH

**URL:**

```text
http://localhost:8080/api/products/1
```


In the example I provided in the Word document, I was able to only update the price field for a product
