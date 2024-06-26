package org.sonatype.nexus.repository.chef.internal.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.sonatype.nexus.repository.chef.internal.ChefFormat;
import org.sonatype.nexus.repository.rest.api.model.CleanupPolicyAttributes;
import org.sonatype.nexus.repository.rest.api.model.ComponentAttributes;
import org.sonatype.nexus.repository.rest.api.model.HostedStorageAttributes;
import org.sonatype.nexus.repository.rest.api.model.SimpleApiHostedRepository;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(value = {"format", "type", "url"}, allowGetters = true)
public class ChefHostedApiRepository
        extends SimpleApiHostedRepository {

    @NotNull
    protected final ChefHostedRepositoryAttributes chef;

    @JsonCreator
    public ChefHostedApiRepository(
            @JsonProperty("name") final String name,
            @JsonProperty("url") final String url,
            @JsonProperty("online") final Boolean online,
            @JsonProperty("chef") final ChefHostedRepositoryAttributes chef,
            @JsonProperty("storage") final HostedStorageAttributes storage,
            @JsonProperty("cleanup") final CleanupPolicyAttributes cleanup,
            @JsonProperty("component") final ComponentAttributes component) {
        super(name, ChefFormat.NAME, url, online, storage, cleanup, component);
        this.chef = chef;
    }

    public ChefHostedRepositoryAttributes getChef() {
        return chef;
    }

}

