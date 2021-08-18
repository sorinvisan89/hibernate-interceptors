package com.playground.demo.equality.ddd.bad;

import com.mihalcea.equality.AbstractEqualityDDDCheckTest;
import com.playground.demo.utils.ddd.ImmutableFiscalRootJpaEntity;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * This test uses a provided Id.
 * Will fail since Hibernate Proxies and byte code manipulation cannot work with no-args private constructors.
 * Even if we make the no-args constructor protected or public, it will still fail since in the RootJpaEntity class we don't used instanceOf for Proxy tests.
 */
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

        private Post() {
        }

        public Post(UUID id){
            super(id);
        }

        @Column
        private String title;

    }
}