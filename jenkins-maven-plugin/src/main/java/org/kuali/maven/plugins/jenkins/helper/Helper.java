/**
 * Copyright 2011-2013 The Kuali Foundation
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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class Helper {
    public static final String EQUALS = "=";
    public static final String SPACE = " ";
    public static final String COMMA = ",";
    public static final String EMPTY_STRING = "";
    public static final String FS = System.getProperty("file.separator");

    public static final String getRelativePath(File dir, File file) {
        String dirPath = dir.getAbsolutePath();
        String filePath = file.getAbsolutePath();
        int pos = filePath.indexOf(dirPath);
        int len = dirPath.length();
        if (pos == -1) {
            return null;
        }
        return filePath.substring(pos + len + FS.length());
    }

    public static final String toCSV(int[] integers) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < integers.length; i++) {
            if (i != 0) {
                sb.append(COMMA);
            }
            sb.append(integers[i]);
        }
        return sb.toString();
    }

    public static final String toCSV(Integer[] integers) {
        return toCSV(toIntArray(integers));
    }

    public static final String toCSV(List<Integer> integers) {
        return toCSV(toIntegerArray(integers));
    }

    public static final int[] toIntArray(List<Integer> integers) {
        int[] ints = new int[integers.size()];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = integers.get(i);
        }
        return ints;
    }

    public static final int[] toIntArray(Integer[] integers) {
        return toIntArray(toIntegerList(integers));
    }

    public static final Integer[] toIntegerArray(List<Integer> integers) {
        return integers.toArray(new Integer[integers.size()]);
    }

    public static final List<Integer> toIntegerList(Integer[] integers) {
        return Arrays.asList(integers);
    }

    public static final List<Integer> toIntegerList(String csv) {
        int[] integers = toIntArray(csv);
        List<Integer> list = new ArrayList<Integer>();
        for (int integer : integers) {
            list.add(integer);
        }
        return list;
    }

    public static final int[] toIntArray(String csv) {
        String[] tokens = splitAndTrimCSV(csv);
        int[] integers = new int[tokens.length];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = new Integer(tokens[i]);
        }
        return integers;
    }

    /**
     * Return a List<String> (never null) where each String element is one "line" from the string passed in
     */
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

    /**
     * Return a List<String> (never null) where each String element is "key=value" from the map and the csv tokens from
     * the csv string. The csv is assumed to be "key=value" pairs separated by commas
     */
    public static final List<String> toKeyValueList(Map<String, String> map, String csv) {
        List<String> list = new ArrayList<String>();
        if (!isEmpty(map)) {
            list.addAll(toKeyValueList(map));
        }
        if (!StringUtils.isBlank(csv)) {
            String[] tokens = splitAndTrimCSV(csv);
            list.addAll(Arrays.asList(tokens));
        }
        return list;
    }

    /**
     * Create a map from an array of key=value strings
     */
    public static final Map<String, String> toMap(String[] keyValuePairs) {
        return toMap(Arrays.asList(keyValuePairs));
    }

    /**
     * Create a map from a list of key=value strings
     */
    public static final Map<String, String> toMap(List<String> keyValuePairs) {
        Map<String, String> map = new HashMap<String, String>();
        for (String keyValuePair : keyValuePairs) {
            String[] tokens = StringUtils.split(keyValuePair, EQUALS);
            String key = tokens[0];
            String value = tokens[1];
            map.put(key, value);
        }
        return map;
    }

    /**
     * Convert the map to a list where each string in the list is "key=value" from the map
     */
    public static final List<String> toKeyValueList(Map<String, String> map) {
        List<String> list = new ArrayList<String>();
        for (Map.Entry<String, String> pair : map.entrySet()) {
            String s = pair.getKey() + EQUALS + pair.getValue();
            list.add(s);
        }
        return list;
    }

    /**
     * Split the CSV string trimming as we go
     */
    public static final List<String> splitAndTrimCSVToList(String s) {
        return toList(splitAndTrim(s, COMMA));
    }

    /**
     * Split the CSV string trimming as we go
     */
    public static final String[] splitAndTrimCSV(String s) {
        return splitAndTrim(s, COMMA);
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

    /**
     * Convert List<String> into String[]
     */
    public static final List<String> toList(String... strings) {
        return Arrays.asList(strings);
    }

    /**
     * Create a one element list from the element passed in
     */
    public static final <T> List<T> toList(T element) {
        List<T> list = new ArrayList<T>();
        list.add(element);
        return list;
    }

    /**
     * Convert List<String> into String[]
     */
    public static final String[] toArray(List<String> list) {
        return list.toArray(new String[list.size()]);
    }

    /**
     * Return "" if the string passed in is null or all whitespace
     */
    public static final String toEmpty(String s) {
        if (StringUtils.isBlank(s)) {
            return EMPTY_STRING;
        } else {
            return s;
        }
    }

    /**
     * Combine the elements in the list into a string separated by spaces
     */
    public static final String toString(List<String> list) {
        return toString(toArray(list), SPACE);
    }

    /**
     * Return a string representing the strings from the list combined into a single string separated by "separator"
     */
    public static final String toString(List<String> list, String separator) {
        return toString(toArray(list), separator);
    }

    /**
     * Return a string representing the tokens combined into a single string separated by a space
     */
    public static final String toString(String[] tokens) {
        return toString(tokens, SPACE);
    }

    /**
     * Return a string representing the tokens combined into a single string separated by "separator"
     */
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

    /**
     * return true if target matches any of the values passed in
     */
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
    public static final boolean anyAreBlank(String... strings) {
        for (String string : strings) {
            if (StringUtils.isBlank(string)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return true if strings is null or length zero
     */
    public static final boolean isEmpty(String[] strings) {
        return strings == null || strings.length == 0;
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
