/*
 * Copyright (c) 2017, Wasiq Bhamla.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.wasiqb.coteafs.config;

import com.github.wasiqb.coteafs.config.util.BasePojo;

/**
 * @author wasiq.bhamla
 * @since 09-Jun-2017 6:40:36 PM
 */
public class ServiceSetting extends BasePojo {
    private int    apiPort;
    private String apiType;
    private String apiUrl;

    /**
     * @author Wasiq Bhamla
     * @since 05-Oct-2019
     */
    public ServiceSetting() {
        this.apiPort = 3000;
        this.apiType = "Rest";
        this.apiUrl = "https://localhost";
    }

    /**
     * @param apiPort the apiPort to set
     * @author wasiq.bhamla
     * @since 27-Jun-2017 7:15:29 PM
     */
    public void setApiPort(final int apiPort) {
        this.apiPort = apiPort;
    }

    /**
     * @param apiType the apiType to set
     * @author wasiq.bhamla
     * @since 27-Jun-2017 7:15:29 PM
     */
    public void setApiType(final String apiType) {
        this.apiType = apiType;
    }

    /**
     * @param apiUrl the apiUrl to set
     * @author wasiq.bhamla
     * @since 27-Jun-2017 7:15:29 PM
     */
    public void setApiUrl(final String apiUrl) {
        this.apiUrl = apiUrl;
    }

    /**
     * @return the apiPort
     * @author wasiq.bhamla
     * @since 27-Jun-2017 7:15:29 PM
     */
    int getApiPort() {
        return this.apiPort;
    }

    /**
     * @return the apiType
     * @author wasiq.bhamla
     * @since 27-Jun-2017 7:15:29 PM
     */
    String getApiType() {
        return this.apiType;
    }

    /**
     * @return the apiUrl
     * @author wasiq.bhamla
     * @since 27-Jun-2017 7:15:29 PM
     */
    String getApiUrl() {
        return this.apiUrl;
    }
}