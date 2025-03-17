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

<img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=OpenJDK&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Redis-DC382D?style=flat-square&logo=redis&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Spring-6DB33F?style=flat-square&logo=spring&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/elasticsearch-%230377CC.svg?style=for-the-badge&logo=elasticsearch&logoColor=white">&nbsp;


//
<img src="https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=Docker&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=flat-square&logo=amazonec2&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Amazon AWS-232F3E?style=flat-square&logo=amazonaws&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/GitHub Actions-2088FF?style=flat-square&logo=githubactions&logoColor=white">&nbsp;
//

//협업툴
<img src="https://img.shields.io/badge/jira-%230A0FFF.svg?style=for-the-badge&logo=jira&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white"/>&nbsp;




# 💡KEY SUMMARY

## 🚀 성능 개선: 검색기능 엘라스틱서치 도입으로 약 99% 성능 최적화

### ✏️ **한줄 요약**
- 기주로 칵테일 조회, 키워드로 칵테일, 재료 조회 기능 구현
- 기존의 QueryDsl에서 ElasticSearch 도입으로 성능 개선 및 동의어 사전, 자동완성 등에 기능으로 사용자성 증가
  </br>
### **🔍 도입 배경**
- QueryDsl을 사용하여 검색기능 구현시 적은데이터에서는 사용자가 불편을 느끼지 못할정도의 속도가 측정되었지만,
- 대용량의 데이터 환경에서 데이터 양이 많아질수록 시간복잡도 증가 발생
  </br>

  

 # 📄트러블슈팅
 




 # 추가 개선 예정
장바구니와 주문의 연관관계 변경으로 데이터 효율 개선

엘라스틱 서치 동의어 사전을 통해 사용성 개선

RabbitMQ NACK, DLQ 구현으로 이벤트 참여 실패 시 재시도 로직 적용

상품 리뷰 기능 추가

프로메테우스, 엘크, 그라파나와 같은 실시간 모니터링 도구 연동으로 시스템 성능 추적 강화 

# 팀원 소개
 
  




 
