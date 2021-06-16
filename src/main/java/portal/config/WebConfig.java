package portal.config;


import java.util.HashMap;

public class WebConfig {

    private final String DEFAULT_ENV = "testEnv";
    private final String HOST = "smtp1.host";

    private final String env = System.getProperty("env", DEFAULT_ENV);

    public String getCallistoUrl() {
        return System.getProperty("remote", "http://callisto.loc");
    }


    public String getUrlAdmin() {
        return "http://admin." + env;
    }

    public String getUrlLk() {
        return "http://lk." + env;
    }

    public HashMap<String, String> getEmailConfig() {
        final HashMap<String, String> emailData = new HashMap<>();
        if (DEFAULT_ENV.equals(env)) {
            emailData.put("username", "test@portal.cloud");
            emailData.put("password", "8ert634h!767f");
            emailData.put("from", "robot-test@portal.cloud");
            emailData.put("host", HOST);
        } else {
            emailData.put("username", "selenium-test@portal.cloud");
            emailData.put("password", "q94_3$3874w7fh5");
            emailData.put("from", "robot-" + env.split("\\.")[0] + "-selenium@portal.cloud");
            emailData.put("host", HOST);
        }
        return emailData;
    }

    public String getApiEnv() {
        return "http://api." + env;
    }

    public String getApiKitchen() {
        return ("http://api.kitchenenv.cloud");
    }
}
