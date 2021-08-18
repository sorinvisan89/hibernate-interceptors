package com.playground.demo.equality.normal.good;

import com.mihalcea.equality.AbstractEqualityCheckTest;
import com.mihalcea.identifier.Identifiable;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class CorrectBigDecimalEqualityTest extends AbstractEqualityCheckTest<CorrectBigDecimalEqualityTest.Post> {

    @Override
    protected Class<?>[] entities() {
        return new Class[]{
                CorrectBigDecimalEqualityTest.Post.class
        };
    }

    @Test
    public void testEquality() {
        CorrectBigDecimalEqualityTest.Post post = new CorrectBigDecimalEqualityTest.Post();
        post.setTitle("Any name here");
        post.setAmount(new BigDecimal("15.45"));

        assertEqualityConsistency(CorrectBigDecimalEqualityTest.Post.class, post);
    }

    @Entity(name = "Post7")
    @Table(name = "post7")
    @Getter
    @Setter
    public static class Post implements Identifiable<UUID> {

        @Id
        @GeneratedValue
        private UUID id;

        @Column
        private String title;

        @Column(precision = 16, scale = 4)
        private BigDecimal amount;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CorrectBigDecimalEqualityTest.Post)) return false;
            CorrectBigDecimalEqualityTest.Post post = (CorrectBigDecimalEqualityTest.Post) o;
            return Objects.equals(this.getId(), post.getId()) && Objects.equals(this.getTitle(), post.getTitle()) && compareMath(this.getAmount(), post.getAmount());
        }

        @Override
        public int hashCode() {
            return Objects.hash(12345678);
        }

        private boolean compareMath(BigDecimal lhs, BigDecimal rhs) {
            if (lhs == null && rhs == null) {
                return true;
            }
            if (lhs == null) {
                return false;
            }
            if (rhs == null){
                return false;
            }

            return lhs.compareTo(rhs) == 0;
        }
    }
}
