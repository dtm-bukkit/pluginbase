/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package com.dumptruckman.minecraft.pluginbase.properties;

import com.dumptruckman.minecraft.pluginbase.locale.Message;
import com.dumptruckman.minecraft.pluginbase.util.Null;

import java.util.ArrayList;

public class NullPropertyBuilder extends PropertyBuilder<Null> {

    NullPropertyBuilder(String name) {
        super(Null.class, name, true);
    }

    public NullPropertyBuilder comment(String comment) {
        return (NullPropertyBuilder) super.comment(comment);
    }

    public NullPropertyBuilder description(Message message) {
        return (NullPropertyBuilder) super.description(message);
    }

    public NullPropertyBuilder deprecated() {
        return (NullPropertyBuilder) super.deprecated();
    }

    public NullPropertyBuilder alias(String alias) {
        return (NullPropertyBuilder) super.alias(alias);
    }

    public NullProperty build() {
        return new DefaultNullProperty(path, comments, new ArrayList<String>(aliases), serializer, validator, description, deprecated, defaultIfMissing);
    }
}
