/*
 * Sonatype Nexus (TM) Open Source Version
 * Copyright (c) 2018-present Sonatype, Inc.
 * All rights reserved. Includes the third-party code listed at http://links.sonatype.com/products/nexus/oss/attributions.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License Version 1.0,
 * which accompanies this distribution and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Sonatype Nexus (TM) Professional Version is available from Sonatype, Inc. "Sonatype" and "Sonatype Nexus" are trademarks
 * of Sonatype, Inc. Apache Maven is a trademark of the Apache Software Foundation. M2eclipse is a trademark of the
 * Eclipse Foundation. All other trademarks are the property of their respective owners.
 */
package org.sonatype.nexus.repository.chef.internal;

import static com.google.common.base.Preconditions.checkNotNull;

public class ChefHostedMetadataInvalidationEvent {

    private final String repositoryName;

    private final String cookbookName;

    private final String cookbookVersion;

    public ChefHostedMetadataInvalidationEvent(final String repositoryName,
                                               final String cookbookName,
                                               final String cookbookVersion) {
        this.repositoryName = checkNotNull(repositoryName);
        this.cookbookName = checkNotNull(cookbookName);
        this.cookbookVersion = checkNotNull(cookbookVersion);
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public String getCookbookName() {
        return cookbookName;
    }

    public String getCookbookVersion() {
        return cookbookVersion;
    }

    @Override
    public String toString() {
        return "ChefHostedMetadataInvalidationEvent{" +
                "repositoryName='" + repositoryName + '\'' +
                ", cookbookName='" + cookbookName + '\'' +
                ", cookbookVersion='" + cookbookVersion + '\'' +
                '}';
    }
}
