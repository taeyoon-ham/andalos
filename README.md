# Andalos

# Version

* Jdk : 21
* SpringBoot : 3.2.4
* Spring Security : 6.2.3
* Hibernate : 6.4.4

# Package Structure

```text
com.taeyoon.api
├── domain                # 엔터티와 비즈니스 규칙을 포함하는 최내부 계층
│   ├── user              # 비즈니스 도메인 (예:유저)
│   │   ├── model         # 도메인 모델(엔터티, 값 객체 등)
│   │   ├── creation      # 도메인 행위
│   │   └── withdraw      # 도메인 행위
│   └── order             # 비즈니스 도메인 (예:주문)
│       ├── model          
│       ├── creation       
│       └── delivery       
│
├── application           # 애플리케이션의 비즈니스 로직을 구현하는 사용 사례 계층
│   ├── dto               # 데이터 전송 객체
│   ├── service           # 응용 서비스 (비즈니스 로직의 구현)
│   └── exception         # 응용 계층의 예외 정의
│
├── infra                 # 인프라스트럭처 계층 (데이터베이스, 메시징 시스템 등 외부와의 통신을 담당)
│   ├── persistence       # 데이터베이스 접근과 관련된 구현 (리포지토리 구현체)
│   ├── configuration     # 애플리케이션 설정과 관련된 클래스
│   └── external          # 외부 시스템과의 통신을 담당
│
└── interfaces            # 인터페이스 어댑터 계층 (사용자 인터페이스, 웹 API 등)
    ├── web               # 웹 인터페이스 (컨트롤러)
    ├── cli               # 커맨드라인 인터페이스
    └── rest              # REST API 인터페이스
```

* 패키지 구조에 대한 고민이 많았다.
* 이미 많은 문제점이 발견된 전통적인 수평 분할 계층형 패키지로는 할 수 없고,
* MSA 로 확장 가능한 도메인 중심의 기능 기반 패키지로 구성해야 한다.
* 또한 저수준과 고수준의 경계가 확실해야 하고 격리 시켜야 하기에 위와 같은 패키지 구조를 구성하였다.

# Domain

```text
├── domain                # 엔터티와 비즈니스 규칙을 포함하는 최내부 계층
│   ├── user              # 비즈니스 도메인 (예:유저)
│   │   ├── model         # 도메인 모델(엔터티, 값 객체 등)
│   │   ├── creation      # 도메인 행위
│   │   └── withdraw      # 도메인 행위
│   └── order             # 비즈니스 도메인 (예:주문)
│       ├── model          
│       ├── creation       
│       └── delivery       
```

* 도메인은 패키지 구조만 보더라도 어떤 서비스를 하는지 알 수 있도록 해야 한다.
* 저수준인 프레임워크와 격리되어야 하고 오로지 도메인 로직에만 집중해야 한다.
* 각 도메인 하위는 엔티티 객체와 행위로 구성한다.

# Application

```text
├── application           # 애플리케이션의 비즈니스 로직을 구현하는 사용 사례 계층
│   ├── dto               # 데이터 전송 객체
│   ├── service           # 응용 서비스 (비즈니스 로직의 구현)
│   └── exception         # 응용 계층의 예외 정의   
```

* 애플리케이션의 비즈니스 로직을 담당하는 영역이다.
* 프레임워크와 종속적이고 비즈니스 로직을 처리할 때 여러 도메인들을 활용할 수 있어야 한다.
* 차후 MSA 로 확장할 경우 수정될 부분이 가장 많은 곳이 될 듯 하다.
* dto : 인터페이스 어답터 계층에서 데이터를 전송할 때 사용하는 객체들이 있는 곳이다.
* service : 비즈니스 로직을 구현하는 곳이다.
* exception : 비즈니스 로직 처리시 발생하는 exception 들이 있는 곳이다.

# Infrastructure

```text
├── infra                 # 인프라스트럭처 계층 (데이터베이스, 메시징 시스템 등 외부와의 통신을 담당)
│   ├── persistence       # 데이터베이스 접근과 관련된 구현 (리포지토리 구현체)
│   ├── configuration     # 애플리케이션 설정과 관련된 클래스
│   └── external          # 외부 시스템과의 통신을 담당
```

* 저수준에 해당하는 패키지들이 있는 곳이다.
* 애플리케이션 설정에 관련된 클래스, 데이터베이스 접근 관련된 클래스 또는 외부 인터페이스에 관련된 클래스들이 있는 곳이다.
* Spring framework 를 사용할 경우 각종 configuration 클래스, Jpa 를 사용할 경우 repository 구현체 등이 있다.
* external 은 타 시스템과의 인터페이스 관련된 클래스, 또는 cloud service 를 활용하기 위한 sdk 처리로직을 담당하는 클래스들이 있다.

# Interfaces

```text
└── interfaces            # 인터페이스 어댑터 계층 (사용자 인터페이스, 웹 API 등)
    ├── web               # 웹 인터페이스 (컨트롤러)
    ├── cli               # 커맨드라인 인터페이스
    └── rest              # REST API 인터페이스
```

* 인터페이스 어댑터 계층이다.
* 하위 패키지만 보더라도 금방 이해가 될 것이다.
* Spring framework 를 사용할 경우 Controller 가 여기에 위치한다.

# 종합

* 인터페이스 어댑터 계층 : `interfaces`, `infra`
* 유스케이스 계층 : `application`
* 엔티티 계층 : `domain`

![the-clean-architecture](https://github.com/taeyoon-ham/andalos/assets/165914588/b75d2e98-ef09-464e-8fb1-fd3b3a60c1c0)
