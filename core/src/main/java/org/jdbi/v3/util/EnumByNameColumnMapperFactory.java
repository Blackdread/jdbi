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
package org.jdbi.v3.util;

import com.google.common.reflect.TypeToken;
import org.jdbi.v3.ResultColumnMapperFactory;
import org.jdbi.v3.StatementContext;
import org.jdbi.v3.tweak.ResultColumnMapper;

/**
 * Produces enum column mappers, which map enums from varchar columns using {@link Enum#valueOf(Class, String)}.
 */
public class EnumByNameColumnMapperFactory implements ResultColumnMapperFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> ResultColumnMapper<? extends T> columnMapperFor(TypeToken<T> type, StatementContext ctx) {
        return (ResultColumnMapper<? extends T>) EnumColumnMapper.byName(
                (Class<? extends Enum>) type.getRawType().asSubclass(Enum.class));
    }

    @Override
    public boolean accepts(TypeToken<?> type, StatementContext ctx) {
        return type.getRawType().isEnum();
    }
}
