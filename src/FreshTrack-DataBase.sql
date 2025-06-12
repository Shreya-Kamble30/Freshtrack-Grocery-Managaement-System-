create database freshtrack;
use freshtrack;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

insert into users value(01,"ShreyaKamble30@gmail.com","Shreya","Java");
insert into users value(02,"UtkarshDeotale27@gmail.com","Utkarsh","Gym");
insert into users value(03,"MayurSurvase18@gmail.com","Mayur","MC-Stan");
insert into users value(04,"SurajSurve08@gmail.com","Suraj","PHP");
insert into users value(05,"admin@gmail.com","Admin","adminpanel");
insert into users value(06,"RohanSonawane13@gmail.com","Rohan","Tavan");
select * from users;

CREATE TABLE category (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(255) NOT NULL
);

INSERT INTO category (category_name) VALUES 
('Dairy, Bread and Eggs'),
('Fruits'),
('Vegetables'),
('Juices and Soft Drinks'),
('Snacks and Munchies'),
('Breakfast and Instant Food'),
('Sweet Tooth'),
('Bakery and Biscuits'),
('Tea, Coffee and Health Drink'),
('Atta, Rice and Dal'),
('Masala, Oil and More'),
('Sauces and Spread'),
('Chicken, Meat and Fish'),
('Baby Care'),
('Cleaning Essentials'),
('Personal Care'),
('Pet Care');

select * from category;

CREATE TABLE products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    image_url VARCHAR(255),
    category_id INT,  
    FOREIGN KEY (category_id) REFERENCES category(category_id)
);

select * from products;


