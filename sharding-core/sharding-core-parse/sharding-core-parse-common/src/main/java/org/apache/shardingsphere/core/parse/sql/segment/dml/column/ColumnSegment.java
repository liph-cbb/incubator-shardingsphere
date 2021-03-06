/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.core.parse.sql.segment.dml.column;

import com.google.common.base.Optional;
import lombok.Getter;
import org.apache.shardingsphere.core.parse.old.lexer.token.Symbol;
import org.apache.shardingsphere.core.parse.sql.segment.OwnerAvailable;
import org.apache.shardingsphere.core.parse.sql.segment.SQLSegment;
import org.apache.shardingsphere.core.parse.sql.segment.common.TableSegment;
import org.apache.shardingsphere.core.parse.sql.segment.dml.predicate.value.PredicateRightValue;
import org.apache.shardingsphere.core.parse.util.SQLUtil;

/**
 * Column segment.
 *
 * @author duhongjun
 * @author zhangliang
 * @author panjuan
 */
@Getter
public class ColumnSegment implements SQLSegment, PredicateRightValue, OwnerAvailable {
    
    private final int startIndex;
    
    private final String name;
    
    private TableSegment owner;
    
    public ColumnSegment(final int startIndex, final String name) {
        this.startIndex = startIndex;
        this.name = SQLUtil.getExactlyValue(name);
    }
    
    /**
     * Get qualified name.
     *
     * @return qualified name
     */
    public final String getQualifiedName() {
        return null == owner ? name : owner.getName() + Symbol.DOT.getLiterals() + name;
    }
    
    @Override
    public final Optional<TableSegment> getOwner() {
        return Optional.fromNullable(owner);
    }
    
    @Override
    public final void setOwner(final SQLSegment owner) {
        this.owner = (TableSegment) owner;
    }
}
