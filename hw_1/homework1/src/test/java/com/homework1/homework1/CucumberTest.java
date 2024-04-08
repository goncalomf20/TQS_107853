package com.homework1.homework1;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com/homework1/homework1")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.homework1.homework1")
public class CucumberTest {

}