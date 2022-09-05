package com.shopapotheke.utilities;

public class EnvironmentUtility {
    public String getConfigFileByEnvironment(Environment environment)
    {
        return switch (environment) {
            case QA -> "config-qa.properties";
            case PROD -> "config-prod.properties";
        };
    }
}
