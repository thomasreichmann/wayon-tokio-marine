# Sistema de Agendamento de Transferências Financeiras

Este repositório contém um projeto desenvolvido como parte do processo seletivo para a empresa Tokio Marine. O projeto consiste em um sistema de agendamento de transferências financeiras, composto por uma API desenvolvida em Spring Boot e um frontend em Vue.js.

## Decisões Arquiteturais

### Backend (Spring Boot)

- **Spring Boot**
- **Banco de Dados em Memória (H2)**
- **Padrão MVC**: Adotado para organizar o projeto em modelos, visualizações e controladores, facilitando a manutenção e a escalabilidade.

### Frontend (Vue.js)

- **Vue.js**
- **Componentização**: A aplicação é dividida em components reutilizáveis, aumentando a manutenabilidade e a clareza do código.
- **Feedback ao Usuário**: Implementação de snackbars para fornecer feedback imediato e claro sobre as ações do usuário, como sucesso ou falha ao criar transações.

## Estrutura do Projeto

O projeto está dividido em duas partes principais:

- `backend`: Diretório contendo a API do sistema, desenvolvida com Spring Boot.
- `frontend`: Diretório com a interface de usuário do sistema, desenvolvida utilizando Vue.js.

Cada diretório possui seu próprio README com detalhes específicos e instruções para execução.

## Tecnologias Utilizadas

- Backend: Spring Boot
- Frontend: Vue.js
- Banco de Dados: H2 (em memória)

Para mais informações sobre cada parte do projeto, por favor, consulte os READMEs específicos nos diretórios `backend` e `frontend`.
