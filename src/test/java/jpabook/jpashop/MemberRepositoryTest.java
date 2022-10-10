package jpabook.jpashop;


import jpabook.jpashop.entity.Member;
import jpabook.jpashop.repo.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    // transactional이 test에 붙어있으면 rollback 을 한다.
    //@Rollback(false)
    @Test
    @Transactional
    public void testMember() throws Exception {

    }
}