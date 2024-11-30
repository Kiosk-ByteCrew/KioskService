# KioskService

This is the Main BE service, containing all the core business logic from order placement to inventory management

Steps to setup this service in your local:
1. Clone this repo to your local working directory.
2. To your application configuration Add VM options as `-Dserver.port=8081` (or any port of your choice).
3. Add environment variable with `spring.datasource.url=jdbc:mysql://localhost:3306/kiosk_database;username=your_username;password=your_password`.

Database name: `kiosk_database`

After setting up the service, you can try hitting the health api. 
HealthApi URL: `http://{your_localhost}/kiosk/api/health`

It should return a response like:
`{
  "status": "UP"
}`

Would need to setup basic database as well in your local machine:

Here's the basic schema used, which i think will suffice for time being:

`CREATE TABLE restaurants (
id INT PRIMARY KEY,
tenantId INT NOT NULL,
name VARCHAR(255) NOT NULL
);`

`CREATE TABLE users (
id INT PRIMARY KEY,
userName VARCHAR(255) NOT NULL,
userDetails JSON
);`

`CREATE TABLE orders (
id INT PRIMARY KEY,
userId INT NOT NULL,
restaurantId INT NOT NULL,
totalAmount DECIMAL(10, 2) NOT NULL,
orderDetailsId INT NOT NULL,
FOREIGN KEY (userId) REFERENCES users(id),
FOREIGN KEY (restaurantId) REFERENCES restaurants(id)
);`