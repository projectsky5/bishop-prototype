ДЗ №3 T1 Иннотех

---

Стартер - https://github.com/projectsky5/synthetic-human-core-starter

В демонстрационном проекте реализовано:
  1. GET localhost:8080/api/v1/android - Для проверка аудита (см. п.3)
  2. POST localhost:8080/api/v1/commands - Для проверки работы с командами и очередью
  3. GET localhost:8080/actuator - базовый вызов actuator
  4. GET localhost:8080/actuator/metrics - Просмотр всех метрик 
  5. GET localhost:8080/actuator/metrics/synthetic.command.completed.total?tag=author:имя автора - Просмотр кол-во обработанных команд автором (Counter)
  6. GET localhost:8080/actuator/metrics/synthetic.command.queue.size - Просмотр заполненности очереди (Gauge)
  7. Kafka Consumer с настройкой через application.yml
  8. Использование аннотации из стартера (AndroidServiceImpl)
  9. Использование обработчика исключений из стартера путем наследования
  10. Подключение стартера в pom.xml

---

**Инструкция для запуска**

1. Настройка проекта
  1. Запустить Kafka: ```docker compose up -d```
  2. Настроить application.yml

Настройка application.yml:
  1. Конфигурация Kafka через spring (spring.kafka.*)
  2. Включение стартера (synthetic.human.core.enabled: true)
  3. Конфигурация стартера:
     1. command: max-pool-size, core-pool-size, queue-capacity, keep-alive-seconds, назначения описаны с помощью JavaDoc + configuration processor
     2. audit: mode: (kafka/console), kafka.topic - указание топика Kafka

---

**Инструкция для тестирования**

***1. Проверка аудита***

1. Выбрать режим в application.yml - synthetic.human.audit.mode: console
2. Вызвать GET localhost:8080/api/v1/android
3. Выбрать режим в application.yml - synthetic.human.audit.mode: kafka
4. Вызвать GET localhost:8080/api/v1/android

---

***2. Проверка выполнения команды***

1. Вызвать POST localhost:8080/api/v1/commands с моделью с приоритетом COMMON вида:
```
{
  "description": "Проверить энергоузел",
  "priority": "COMMON",
  "author": "John Doe",
  "time": "2025-07-19T15:00:00"
}
```

2. Вызвать POST localhost:8080/api/v1/commands с моделью с приоритетом CRITICAL вида:
```
{
  "description": "Проверить энергоузел",
  "priority": "CRITICAL",
  "author": "John Doe",
  "time": "2025-07-19T15:00:00"
}
```

---

***3. Проверка обработки исключений***

1.  Вызвать POST localhost:8080/api/v1/commands с моделью с приоритетом CRITICAL вида:
```
{
  "description": "",
  "priority": "CRITICAL",
  "author": "John Doe",
  "time": "2025-7-19T1:00:00"
}
```
Значения в модели можно менять для проверки работы валидации

2. Установить в synthetic.human.core.command.queue-capacity: 1
3. Быстро вызвать несколько POST localhost:8080/api/v1/commands с моделью вида:
```
{
  "description": "description",
  "priority": "CRITICAL",
  "author": "John Doe",
  "time": "2025-07-19T15:00:00"
}
```
В результате будет возвращен ответ сервера с 409 Conflict и описанием ошибки

---

***4. Проверка работы метрик***

1. Вызвать несколько POST localhost:8080/api/v1/commands с моделью вида:
```
{
  "description": "description",
  "priority": "CRITICAL",
  "author": "John Doe",
  "time": "2025-07-19T15:00:00"
}
```
Автор должен быть одним и тем же

2. Вызвать GET localhost:8080/actuator/metrics/synthetic.command.completed.total?tag=author:имя автора

3. Установить в synthetic.human.core.command.queue-capacity: 30
4. Быстро вызвать несколько POST localhost:8080/api/v1/commands с моделью вида:
```
{
  "description": "description",
  "priority": "CRITICAL",
  "author": "John Doe",
  "time": "2025-07-19T15:00:00"
}
```
5. Вызвать GET localhost:8080/actuator/metrics/synthetic.command.queue.size



      
