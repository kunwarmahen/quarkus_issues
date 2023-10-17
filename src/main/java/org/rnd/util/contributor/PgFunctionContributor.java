package org.rnd.util.contributor;

import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.boot.model.FunctionContributor;
import org.hibernate.type.BasicType;
import org.hibernate.type.StandardBasicTypes;

public class PgFunctionContributor implements FunctionContributor {
    @Override
    public void contributeFunctions(FunctionContributions functionContributions) {
        BasicType<Double> resolveType = functionContributions.getTypeConfiguration().getBasicTypeRegistry()
                .resolve(StandardBasicTypes.DOUBLE);
        // functionContributions.getFunctionRegistry().registerPattern("ects",
        // "to_tsvector(?1) @@ websearch_to_tsquery(?2)", resolveType);
        // functionContributions.getFunctionRegistry().registerPattern("ecfts", "?1 @@
        // websearch_to_tsquery(?2)",
        // resolveType);
        functionContributions.getFunctionRegistry().registerNamed("earth_distance", resolveType);
    }
}
