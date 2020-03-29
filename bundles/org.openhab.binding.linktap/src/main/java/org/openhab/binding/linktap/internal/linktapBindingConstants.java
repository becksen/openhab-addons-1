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

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.core.thing.ThingTypeUID;

/**
 * The {@link linktapBindingConstants} class defines common constants, which are
 * used across the whole binding.
 *
 * @author becksen - Initial contribution
 */
@NonNullByDefault
public class linktapBindingConstants {

    private static final String BINDING_ID = "linktap";

    // List of all Thing Type UIDs
    // public static final ThingTypeUID THING_TYPE_SAMPLE = new ThingTypeUID(BINDING_ID, "sample");
    public static final ThingTypeUID THING_TYPE_BRIDGE = new ThingTypeUID(BINDING_ID, "gateway");

    // supported linktap device Taplinker TP-1B
    public static final ThingTypeUID THING_TYPE_TP1B = new ThingTypeUID(BINDING_ID, "TP1B");

    public static final Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = Stream.of(THING_TYPE_BRIDGE, THING_TYPE_TP1B)
            .collect(Collectors.toSet());

    // List of all Channel ids
    // public static final String CHANNEL_1 = "channel1";
    public static final String CHANNEL_STATUS = "status";

    // REST API URL
    public static final String REST_URL = "https://www.link-tap.com/api/";
}
