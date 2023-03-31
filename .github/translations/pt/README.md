# Qual é o Prompt?

Nesse jogo você deve advinhar qual é o prompt que foi necessário para o DALL-E gerar a imagem. É um jogo simples, mas divertido.

## Índice

* [Links importantes](#links-importantes)
* [Como funciona](#como-funciona)
* [Técnologias utilizadas](#técnologias-utilizadas)
* [Como usar a API](#como-usar-a-api)
* [Como usar o Site](#como-usar-o-site)


## Links importantes

* [Site]()
* [API]()
* [Swagger]()
* [Postman]()

## Técnologias utilizadas
* **OpenAI API**: gerar os prompts.
* **DALL-E**: gerar as imagens.
* **Spring Boot**: criar a API.
* **Angular**: criar o site.

## Como usar a API

### 1 — Criando um jogo

Para começar, você deve fazer uma requisição POST para o endpoint `/game/create` com o seguinte formato:

* **Param**: `language` com o valor `PT_BR` ou `EN_US` para o idioma que você deseja jogar.
* **Body**: JSON com o seguinte formato: 
```json
{
    "nickname": "seu-nickname"
}
```

### 2 — Resultado do jogo

Após criar o jogo, você receberá um JSON com o seguinte formato:

```json
{
    "game_id": "id-do-jogo",
    "image_url": "url-da-imagem"
}
```

### 3 — Enviando a resposta

Para enviar a resposta, você deve fazer uma requisição POST para o endpoint `/game/answer` com o seguinte body:

```json
{
    "game_id": "id-do-jogo",
    "answer": "sua-resposta"
}
```

### 4 — Resultado da resposta

Após enviar a resposta, você receberá um JSON com o seguinte formato:

```json
{
    "image_url": "url-da-imagem",
    "correct_answer": "resposta-correta",
    "user_answer": "sua-resposta",
    "score": "pontuação"
}
```

### 5 — Ranking

Para ver o ranking, você deve fazer uma requisição GET para o endpoint `/game/ranking`.

Você pode passar os seguintes parâmetros:

* **page**: número da página que você deseja ver. Por padrão, é 0.
* **size**: número de jogadores que você deseja ver por página. Por padrão, é 25.
* **language**: idioma que você deseja ver. Por padrão, você verá o ranking geral. Você pode passar `PT_BR` ou `EN_US`.
* **date**: data que você deseja ver. Por padrão, você verá o ranking geral. Você pode passar `LAST_WEEK`, `LAST_MONTH` ou `LAST_YEAR`.

## Como usar o site

### 1 — Criando um jogo

Para começar, você deve clicar no botão "Criar jogo" e preencher o formulário com o seu nickname e o idioma que você deseja jogar.

### 2 — Resultado do jogo

Após criar o jogo, você verá a imagem que você deve advinhar o prompt.

### 3 — Enviando a resposta

Para enviar a resposta, você deve preencher o campo de texto com a sua resposta e clicar no botão "Enviar resposta".

### 4 — Resultado da resposta

Após enviar a resposta, você verá a imagem que você deve advinhar o prompt, a resposta correta, a sua resposta e a sua pontuação.
