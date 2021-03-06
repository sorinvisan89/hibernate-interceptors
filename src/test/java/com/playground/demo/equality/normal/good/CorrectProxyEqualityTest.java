package com.playground.demo.equality.normal.good;

import com.mihalcea.equality.AbstractEqualityCheckTest;
import com.mihalcea.identifier.Identifiable;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;


public class CorrectProxyEqualityTest extends AbstractEqualityCheckTest<CorrectProxyEqualityTest.Post> {

    @Override
    protected Class<?>[] entities() {
        return new Class[]{
            Post.class
        };
    }

    @Test
    public void testEquality() {
        Post post = new Post();
        post.setId(UUID.randomUUID());
        post.setTitle("Any name here");


        assertEqualityConsistency(Post.class, post);
    }

    @Entity(name = "Post1")
    @Table(name = "post1")
    @Getter
    @Setter
    public static class Post implements Identifiable<UUID> {

        @Id
        private UUID id;

        private String title;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Post)) return false;
            Post post = (Post) o;
            return Objects.equals(this.getId(), post.getId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
}
