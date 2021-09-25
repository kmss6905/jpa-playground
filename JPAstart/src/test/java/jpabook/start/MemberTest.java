package jpabook.start;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

import javax.persistence.PostLoad;

class MemberTest {

    @Test
    public void simpleAdd(){
        int a = 1;
        int b = 2;
        int result = a + b;
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void getMember(){

    }
}