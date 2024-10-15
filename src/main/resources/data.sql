INSERT INTO brand values (1, 'Zara');
INSERT INTO product values(35455,'Lorem Ipsum');

INSERT INTO PRICE(id,currency, start_date,end_date, price_list, priority, "value",brand_product_id, product_entity_id)
VALUES(1000, 0, '2020-06-14 00:00:00','2020-12-31 23:59:59', 1, 0, 35.50, 1,35455),
(1001, 0, '2020-06-14 15:00:00','2020-06-14 18:30:00', 2, 1, 25.45, 1,35455),
(1002, 0, '2020-06-15 00:00:00','2020-06-15 11:00:00', 3, 1, 30.50, 1,35455),
(1003, 0, '2020-06-15 16:00:00','2020-12-31 23:59:59', 4, 1, 38.95, 1,35455);

