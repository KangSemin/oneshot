# 🍹oneshot🍷 
칵테일 마니아층을 위한 <U>칵테일과 재료조회</U> 및 <U>관련물품 판매</U> 서비스입니다.

프로젝트 기간 : 2025-02-11 ~ 2025-03-17

## ✨ 주요서비스
### 기주로 칵테일 검색
펜트리에 담긴 기주를 기반으로 사용자가 가지고 있는 기주 중 만들 수 있는 칵테일 추천서비스
### 인기칵테일
조회수와 즐겨찾기수로 1시간마다 갱신되는 인기칵테일 top10
### 실시간 채팅식 문의기능
관리자와 실시간채팅으로 가능한 문의기능
### OPENRUN EVENT
선착순 이벤트

## 🔗목차
1. [📌 Architecture](#-architecture)
2. [📃 API](#-api)
3. [📊 WireFrame](#-WireFrame)
4. [🔧 기술스택](#-기술스택)
5. [🗝️ KEY SUMMARY](#-key-summary)
6. [🪛적용 기술](#적용-기술)
7. [🎯트러블슈팅](#트러블슈팅)
8. [🎢추가 개선 예정](#추가-개선-예정)
9. [👥팀원 소개](#팀원-소개)

##  📌 Architecture

![Image](https://github.com/user-attachments/assets/abcbde92-2429-4589-b48f-82d471362266)

## 📃 API
👇👇 Link 👇👇 

[API 명세서 링크](https://docs.google.com/spreadsheets/d/1EANpCwBciTfysj16doEwfIinHsJzfgDZ8P7a7zq49T0/edit?gid=0#gid=0)

## 📊 WireFrame
![Image](https://github.com/user-attachments/assets/4fd81874-cac9-4dc9-bc2d-28fc353bc3b6)

👇👇 Link 👇👇

[WireFrame  링크](https://www.figma.com/design/VpTM2b3OTPGFkTFMef3Imc/Untitled?node-id=0-1&t=qD3uG8KuG1AEFkOk-1)

## 🔧 기술스택

### Development
<img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=OpenJDK&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Redis-DC382D?style=flat-square&logo=redis&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Spring-6DB33F?style=flat-square&logo=spring&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/elasticsearch-%230377CC.svg?style=for-the-badge&logo=elasticsearch&logoColor=white">&nbsp;


### Operation
<img src="https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=Docker&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=flat-square&logo=amazonec2&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Amazon AWS-232F3E?style=flat-square&logo=amazonaws&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/GitHub Actions-2088FF?style=flat-square&logo=githubactions&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white"/>&nbsp;


###  Documentation
<img src="https://img.shields.io/badge/jira-%230A0FFF.svg?style=for-the-badge&logo=jira&logoColor=white"/>&nbsp;



## 🗝️ KEY SUMMARY

### 🚀 성능 개선: 검색기능 엘라스틱서치 도입으로약 99% 성능 최적화

>#### ✏️ **한줄 요약**
>- 기주로 칵테일 조회, 키워드로 칵테일, 재료 조회 기능 구현
>- 기존의 QueryDsl에서 ElasticSearch 도입으로 성능 개선 및 동의어 사전, 자동완성 등에 기능으로 사용자성 증가
  </br>

>#### **🔍 도입 배경**
>- QueryDsl을 사용하여 검색기능 구현시 적은데이터에서는 사용자가 불편을 느끼지 못할정도의 속도가 측정되었지만,
>- 대용량의 데이터 환경에서 데이터 양이 많아질수록 시간복잡도 증가 발생으로 엘라스틱서치 도입
  </br>

### 🚀 성능 개선: redis 사용으로 쿼리 감소, 빠른 조회가능

>#### ✏️ **한줄 요약**
>- 칵테일의 조회수, 즐겨찾기 count부분 관리
>- TTL 기능으로 블랙리스트의 만료 시간을 토큰 만료시간과 같게 설정가능함
>- 인메모리의 빠른 조회 속도로 토큰 검증 작업이 빨라짐

>#### **🔍 도입 배경**
>- 자주 조회되는 데이터에 대한 쿼리 부담 감소위해 도입
  </br>

## 🪛적용 기술

### 💾 **데이터베이스 및 캐싱**
>**Redis**
>   - **적용 위치**: 캐시 서버
>   - **사용 이유**: 
>     - 실시간 상품 할인율과 최저가 조회 성능 향상. 
>     - TTL 설정으로 타임세일 종료 시 데이터 자동 삭제.
### 📮 메시징 시스템
>  **Rabbit MQ**
>   - **적용 위치**: 선착순 이벤트 사후 처리를 위한 비동기 통신
>   - **사용 이유**:
>     - FIFO 큐 방식으로 메시지 순서 보장.
>     - NACK, DLQ 기능으로 메시지 영속성 제공.
>     - 단일 서버 환경과 1,000명 규모에 적합.
>   - **구체적 역할**:
>     - 이벤트 참여 시 비동기 사후 처리 (결제, 알림 등)
>     -  메시지 내역 관리

>**Lua Script**
>   - **적용 위치**: 선착순 이벤트 동시성 제어
>   - **사용 이유**: 
>     - 원자적 실행으로 높은 TPS 처리 가능.
>     - 정확한 카운트 관리와 데이터 정합성 보장.
>   - **구체적 역할**:
>        - 이벤트 참여 요청의 동시성 제어,
>        - limitCount와 실제 처리 카운트 관리

### 🛰️ 실시간 통신
>**Web Socket**
>   - **적용 위치**: CS업무를 위한 실시간 채팅
>   - **사용 이유**: 높은 실시간성과 양방향 통신
>   - **구체적 역할**: 
>     - 상품이나 주문 관련으로 문의하려는 유저별로 웹소켓 연결을 유지
>     - 실시간으로 메시지를 전송


>2. **SSE(Server-Sent Events)**
>- **적용 위치**: 선착순 이벤트 결과 실시간 알림
>- **사용 이유**:
>     - 서버에서 클라이언트로의 단방향 통신에 최적화.
>   -  WebSocket보다 가벼운 리소스 사용으로 효율적.
>- **구체적 역할**: 사용자에게 이벤트 참여 결과 실시간 전달

## 🎯트러블슈팅

<details>
  <summary>Repository 사용 누락으로 인한 엘라스틱서치 인덱스 자동 생성 실패</summary>

### 문제
다음과 같이 엘라스틱서치의 인덱스 설정을 직접 해주고 도큐먼트에 파일 위치를 매핑시켜 주었음에도 키바나에서 인덱스를 확인해 보았을 때 인덱스가 자동으로 생성되지 않는 문제가 발생

### 원인
- 애플리케이션이 실행될 때 엘라스틱서치는 `@document` 에너테이션이 달린 클래스를 읽는다.
- 레포지토리 인터페이스를 구현한 레포지토리가 등록되면서, 레포지토리 내부의 `IndexOperations`를 통해 해당 이름의 인덱스가 이미 존재하는지 확인하고, 존재하지 않으면 `@mapping`, `@Setting`의 path 경로에 있는 파일 내용을 기반으로 인덱스를 생성한다.

### 해결
repository를 사용하지 않고, operations를 사용하여 기능을 구현하고 있었기 때문에, elasticSearchRepository의 구현체를 생성하지 않아 indexOperation을 통한 인덱스 생성이 자동으로 일어나지 않았습니다. repository를 사용하지 않으면 indexOperations 구현체를 통해 직접 인덱스를 생성해 줘야 합니다.
</details>

<details>
  <summary>OpenSearch 호환을 위한 ElasticSearch RestClient 관련 리팩토링</summary>

### 배경
- 배포를 위해 docker-compose로 구동하던 ElasticSearch를 AWS에서 구축할 필요가 생겼다.
- OpenSearch와 ElasticSearch의 호환성이 높다는 정보를 듣고 시도

### 시도1 - OpenSearch 관련 라이브러리로 리팩토링 → `삽질`
- 처음에는 OpenSearch 관련해서 나오는 블로그 글들을 활용해서 리팩토링을 시도했다.
- OS와 ES의 빈이 겹치는 에러가 있어서 아래 코드를 SpringApplication에 추가했다.
  ```java
  @SpringBootApplication(exclude = {ElasticsearchDataAutoConfiguration.class})
  ```
- 하지만 여전히 문제가 생겨서 ElasticSearch 의존성을 제거해야 한다는 내용을 보고 리팩터링을 시도했다.
- 하다보니 너무 관련된 변경이 많아졌고 이러면 로컬에서 도커로 테스트할 수 없다는 문제점이 생겼다.
- 호환성이 높다는 말에 의심이 돼서 검색해 본 결과 아래의 글을 찾을 수 있었다.

### 시도2 - RestClient를 수정해서 호환되게 변경
- https://flambeeyoga.tistory.com/entry/Spring-elasticsearch-rest-client-853%EC%97%90-opensearch-%EC%97%B0%EA%B2%B0%ED%95%98%EA%B8%B0
- 이 글에서는 나와 비슷하게 ES로 만들어진 프로젝트를 OS 환경에 배포하려고 하고 있었다.
- 요약하자면 **ES와 OS 모두 Rest API 기반**이기 때문에 OS 라이브러리 의존성 없이도 호환이 가능하다!
- 다만 RestClient를 OS에 맞게 변경할 필요가 있었다.
- 하지만 글과는 다르게 Timeout 예외가 발생

### 시도 2-1 - 이어서 RestClient를 수정해서 호환되게 변경
- 다음부터는 블로그에 나온 상태코드와 같아서 이어서 진행할 수 있었다.
- 블로그에서는 `RestClient` 빈을 직접 생성하는데 우리 프로젝트는 `ClientConfiguration`이 설정되어 있었다.
- 자세히는 모르지만 최신버전에서 추천되는 방식으로 여러가지 편의기능을 제공하는 것 같다.
- 아무튼 디버그 모드에서 콜스택을 따라가서 확인한 결과 이 친구를 사용하는 방식은 강제로 Header를 바꿔버려서 헤더를 알맞게 수정해도 OS에서 406 Not Acceptable을 반환한다.
- 또 OS와 호환되기 위해서는 추가적인 헤더가 필요했다.
- 결국 `ClientConfiguration`을 사용하는 방식으로는 힘들다고 판단해서 `ElasticsearchClient`를 직접 반환하는 코드로 변경

```java
@Bean
public ElasticsearchClient elasticsearchClient() {
    // Basic 인증 설정
    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    credentialsProvider.setCredentials(AuthScope.ANY,
            new UsernamePasswordCredentials(username, password));

    Header[] defaultHeaders = new Header[] {
            new BasicHeader("Content-Type", "application/json")
    };

    // RestClientBuilder에 인증 정보/추가헤더 적용
    RestClientBuilder builder = RestClient
            .builder(new HttpHost(host, port, "https"))
            .setDefaultHeaders(defaultHeaders)
            .setHttpClientConfigCallback(httpClientBuilder ->
                    httpClientBuilder
                            .setDefaultCredentialsProvider(credentialsProvider)
                            .addInterceptorLast(new HttpResponseInterceptor() {
                                @Override
                                public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
                                    response.addHeader("X-Elastic-Product", "Elasticsearch");
                                }
                            })
            );

    RestClient restClient = builder.build();

    ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper(objectMapper));

    return new ElasticsearchClient(transport);
}
```

### 해결
스프링 앱을 실행하면 정상적으로 OpenSearch에 연결되고 실행이 가능해졌습니다. 로컬 테스트 환경에서는 여전히 도커의 엘라스틱 서치를 사용해야 하기 때문에 아이디, 비밀번호를 요구하지 않고, HTTPS를 사용하지 않는 빈을 하나 더 만들고 프로필을 이용해서 사용 환경을 구분할 수 있도록 했습니다.
</details>

## 🎢추가 개선 예정
> 
> 
>- 장바구니와 주문의 연관관계 변경으로 데이터 효율 개선
>
> 
>- 엘라스틱 서치 동의어 사전을 통해 사용성 개선
>
> 
>- RabbitMQ NACK, DLQ 구현으로 이벤트 참여 실패 시 재시도 로직 적용
>
> 
>- 상품 리뷰 기능 추가
>
> 
>- 프로메테우스, 엘크, 그라파나와 같은 실시간 모니터링 도구 연동으로 시스템 성능 추적 강화 

## 👥팀원 소개

| 이름  | 직책  | MBTI | Github 주소 |
|-----|-----|------|-----------|
| 강세민 | `팀장`  | `ENTP` | https://github.com/KangSemin |
| 이수진 | `부팀장` | `ISFJ` | https://github.com/jin18302 |
| 강윤현 | `팀원`  | `INFP` | https://github.com/cnux9 |
| 고예나 | `팀원`  | `ENFJ` | https://github.com/goo3oo |


  




 
