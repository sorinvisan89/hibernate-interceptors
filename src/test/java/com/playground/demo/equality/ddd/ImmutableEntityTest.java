package com.playground.demo.equality.ddd;

import com.mihalcea.equality.AbstractEqualityDDDCheckTest;
import com.playground.demo.equality.ddd.utils.ImmutableRootJpaEntity;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

public class ImmutableEntityTest extends AbstractEqualityDDDCheckTest<ImmutableEntityTest.Post> {

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

    @Entity(name = "Post")
    @Table(name = "post")
    @Getter
    @Setter
    public static class Post extends ImmutableRootJpaEntity<String> {

        @Id
        private String id;

        private String title;
    }
}