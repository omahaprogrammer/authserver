/*
 * Copyright 2016 Jonathan Paz <jonathan@pazdev.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pazdev.authserver.oauth2;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.common.collect.ImmutableMap;
import com.pazdev.jwt.JWTClaims;
import java.time.Instant;
import java.util.Map;

/**
 *
 * @author Jonathan Paz <jonathan@pazdev.com>
 */
@JsonDeserialize(builder = OAuth2Token.Builder.class)
public class OAuth2Token extends JWTClaims {
    @JsonProperty("scope")
    private final String scope;
    @JsonProperty("client_id")
    private final String clientId;
    @JsonProperty("username")
    private final String username;
    @JsonProperty("token_type")
    private final String tokenType;

    public OAuth2Token(String scope,
            String clientId,
            String username,
            String tokenType,
            String issuer,
            String subject,
            String audience,
            Instant expirationTime,
            Instant notBefore,
            Instant issuedAt,
            String jwtId,
            Map<String, Object> claims) {
        super(issuer, subject, audience, expirationTime, notBefore, issuedAt, jwtId, claims);
        this.scope = scope;
        this.clientId = clientId;
        this.username = username;
        this.tokenType = tokenType;
    }

    public String getScope() {
        return scope;
    }

    public String getClientId() {
        return clientId;
    }

    public String getUsername() {
        return username;
    }

    public String getTokenType() {
        return tokenType;
    }

    @JsonPOJOBuilder
    public static class Builder extends JWTClaims.Builder {
        protected String scope;
        protected String clientId;
        protected String username;
        protected String tokenType;

        @JsonProperty("scope")
        public Builder withScope(String scope) {
            this.scope = scope;
            return this;
        }

        @JsonProperty("client_id")
        public Builder withClientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        @JsonProperty("username")
        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        @JsonProperty("token_type")
        public Builder withTokenType(String tokenType) {
            this.tokenType = tokenType;
            return this;
        }

        @Override
        public OAuth2Token build() {
            return new OAuth2Token(scope,
                    clientId,
                    username,
                    tokenType,
                    issuer,
                    subject,
                    audience,
                    expirationTime,
                    notBefore,
                    issuedAt,
                    jwtId,
                    ImmutableMap.copyOf(claims));
        }
    }
}
