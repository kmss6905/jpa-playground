import jpabook.start.Member;
import jpabook.start.MemberType;
import jpabook.start.Team;
import org.junit.jupiter.api.Assertions;

import javax.persistence.*;
import java.util.List;
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

            mapping(em); // 비지니스 로직


            tx.commit(); // 트렌젝션 -커밋
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 데이터 리소스를 사용하기 때문에 반드시 실행하고 나서 반드시 닫아주어야 한다.
            // 그리고 웹 애플리케이션을 내릴때는 엔티티매니저 팩토리 까지 닫아야 한다.
        }

        emf.close();
    }

    private static void jpafirst(EntityManager em) {
        // 팀 저장
        Team team = new Team();
        team.setName("TeamA");
        em.persist(team);

        Member member1 = new Member();
        member1.setAge(10);
        member1.setUsername("min");
        member1.setPhoneNumber("101010");
        member1.setTeam(team);

        Member member2 = new Member();
        member2.setAge(20);
        member2.setUsername("shik");
        member2.setPhoneNumber("202020");
        member2.setTeam(team);

        em.persist(member1);
        em.persist(member2);

        em.flush();
        em.clear();

        Team findTeam = em.find(Team.class, team.getId());
        for(Member m: findTeam.getMembers()){
            System.out.println("m.getUsername() = " + m.getUsername());
        }
    }

    private static void mapping(EntityManager em){
        log.info("-----------------매핑테스트(양방향-연관관계의 주인)----------------");

        Team team = new Team();
        team.setName("TeamA");
        em.persist(team);

        Member member = new Member();
        member.setMemberType(MemberType.ADMIN);
        member.setUsername("minshik");
        member.setPhoneNumber("01094260450");
        member.setTeam(team);
        team.getMembers().add(member);
        em.persist(member);


        em.flush();
        em.clear();
    }

    private static void findAllMemberByJPQL(EntityManager em){
        TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
        List<Member> members = query.getResultList();
    }
}
