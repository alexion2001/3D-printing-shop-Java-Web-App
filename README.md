# 3D Printing Shop
Final project for the Java Web Programming course

### Application info

This application aims to facilitate the management of a 3D printing shop, by allowing you to add and query data about employees,customers, products or printers.

### Business requirements

1. The application must allow the enrollment and management of clients.
2. The application must allow the enrollment and management of employees.
3. The application must allow stock management for filaments: it must be possible to manage the stock for each type of filament.
4. The application must allow the management of products: must know all the data necessary for the sale as well as the production of a product.
5. The application must allow the management of printers: allowing the management of compatibility between products and printers to determine whether a product can be printed on a certain printer, depending on the type of filament it is made of.
6. The application must allow adding and managing orders: we need to know the customer for each order and the products that are part of it.
7. The application must allow viewing the status of a printer in real time, depending on whether it is busy or not.
8. The application must allow the visualization of all actions performed by employees.
9. The application must allow viewing in real time the status of each product in an order and the estimated time for its delivery.
10. The application must allow the management of data regarding the status of payments made by the customer for all orders placed.

### Features for the MVP phase.
1. The application allows adding, editing, deleting and viewing customer data.
2. The application allows adding, editing, deleting and viewing employees data. 
3. The application allows adding, editing, deleting and viewing filament data to be able to manage stocks.
4. The application allows adding, editing, deleting and viewing products data necessary for the sale and production (the name of the filament from which it is made).
5. The application allows adding, editing, deleting and viewing printers data, allowing the management of compatibility between products and printers to determine whether a product can be printed on a certain printer, depending on the type of filament it is made of.
6. The application allows adding and viewing orders data together with the client and the products that are part of it. You can also filter an order by customer or date.

### Entities and relationships

1. Product - can be part of an order and it is made of a type of filament
2. Filament - has compatibility with at least one printer 
3. Printer - has compatibility with at least one filament
4. Employee
5. Order -  has one customer and at least one product
6. Customer - may place orders

Entity-relationship diagram:

![Untitled Diagram drawio](https://github.com/alexion2001/3D-printing-shop-Java-Web-App/assets/96074975/5e6d811b-d18a-4838-a0fa-42aca0390d56)


