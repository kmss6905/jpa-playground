package jpabook.start;

import javax.persistence.*;

@Entity
@Table(name = "MEMBER")
public class Member {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id; // 아이디

    @Column(name = "USERNAME")
    private String username; // 이름

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber; // 휴대폰번호

    private Integer age; // 나이

    public Member() {
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }

    // Getter, Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
