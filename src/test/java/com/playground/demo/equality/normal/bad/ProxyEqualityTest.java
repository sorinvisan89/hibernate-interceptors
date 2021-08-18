package com.playground.demo.equality.normal.bad;

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


public class ProxyEqualityTest extends AbstractEqualityCheckTest<ProxyEqualityTest.Post> {

    @Override
    protected Class<?>[] entities() {
        return new Class[]{
            Post.class
        };
    }

    @Test
    public void testEquality() {
        Post post = new Post();
        post.setId(UUID.randomUUID().toString());
        post.setTitle("Any name here");

        assertEqualityConsistency(Post.class, post);
    }

    @Entity(name = "Post3")
    @Table(name = "post3")
    @Getter
    @Setter
    public static class Post implements Identifiable<String> {

        @Id
        private String id;

        private String title;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Post)) return false;
            Post post = (Post) o;
            // We need getters here since proxy members are null
            return Objects.equals(id, post.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
}
