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

import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.binding.BaseBridgeHandler;
import org.eclipse.smarthome.core.types.Command;
import org.openhab.binding.linktap.internal.linktapConfiguration;
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

    private @Nullable linktapConfiguration config;

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

}