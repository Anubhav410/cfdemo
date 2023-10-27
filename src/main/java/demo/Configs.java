package demo;

public class Configs {
    HeartBeatConfig heartBeatConfig;
    OrchestratorConfig orchestratorConfig;

    public HeartBeatConfig getHeartBeatConfig() {
        return heartBeatConfig;
    }

    public void setHeartBeatConfig(HeartBeatConfig heartBeatConfig) {
        this.heartBeatConfig = heartBeatConfig;
    }

    public OrchestratorConfig getOrchestratorConfig() {
        return orchestratorConfig;
    }

    public void setOrchestratorConfig(OrchestratorConfig orchestratorConfig) {
        this.orchestratorConfig = orchestratorConfig;
    }


    public static class HeartBeatConfig {

        private String endpoint;
        private AuthConfig authConfig;
        private long heartBeatFrequencyMils;

        // Add constructor, getters, and setters

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public AuthConfig getAuthConfig() {
            return authConfig;
        }

        public void setAuthConfig(AuthConfig authConfig) {
            this.authConfig = authConfig;
        }

        public long getHeartBeatFrequencyMils() {
            return heartBeatFrequencyMils;
        }

        public void setHeartBeatFrequencyMils(long heartBeatFrequencyMils) {
            this.heartBeatFrequencyMils = heartBeatFrequencyMils;
        }
    }

    public static class OrchestratorConfig {
        private String url;
        private AuthConfig authConfig;

        // Add constructor, getters, and setters

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public AuthConfig getAuthConfig() {
            return authConfig;
        }

        public void setAuthConfig(AuthConfig authConfig) {
            this.authConfig = authConfig;
        }
    }

    public static class SourceConfig {
        // Add necessary fields, constructor, getters, and setters
    }

    public static class AuthConfig {
        private String username;
        private String password;

        // Add constructor, getters, and setters

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
