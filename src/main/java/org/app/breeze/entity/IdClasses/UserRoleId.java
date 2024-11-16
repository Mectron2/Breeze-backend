package org.app.breeze.entity.IdClasses;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class UserRoleId implements Serializable {
    private Long userId;
    private Long roleId;
}
