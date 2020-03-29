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

package org.openhab.binding.linktap.internal.listener;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * Try to find linktap things.
 *
 * @author Timmy Becker - Initial contribution
 */
@NonNullByDefault
public interface linktapThingDataListener<T> {

    /**
     * An initial value for the data was received or the value is send again due to a refresh.
     *
     * @param data the data
     */
    void onNewData(T data);

    /**
     * Existing data was updated to a new value.
     *
     * @param oldData the previous value
     * @param data the current value
     */
    void onUpdatedData(T oldData, T data);

    /**
     * A Nest thing which previously had data is missing. E.g. it was removed from the account.
     *
     * @param deviceId identifies the Nest thing
     */
    void onMissingData(String deviceId);

}