/*
 * Copyright 2012 the original author or authors.
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

package org.gradle.api.internal.externalresource;

import org.gradle.api.Nullable;

import java.io.Serializable;
import java.util.Date;

public class DefaultExternalResourceMetaData implements ExternalResourceMetaData, Serializable {

    private final String location;
    private final Date lastModified;
    private final long contentLength;
    private final String etag;

    public DefaultExternalResourceMetaData(String location, long lastModified, long contentLength, @Nullable String etag) {
        this(location, lastModified > 0 ? new Date(lastModified) : null, contentLength, etag);
    }
    
    public DefaultExternalResourceMetaData(String location, @Nullable Date lastModified, long contentLength, @Nullable String etag) {
        this.location = location;
        this.lastModified = lastModified;
        this.contentLength = contentLength;
        this.etag = etag;
    }

    public String getLocation() {
        return location;
    }

    @Nullable
    public Date getLastModified() {
        return lastModified;
    }

    public long getContentLength() {
        return contentLength;
    }

    @Nullable
    public String getEtag() {
        return etag;
    }
}
