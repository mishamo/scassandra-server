/*
 * Copyright (C) 2014 Christopher Batey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.scassandra.http.client;

import com.google.common.collect.ImmutableMap;
import org.scassandra.server.priming.ErrorConstants;

import java.util.Map;

public class WriteTimeoutConfig extends Config {

    private final WriteTypePrime writeType;
    private final int receivedAcknowledgements;
    private final int requiredAcknowledgements;
    private final Consistency consistencyLevel;

    public WriteTimeoutConfig(WriteTypePrime writeType, int receivedAcknowledgements, int requiredAcknowledgements) {
        this(writeType, receivedAcknowledgements, requiredAcknowledgements, null);
    }

    public WriteTimeoutConfig(WriteTypePrime writeType, int receivedAcknowledgements, int requiredAcknowledgements, Consistency consistencyLevel) {
        this.writeType = writeType;
        this.receivedAcknowledgements = receivedAcknowledgements;
        this.requiredAcknowledgements = requiredAcknowledgements;
        this.consistencyLevel = consistencyLevel;
    }

    @Override
    Map<String, ?> getProperties() {
        ImmutableMap.Builder<String,String> builder = ImmutableMap.<String,String>builder()
                .put(ErrorConstants.ReceivedResponse(), String.valueOf(this.receivedAcknowledgements))
                .put(ErrorConstants.RequiredResponse(), String.valueOf(this.requiredAcknowledgements))
                .put(ErrorConstants.WriteType(), String.valueOf(writeType.toString()));

        if(consistencyLevel != null) {
            builder.put(ErrorConstants.ConsistencyLevel(), String.valueOf(consistencyLevel.toString()));
        }

        return builder.build();
    }
}
