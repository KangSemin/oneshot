# 🍹oneshot🍷 
칵테일 마니아층을 위한 칵테일과 재료조회 및 관련물품 판매 서비스입니다

프로젝트 기간 : 2025-02-11 ~ 2025-03-17


# ✨주요서비스
### 기주로 칵테일 검색
펜트리에 담긴 기주를 기반으로 사용자가 가지고 있는 기주 중 만들 수 있는 칵테일 추천서비스
### 인기칵테일
조회수와 즐겨찾기수로 1시간마다 갱신되는 인기칵테일 top10
### 실시간 채팅식 문의기능
관리자와 실시간채팅으로 가능한 문의기능
### OPENRUN EVENT 



#  📌Architecture
![Image](https://github.com/user-attachments/assets/abcbde92-2429-4589-b48f-82d471362266)

# 🔧기술스택

## Development
<img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=OpenJDK&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Redis-DC382D?style=flat-square&logo=redis&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Spring-6DB33F?style=flat-square&logo=spring&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/elasticsearch-%230377CC.svg?style=for-the-badge&logo=elasticsearch&logoColor=white">&nbsp;


## Operation
<img src="https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=Docker&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=flat-square&logo=amazonec2&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Amazon AWS-232F3E?style=flat-square&logo=amazonaws&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/GitHub Actions-2088FF?style=flat-square&logo=githubactions&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white"/>&nbsp;


##  Documentation
<img src="https://img.shields.io/badge/jira-%230A0FFF.svg?style=for-the-badge&logo=jira&logoColor=white"/>&nbsp;



# 💡KEY SUMMARY

## 🚀 성능 개선: 검색기능 엘라스틱서치 도입으로 약 99% 성능 최적화

### ✏️ **한줄 요약**
- 기주로 칵테일 조회, 키워드로 칵테일, 재료 조회 기능 구현
- 기존의 QueryDsl에서 ElasticSearch 도입으로 성능 개선 및 동의어 사전, 자동완성 등에 기능으로 사용자성 증가
  </br>
### **🔍 도입 배경**
- QueryDsl을 사용하여 검색기능 구현시 적은데이터에서는 사용자가 불편을 느끼지 못할정도의 속도가 측정되었지만,
- 대용량의 데이터 환경에서 데이터 양이 많아질수록 시간복잡도 증가 발생으로 엘라스틱서치 도입
  </br>

## 🚀 성능 개선: redis 사용으로 쿼리 감소, 빠른 조회가능

### ✏️ **한줄 요약**
- 칵테일의 조회수, 즐겨찾기 count부분 관리
- TTL 기능으로 블랙리스트의 만료 시간을 토큰 만료시간과 같게 설정가능함
- 인메모리의 빠른 조회 속도로 토큰 검증 작업이 빨라짐

  </br>

### :플로피_디스크: **데이터베이스 및 캐싱**
1. **Redis**
   - **적용 위치**: 캐시 서버
   - **사용 이유**: 실시간 상품 할인율과 최저가 조회 성능 향상. TTL 설정으로 타임세일 종료 시 데이터 자동 삭제.
### :우편물이_담긴_우편함: 메시징 시스템
1. **Rabbit MQ**
   - **적용 위치**: 선착순 이벤트 사후 처리를 위한 비동기 통신
   - **사용 이유**:
     - FIFO 큐 방식으로 메시지 순서 보장.
     - NACK, DLQ 기능으로 메시지 영속성 제공.
     - 단일 서버 환경과 1,000명 규모에 적합.
   - **구체적 역할**:
     - 이벤트 참여 시 비동기 사후 처리 (결제, 알림 등)
     -  메시지 내역 관리
2. **Lua Script**
   - **적용 위치**: 선착순 이벤트 동시성 제어
   - **사용 이유**: 원자적 실행으로 높은 TPS 처리 가능.
        - 정확한 카운트 관리와 데이터 정합성 보장.
   - **구체적 역할**:
        - 이벤트 참여 요청의 동시성 제어,
        - limitCount와 실제 처리 카운트 관리
### :시계_반대_방향_화살표: 실시간 통신
1. **Web Socket**
   - **적용 위치**:
   - **사용 이유**:
   - **구체적 역할**:
2. **SSE(Server-Sent Events)**
   - **적용 위치**: 선착순 이벤트 결과 실시간 알림
   - **사용 이유**:
        - 서버에서 클라이언트로의 단방향 통신에 최적화.
      -  WebSocket보다 가벼운 리소스 사용으로 효율적.
   - **구체적 역할**: 사용자에게 이벤트 참여 결과 실시간 전달

</br>
 # 📄트러블슈팅
 <details>
  <summary>OpenSearch 호환을 위한 ElasticSearch RestClient 관련 리팩토링</summary>
   - 배포를 위해 docker-compose로 구동하던 ElasticSearch를 AWS에서 구축할 필요가 생겼다.
- OpenSearch와 ElasticSearch의 호환성이 높다는 정보를 듣고 시도

# 시도1 - OpenSearch 관련 라이브러리로 리팩토링 → `삽질`

- 처음에는 OpenSearch 관련해서 나오는 블로그 글들을 활용해서 리팩토링을 시도했다.
- OS와 ES의 빈이 겹치는 에러가 있어서 아래 코드를 SpringApplication에 추가했다.
- `@SpringBootApplication(exclude = {ElasticsearchDataAutoConfiguration.class})`
- 하지만 여전히 문제가 생겨서 ElasticSearch 의존성을 제거해야 한다는 내용을 보고 리팩터링을 시도했다.
- 하다보니 너무 관련된 변경이 많아졌고 이러면 로컬에서 도커로 테스트할 수 없다는 문제점이 생겼다.
- 호환성이 높다는 말에 의심이 돼서 검색해 본 결과 아래의 글을 찾을 수 있었다.

# 시도2 - RestClient를 수정해서 호환되게 변경

- https://flambeeyoga.tistory.com/entry/Spring-elasticsearch-rest-client-853%EC%97%90-opensearch-%EC%97%B0%EA%B2%B0%ED%95%98%EA%B8%B0
- 이 글에서는 나와 비슷하게 ES로 만들어진 프로젝트를 OS 환경에 배포하려고 하고 있었다.
- 요약하자면 **ES와 OS 모두 Rest API 기반**이기 때문에 OS 라이브러리 의존성 없이도 호환이 가능하다!
- 다만 RestClient를 OS에 맞게 변경할 필요가 있었다.
- 하지만 글과는 다르게 Timeout 예외가 발생

```java
Caused by: org.springframework.dao.DataAccessResourceFailureException: Timeout connecting to [[vpc-oneshot-elasticsearch-qm3januchy4i3zzbovj3hdwneq.ap-northeast-2.es.amazonaws.com/172.31.4.176:9200](http://vpc-oneshot-elasticsearch-qm3januchy4i3zzbovj3hdwneq.ap-northeast-2.es.amazonaws.com/172.31.4.176:9200)]
```

# 시도3 - AWS OpenSearch에 접근할 수 있게 보안 설정 변경

- 일단 AWS의 OS 서비스 도메인은 노드 1개로 단순하게 구성했고 VPC 중 서브넷 하나에 선택했다.
- 하지만 보안 설정과 정책을 아무리 바꿔봐도 로컬 환경과 동일한 VPC에 있는 EC2에서 둘 다 접근이 불가능했다.
- 공식문서를 따라서 OS 도메인을 퍼블릭으로 새로 생성하고 호스트/포트/로그인 정보를 바꾸면서 응답을 받는 데에 성공했다.(OS 도메인 생성 겁나 오래 걸림)
- OS는 퍼블릭일 경우 `세분화된 액세스`와 `https 사용`이 강제되고 → Basic Auth와 SSL + 443포트를 사용해야 한다. → **9200 포트를 고집했던 것이 문제의 원인 중의 하나**였음!
- 퍼블릭 도메인에서 포트와 엔드포인트를 적절하게 설정하니 에러코드지만 응답을 받을 수 있었다.

# 시도 2-1 - 이어서 RestClient를 수정해서 호환되게 변경

- 다음부터는 블로그에 나온 상태코드와 같아서 이어서 진행할 수 있었다.
- 블로그에서는 `RestClient` 빈을 직접 생성하는데 우리 프로젝트는 `ClientConfiguration` 가 설정되어 있었다.
- 자세히는 모르지만 최신버전에서 추천되는 방식으로 여러가지 편의기능을 제공하는 것 같다.
- 아무튼 디버그 모드에서 콜스택을 따라가서 확인한 결과 이 친구를 사용하는 방식은 강제로 Header를 바꿔버려서 헤더를 알맞게 수정해도 OS에서 406 Not Acceptable을 반환한다.
- 또 OS와 호환되기 위해서는 추가적이 헤더가 필요했다.
- 결국 `ClientConfiguration` 을 사용하는 방식으로 힘들다고 판단해서 `ElasticsearchClient`를 직접 반환하는 코드로 변경
- 블로그 내용은 코틀린으로 작성되어 자잘한 삽질들이 있었다…

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

- 스프링 앱을 실행하면 정상적으로 OpenSearch에 연결되고 실행이 가능하다…

![Image](https://github.com/user-attachments/assets/c779668a-f73e-4dc7-92fa-650cce5a8dd2)

- 로컬 테스트 환경에서는 여전히 도커의 엘라스틱 서치를 사용해야 하기 때문에 아이디, 비밀번호를 요구하지 않고, HTTPS를 사용하지 않는 빈을 하나 더 만들고 프로필을 이용해서 사용 환경을 구분할 수 있도록 했다.
  <!-- 내용 -->
</details>
</br>
 <details>
  <summary>즐겨찾기 등록시 쿼리 여러번 발생</summary>
문제:
Favorite엔티티에 Uesr객체, Cocktail객체를 참조
User객체 반환 시, Cocktail객체 반환 시, Favorite 객체 저장 시 총 쿼리 세 번 발생
즐겨찾기기 등록은 빈번하게 발생하는 요청인데, 한 번의 요청에 세 번의 쿼리발생은
문제라고 인식
문제해결:
findById 대신 **getReferenceById로 프록시 객체 반환
User: getReferenceById
User는 로그인한 유저이기 때문에, userId가 존재하지 않을 가능성이 없다.
따라서 User객체를 프록시 객체로 반환하게 될 때 발생할 문제가 없다.
Cocktail: findById
Cocktail아이디는 입력받아 사용
따라서, Cocktail객체가 존재하지 않을 가능성이 있다. 
그리고 Cocktail객체의 Count 값을 변경시켜야 해서 칵테일 객체는 필요
대신 Cocktail객체가 존재하는지 확인하는 절차를 먼저 거치도록 함
Exist 쿼리문으로 Cocktail객체의 존재확인을 하고, 없다면 예외 발생
이렇게 함으로써 칵테일 객체가 없을 때 불필요한 쿼리발생 방지
Exist 쿼리는 Count쿼리보다도 가볍기 때문에 적절하다고 판단
(findById - 모든 컬럼 데이터를 가져옴
  count - COUNT를 사용하므로 조건에 맞는 모든 행을 확인
  exist - 조건에 맞는 첫 행 발견 시 즉시 반환)
결론
 User는 프록시 객체로 반환: 쿼리발생 X
Cocktail 존재 여부 확인: 쿼리 발생하나 무척 가벼움, exixt 쿼리발생 1
Cocktail 객체 반환: select 쿼리발생 1
Favorite save: update쿼리 발생 1
쿼리 발생횟수는 3번으로 동일하나, exist 쿼리가 가벼우므로 최적화
레디스에 즐겨찾기 카운트값을 캐싱
칵테일 객체의 즐겨찾기카운트컬럼에 직접 접근할 필요가 없어짐
따라서, Cocktail객체도 프록시객체로 저장해도 됨
User 객체는 로그인한 유저의 디테일 정보이므로, Cocktail 객체는 
exist 쿼리문으로 존재하는지 확인하므로, getReferenceById 객체 참조해도
문제되지 않는다고 판단
결론
 User 프록시 객체로 반환: 쿼리발생 X
Cocktail 존재 여부 확인: 쿼리 발생하나 무척 가벼움, exixt 쿼리발생 1
Cocktail 프록시 객체로 반환: 쿼리발생 X
Favorite save: update쿼리 발생 1
쿼리 발생횟수 2번
**일반적인 findById vs getReferenceById:
findById
CopyUser user = userRepository.findById(1L).get();
// 즉시 SELECT 쿼리 실행
// SELECT * FROM user WHERE id = 1

​
getReferenceById
CopyUser userReference = userRepository.getReferenceById(1L);
// 이 시점에는 쿼리 실행 안 함
// 실제 user 데이터가 필요한 시점에 쿼리 실행

​
작동 방식:
Copy// 1. 프록시 객체만 생성 (쿼리 없음)
User userReference = userRepository.getReferenceById(1L);

// 2. 이 시점에는 id만 가지고 있음
Long userId = userReference.getId();  // 쿼리 발생 안 함

// 3. 실제 데이터가 필요한 시점에 쿼리 발생
String userName = userReference.getName();  // 이때 SELECT 쿼리 발생

​
즐겨찾기 생성시에는:
Copy@Transactional
public FavoriteResponseDto createFavorite(CreateFavoriteSDto dto) {
    // 1. 프록시 User 객체 생성 (쿼리 없음)
    User userReference = userRepository.getReferenceById(dto.getUserId());

    // 2. 레시피 조회 (첫 번째 쿼리)
    Recipe recipe = recipeRepository.findById(dto.getRecipeId())
            .orElseThrow(() -> new NotFoundException(ErrorCode.RECIPE_NOT_FOUND));

    // 3. 즐겨찾기 저장 (두 번째 쿼리)
    // 여기서는 User의 실제 데이터가 필요없고 ID만 필요하므로
    // 추가 쿼리 발생하지 않음
    Favorite favorite = Favorite.from(userReference, recipe);
    favoriteRepository.save(favorite);

    return FavoriteResponseDto.from(favorite);
}
  <!-- 내용 -->
</details>

<details>
  <summary></summary>
	다음과 같이 엘라스틱서치의 인덱스 설정을 직접 해주고 도큐먼트에 파일 위치를 

매핑시켜 주었음에도 키바나에서 인덱스를 확인해 보았을 때 인덱스가 자동으로 생성되지 않는 문제가 발생하였습니다.

![Image](https://github.com/user-attachments/assets/564e19ea-32a5-4cda-82d3-cf86b23d97c9)
![Image](https://github.com/user-attachments/assets/9f1a1960-ad8b-4b37-97a2-e1efc68a0c2a)

 

- 인덱스를 생성하기 위해서는 `elasticeSearchRepository`가 필요하다.
    
    엘라스틱서치의 인덱스 정보를 읽고, 이를 기반으로 인덱스를 생성해 주는 것은 `repository`의 책임인데, `elasticSearchRepository` 인터페이스를 구현하지 않았기 때문에 발생한 이슈였다.
    

## Spring Data ElasticSearch가 인덱스를 자동 생성하는 과정

- 애플리케이션이 실행될 때 엘라스틱서치는  `@document` 에너테이션이 달린 클래스를 읽는다.
- 레포지토리 인터페이스를 구현한 레포지토리가 등록되면서, 레포지토리 내부의 `IndexOperations`를 통해 해당 이름의 인덱스가 이미 존재하는지 확인하고, 존재하지 않으면 `@mapping`, `@Setting`의 path 경로에 있는 파일 내용을 기반으로 인덱스를 생성한다.

repository를 사용하지 않고, `operations`를 사용하여 기능을 구현하고 있었기 때문에, `elasticSearchRepository`의 구현체를 생성하지 않아 `indexOperation`을 통한 인덱스 생성이 자동으로 일어나지 않았기 때문입니다. repository를 사용하지 않으면 `indexOperations` 구현체를 통해 직접 인덱스를 생성해 줘야 한다.
  <!-- 내용 -->
</details>




 # 🩹추가 개선 예정
장바구니와 주문의 연관관계 변경으로 데이터 효율 개선

엘라스틱 서치 동의어 사전을 통해 사용성 개선

RabbitMQ NACK, DLQ 구현으로 이벤트 참여 실패 시 재시도 로직 적용

상품 리뷰 기능 추가

프로메테우스, 엘크, 그라파나와 같은 실시간 모니터링 도구 연동으로 시스템 성능 추적 강화 

# 팀원 소개
 
  




 
