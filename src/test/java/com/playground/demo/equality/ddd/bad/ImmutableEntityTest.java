package com.playground.demo.equality.ddd.bad;

import com.mihalcea.equality.AbstractEqualityDDDCheckTest;
import com.playground.demo.equality.ddd.utils.ImmutableFiscalRootJpaEntity;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import javax.persistence.Column;
import javax.persistence.Entity;
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
        Post post = new Post(UUID.randomUUID());
        post.setTitle("Any name here");

        assertEqualityConsistency(Post.class, post);
    }

    @Entity(name = "Post4")
    @Table(name = "post4")
    @Getter
    @Setter
    public static class Post extends ImmutableFiscalRootJpaEntity {

        public Post() {
        }

        public Post(UUID id){
            super(id);
        }

        @Column
        private String title;

    }
}