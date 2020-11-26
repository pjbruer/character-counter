## A simple REST api to find and count words that all share their first character.
#### Prerequisites
- Maven

```
git clone https://github.com/pjbruer/character-counter.git

cd character-counter/

./mvnw mvn clean install

./mvnw mvn test

./mvnw spring-boot:run
```

### Endpoints available
#### Words
```
/findWordsParams: (@RequestParameters)
    http: POST  
    endpoint: http://localhost:8080/api/findWordsParams/ 
    param1: "text": "abba rosor apa sms aha bob"
    param2: "character": "a"

/findWordsBody: (@RequestBody)
    http: POST
    endpoint: http://localhost:8080/api/findWordsBody/
    body: {"text": "abba rosor apa sms aha bob", "character": "a"}

```
