# StarLord

StarLord é um projeto desenvolvido para Android com o kotlin.

A proposta do aplicativo é listar os repositórios do github com mais estrelas ⭐
feitos na linguagem kotlin.

## 🚀 Começando

Para começar a trabalhar com o projeto StarLord, siga os passos abaixo:

### 🧰 Pré-requisitos

- Git
- Android Studio Narwhal 3 Feature Drop | 2025.1.3 ou superior
- JDK 17 ou superior (opcional, pois o Android Studio possui a jdk integrada)
- [Gradle +8.](https://gradle.org/install/) (opcional, pois o projeto inclui o wrapper)

### 📦 Instalação

## 1. Clone o repositório:

```bash
   git clone git@github.com:Jhonnycpp/StarLord.git
   cd StarLord
```
   
## 2. 📁 Estrutura de Diretórios

```
StarLord/
├── .gitignore
├── LICENSE
├── build.gradle.kts
├── gradle/
|     └── libs.versions.toml                   <--- Configurações de dependências
├── gradlew
├── gradlew.bat
├── settings.gradle.kts
└── app/
    └── src/
        └── main/
            └── kotlin/
                └── br/com/jhonny/starlord/
                    ├── di                      <--- Configuração dos modulos injetaveis
                    ├── extension               <--- Extensões para facilitar a vida
                    ├── feature                 <--- As camadas de funcionalidade
                    ├── ui                      <--- As camadas de visuais
                    |    ├── navigation         <--- Navegação
                    |    ├── screen             <--- Telas separadas por funcionalidade
                    |    ├── theme              <--- Tema do app
                    |    └── MainActivity.kt    <--- Ponto de entrada do app
                    └── MainApplication.kt      <--- Ponto de entrada do projeto
```
## 3. 🛠 Tecnologias Utilizadas

Este projeto utiliza um conjunto moderno de tecnologias para desenvolvimento Android:
- Linguagem: Kotlin
- Arquitetura: 
  - Clean Architecture: Esta é uma arquitetura de software que visa separar as preocupações do seu código em camadas distintas (como Domínio, Casos de Uso, Interface Adapters e Frameworks & Drivers). O objetivo é criar um sistema mais testável, manutenível e independente de frameworks ou detalhes de implementação externos. No seu readme.md, ela está listada explicitamente.
  - MVVM: Esta é uma arquitetura de apresentação (parte da camada de "Interface Adapters" na Clean Architecture) que facilita a separação da lógica de apresentação da UI.
    - Model: Representa os dados e a lógica de negócios (geralmente vindo da camada de Domínio/Casos de Uso na Clean Architecture).
    - View: A UI (no seu caso, implementada com Jetpack Compose) que observa o ViewModel.
    - ViewModel: Expõe dados do Model para a View e lida com a lógica de apresentação e as interações do usuário. Ele não tem conhecimento direto da View.
- Princípios de Design de Código:
  - SOLID: Adotamos os cinco princípios SOLID (Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, Dependency Inversion) como guia para escrever código de alta qualidade, promovendo a manutenibilidade, testabilidade e escalabilidade do projeto.
- Interface de Usuário:
  - Jetpack Compose para uma UI declarativa e moderna.
  - Material Design 3 para componentes visuais.
- Injeção de Dependência:
  - Koin para gerenciamento de dependências leve e pragmático.
- Networking:
  - Retrofit para requisições HTTP type-safe.
  - OkHttp (indiretamente via Coil e Retrofit) como cliente HTTP.•
- KotlinX Serialization para parsing de JSON.
- Carregamento de Imagens:
  - Coil para carregamento eficiente de imagens.
- Navegação: Jetpack Navigation Compose para navegação entre telas do Compose.
- Testes:
  - Unitários: JUnit 4, MockK, Turbine (para Flows), KotlinX Coroutines Test.
  - Instrumentados: JUnit 4, Compose UI Test, Navigation Testing.
- Utilitários AndroidX/Jetpack: Core KTX, Lifecycle KTX.
- Build System: Gradle com Kotlin DSL.
- Cobertura de Código: JaCoCo.

## 4. 🧰 Compile o projeto:
```bash
    ./gradlew :app:assemble
```

## 5. 🧪 Testes
### Unitários 
```bash
    ./gradlew :app:check
```

### Instrumentados
```bash
    emulator -list-avds # Lista os avds disponíveis
    emulator -avd <nome_do_avd> -netdelay none -netspeed full # Inicia o emulador com a velocidade máxima na rede
    adb wait-for-device # Espera até que o dispositivo esteja conectado
    while [[ $(adb shell getprop sys.boot_completed | tr -d '\r') != "1" ]]; do # Espera até que o dispositivo esteja ligado
    sleep 1
    done
    ./gradlew connectedAndroidTest # Executa os testes instrumentados
```  

## 6. Coverage
Este comando gera um relatório de cobertura de código unitário e instrumentado.
```bash
    ./gradlew :app:jacocoFullReport
```

Para visualizar basta abrir o relatorio em html na pasta `./app/build/reports/jacoco/jacocoFullReport/html/index.html` no seu navegador de preferencia.

## 7. App
O possui a funcionalidade de exibir os repositórios do github com mais estrelas ⭐

|Retrato|Deitado|
|-|-|
|<video src="./doc/videos/repositories-scroll-portrait.mp4"/>|<video src="./doc/videos/repositories-scroll-landscape.mp4"/>|

E também possui a funcionalidade de exibir as informações do repositório selecionado.

|Retrato|Deitado|
|-|-|
|<video src="./doc/videos/repository-detail-portrait.mp4"/>|<video src="./doc/videos/repository-detail-landscape.mp4"/>|
