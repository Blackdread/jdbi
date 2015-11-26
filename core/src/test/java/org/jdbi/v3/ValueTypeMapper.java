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

package org.jdbi.v3;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.common.reflect.TypeToken;
import org.jdbi.v3.tweak.ResultColumnMapper;

public class ValueTypeMapper implements ResultColumnMapper<ValueType> {
    public ValueTypeMapper() {}

    @Override
    public ValueType mapColumn(ResultSet r, int columnNumber, StatementContext ctx) throws SQLException {
        return ValueType.valueOf(r.getString(columnNumber));
    }

    @Override
    public ValueType mapColumn(ResultSet r, String columnLabel, StatementContext ctx) throws SQLException {
        return ValueType.valueOf(r.getString(columnLabel));
    }

    public static class Factory implements ResultColumnMapperFactory {
        @Override
        public boolean accepts(TypeToken<?> type, StatementContext ctx) {
            return ValueType.class.isAssignableFrom(type.getRawType());
        }

        @SuppressWarnings("unchecked")
        @Override
        public <T> ResultColumnMapper<? extends T> columnMapperFor(TypeToken<T> type, StatementContext ctx) {
            return (ResultColumnMapper<? extends T>) new ValueTypeMapper();
        }
    }
}
