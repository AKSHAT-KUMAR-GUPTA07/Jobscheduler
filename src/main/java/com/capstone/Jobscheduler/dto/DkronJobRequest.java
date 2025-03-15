package com.capstone.Jobscheduler.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DkronJobRequest {
    private String name;
    private String schedule;
    private String executor;

    @JsonProperty("executor_config")
    private Map<String, Object> executorConfig;

    private String owner;
    private boolean disabled;


    //allargconstructor
    public DkronJobRequest(String name, String schedule ,String executor, Map<String, Object> executorConfig,  String owner , boolean disabled){
        this.name=name;
        this.schedule=schedule;
        this.executor=executor;
        this.executorConfig=executorConfig;
        this.owner=owner;
        this.disabled=disabled;
    }
}
