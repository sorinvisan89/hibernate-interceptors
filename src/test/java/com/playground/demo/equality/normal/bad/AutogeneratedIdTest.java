package com.playground.demo.equality.normal.bad;

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

/**
 * This test uses an autogenerated Id.
 * Will fail since hashcode needs a fixed value when the Id is autogenerated
 */
public class AutogeneratedIdTest extends AbstractEqualityCheckTest<AutogeneratedIdTest.Post> {

    @Override
    protected Class<?>[] entities() {
        return new Class[]{
                AutogeneratedIdTest.Post.class
        };
    }

    @Test
    public void testEquality() {
        AutogeneratedIdTest.Post post = new AutogeneratedIdTest.Post();
        post.setTitle("Any name here");


        assertEqualityConsistency(AutogeneratedIdTest.Post.class, post);
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
            if (!(o instanceof AutogeneratedIdTest.Post)) return false;
            AutogeneratedIdTest.Post post = (AutogeneratedIdTest.Post) o;
            return Objects.equals(this.getId(), post.getId());
        }

        @Override
        public int hashCode() {
            // Should be fixed value for autogenerated ids
            return Objects.hash(this.getId());
        }
    }
}