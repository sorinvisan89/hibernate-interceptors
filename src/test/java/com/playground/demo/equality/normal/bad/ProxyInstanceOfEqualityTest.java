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
import java.util.Objects;
import java.util.UUID;



public class ProxyInstanceOfEqualityTest extends AbstractEqualityCheckTest<ProxyInstanceOfEqualityTest.Post> {

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

    @Entity(name = "Post2")
    @Table(name = "post2")
    @Getter
    @Setter
    public static class Post implements Identifiable<UUID> {

        @Id
        private UUID id;

        @Column
        private String title;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            // Will not work for proxies
            if (o == null || getClass() != o.getClass()) return false;
            Post post = (Post) o;
            return Objects.equals(id, post.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
}
