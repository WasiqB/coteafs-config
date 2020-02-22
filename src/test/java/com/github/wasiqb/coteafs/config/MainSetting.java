/*
 * Copyright (c) 2020, Wasiq Bhamla.
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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wasiq Bhamla
 * @since 05-Jan-2020
 */
public class MainSetting {
    private ServiceSetting              service;
    private Map<String, ServiceSetting> services;

    public MainSetting() {
        this.services = new HashMap<>();
    }

    /**
     * @param service
     * @param services
     * @author Wasiq Bhamla
     * @since 05-Jan-2020
     */
    public MainSetting(final ServiceSetting service, final Map<String, ServiceSetting> services) {
        this.service = service;
        this.services = services;
    }

    /**
     * @return the service
     * @author Wasiq Bhamla
     * @since 05-Jan-2020
     */
    public ServiceSetting getService() {
        return this.service;
    }

    /**
     * @param name
     * @return service
     * @author Wasiq Bhamla
     * @since 05-Jan-2020
     */
    public ServiceSetting getService(final String name) {
        return this.services.get(name);
    }

    /**
     * @return the services
     * @author Wasiq Bhamla
     * @since 05-Jan-2020
     */
    public Map<String, ServiceSetting> getServices() {
        return this.services;
    }

    /**
     * @param service the service to set
     * @author Wasiq Bhamla
     * @since 05-Jan-2020
     */
    public void setService(final ServiceSetting service) {
        this.service = service;
    }

    /**
     * @param services the services to set
     * @author Wasiq Bhamla
     * @since 05-Jan-2020
     */
    public void setServices(final Map<String, ServiceSetting> services) {
        this.services = services;
    }
}