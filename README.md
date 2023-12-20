# Telegram cyberdefender moderator

This bot is a moderator for chats group to spot harassment 
done to anyone present in the group.

The criteria involve 5 classes:
    - Not offensive
    - Not offensive but it contains expletive words
    - Offensive general
    - Offensive personal
    - Grooming personal

## Setting up the bot

Create a config file with name config.properties

```.env
BOT_TOKEN=your-bot-token
URL_FILTER=http://your.path.to.the.filter.com/some_url
```

## Running
```bash
mvn dependency:resolve
mvn clean package
java -jar target/Telegram-cyber..-with-dependecies.jar
```