package com.clvs.seunggyu2.service;

import com.clvs.seunggyu2.domain.Member;
import com.clvs.seunggyu2.repository.MemberRepository;
import com.clvs.seunggyu2.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // test실행시 먼저 실행되며, test 후에 DB에 반영된 insert query를 롤백을 해준다.
class MemberServiceIntegrationTest {

    // 원래는 생성자로 인젝션했지만 테스트에서는 다른 곳에 사용될 일이 없으니 @Autowired로 바로 사용한다.
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    // 객체를 생성해서 넣는 것이 아니라 스프링 컨테이너한테 MemberService, MemberRepository를 받아온다.
//    @BeforeEach
//    public void beforeEach() {
//        memberRepository = new MemoryMemberRepository();
//        memberService = new MemberService(memberRepository);
//    }

    // 이 메소드가 필요했던 이유는 메모리 DB에 있는 데이터를 다음 테스트에 영향을 주지 않기 위해 clear하는 용도였다.
    // 지금은 -> @Transactional 로 대체한다.
//    @AfterEach
//    public void afterEach() {
//        memberRepository.clearStore();
//    }

    @Test
    public void 회원가입() throws Exception {
        //Given
        Member member = new Member();
        member.setName("spring");

        //When
        Long saveId = memberService.join(member);

        //Then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //Given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));//예외가 발생해야 한다.

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}