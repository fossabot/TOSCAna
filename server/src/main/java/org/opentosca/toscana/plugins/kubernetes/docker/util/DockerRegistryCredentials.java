package org.opentosca.toscana.plugins.kubernetes.docker.util;

import com.spotify.docker.client.messages.AuthConfig;

public class DockerRegistryCredentials {
    private final String registryURL;
    private final String username;
    private final String password;
    private final String repository;

    public DockerRegistryCredentials(String registryURL, String username, String password, String repository) {
        this.registryURL = registryURL;
        this.username = username;
        this.password = password;
        this.repository = repository;
    }

    public String getRegistryURL() {
        return registryURL;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRepository() {
        return repository;
    }

    public AuthConfig toAuthConfig() {
        return AuthConfig.builder()
            .username(username)
            .password(getPassword())
            .serverAddress(registryURL)
            .build();
    }
}
