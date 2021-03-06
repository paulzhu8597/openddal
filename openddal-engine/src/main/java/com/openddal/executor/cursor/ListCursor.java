/*
 * Copyright 2014-2016 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the “License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an “AS IS” BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.openddal.executor.cursor;

import java.util.ArrayList;

import com.openddal.message.DbException;
import com.openddal.result.Row;
import com.openddal.result.SearchRow;

/**
 * @author jorgie.li
 *
 */
public class ListCursor implements Cursor {

    private Row current;
    private final ArrayList<Row> rows;
    private int index;

    ListCursor(ArrayList<Row> rows) {
        this.rows = rows;
    }

    @Override
    public Row get() {
        return current;
    }

    @Override
    public SearchRow getSearchRow() {
        return current;
    }

    @Override
    public boolean next() {
        current = index >= rows.size() ? null : rows.get(index++);
        return current != null;
    }

    @Override
    public boolean previous() {
        throw DbException.throwInternalError();
    }

}
