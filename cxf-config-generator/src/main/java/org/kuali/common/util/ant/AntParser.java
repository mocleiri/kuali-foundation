/**
 * Copyright 2004-2012 The Kuali Foundation
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
package org.kuali.common.util.ant;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class AntParser {
    Counter depth = new Counter();

    public static void main(String[] args) {
        new AntParser().execute(args);
    }

    public void execute(String[] args) {
        try {
            Map<String, Target> targets = getTargets();
            List<Target> list = getExecutionOrder("dist-local", targets);
            for (Target t : list) {
                String name = t.getName();
                if (name.contains("init-")) {
                    continue;
                }
                System.out.println(space(t.getDepth()) + t.getName());
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    protected String space(int count) {
        return StringUtils.repeat("-", count);
    }

    protected List<Target> getExecutionOrder(String targetName, Map<String, Target> map) {
        Target target = map.get(targetName);
        List<Target> targets = new ArrayList<Target>();
        List<Target> depends = target.getDepends();
        targets.add(clone(target, depth.getValue()));
        depth.increment();
        for (Target depend : depends) {
            targets.addAll(getExecutionOrder(depend.getName(), map));
        }
        depth.decrement();
        return targets;
    }

    protected Target clone(Target t, int depth) {
        Target clone = new Target();
        clone.setName(t.getName());
        clone.setContent(t.getContent());
        clone.setDepends(t.getDepends());
        clone.setDependsNames(t.getDependsNames());
        clone.setDepth(depth);
        clone.setName(t.getName());
        return clone;
    }

    protected Map<String, Target> getTargets() throws IOException {
        File file = new File("/Users/jeffcaddel/ws/ole/build.xml");
        Map<String, Target> targets = getTargets(file);
        for (String name : targets.keySet()) {
            Target t = targets.get(name);
            t.setDepends(getDependsTargets(t, targets));
        }
        return targets;
    }

    protected List<Target> getDependsTargets(Target t, Map<String, Target> map) {
        List<String> dependsNames = t.getDependsNames();
        List<Target> dts = new ArrayList<Target>();
        for (String name : dependsNames) {
            Target dt = map.get(name);
            dts.add(dt);
        }
        return dts;
    }

    protected Map<String, Target> getTargets(File file) throws IOException {
        String startToken = "<target";
        String s = FileUtils.readFileToString(file);
        int pos = s.indexOf(startToken);
        Map<String, Target> targets = new HashMap<String, Target>();
        while (pos != -1) {
            String content = startToken + StringUtils.substringBetween(s, startToken, "</target>");
            String name = StringUtils.substringBetween(content, "name=\"", "\"");
            Target t = new Target();
            t.setName(name);
            t.setContent(content);
            t.setDependsNames(getDependsList(content));
            if (targets.get(name) != null) {
                throw new IllegalStateException("Duplicate targets in ant file");
            } else {
                targets.put(name, t);
            }
            s = s.substring(pos + startToken.length());
            pos = s.indexOf(startToken);
        }
        return targets;
    }

    protected List<String> splitAndTrimCSVToList(String csv) {
        if (StringUtils.isBlank(csv)) {
            return new ArrayList<String>();
        }
        String[] tokens = csv.split(",");
        List<String> list = new ArrayList<String>();
        for (String token : tokens) {
            list.add(token.trim());
        }
        return list;
    }

    protected List<String> getDependsList(String target) {
        String depends = StringUtils.substringBetween(target, "depends=\"", "\"");
        return splitAndTrimCSVToList(depends);
    }

    protected Target getTarget(String s) {
        String name = StringUtils.substringBetween(s, "name=\"", "\"");
        String depends = StringUtils.substringBetween(s, "depends=\"", "\"");
        Target target = new Target();
        target.setName(name);
        return target;
    }

}
