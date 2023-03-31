# What's the Prompt?

In this game, you must guess what prompt was used for DALL-E to generate the image. It's a simple yet fun game.

## Index

* [Important Links](#important-links)
* [How it works](#how-it-works)
* [Technologies Used](#technologies-used)
* [How to use the API](#how-to-use-the-api)
* [How to use the Site](#how-to-use-the-site)

## Important Links

* [Site]()
* [API]()
* [Swagger]()
* [Postman]()

## Technologies Used

* **OpenAI API**: generate the prompts.
* **DALL-E**: generate the images.
* **Spring Boot**: create the API.
* **Angular**: create the website.

## How to use the API

### 1 - Creating a game

To start, you must make a POST request to the `/game/create` endpoint with the following format:

* **Param**: `language` with the value `PT_BR` or `EN_US` for the language you wish to play in.
* **Body**: JSON with the following format:

```json
{
  "nickname": "your-nickname"
}
```

### 2 - Game result

After creating the game, you will receive a JSON with the following format:

```json
{
  "game_id": "game-id",
  "image_url": "image-url"
}
```

### 3 - Sending the answer

To send the answer, you must make a POST request to the `/game/answer` endpoint with the following body:

```json
{
  "game_id": "game-id",
  "answer": "your-answer"
}
``` 

### 4 - Answer result

After sending the answer, you will receive a JSON with the following format:

```json
{
  "image_url": "image-url",
  "correct_answer": "correct-answer",
  "user_answer": "your-answer",
  "score": "score"
}
```

### 5 - Ranking

To see the ranking, you must make a GET request to the `/game/ranking` endpoint.

You can pass the following parameters:

* **page**: the page number you wish to see. By default, it is 0.
* **size**: the number of players you wish to see per page. By default, it is 25.
* **language**: the language you wish to see. By default, you will see the overall ranking. You can pass `PT_BR` or `EN_US`.
* **date**: the date you wish to see. By default, you will see the overall ranking. You can pass `LAST_WEEK`, `LAST_MONTH`, or `LAST_YEAR`.

## How to use the Site

### 1 - Creating a game

To start, you should click on the "Create game" button and fill out the form with your nickname and the language you wish to play in.

### 2 - Game result

After creating the game, you will see the image that you must guess the prompt for.

### 3 - Submitting the answer

To submit your answer, you should fill out the text field with your answer and click on the "Submit answer" button.

### 4 - Answer result

After submitting your answer, you will see the image that you must guess the prompt for, the correct answer, your answer, and your score.