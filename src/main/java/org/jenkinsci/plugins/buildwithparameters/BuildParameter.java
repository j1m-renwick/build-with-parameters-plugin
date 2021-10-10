package org.jenkinsci.plugins.buildwithparameters;

import hudson.model.BooleanParameterValue;
import hudson.model.ParameterValue;
import hudson.model.PasswordParameterValue;
import hudson.model.StringParameterValue;
import hudson.model.TextParameterValue;
import java.util.List;

public class BuildParameter {

    static final String JOB_DEFAULT_PASSWORD_PLACEHOLDER = "job_default_password";
    private BuildParameterType type;
    private final String name;
    private final String description;
    private String value;
    private Object toBind;
    private List<String> choices = null;

    public BuildParameter(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public BuildParameter(String name, String description, Object toBind) {
        this.name = name;
        this.description = description;
        this.toBind = toBind;
    }

    public static boolean isDefaultPasswordPlaceholder(String candidate) {
        if (candidate == null) {
            return false;
        }
        return JOB_DEFAULT_PASSWORD_PLACEHOLDER.equals(candidate);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(ParameterValue parameterValue) {
        if (parameterValue instanceof StringParameterValue) {
            this.value = ((StringParameterValue) parameterValue).value;
        } else if (parameterValue instanceof TextParameterValue) {
            this.value = ((TextParameterValue) parameterValue).value;
        } else if (parameterValue instanceof BooleanParameterValue) {
            this.value = String.valueOf(((BooleanParameterValue) parameterValue).value);
        } else if (parameterValue instanceof PasswordParameterValue) {
            this.value = JOB_DEFAULT_PASSWORD_PLACEHOLDER;
        }
    }

    public BuildParameterType getType() {
        return type;
    }

    public void setType(BuildParameterType type) {
        this.type = type;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public Object getToBind() { return toBind; }

    public void setToBind(Object toBind) {this.toBind = toBind;}
}
