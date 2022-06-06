package com.sbz.proj.model.events;


import com.sbz.proj.model.Action;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.io.Serializable;
import java.util.Date;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Expires("30m")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActionInsertEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date executionTime;
    private Action action;

}
