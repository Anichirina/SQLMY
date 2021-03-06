# SQLMY
## Задача - Скоро deadline
Случилось то, что обычно случается ближе к дедлайну: никто ничего не успевает и винит во всём остальных других.

Разработчикам не особо до вас ("им ведь нужно пилить новые фичи"), поэтому они подготовили для вас сборку, работающую с СУБД и даже приложили схему БД (см. файл schema.sql),
но при этом сказали "остальное вам нужно сделать самим, там не сложно" 😈.

### Что вам нужно сделать:

- Внимательно изучить схему
- Создать Docker Container на базе MySQL 8 (прописать создание БД, пользователя, пароля)
- Запустить SUT (app-deadline.jar): для указания параметров подключения к БД можно использовать:
1. либо переменные окружения DB_URL, DB_USER, DB_PASS
1. либо указать их через флаги командной строки при запуске: -P:jdbc.url=..., -P:jdbc.user=..., -P:jdbc.password=... (внимание: при запуске флаги не нужно указывать через запятую!). Данное приложение не использует файл application.properties в качестве конфигурации, конфигурационный файл находится внутри jar архива.
1. либо можете схитрить и попробовать подобрать значения, зашитые в саму SUT
### А дальше выясняется куча забавных вещей 😈. 
- Проблема первая: SUT не стартует
- Проблема вторая: SUT валится при повторном перезапуске
- Проблема третья (опциональна): пароли
Если вы добрались до этого шага и всё-таки успешно запустили SUT, то вы уже герой!

Но теперь выяснилась следующая забавная информация: разработчики фронтенда поругались с разработчиками бэкенда и вы можете протестировать только "Вход в систему".

Внимательно посмотрите, как и куда сохраняются коды генерации в СУБД и напишите тест, который взяв информацию из БД о сгенерированном коде позволит вам протестировать
"Вход в систему" через веб-интерфейс.

P.S. Неплохо бы ещё проверить, что при трёхкратном неверном вводе пароля система блокируется.

Итого в результате у вас должно получиться:

- docker-compose.yml*
- app-deadline.jar
- schema.sql
- код ваших авто-тестов
Если ваша система не поддерживает Docker, то вам придётся (к сожалению) вручную установить MySQL на свой компьютер и отрабатывать тесты уже на ней. В этом случае положите
в репозиторий файлик README.md, в котором опишите последовательность действий (со скриншотами) для установки сервера MySQL и загрузки в него файла schema.sql.
