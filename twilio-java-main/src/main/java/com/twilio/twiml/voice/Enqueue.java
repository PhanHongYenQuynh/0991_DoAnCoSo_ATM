/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.twiml.voice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.twilio.converter.Promoter;
import com.twilio.http.HttpMethod;
import com.twilio.twiml.TwiML;
import com.twilio.twiml.TwiMLException;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * TwiML wrapper for {@code <Enqueue>}
 */
@JsonDeserialize(builder = Enqueue.Builder.class)
public class Enqueue extends TwiML {
    private final URI action;
    private final Integer maxQueueSize;
    private final HttpMethod method;
    private final URI waitUrl;
    private final HttpMethod waitUrlMethod;
    private final String workflowSid;
    private final String name;

    /**
     * For XML Serialization/Deserialization
     */
    private Enqueue() {
        this(new Builder());
    }

    /**
     * Create a new {@code <Enqueue>} element
     */
    private Enqueue(Builder b) {
        super("Enqueue", b);
        this.action = b.action;
        this.maxQueueSize = b.maxQueueSize;
        this.method = b.method;
        this.waitUrl = b.waitUrl;
        this.waitUrlMethod = b.waitUrlMethod;
        this.workflowSid = b.workflowSid;
        this.name = b.name;
    }

    /**
     * The body of the TwiML element
     *
     * @return Element body as a string if present else null
     */
    protected String getElementBody() {
        return this.getName() == null ? null : this.getName();
    }

    /**
     * Attributes to set on the generated XML element
     *
     * @return A Map of attribute keys to values
     */
    protected Map<String, String> getElementAttributes() {
        // Preserve order of attributes
        Map<String, String> attrs = new HashMap<>();

        if (this.getAction() != null) {
            attrs.put("action", this.getAction().toString());
        }
        if (this.getMaxQueueSize() != null) {
            attrs.put("maxQueueSize", this.getMaxQueueSize().toString());
        }
        if (this.getMethod() != null) {
            attrs.put("method", this.getMethod().toString());
        }
        if (this.getWaitUrl() != null) {
            attrs.put("waitUrl", this.getWaitUrl().toString());
        }
        if (this.getWaitUrlMethod() != null) {
            attrs.put("waitUrlMethod", this.getWaitUrlMethod().toString());
        }
        if (this.getWorkflowSid() != null) {
            attrs.put("workflowSid", this.getWorkflowSid());
        }

        return attrs;
    }

    /**
     * Action URL
     *
     * @return Action URL
     */
    public URI getAction() {
        return action;
    }

    /**
     * Maximum size of queue
     *
     * @return Maximum size of queue
     */
    public Integer getMaxQueueSize() {
        return maxQueueSize;
    }

    /**
     * Action URL method
     *
     * @return Action URL method
     */
    public HttpMethod getMethod() {
        return method;
    }

    /**
     * Wait URL
     *
     * @return Wait URL
     */
    public URI getWaitUrl() {
        return waitUrl;
    }

    /**
     * Wait URL method
     *
     * @return Wait URL method
     */
    public HttpMethod getWaitUrlMethod() {
        return waitUrlMethod;
    }

    /**
     * TaskRouter Workflow SID
     *
     * @return TaskRouter Workflow SID
     */
    public String getWorkflowSid() {
        return workflowSid;
    }

    /**
     * Friendly name
     *
     * @return Friendly name
     */
    public String getName() {
        return name;
    }

    /**
     * Create a new {@code <Enqueue>} element
     */
    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder extends TwiML.Builder<Builder> {
        /**
         * Create and return a {@code <Enqueue.Builder>} from an XML string
         */
        public static Builder fromXml(final String xml) throws TwiMLException {
            try {
                return OBJECT_MAPPER.readValue(xml, Builder.class);
            } catch (final JsonProcessingException jpe) {
                throw new TwiMLException(
                    "Failed to deserialize a Enqueue.Builder from the provided XML string: " + jpe.getMessage());
            } catch (final Exception e) {
                throw new TwiMLException("Unhandled exception: " + e.getMessage());
            }
        }

        private URI action;
        private Integer maxQueueSize;
        private HttpMethod method;
        private URI waitUrl;
        private HttpMethod waitUrlMethod;
        private String workflowSid;
        private String name;

        /**
         * Create a {@code <Enqueue>} with name
         */
        public Builder(String name) {
            this.name = name;
        }

        /**
         * Create a {@code <Enqueue>} with child elements
         */
        public Builder() {
        }

        /**
         * Action URL
         */
        @JacksonXmlProperty(isAttribute = true, localName = "action")
        public Builder action(URI action) {
            this.action = action;
            return this;
        }

        /**
         * Action URL
         */
        public Builder action(String action) {
            this.action = Promoter.uriFromString(action);
            return this;
        }

        /**
         * Maximum size of queue
         */
        @JacksonXmlProperty(isAttribute = true, localName = "maxQueueSize")
        public Builder maxQueueSize(Integer maxQueueSize) {
            this.maxQueueSize = maxQueueSize;
            return this;
        }

        /**
         * Action URL method
         */
        @JacksonXmlProperty(isAttribute = true, localName = "method")
        public Builder method(HttpMethod method) {
            this.method = method;
            return this;
        }

        /**
         * Wait URL
         */
        @JacksonXmlProperty(isAttribute = true, localName = "waitUrl")
        public Builder waitUrl(URI waitUrl) {
            this.waitUrl = waitUrl;
            return this;
        }

        /**
         * Wait URL
         */
        public Builder waitUrl(String waitUrl) {
            this.waitUrl = Promoter.uriFromString(waitUrl);
            return this;
        }

        /**
         * Wait URL method
         */
        @JacksonXmlProperty(isAttribute = true, localName = "waitUrlMethod")
        public Builder waitUrlMethod(HttpMethod waitUrlMethod) {
            this.waitUrlMethod = waitUrlMethod;
            return this;
        }

        /**
         * TaskRouter Workflow SID
         */
        @JacksonXmlProperty(isAttribute = true, localName = "workflowSid")
        public Builder workflowSid(String workflowSid) {
            this.workflowSid = workflowSid;
            return this;
        }

        /**
         * Friendly name
         */
        @JacksonXmlProperty(isAttribute = true, localName = "name")
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Add a child {@code <Task>} element
         */
        @JacksonXmlProperty(isAttribute = false, localName = "Task")
        public Builder task(Task task) {
            this.children.add(task);
            return this;
        }

        /**
         * Create and return resulting {@code <Enqueue>} element
         */
        public Enqueue build() {
            return new Enqueue(this);
        }
    }
}