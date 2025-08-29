# DataBase Schema

create database javacartdb;

use javacartdb;

create table products (
	id int primary key,
    name varchar(30) not null,
    category varchar(20),
    price double not null,
    stock int not null
);

insert into products (id, name, category, price, stock) values
(1, 'Laptop', 'Electronics', 55000.00, 10),
(2, 'Smartphone', 'Electronics', 25000.00, 20),
(3, 'Headphones', 'Accessories', 2000.00, 50),
(4, 'Washing Machine', 'Home Appliances', 32000.00, 5),
(5, 'Refrigerator', 'Home Appliances', 45000.00, 7),
(6, 'Microwave Oven', 'Home Appliances', 12000.00, 12),
(7, 'Office Chair', 'Furniture', 8000.00, 15),
(8, 'Running Shoes', 'Sports', 3000.00, 25),
(9, 'Novel - The Alchemist', 'Books', 499.00, 30),
(10, 'T-Shirt', 'Clothing', 799.00, 40);

select * from products;

# Compiling And Executing The Program
Compiling the code:-
javac -cp .;<Your Drive>:\JavaCart\lib\mysql-connector-j-9.4.0.jar database\Database.java models\Product.java JavaCartApp.java

Executing the code:-
java -cp .;<Your Drive>:\JavaCart\lib\mysql-connector-j-9.4.0.jar JavaCartApp
