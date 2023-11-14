package com.travelbee.app.security.oauth2;

import com.travelbee.app.util.Provider;

import java.util.Map;

public class OAuth2UserDetailFactory {

    public static OAuth2Userdetails getOAuth2Userdetails(String registrantionId, Map<String, Object> attributes){
        if(registrantionId.equals(Provider.google.name())){
            return new OAuth2GoogleUser(attributes);
        }else if (registrantionId.equals(Provider.facebook.name())){
            return new OAuth2FacebookUser(attributes);
        }else if(registrantionId.equals(Provider.github.name())){
            return new OAuth2GithubUser(attributes);
        }
        return null;
    }
}
