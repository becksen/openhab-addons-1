/**
 * /**
 * * Copyright (c) 2010-2019 Contributors to the openHAB project
 * *
 * * See the NOTICE file(s) distributed with this work for additional
 * * information.
 * *
 * * This program and the accompanying materials are made available under the
 * * terms of the Eclipse Public License 2.0 which is available at
 * * http://www.eclipse.org/legal/epl-2.0
 * *
 * * SPDX-License-Identifier: EPL-2.0
 * *
 * * @author Timmy Becker - Initial contribution
 */

package org.openhab.binding.linktap.handler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.ThingStatusDetail;
import org.eclipse.smarthome.core.thing.binding.BaseBridgeHandler;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.eclipse.smarthome.core.types.Command;
import org.openhab.binding.linktap.internal.data.linktapIdentifiable;
import org.openhab.binding.linktap.internal.listener.linktapThingDataListener;
import org.openhab.binding.linktap.internal.update.LinktapCompositeUpdateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link LinkTapHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Timmy Becker - Initial contribution
 */

public class linktapBridgeHandler extends BaseBridgeHandler {

    private final Logger logger = LoggerFactory.getLogger(linktapBridgeHandler.class);

    // private @Nullable linktapConfiguration config;

    private final LinktapCompositeUpdateHandler updateHandler = new LinktapCompositeUpdateHandler(
            this::getPresentThingsLinktapIds);

    public linktapBridgeHandler(Bridge gateway) {
        super(gateway);

    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        // do nothing
    }

    @Override
    public void initialize() {
        scheduler.execute(this::configure);
    }

    private void configure() {
        logger.debug("Update status to ONLINE.");

        updateStatus(ThingStatus.ONLINE);
    }

    public <T> boolean addThingDataListener(Class<T> dataClass, linktapThingDataListener<T> listener) {
        return updateHandler.addListener(dataClass, listener);
    }

    public <T> boolean addThingDataListener(Class<T> dataClass, String nestId, linktapThingDataListener<T> listener) {
        return updateHandler.addListener(dataClass, nestId, listener);
    }

    public <T> boolean removeThingDataListener(Class<T> dataClass, linktapThingDataListener<T> listener) {
        return updateHandler.removeListener(dataClass, listener);
    }

    public <T> boolean removeThingDataListener(Class<T> dataClass, String nestId,
            linktapThingDataListener<T> listener) {
        return updateHandler.removeListener(dataClass, nestId, listener);
    }

    public @Nullable <T> T getLastUpdate(Class<T> dataClass, String linktapId) {
        return updateHandler.getLastUpdate(dataClass, linktapId);
    }

    public <T> List<T> getLastUpdates(Class<T> dataClass) {
        return updateHandler.getLastUpdates(dataClass);
    }

    private Set<String> getPresentThingsLinktapIds() {
        Set<String> linktapIds = new HashSet<>();
        for (Thing thing : getThing().getThings()) {
            ThingHandler handler = thing.getHandler();
            if (handler != null && thing.getStatusInfo().getStatusDetail() != ThingStatusDetail.GONE) {
                linktapIds.add(((linktapIdentifiable) handler).getId());
            }
        }
        return linktapIds;
    }
}