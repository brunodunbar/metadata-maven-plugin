package com.brunodunbar.metadata;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.ResolutionScope;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Mojo(name = "metadata", defaultPhase = LifecyclePhase.PROCESS_CLASSES,
        requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME,
        configurator = "include-project-dependencies")
public class MetadataMojo extends AbstractMojo
{
    public void execute() throws MojoExecutionException
    {
        try {
            Object test = Class.forName("com.brunodunbar.Test").newInstance();

            Method doSomething = test.getClass().getMethod("doSomething");
            String value = (String) doSomething.invoke(test);

            getLog().info(value);

        } catch (Exception e) {
            getLog().error("Unable to load class");
        }

    }
}