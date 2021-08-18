package com.playground.demo.equality.normal.bad;

import com.mihalcea.equality.AbstractEqualityCheckTest;
import com.mihalcea.identifier.Identifiable;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * This test uses a provided Id.
 * Will fail since we have BigDecimal member which when retrieved after persisted will take into account the DB scale and precision specifications.
 */
public class BigDecimalEqualityTest extends AbstractEqualityCheckTest<BigDecimalEqualityTest.Post> {

    @Override
    protected Class<?>[] entities() {
        return new Class[]{
                BigDecimalEqualityTest.Post.class
        };
    }

    @Test
    public void testEquality() {
        BigDecimalEqualityTest.Post post = new BigDecimalEqualityTest.Post();
        post.setId(UUID.randomUUID().toString());
        post.setTitle("Any name here");
        post.setAmount(new BigDecimal("15.34"));

        assertEqualityConsistency(BigDecimalEqualityTest.Post.class, post);
    }

    @Entity(name = "Post6")
    @Table(name = "post6")
    @Getter
    @Setter
    public static class Post implements Identifiable<String> {

        @Id
        private String id;

        @Column
        private String title;

        @Column(precision = 16, scale = 4)
        private BigDecimal amount;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof BigDecimalEqualityTest.Post)) return false;
            Post post = (Post) o;
            return Objects.equals(this.getId(), post.getId()) && Objects.equals(this.getTitle(), post.getTitle()) && Objects.equals(this.getAmount(), post.getAmount());
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, title, amount);
        }
    }
}