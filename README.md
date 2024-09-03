
![image](https://github.com/user-attachments/assets/face0948-b40e-4fcd-98f3-ce0eacca7a43)
 
# Pior Filme Awards

 Descrição: Aplicativo desenvolvido em kotlin para consumo de dados atraves de APi

https://github.com/user-attachments/assets/452b4237-7a5d-406c-ac9e-ea75a5d19e60

 Status do projeto: Em construção

## TABELA DE CONTEÚDO

 1. [Tabela de Conteudo](https://github.com/andrezzab/PiorFilmeAwards/blob/main/README.md#tabela-de-conte%C3%BAdo)
 2. [Instalação](https://github.com/andrezzab/PiorFilmeAwards/blob/main/README.md#2-instala%C3%A7%C3%A3o-1)
 3. [Como usar](https://github.com/andrezzab/PiorFilmeAwards/blob/main/README.md#3-como-usar-1)
    3.1. [Pre-requisitos](https://github.com/andrezzab/PiorFilmeAwards/blob/main/README.md#31-pr%C3%A9-requisitos)
 4. [Tecnologias](https://github.com/andrezzab/PiorFilmeAwards/blob/main/README.md#4-tecnologias-1)

# 2. Instalação

- Instale o programa [Android Studio](https://developer.android.com/studio?hl=pt-br&_gl=1*sz1gbq*_up*MQ..&gclid=CjwKCAjwxNW2BhAkEiwA24Cm9Bh_khqwnTH2tZ7AAylxEqtvt1ByRbdEnWz07iAhYglvlZt1nTyMlRoC5D0QAvD_BwE&gclsrc=aw.ds) no seu computador.

# 3. Como usar
### 3.1 Pré-requisitos

- As dependências do projeto foram adicionadas ao build.grade:

    implementation("com.squareup.retrofit2:retrofit:2.11.0")

    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")
  
    implementation("com.squareup.retrofit2:converter-moshi:2.11.0")
  
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
  
    implementation("com.google.code.gson:gson:2.11.0")

- No Android Studio, acesse File -> Settings -> Android SDK e instale a API 34:
![image](https://github.com/user-attachments/assets/5f85fa6c-07ab-49ad-a26b-1097ef079247)

- Vá até o menu de gradle e configure com o [gradle 8.1.1](https://gradle.org/next-steps/?version=8.1.1&format=bin) e o JDK 17. Clique em Apply e OK.
![image](https://github.com/user-attachments/assets/0349babb-9f8a-48d3-8481-785cbbff7af4)

- Vá até o menu de Project Structure e configure:
  ![image](https://github.com/user-attachments/assets/9afc2ba9-d1e8-4090-bc80-0816889bf630)

- Se tudo for feito corretamente, todas as dependências serão instaladas e poderá executar o projeto.

- Crie um device virtual (PIXEL XL API 34):
  
  ![image](https://github.com/user-attachments/assets/8bcba173-3eab-4a02-b82e-d5bfbab4b7b5)
  ![image](https://github.com/user-attachments/assets/f37d7e47-b5d7-49fb-b377-ba2b673a9379)

# 4. Tecnologias

### Fragments 

Um Fragment representa uma parte reutilizável da IU do seu app. Um fragmento define e gerencia o próprio layout, tem o próprio ciclo de vida e pode processar os próprios eventos de entrada. Os fragmentos não podem existir sozinhos. Eles precisam ser hospedados por uma atividade ou por outro fragmento. A hierarquia de visualização do fragmento se torna parte da hierarquia de visualização do host ou é anexada a ela. [[1](https://developer.android.com/guide/fragments?hl=pt-br)]

### Retrofit 

Retrofit is the class through which your API interfaces are turned into callable objects. By default, Retrofit will give you sane defaults for your platform but it allows for customization. [[2](https://square.github.io/retrofit/)


