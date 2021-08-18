package com.playground.demo.equality.normal.good;

import com.mihalcea.equality.AbstractEqualityCheckTest;
import com.mihalcea.identifier.Identifiable;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

public class CorrectAutogeneratedIdTest extends AbstractEqualityCheckTest<CorrectAutogeneratedIdTest.Post> {

    @Override
    protected Class<?>[] entities() {
        return new Class[]{
                CorrectAutogeneratedIdTest.Post.class
        };
    }

    @Test
    public void testEquality() {
        CorrectAutogeneratedIdTest.Post post = new CorrectAutogeneratedIdTest.Post();
        post.setTitle("Any name here");


        assertEqualityConsistency(CorrectAutogeneratedIdTest.Post.class, post);
    }

    @Entity(name = "Post5")
    @Table(name = "post5")
    @Getter
    @Setter
    public static class Post implements Identifiable<UUID> {

        @Id
        @GeneratedValue
        private UUID id;

        private String title;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CorrectAutogeneratedIdTest.Post)) return false;
            CorrectAutogeneratedIdTest.Post post = (CorrectAutogeneratedIdTest.Post) o;
            return Objects.equals(this.getId(), post.getId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(12345678);
        }
    }
}