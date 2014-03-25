package org.kuali.common.util.metainf.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.metainf.spring.MetaInfDataLocation;
import org.kuali.common.util.metainf.spring.MetaInfDataType;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * Sort first by the data passed into the configurable comparator, then sort lexicographically by directory structure,
 * then filename
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
public class ConfigurablePathComparator extends PathComparator {

    private List<String> qualifierOrder = Collections.emptyList();
    private List<MetaInfDataLocation> locationOrder = Collections.emptyList();
    private List<MetaInfDataType> typeOrder = Collections.emptyList();

    @Override
    protected int compare(int index, String[] tokens1, String[] tokens2) {
        String string1 = tokens1[index];
        String string2 = tokens2[index];

        if (!CollectionUtils.isEmpty(qualifierOrder)) {
            if (qualifierOrder.contains(string1) && qualifierOrder.contains(string2)) {
                int compare = Ordering.explicit(qualifierOrder).compare(string1, string2);

                // If the comparison comes back as anything but zero, we are done
                if (compare != 0) {
                    return compare;
                }
            }
        }

        if (!CollectionUtils.isEmpty(locationOrder)) {
            if (isLocation(string1) && isLocation(string2)) {
                MetaInfDataLocation location1 = MetaInfDataLocation.valueOf(StringUtils.upperCase(string1));
                MetaInfDataLocation location2 = MetaInfDataLocation.valueOf(StringUtils.upperCase(string2));

                int compare = Ordering.explicit(locationOrder).compare(location1, location2);

                // If the comparison comes back as anything but zero, we are done
                if (compare != 0) {
                    return compare;
                }
            }
        }

        if (!CollectionUtils.isEmpty(typeOrder)) {
            if (isType(string1) && isType(string2)) {
                MetaInfDataType type1 = MetaInfDataType.valueOf(StringUtils.upperCase(string1));
                MetaInfDataType type2 = MetaInfDataType.valueOf(StringUtils.upperCase(string2));

                int compare = Ordering.explicit(typeOrder).compare(type1, type2);

                // If the comparison comes back as anything but zero, we are done
                if (compare != 0) {
                    return compare;
                }
            }
        }

        return super.compare(index, tokens1, tokens2);
    }

    protected boolean isLocation(String token) {
        return EnumUtils.isValidEnum(MetaInfDataLocation.class, StringUtils.upperCase(token));
    }

    protected boolean isType(String token) {
        return EnumUtils.isValidEnum(MetaInfDataType.class, StringUtils.upperCase(token));
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

        public ConfigurablePathComparator build() {
            this.qualifierOrder = ImmutableList.copyOf(qualifierOrder);
            this.locationOrder = ImmutableList.copyOf(locationOrder);
            this.typeOrder = ImmutableList.copyOf(typeOrder);
            return new ConfigurablePathComparator(this);
        }

    }

    private ConfigurablePathComparator(Builder builder) {
        this.qualifierOrder = builder.qualifierOrder;
        this.locationOrder = builder.locationOrder;
        this.typeOrder = builder.typeOrder;
    }

}
