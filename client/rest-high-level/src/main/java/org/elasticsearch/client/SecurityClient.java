/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0 and the Server Side Public License, v 1; you may not use this file except
 * in compliance with, at your election, the Elastic License 2.0 or the Server
 * Side Public License, v 1.
 */

package org.elasticsearch.client;

import org.elasticsearch.client.security.ClearRealmCacheRequest;
import org.elasticsearch.client.security.ClearRealmCacheResponse;
import org.elasticsearch.client.security.DelegatePkiAuthenticationRequest;
import org.elasticsearch.client.security.DelegatePkiAuthenticationResponse;

import java.io.IOException;

import static java.util.Collections.emptySet;

/**
 * A wrapper for the {@link RestHighLevelClient} that provides methods for accessing the Security APIs.
 * <p>
 * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/security-api.html">Security APIs on elastic.co</a>
 *
 * @deprecated The High Level Rest Client is deprecated in favor of the
 * <a href="https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/current/introduction.html">
 * Elasticsearch Java API Client</a>
 */
@Deprecated(since = "7.16.0", forRemoval = true)
@SuppressWarnings("removal")
public final class SecurityClient {

    private final RestHighLevelClient restHighLevelClient;

    SecurityClient(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    /**
     * Clears the cache in one or more realms.
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/security-api-clear-cache.html">
     * the docs</a> for more.
     *
     * @param request the request with the realm names and usernames to clear the cache for
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @return the response from the clear realm cache call
     * @throws IOException in case there is a problem sending the request or parsing back the response
     */
    public ClearRealmCacheResponse clearRealmCache(ClearRealmCacheRequest request, RequestOptions options) throws IOException {
        return restHighLevelClient.performRequestAndParseEntity(
            request,
            SecurityRequestConverters::clearRealmCache,
            options,
            ClearRealmCacheResponse::fromXContent,
            emptySet()
        );
    }

    /**
     * Get an Elasticsearch access token from an {@code X509Certificate} chain. The certificate chain is that of the client from a mutually
     * authenticated TLS session, and it is validated by the PKI realms with {@code delegation.enabled} toggled to {@code true}.<br>
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/security-api-delegate-pki-authentication.html"> the
     * docs</a> for more details.
     *
     * @param request the request containing the certificate chain
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @return the response from the delegate-pki-authentication API key call
     * @throws IOException in case there is a problem sending the request or parsing back the response
     */
    public DelegatePkiAuthenticationResponse delegatePkiAuthentication(DelegatePkiAuthenticationRequest request, RequestOptions options)
        throws IOException {
        return restHighLevelClient.performRequestAndParseEntity(
            request,
            SecurityRequestConverters::delegatePkiAuthentication,
            options,
            DelegatePkiAuthenticationResponse::fromXContent,
            emptySet()
        );
    }

}
