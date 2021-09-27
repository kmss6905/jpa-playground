## JPA는 다양한 쿼리 방법을 지원
* JPQL
* JPA Criteria
* QueryDSL
* 네이티브 SQL
* JDBC API 직접사용, MyBatis, SpringJdbcTemplate 함께 사용

---
* 가장 단순한 조회 방법
    * EntityManager.find()
    * 객체 그래프 탐색
  
* 나이가 18살 이상인 회원을 모두 검색하고 싶다면?


### JPQL

* JPA를 사용하면 엔티티 객체를 중심으로 개발
* 문제는 검색 쿼리
* 검색을 할 때도 테이블이 아닌 엔티티 객체를 대상으로 검색
* 모든 DB 데이터를 객체로 변환해서 검색하는 것은 불가능
* JPA 는 SQL을 추상화한 JPQL 이라는 객체 지향 쿼리 언어 제공.
* SQL을 추상화했기 때문에 특정 DB에 종속적이지 않음
 

### Named 쿼리 - 어노테이션



