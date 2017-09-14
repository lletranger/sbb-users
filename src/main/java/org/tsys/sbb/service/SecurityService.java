package org.tsys.sbb.service;

public interface SecurityService {
    /**
     * Autologin local user in spring security after registration
     *
     * @param username String
     * @param password String
     */
    void autoLogin(String username, String password);

    /**
     * Autologin social user in spring security after login via Facebook
     *
     * @param username String
     */
    void autoLoginSocialUser(String username);
}
