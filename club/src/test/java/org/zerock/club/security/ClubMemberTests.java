package org.zerock.club.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.club.entity.ClubMember;
import org.zerock.club.entity.ClubMemberRole;
import org.zerock.club.repository.ClubMemberRepository;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ClubMemberTests {

    @Autowired
    private ClubMemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("더미데이터 추가")
    public void insertDummies(){

        //1-80까지 USER
        //81-90까지 USER,MANAGER
        //91-100까지 USER,MANAGER,ADMIN

        IntStream.rangeClosed(1,100).forEach(i -> {
            ClubMember clubMember = ClubMember.builder()
                    .email("user"+i+"@zerock.org")
                    .name("사용자"+i)
                    .fromSocial(false)
                    .password(passwordEncoder.encode("1111"))
                    .build();

            //default role
            clubMember.addMemberRole(ClubMemberRole.USER);
            if (i > 80){
                clubMember.addMemberRole(ClubMemberRole.MANAGER);
            }
            if (i > 90){
                clubMember.addMemberRole(ClubMemberRole.USER);
            }
            repository.save(clubMember);
        });
    }

    @Test
    @DisplayName("회원 데이터 조회 테스트")
    public void testRead(){
        Optional<ClubMember> result = repository.findByEmail("user95@zerock.org", false);
        ClubMember clubMember = result.get();
        System.out.println(clubMember);
    }
}
