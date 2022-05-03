package com.playground.demo.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CustomEvent extends ApplicationEvent {

    private Integer id;
    private String name;

    public CustomEvent(final Object source, final Integer id, final String name) {
        super(source);
        this.id = id;
        this.name = name;
    }
}
