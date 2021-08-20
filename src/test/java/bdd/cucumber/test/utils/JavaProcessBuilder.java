package bdd.cucumber.test.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaProcessBuilder {
    Logger logger = LoggerFactory.getLogger(JavaProcessBuilder.class);
    private JavaProcessBuilder() {
    }

    public static synchronized Process launchApplication() throws IOException, InterruptedException {

        String appJarHomeDir = "build" + File.separator + "libs";
        String jar = "inventory-services-0.0.1-SNAPSHOT.jar";
        String jarPath= appJarHomeDir + File.separator + jar;
        File log = new File("applicationLog");
        String springProfile = "-Dspring.profiles.active=" + "local";
        List<String> listArguments = new ArrayList<>(Arrays.asList(Constants.JAVA_BIN,springProfile,"-jar", jarPath));
        ProcessBuilder builder = new ProcessBuilder(listArguments.toArray(new String[0]));
        redirectApplicationLog(builder,log);
        Process process = builder.start();
        Thread.sleep(15000);
        return process;
    }

    private static synchronized void redirectApplicationLog(ProcessBuilder builder, File log) {
        builder.redirectOutput(ProcessBuilder.Redirect.to(log));
        builder.redirectError(ProcessBuilder.Redirect.to(log));
    }


}
