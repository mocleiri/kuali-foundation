package org.kuali.common.util.metainf.model;

import com.google.common.collect.ImmutableList;
import org.kuali.common.util.metainf.spring.MetaInfDataLocation;
import org.kuali.common.util.metainf.spring.MetaInfDataType;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * Sort first by the data passed into the configurable comparator, then sort lexicographically by directory structure,
 * then filename of the locations contained in each {@code MetaInfResource}
 * </p>
 *
 * For example:
 *
 * <pre>
 *   2 - server/demo/a/foo2.txt         1 - client/bootstrap/a/foo1.txt
 *   3 - server/demo/a/b/foo.txt        2 - server/demo/a/foo2.txt
 *   1 - client/bootstrap/a/foo1.txt    3 - server/demo/a/b/foo.txt
 * </pre>
 *
 */
public class MetaInfResourceConfigurablePathComparator implements Comparator<MetaInfResource> {

    private ConfigurablePathComparator comparator = ConfigurablePathComparator.builder().build();

    @Override
    public int compare(MetaInfResource one, MetaInfResource two) {
        return comparator.compare(one.getLocation(), two.getLocation());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        // Optional
        private List<String> qualifierOrder = Collections.emptyList();
        private List<MetaInfDataLocation> locationOrder = Collections.emptyList();
        private List<MetaInfDataType> typeOrder = Collections.emptyList();

        public Builder qualifierOrder(List<String> qualifierOrder) {
            this.qualifierOrder = qualifierOrder;
            return this;
        }

        public Builder locationOrder(List<MetaInfDataLocation> locationOrder) {
            this.locationOrder = locationOrder;
            return this;
        }

        public Builder typeOrder(List<MetaInfDataType> typeOrder) {
            this.typeOrder = typeOrder;
            return this;
        }

        public MetaInfResourceConfigurablePathComparator build() {
            this.qualifierOrder = ImmutableList.copyOf(qualifierOrder);
            this.locationOrder = ImmutableList.copyOf(locationOrder);
            this.typeOrder = ImmutableList.copyOf(typeOrder);
            return new MetaInfResourceConfigurablePathComparator(this);
        }

    }

    private MetaInfResourceConfigurablePathComparator(Builder builder) {
        ConfigurablePathComparator.Builder comparatorBuilder = ConfigurablePathComparator.builder();
        comparatorBuilder = comparatorBuilder.qualifierOrder(builder.qualifierOrder);
        comparatorBuilder = comparatorBuilder.locationOrder(builder.locationOrder);
        comparatorBuilder = comparatorBuilder.typeOrder(builder.typeOrder);
        comparator = comparatorBuilder.build();
    }

}
