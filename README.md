# IVENTO
Ivento is a inventory management application where user able to tracking inventory levels, orders sales and deliveries.

Feel free to visit the live application
<br>
https://invento-oculuszzz.netlify.app


# Tools & Libraries
* Java 17 or above
* Spring boot - V 3.0.4
* PgAdmin4 (PostgreSQL) - Database


# Externalized Configuration

Feel free to modify/change the configuration for application properties or DB configuration which located at
<br>
/ivento/src/main/resources/application.properties

OR create specific application properties
<br>
https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config

Open http://localhost:8091 to view it in the browser.

The page will reload if you make edits.

# Definition Tables 

<br /><br /> ![images/Ivento entitites diagram.png](/images/Ivento%20entitites%20diagram.png) <br /><br />

## 1. USERS Table
| Coloumn | Description |
| --- | --- |
| id | User Id |
| username | User username |
| email | User Email |
| image | User Image |
| role | User role |
| password | User password |
| is_blocked | User is blocked or not |
| last_logged_in | User last login (Timestamp format) |
| last_updated | User last update (Timestamp format) |

## 2. JWT_TOKEN Table
| Coloumn | Description |
| --- | --- |
| id | JWT token id |
| access_token | Access token of user |
| refresh_token | Refresh token of user |
| last_update | Last update of JWT token (Timestamp format) |
| user_id | Foreign key of user id from USERS table |

## 3. PRODUCTS Table
| Coloumn | Description |
| --- | --- |
| id | Product Id |
| brand | Product brand |
| name | Product name |
| product_code | Product code |
| price | Product price per-unit |
| quantity | Total quantity of product in stock |
| last_update | Product last update (Timestamp format) |

## 4. CUSTOMER_ORDER Table
| Coloumn | Description |
| --- | --- |
| id | Customer order Id |
| name | Customer full name |
| phone_number | Customer phone number |
| address | Customer address |
| company_name | Customer company name |
| company_address | Customer company address |
| total_price | Total price order |
| status | Status order |
| last_update | Customer order last update (Timestamp format) |

## 5. ORDERED_PRODUCT Table
| Coloumn | Description |
| --- | --- |
| id | Ordered product id |
| brand | Ordered product brand |
| name | Ordered product name |
| product_code | Ordered product code |
| price | Ordered product price per-unit |
| quantity | Total quantity of ordered product |
| total_price | Ordered product total price |
| product_id | Foreign key of product id from PRODUCTS table |

## 6. CUSTOMER_ORDERED_PRODUCT Table
| Coloumn | Description |
| --- | --- |
| customer_order_id | Foreign key of customer order id from CUSTOMER_ORDER table |
| product_id | Foreign key of ordered product id from ORDERED_PRODUCT table |

# Related notes

Spring Security <br>
https://docs.spring.io/spring-security/reference/index.html

Spring Data <br>
https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

