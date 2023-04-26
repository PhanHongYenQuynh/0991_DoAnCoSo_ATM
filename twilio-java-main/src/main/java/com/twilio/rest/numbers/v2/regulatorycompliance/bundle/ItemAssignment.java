/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Numbers
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.twilio.rest.numbers.v2.regulatorycompliance.bundle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.base.Resource;
import com.twilio.converter.DateConverter;
import com.twilio.exception.ApiConnectionException;

import com.twilio.exception.ApiException;

import lombok.ToString;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.ZonedDateTime;

import java.util.Objects;



@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class ItemAssignment extends Resource {
    private static final long serialVersionUID = 241370748977037L;

    public static ItemAssignmentCreator creator(final String pathBundleSid, final String objectSid){
        return new ItemAssignmentCreator(pathBundleSid, objectSid);
    }

    public static ItemAssignmentDeleter deleter(final String pathBundleSid, final String pathSid){
        return new ItemAssignmentDeleter(pathBundleSid, pathSid);
    }

    public static ItemAssignmentFetcher fetcher(final String pathBundleSid, final String pathSid){
        return new ItemAssignmentFetcher(pathBundleSid, pathSid);
    }

    public static ItemAssignmentReader reader(final String pathBundleSid){
        return new ItemAssignmentReader(pathBundleSid);
    }

    /**
    * Converts a JSON String into a ItemAssignment object using the provided ObjectMapper.
    *
    * @param json Raw JSON String
    * @param objectMapper Jackson ObjectMapper
    * @return ItemAssignment object represented by the provided JSON
    */
    public static ItemAssignment fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, ItemAssignment.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
    * Converts a JSON InputStream into a ItemAssignment object using the provided
    * ObjectMapper.
    *
    * @param json Raw JSON InputStream
    * @param objectMapper Jackson ObjectMapper
    * @return ItemAssignment object represented by the provided JSON
    */
    public static ItemAssignment fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, ItemAssignment.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String bundleSid;
    private final String accountSid;
    private final String objectSid;
    private final ZonedDateTime dateCreated;
    private final URI url;

    @JsonCreator
    private ItemAssignment(
        @JsonProperty("sid")
        final String sid,

        @JsonProperty("bundle_sid")
        final String bundleSid,

        @JsonProperty("account_sid")
        final String accountSid,

        @JsonProperty("object_sid")
        final String objectSid,

        @JsonProperty("date_created")
        final String dateCreated,

        @JsonProperty("url")
        final URI url
    ) {
        this.sid = sid;
        this.bundleSid = bundleSid;
        this.accountSid = accountSid;
        this.objectSid = objectSid;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.url = url;
    }

        public final String getSid() {
            return this.sid;
        }
        public final String getBundleSid() {
            return this.bundleSid;
        }
        public final String getAccountSid() {
            return this.accountSid;
        }
        public final String getObjectSid() {
            return this.objectSid;
        }
        public final ZonedDateTime getDateCreated() {
            return this.dateCreated;
        }
        public final URI getUrl() {
            return this.url;
        }

    @Override
    public boolean equals(final Object o) {
        if (this==o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ItemAssignment other = (ItemAssignment) o;

        return Objects.equals(sid, other.sid) &&  Objects.equals(bundleSid, other.bundleSid) &&  Objects.equals(accountSid, other.accountSid) &&  Objects.equals(objectSid, other.objectSid) &&  Objects.equals(dateCreated, other.dateCreated) &&  Objects.equals(url, other.url)  ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, bundleSid, accountSid, objectSid, dateCreated, url);
    }

}
