package jpabook.start;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.*;
import java.util.List;

class MemberTest {

    private EntityManager em;
    private EntityManagerFactory emf;

    @BeforeEach
    public void init(){
        emf = Persistence.createEntityManagerFactory("jpabook");
        em = emf.createEntityManager();
    }

    @AfterEach
    public void after(){
        em.close();
        emf.close();
    }

    @Test
    public void simpleAdd(){
        int a = 1;
        int b = 2;
        int result = a + b;
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void JPQLTest(){
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //given
        Member member1 = new Member();
        member1.setUsername("Kim");
        em.persist(member1);

        Member member2 = new Member();
        member2.setUsername("Park");
        em.persist(member2);

        em.flush();
        em.clear();


        //when
        TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
        List<Member> findMembers = query.getResultList();

        // then
        assertThat(findMembers).extracting("username")
                .contains("Kim", "Park");
        tx.commit();
    }
}