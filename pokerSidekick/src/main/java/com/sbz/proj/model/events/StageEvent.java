package com.sbz.proj.model.events;

import com.sbz.proj.model.StageName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("30m")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StageEvent {

    private StageName stageName;
}
