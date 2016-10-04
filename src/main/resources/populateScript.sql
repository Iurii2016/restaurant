insert into positions (id, name) values(1, 'accountant');
insert into positions (id, name) values(2, 'administrator');
insert into positions (id, name) values(3, 'waiter');
insert into positions (id, name) values(4, 'cook');
insert into positions (id, name) values(5, 'security guard');

insert into employees (id, surname, name, birthday, phone_number, salary, position_id) values(1, 'Ostapenko', 'Olga', '1988-07-27', '+380660000101', 10000.0, 2);
insert into employees (id, surname, name, birthday, phone_number, salary, position_id) values(2, 'Bondarenko', 'Dima', '1986-05-20', '+380670000101', 5000.0, 5);
insert into employees (id, surname, name, birthday, phone_number, salary, position_id) values(3, 'Kondratenko', 'Igor', '1982-06-12', '+380670010101', 5000.0, 5);
insert into employees (id, surname, name, birthday, phone_number, salary, position_id) values(4, 'Novikova', 'Tania', '1990-02-02', '+380970110101', 5000.0, 3);
insert into employees (id, surname, name, birthday, phone_number, salary, position_id) values(5, 'Ivanenko', 'Irina', '1992-01-02', '+380971110101', 5000.0, 3);
insert into employees (id, surname, name, birthday, phone_number, salary, position_id) values(6, 'Igorenko', 'Oleg', '1993-08-18', '+380660010101', 5000.0, 3);
insert into employees (id, surname, name, birthday, phone_number, salary, position_id) values(7, 'Ivanova', 'Elena', '1980-01-19', '+380661010101', 11000.0, 1);
insert into employees (id, surname, name, birthday, phone_number, salary, position_id) values(8, 'Bondar', 'Oleg', '1986-01-10', '+380661010102', 15000.0, 4);
insert into employees (id, surname, name, birthday, phone_number, salary, position_id) values(9, 'Golubeva', 'Olga', '1989-01-19', '+380661010103', 8000.0, 4);
insert into employees (id, surname, name, birthday, phone_number, salary, position_id) values(10, 'Bilous', 'Artem', '1992-03-06', '+380661010104', 6000.0, 4);

insert into ingredients (id, ingredient) values(1, 'salt');
insert into ingredients (id, ingredient) values(2, 'pepper');
insert into ingredients (id, ingredient) values(3, 'sugar');
insert into ingredients (id, ingredient) values(4, 'potato');
insert into ingredients (id, ingredient) values(5, 'pork');
insert into ingredients (id, ingredient) values(6, 'veal');
insert into ingredients (id, ingredient) values(7, 'rice');
insert into ingredients (id, ingredient) values(8, 'carp');
insert into ingredients (id, ingredient) values(9, 'trout');
insert into ingredients (id, ingredient) values(10, 'lemon');
insert into ingredients (id, ingredient) values(11, 'onion');
insert into ingredients (id, ingredient) values(12, 'tea');
insert into ingredients (id, ingredient) values(13, 'juice');

insert into warehouse(id, ingredient_id, quantity, unit) values(1, 1, 3.0, 'kg');
insert into warehouse(id, ingredient_id, quantity, unit) values(2, 2, 0.5, 'kg');
insert into warehouse(id, ingredient_id, quantity, unit) values(3, 3, 2, 'kg');
insert into warehouse(id, ingredient_id, quantity, unit) values(4, 4, 10, 'kg');
insert into warehouse(id, ingredient_id, quantity, unit) values(5, 5, 3, 'kg');
insert into warehouse(id, ingredient_id, quantity, unit) values(6, 6, 3, 'kg');
insert into warehouse(id, ingredient_id, quantity, unit) values(7, 7, 4, 'kg');
insert into warehouse(id, ingredient_id, quantity, unit) values(8, 8, 4, 'kg');
insert into warehouse(id, ingredient_id, quantity, unit) values(9, 9, 1, 'kg');
insert into warehouse(id, ingredient_id, quantity, unit) values(10, 10, 1, 'kg');
insert into warehouse(id, ingredient_id, quantity, unit) values(11, 11, 5, 'kg');
insert into warehouse(id, ingredient_id, quantity, unit) values(12, 12, 0.5, 'kg');
insert into warehouse(id, ingredient_id, quantity, unit) values(13, 13, 5, 'l');

insert into categories(id, name) values(1, 'first dishes');
insert into categories(id, name) values(2, 'main dishes');
insert into categories(id, name) values(3, 'garnishes');
insert into categories(id, name) values(4, 'drinks');
insert into categories(id, name) values(5, 'cutting');
insert into categories(id, name) values(6, 'salad');

insert into dishes(id, name, category_id, price, weight) values(1, 'Rice soup', 1, 17.0, 200);
insert into dishes(id, name, category_id, price, weight) values(2, 'rice', 3, 15.0, 250);
insert into dishes(id, name, category_id, price, weight) values(3, 'puree', 3, 11.5, 250);
insert into dishes(id, name, category_id, price, weight) values(4, 'pork chop', 2, 25, 300);
insert into dishes(id, name, category_id, price, weight) values(5, 'veal chop', 2, 30, 300);
insert into dishes(id, name, category_id, price, weight) values(6, 'trout', 5, 50, 100);
insert into dishes(id, name, category_id, price, weight) values(7, 'tea', 4, 15, 200);
insert into dishes(id, name, category_id, price, weight) values(8, 'juice', 4, 25, 250);

insert into dish_ingredients(dish_id, ingredient_id, quantity, unit) values(1, 1, 10, 'g');
insert into dish_ingredients(dish_id, ingredient_id, quantity, unit) values(1, 2, 20, 'g');
insert into dish_ingredients(dish_id, ingredient_id, quantity, unit) values(1, 4, 30, 'g');
insert into dish_ingredients(dish_id, ingredient_id, quantity, unit) values(1, 5, 5, 'g');
insert into dish_ingredients(dish_id, ingredient_id, quantity, unit) values(1, 7, 20, 'g');
insert into dish_ingredients(dish_id, ingredient_id, quantity, unit) values(1, 11, 45, 'g');
insert into dish_ingredients(dish_id, ingredient_id, quantity, unit) values(2, 7, 15, 'g');
insert into dish_ingredients(dish_id, ingredient_id, quantity, unit) values(2, 1, 6, 'g');
insert into dish_ingredients(dish_id, ingredient_id, quantity, unit) values(3, 4, 1, 'g');
insert into dish_ingredients(dish_id, ingredient_id, quantity, unit) values(3, 1, 3, 'g');
insert into dish_ingredients(dish_id, ingredient_id, quantity, unit) values(4, 1, 43, 'g');
insert into dish_ingredients(dish_id, ingredient_id, quantity, unit) values(4, 2, 62, 'g');
insert into dish_ingredients(dish_id, ingredient_id, quantity, unit) values(4, 5, 22, 'g');
insert into dish_ingredients(dish_id, ingredient_id, quantity, unit) values(5, 1, 17, 'g');
insert into dish_ingredients(dish_id, ingredient_id, quantity, unit) values(5, 2, 56, 'g');
insert into dish_ingredients(dish_id, ingredient_id, quantity, unit) values(5, 6, 34, 'g');
insert into dish_ingredients(dish_id, ingredient_id, quantity, unit) values(6, 1, 22, 'g');
insert into dish_ingredients(dish_id, ingredient_id, quantity, unit) values(6, 6, 15, 'g');
insert into dish_ingredients(dish_id, ingredient_id, quantity, unit) values(7, 12, 5, 'g');
insert into dish_ingredients(dish_id, ingredient_id, quantity, unit) values(8, 13, 200, 'l');

insert into menuname(id, name) values(1, 'Hot dishes');
insert into menuname(id, name) values(2, 'Cold dishes');
insert into menuname(id, name) values(3, 'Drinks');

insert into menu(id, menuname_id, dish_id) values(1, 1, 1);
insert into menu(id, menuname_id, dish_id) values(2, 1, 2);
insert into menu(id, menuname_id, dish_id) values(3, 1, 3);
insert into menu(id, menuname_id, dish_id) values(4, 1, 4);
insert into menu(id, menuname_id, dish_id) values(5, 1, 5);
insert into menu(id, menuname_id, dish_id) values(6, 2, 6);
insert into menu(id, menuname_id, dish_id) values(7, 3, 7);
insert into menu(id, menuname_id, dish_id) values(8, 3, 8);

insert into orders(id, waiter_id, table_number, date, status) values(1, 4, 1, '2016-07-25', 'opened');
insert into orders(id, waiter_id, table_number, date, status) values(2, 5, 3, '2016-07-25', 'opened');
insert into orders(id, waiter_id, table_number, date, status) values(3, 6, 4, '2016-07-25', 'opened');
insert into orders(id, waiter_id, table_number, date, status) values(4, 4, 2, '2016-07-25', 'opened');

insert into dish_to_order(order_id, dish_id) values(1,1);
insert into dish_to_order(order_id, dish_id) values(2,7);
insert into dish_to_order(order_id, dish_id) values(3,8);
insert into dish_to_order(order_id, dish_id) values(4,3);
insert into dish_to_order(order_id, dish_id) values(4,4);

insert into dishcooking(id, dish_id, cook_id, order_id, date) values(1, 1, 10, 1, '2016-07-25');
insert into dishcooking(id, dish_id, cook_id, order_id, date) values(2, 7, 10, 2, '2016-07-25');
insert into dishcooking(id, dish_id, cook_id, order_id, date) values(3, 8, 9, 3, '2016-07-25');
insert into dishcooking(id, dish_id, cook_id, order_id, date) values(4, 3, 8, 4, '2016-07-25');
insert into dishcooking(id, dish_id, cook_id, order_id, date) values(5, 4, 8, 4, '2016-07-25');