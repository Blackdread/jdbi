/*
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
package org.jdbi.v3.core.mapper;

import static org.jdbi.v3.core.qualifier.Qualifiers.getQualifiers;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.Set;

import org.jdbi.v3.core.config.ConfigRegistry;
import org.jdbi.v3.core.qualifier.QualifiedType;
import org.jdbi.v3.meta.Beta;

@FunctionalInterface
@Beta
interface QualifiedColumnMapperFactory {
    Optional<ColumnMapper<?>> build(QualifiedType type, ConfigRegistry config);

    static QualifiedColumnMapperFactory adapt(ColumnMapperFactory factory) {
        Set<Annotation> qualifiers = getQualifiers(factory.getClass());
        return (type, config) -> type.getQualifiers().equals(qualifiers)
            ? factory.build(type.getType(), config)
            : Optional.empty();
    }

    static QualifiedColumnMapperFactory of(QualifiedType type, ColumnMapper<?> mapper) {
        return (t, c) -> t.equals(type) ? Optional.of(mapper) : Optional.empty();
    }
}
