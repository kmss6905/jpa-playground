import jpabook.start.Member;
import jpabook.start.MemberType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.logging.Logger;

public class JpaMain {

    private static final Logger log = Logger.getLogger(JpaMain.class.getName());

    public static void main(String[] args) {

        // [엔티티 메니저 팩토리] - 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

        // [엔티티 매니저] - 생성
        EntityManager em = emf.createEntityManager();

        // [트랜젝션] 흭득
        EntityTransaction tx = em.getTransaction();
        log.info("new transaction has been created");

        try {
            log.info("start transaction");
            tx.begin(); // 트렌젝션 - 시작
            // 비지니스 로직
            Member member = new Member();
//            member.setId(1L);
            member.setUsername("minshik");
            member.setAge(21);
            member.setPhoneNumber("01094260450");
            member.setMemberType(MemberType.USER);
            em.persist(member);
            tx.commit(); // 트렌젝션 -커밋
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 데이터 리소스를 사용하기 때문에 반드시 실행하고 나서 반드시 닫아주어야 한다.
            // 그리고 웹 애플리케이션을 내릴때는 엔티티매니저 팩토리 까지 닫아야 한다.
        }

        emf.close();
    }

    private static void login(EntityManager em){

    }
}
