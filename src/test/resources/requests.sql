REPLACE INTO users (id, login, password, status)
VALUES (1, 'vasya', 'qwerty123', 'active'),
       (2, 'petya', 'password', 'incorect');

REPLACE INTO users (id, login, password, status)
VALUES (2, 'petya', 'password', 'incorect');

REPLACE INTO cards (id, user_id, number, balance_in_kopecks)
VALUES (1, 1, '5559000000000001', 1000000),
       (2, 1, '5559000000000002', 1000000);

REPLACE INTO card_transactions (source, target, amount_in_kopecks, created)
VALUES ('5559000000000001', '5559000000000002', 10000, 5000);

UPDATE cards
SET balance_in_kopecks = balance_in_kopecks - 10000
WHERE number = '5559000000000001';

UPDATE cards
SET balance_in_kopecks = balance_in_kopecks + 10000
WHERE number = '5559000000000002';

DELETE
FROM auth_codes
WHERE created < NOW() - INTERVAL 5 MINUTE;

-- выборка всех столбцов и всех строк из таблицы users (будьте осторожны на больших таблицах)
SELECT *
FROM users;
-- выборка только определённых столбцов
SELECT id, login
FROM users;
-- выборка по условию
SELECT balance_in_kopecks
FROM cards
WHERE number = '5559000000000002';
-- вычисляемые столбцы
SELECT balance_in_kopecks / 100 AS balance_in_rub
FROM cards
WHERE number = '5559000000000002';

SELECT max(cards.balance_in_kopecks)
FROM cards;

SELECT min(cards.balance_in_kopecks)
FROM cards;

SELECT sum(balance_in_kopecks)
FROM cards
WHERE user_id = 1;

SELECT count(*), user_id
FROM cards
GROUP BY user_id;

SELECT *
FROM cards;

SELECT *
FROM card_transactions;

SELECT *
FROM auth_codes;

SELECT *
FROM users;

DELETE
FROM users;

SELECT status
FROM users
WHERE login;

DELETE
FROM cards;

DELETE
from app.users
