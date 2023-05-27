insert into category(id, name, img_url) values
                                            (1, 'Maści', '../img/categories/maść.jpg'),
                                            (2, 'Szczepionki', '../img/categories/szczepionka.png'),
                                            (3, 'Tabletki', '../img/categories/tabletka.jpg'),
                                            (4, 'Kremy', '../img/categories/krem.png'),
                                            (5, 'Mydła','../img/categories/mydło.jpg'),
                                            (6, 'Syropy','../img/categories/syrop.png');

insert into product (id, producer, name, amount, price, category_id) values
                                                                         (1, 'Pfizer', 'Szczepionka', 50, 300, 2),
                                                                         (2, 'Polfarma', 'Maść na ból głowy', 30, 15, 1);

insert into assign (id, sum_price, finish_time) values
                                                    (1, 330.0, '2022-10-08 15:00:00'),
                                                    (2, 600.0, '2022-10-08 15:00:00');

insert into assign_product (assign_id, product_id) values
                                                       (1, 1),
                                                       (1, 2),
                                                       (2 ,1),
                                                       (2, 1);
