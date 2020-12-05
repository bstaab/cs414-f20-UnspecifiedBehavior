package com.mrpowers.requests;
import com.mrpowers.exceptions.IllegalMoveException;

public abstract class RequestData {

        protected final static int CURRENT_SUPPORTED_VERSION = 1;

        protected String requestType;
        protected Integer requestVersion;

        public String getRequestType() {
            return requestType;
        }

        public Integer getRequestVersion() {
            return requestVersion;
        }

        // Overrideable Methods
        public abstract void buildResponse() throws RequestException, IllegalMoveException;
}
