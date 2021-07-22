# Como contribuir com a documentação

## Passo 1

Fazer o clone do repositório

```bash
git clone https://github.com/ifspcodelab/gestao-estagios.git
```

## Passo 2

Fazer o checkout na brach gh-pages

```bash
git checkout -b gh-pages origin/gh-pages
```

## Passo 3

Criar uma branch local para trabalhar a issue

```bash
git checkout -b docs-descricao-issue
```

## Passo 4

Instalar as dependências do projeto e inicia o servidor local

```
npm install
```

```
./node_modules/.bin/docsify serve
```

## Passo 5

Trabalhar as alterações da issue e testar localmente

Acessar localhost:3000


## Passo 6

Adicionar e fazer o commit

```bash
git add .
git commit -m "docs: add task description"
```

## Passo 7

Fazer push para o github

```bash
git push
```