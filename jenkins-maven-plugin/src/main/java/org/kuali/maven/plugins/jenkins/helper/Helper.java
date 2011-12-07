/**
 * Copyright 2004-2011 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.maven.plugins.jenkins.helper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class Helper {
    public static final String EQUALS = "=";

    /**
     * Return a List<String> (never null) where each String element is "key=value" from the map and the csv tokens from
     * the csv string. The csv is assumed to be "key=value" pairs separated by commas
     */
    public static final List<String> toKeyValuePairs(Map<String, String> map, String csv) {
        List<String> paramArgs = new ArrayList<String>();
        if (!isEmpty(map)) {
            paramArgs.addAll(toList(map));
        }
        if (!StringUtils.isBlank(csv)) {
            String[] tokens = Helper.splitAndTrimCSV(csv);
            paramArgs.addAll(Arrays.asList(tokens));
        }
        return paramArgs;
    }

    public static final List<String> getLines(String s) {
        if (StringUtils.isBlank(s)) {
            return new ArrayList<String>();
        }
        InputStream in = new ByteArrayInputStream(s.getBytes());
        try {
            return IOUtils.readLines(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final List<String> toList(Map<String, String> params) {
        return toList(params, EQUALS);
    }

    /**
     * Convert the map to a list
     */
    public static final List<String> toList(Map<String, String> params, String separator) {
        List<String> args = new ArrayList<String>();
        for (Map.Entry<String, String> pair : params.entrySet()) {
            String arg = pair.getKey() + separator + pair.getValue();
            args.add(arg);
        }
        return args;
    }

    /**
     * Split the string trimming as we go
     */
    public static final String[] splitAndTrimCSV(String s) {
        return splitAndTrim(s, ",");
    }

    /**
     * Split the string trimming as we go
     */
    public static final String[] splitAndTrim(String s, String separator) {
        String[] tokens = StringUtils.split(s, separator);
        for (String token : tokens) {
            token = token.trim();
        }
        return tokens;
    }

    public static final <T> List<T> toList(T element) {
        List<T> list = new ArrayList<T>();
        list.add(element);
        return list;
    }

    public static final String[] toArray(List<String> list) {
        return list.toArray(new String[list.size()]);
    }

    public static final String toEmpty(String s) {
        if (StringUtils.isBlank(s)) {
            return "";
        } else {
            return s;
        }
    }

    public static final String toString(List<String> list) {
        return toString(list.toArray(new String[list.size()]), " ");
    }

    public static final String toString(List<String> list, String separator) {
        return toString(list.toArray(new String[list.size()]), separator);
    }

    public static final String toString(String[] tokens) {
        return toString(tokens, " ");
    }

    public static final String toString(String[] tokens, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tokens.length; i++) {
            if (i != 0) {
                sb.append(separator);
            }
            sb.append(tokens[i]);
        }
        return sb.toString();
    }

    public static final boolean isMatch(int target, int... values) {
        for (int value : values) {
            if (value == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return true if map is null or size zero
     */
    public static final boolean isEmpty(Map<?, ?> map) {
        return map == null || map.size() == 0;
    }

    /**
     * Return true if c is null or size zero
     */
    public static final boolean isEmpty(Collection<?> c) {
        return c == null || c.size() == 0;
    }

    /**
     * Return true if any of the args passed in are blank
     */
    public static final boolean anyAreBlank(String... args) {
        for (String arg : args) {
            if (StringUtils.isBlank(arg)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return true if args is null or length zero
     */
    public static final boolean isEmpty(String... args) {
        return args == null || args.length == 0;
    }

    /**
     * Add args to list
     */
    public static final void addToList(List<String> list, String... args) {
        if (isEmpty(args)) {
            return;
        }
        for (String arg : args) {
            list.add(arg);
        }
    }

}
