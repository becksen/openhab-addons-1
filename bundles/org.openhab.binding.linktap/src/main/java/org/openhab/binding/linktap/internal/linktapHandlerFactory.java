/**
 * Copyright (c) 2010-2020 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.linktap.internal;

import static org.openhab.binding.linktap.internal.linktapBindingConstants.THING_TYPE_BRIDGE;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.ThingUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.eclipse.smarthome.core.thing.binding.ThingHandlerFactory;
import org.openhab.binding.linktap.handler.linktapBridgeHandler;
import org.openhab.binding.linktap.internal.discovery.linktapDiscoveryService;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Component;

/**
 * The {@link linktapHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Timmy Becker - Initial contribution
 */
@NonNullByDefault
@Component(configurationPid = "binding.linktap", service = ThingHandlerFactory.class)
public class linktapHandlerFactory extends BaseThingHandlerFactory {

    private static final Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = Collections.singleton(THING_TYPE_BRIDGE); // or
                                                                                                                  // tp1b
                                                                                                                  // to
                                                                                                                  // be
                                                                                                                  // checked
                                                                                                                  // again

    // Allow autodiscovery?

    private Map<ThingUID, @Nullable ServiceRegistration<?>> discoveryService = new HashMap<>();

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    /**
     * Creates a handler for the particular thing. Also creates the discovery service
     * when the bridge is created.
     */

    @Override
    protected @Nullable ThingHandler createHandler(Thing thing) {
        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        // or thing type_tp1b?
        if (THING_TYPE_BRIDGE.equals(thingTypeUID)) {
            // myImplementation
            linktapBridgeHandler handler = new linktapBridgeHandler((Bridge) thing);
            linktapDiscoveryService service = new linktapDiscoveryService(handler);

            return new linktapHandler(thing);
        }

        // return null;
        // TB Test
        return new linktapHandler(thing);
    }
}
