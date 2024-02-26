# TaPago
API do projeto TaPago - Controle de Gastos Pessoais

## Tarefas

- [ ] CRUD de Categorias
- [ ] CRUD de Movimentações
- [ ] CRUD de Usuarios
- [ ] Dashboard

## Documentação da API

### Endpoints
- [Listar Todas as Categorias](#listar-todas-as-categorias)

- [Cadastrar Categoria](#cadastrar-categoria)
- [Detalhes da Categoria](#detalhes-da-categoria)
- [Apagar Categoria](#apagar-categoria)
- [Atualizar Categoria](#atualizar-categoria)

### Listar todas as categorias

`GET` /categoria

Retorna um array com todas as categorias cadastradas.

#### Exemplo de Resposta

```js
[
    {
        "id": 1,
        "nome": "Alimentação",
        "icone" : "fast-food"

    }
]
```

#### Códigos de Status

|código|descrição|
|------|---------|
|200|Os dados das categorias foram retornados com sucesso
|401|Acesso negado. Você deve se autenticar

---

### Cadastrar Categoria

`POST` /categoria

Cria uma nova categoria com os dados enivados no corpo da requisição

#### Corpo da requisição

|campo|tipo|obrigatório|descrição|
|-----|----|:----------:|------|
|nome|string|✅| Um nome curto para a categoria.
|icone|string|❌|O nome do icone de acordo com a biblioteca Material Icons.

```js

{
        "nome": "Alimentação",
        "icone" : "fast-food"

}

```


#### Exemplo de Resposta

```js

{
        "id": 1,
        "nome": "Alimentação",
        "icone" : "fast-food"

}

```

#### Códigos de Status

|código|descrição|
|------|---------|
|201|Categoria cadastrada com sucesso
|400|Dados enviados são inválidos. Verifique o corpo da requisição
|401|Acesso negado. Você deve se autenticar

---

### Detalhes da Categoria

`GET` /categoria/`{id}`

Retorna os detalhes da categoria com o `id` informado como parâmetro path

#### Exemplo de Resposta

```js
// requisição para /categoria/1
{
        "id": 1,
        "nome": "Alimentação",
        "icone" : "fast-food"

}

```

#### Códigos de Status

|código|descrição|
|------|---------|
|200|Os dados das categorias foram retornados com sucesso
|401|Acesso negado. Você deve se autenticar
|404|Não existe categoria com o `id` informado

---

### Apagar Categoria

`DELETE` /categoria/`{id}`

Apaga a categoria com o `id` espeficiado no parâmetro de path.

#### Códigos de Status

|código|descrição|
|------|---------|
|204|Categoria foi apagada com sucesso
|401|Acesso negado. Você deve se autenticar
|404|Não existe categoria com o `id` informado

---

### Atualizar Categoria

`PUT` /categoria/`{id}`

Altera os dados da categoria especificada no `id` utilizando as informações enviadas no corpo da requisição

#### Corpo da requisição

|campo|tipo|obrigatório|descrição|
|-----|----|:----------:|------|
|nome|string|✅| Um nome curto para a categoria.
|icone|string|✅|O nome do icone de acordo com a biblioteca Material Icons.

```js

{
        "nome": "Alimentação",
        "icone" : "fast-food"

}

```
#### Exemplo de Resposta

```js

{
        "id": 1,
        "nome": "Alimentação",
        "icone" : "fast-food"

}

```

#### Códigos de Status

|código|descrição|
|------|---------|
|200|Categoria alterada com sucesso
|400|Dados enviados são inválidos. Verifique o corpo da requisição
|401|Acesso negado. Você deve se autenticar
|404|Não existe categoria com o `id` informado

---
