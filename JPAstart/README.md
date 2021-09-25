### JPA

### 프로젝트 구성
* Maven
* Hibernate 5.5 ( https://hibernate.org/orm/releases/5.5/ )
* H2 database
* java 14

JPA 구현체 중 가장 성숙되고 널리 많이 사용되는 라이브러리인 Hibernate 를 사용하기로 결정  
자바버전은 현재 14로 맞춰놓은 상태. 추후 8, 11로 개선예정


### 주제
1. JPA 란 무엇인가
2. 엔티티의 생명주기

---
### 주의
* 엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체서 공유
* 엔티티 매니저는 쓰레드간에 공유하면 안된다(사용하고 버려야 한다)
* JPA의 모든 데이터 변경은 트랜젝션 안에서 실행

데이터 커넥션당 엔티티 매니저는 엮인다. 그렇기 때문에 한번 사용하고 버려야 한다.
반드시 데이터의 변경/저장 등의 로직은 트랜젝션 안에서 처리해야한다.


### 데이터 베이스 스키마 자동 생성
* DDL을 애플리케이션 실행 시점에 자동 생성
* 테이블중심 -> 객체중심
* 데이터베이스 방언을 활용해서 데이터베이스에 맞는
적절한 DDL 생성
* 이렇게 **생성된 DDL은 개발 장비에서만 사용**
* 생성된 DDL은 운영서버에서는 사용하지 않거나,
적절히 다듬은 후 사용
  
### 데이터베이스 스키마 자동 생성하기
* hibernate.hbm2ddl.auto
    * create: 기존 테이블 삭제 후 다시 생성(DROP + CREATE)
    * create-drop: create와 같으나 종료시점에 테이블 DROP
    * update: 변경분만 반영 (운영 DB에서는 사용 X)
    * validate: 엔티티와 테이블이 정상 매핑되었는지 확인
    * none: 아무것도 하지 않음
    
### 데이터베이스 스키마 자동 생성 주의...
* 운영장비에서는 절대 create, create-drop, update 사용
하면 안된다.
* 개발 초기 단계는 create 또는 update
* 테스트 서버는 update 또는 validate
* 스테이징와 운영 서버는 validate 또는 none


###@Column
* 가장 많이 사용됨
* name: 필드와 매핑할 테이블의 컬럼 이름
* insertable, updatable: 읽기전용
* nullable: null 허용여부 결정, DDL 생성시 사용
* unique: 유니크 제약 조건, DDL 생성시 사용
* columnDefinition, legth, precision, scale(DDL)

###@Temporal
* 날짜 타입 매핑

```java

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Temporal(TemporalType.DATE)
private Date date; //날짜

@Temporal(TemporalType.TIME)
private Date time; //시간

@Temporal(TemporalType.TIMESTAMP)
private Date timestamp; // 날짜와 시간
```
###@Enumerated
* 열거형 매핑
* EnumType.ORDINAL: 순서를 저장(기본값)
* EnumType.STRING: 열거형 이름을 그대로 저장,
가급적 이것을 사용 !!!!

```java

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Enumerated(EnumType.STRING)
private RoleType roleType;

```

###@Transient
* 이 필드는 매핑하지 않는다.
* 애플리케이션에서 DB에 저장하지 않는 필드
